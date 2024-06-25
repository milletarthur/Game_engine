package View.draw;

import javax.swing.*;

import Automates.Automate;
import Automates.AutomatonLoader;
import Labyrinthe.*;
import controller.*;
import controller.listener.JSONWindow;
import controller.listener.Key_Listener;

import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * première fenêtre du jeu pour remplir le fichier de config
 */
public class WindowInitGame extends JFrame {

	public static int T_case = 60;

	private static final long serialVersionUID = 1L;

	JTextField name1, name2;
	JRadioButton labyrinthe, arene;
	JSlider s_HAUTEUR, s_LARGEUR, s_VISIBILITY;
	Image init;

	public WindowInitGame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bienvenue dans ce moteur de Jeux");
		this.setSize(450, 400);

		this.init = DrawTerrain.drawEntity("resources/graphisme/init.png");

		JPanel choice = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(init, 0, 0, this.getWidth(), this.getHeight(), this);
			}

		};

		choice.setLayout(null);
		choice.setBackground(Color.BLACK);
		JLabel jeu = new JLabel("Sélection du jeu : ");
		JLabel name = new JLabel("Noms des joueurs : ");
		labyrinthe = new JRadioButton("Labyrinthe");
		arene = new JRadioButton("Arène");
		ButtonGroup group = new ButtonGroup();
		group.add(labyrinthe);
		group.add(arene);
		name1 = new JTextField();
		name2 = new JTextField();

		jeu.setForeground(Color.WHITE);
		jeu.setBounds(20, 155, 200, 20);
		name.setForeground(Color.WHITE);
		name.setBounds(280, 155, 200, 20);
		labyrinthe.setBounds(20, 180, 110, 30);
		arene.setBounds(20, 215, 110, 30);
		labyrinthe.setForeground(Color.WHITE);
		labyrinthe.setOpaque(false);
		arene.setForeground(Color.WHITE);
		arene.setOpaque(false);
		name1.setBounds(280, 180, 135, 20);
		name2.setBounds(280, 215, 135, 20);

		choice.add(jeu);
		choice.add(name);
		choice.add(labyrinthe);
		choice.add(arene);
		choice.add(name1);
		choice.add(name2);

		JButton jouer = new JButton("Jouer");
		jouer.addActionListener(new JSONWindow(this));
		jouer.setBounds(171, 250, 110, 30);
		choice.add(jouer);

		this.add(choice, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void initGame() throws IOException {

		// initialisation de la grille

		Field terrain = new Field(JSONWindow.hauteur, JSONWindow.largeur, JSONWindow.densite, JSONWindow.d_pickable,
				JSONWindow.d_mine, JSONWindow.d_pomme, JSONWindow.d_potion, JSONWindow.d_pioche, JSONWindow.d_bombe,
				JSONWindow.d_cassable, JSONWindow.d_invisible, JSONWindow.d_normal, JSONWindow.nb_obstacles,
				JSONWindow.nb_zombie, JSONWindow.nb_squelette, JSONWindow.seed);

		KeyPressed kp = new KeyPressed();
		TickListener tl = new TickListener(terrain);
		
//		ajout d'un automate
		AutomatonLoader al = new AutomatonLoader(terrain, kp, tl);
		LinkedList<Automate> l_aut = null;
		if (JSONWindow.jeu.equals("Labyrinthe"))
			 l_aut = al.loadAutomata("resources/automata/jeu1.gal");
		else if (JSONWindow.jeu.equals("Arène"))
			l_aut = al.loadAutomata("resources/automata/jeu2.gal");
		tl.setAllAutoList(l_aut);

		Joueur j1 = new Joueur(2, 0, 1);
		Joueur j2 = new Joueur(3, 0, 2);

		if (JSONWindow.jeu.equals("Labyrinthe")) {
			terrain.add(j1, 2, 0);
			terrain.add(j2, 3, 0);
		} else if (JSONWindow.jeu.equals("Arène")) {
			j1.set_ligne(3);
			j2.set_colonne(terrain.get_colonne() - 1);
			j2.set_ligne(terrain.get_ligne() - 3);
			terrain.add(j1, 3, 0);
			terrain.add(j2, terrain.get_ligne() - 3, terrain.get_colonne() - 1);
			Interrupteur int1 = new Interrupteur(2, 0, new LinkedList<Entity>());
			Interrupteur int2 = new Interrupteur(terrain.get_ligne() - 4, terrain.get_colonne() - 1, new LinkedList<Entity>());
			tl.add(int1);
			tl.add(int2);
			int1.setTeam(1);
			int2.setTeam(2);
			terrain.add(int1, 2, 0);
			terrain.add(int2, terrain.get_ligne() - 4, terrain.get_colonne() - 1);
		}
		// terrain.printGame();

		// Initialisation de la fenêtre
		DrawWindow w = new DrawWindow(terrain.get_colonne(), terrain.get_ligne(), terrain, T_case,
				JSONWindow.visibility, j1, j2);

		Viewport v1 = new Viewport(w.get_dt1(), T_case, JSONWindow.visibility);
		Viewport v2 = new Viewport(w.get_dt2(), T_case, JSONWindow.visibility);

		End end = new End(terrain, j1, j2, w);
		TicTac tt = new TicTac(tl, j1, j2, v1, v2, end);
		tt.add_window(w);

		w.init_Window(v1, v2, w.get_invent(), tt);
		v1.centrerViewport(j1);
		v2.centrerViewport(j2);

		// création du lien entre Entity et Automate
		LinkedList<Entity> liste_mur_cassable = terrain.get_cassable();
		LinkedList<Entity> liste_mur_invisible = terrain.get_invisible();
		LinkedList<Entity> liste_mur_normal = terrain.get_normal();
		LinkedList<Entity> liste_interrupteur = terrain.get_interrupteur();
		LinkedList<Entity> liste_porte = terrain.get_porte();
		LinkedList<Entity> liste_sable = terrain.get_sable();
		LinkedList<Entity> liste_squelette = terrain.get_squelette();
		LinkedList<Entity> liste_zombie = terrain.get_zombie();
		LinkedList<Entity> liste_pomme = terrain.get_pommes();
		LinkedList<Entity> liste_potion = terrain.get_potions();
		LinkedList<Entity> liste_pioche = terrain.get_pioche();
		LinkedList<Entity> liste_bombe = new LinkedList<Entity>();
		if (JSONWindow.name1.equals("labyrinthe"))
			liste_bombe = terrain.get_bombes();
		LinkedList<Entity> liste_teleporteur = terrain.get_teleporteur();
		LinkedList<Entity> liste_mine = terrain.get_mine();

		Iterator<Automate> iter = l_aut.iterator();
		while (iter.hasNext()) {
			Automate a = iter.next();
			String a_name = a.get_name();
			if (a_name.equals(JSONWindow.aut_j1)) {
				tl.add(a, j1);
				terrain.updateJoueur(j1);
			} else if (a_name.equals(JSONWindow.aut_j2)) {
				tl.add(a, j2);
				terrain.updateJoueur(j2);
			} else if (a_name.equals(JSONWindow.aut_apple)) {
				Iterator<Entity> iterPomme = liste_pomme.iterator();
				while (iterPomme.hasNext()) {
					tl.add(a, iterPomme.next());
				}
			} else if (a_name.equals(JSONWindow.aut_bombe)) {
				Iterator<Entity> iterBombe = liste_bombe.iterator();
				while (iterBombe.hasNext()) {
					tl.add(a, iterBombe.next());
				}
			} else if (a_name.equals(JSONWindow.aut_cassable)) {
				Iterator<Entity> iterCassable = liste_mur_cassable.iterator();
				while (iterCassable.hasNext()) {
					tl.add(a, iterCassable.next());
				}
			} else if (a_name.equals(JSONWindow.aut_interrupteur)) {
				Iterator<Entity> iterInterrupteur = liste_interrupteur.iterator();
				while (iterInterrupteur.hasNext()) {
					tl.add(a, iterInterrupteur.next());
				}
			} else if (a_name.equals(JSONWindow.aut_invisible)) {
				Iterator<Entity> iterInivisible = liste_mur_invisible.iterator();
				while (iterInivisible.hasNext()) {
					tl.add(a, iterInivisible.next());
				}
			} else if (a_name.equals(JSONWindow.aut_mine)) {
				Iterator<Entity> iterMine = liste_mine.iterator();
				while (iterMine.hasNext()) {
					tl.add(a, iterMine.next());
				}
			} else if (a_name.equals(JSONWindow.aut_normal)) {
				Iterator<Entity> iterNormal = liste_mur_normal.iterator();
				while (iterNormal.hasNext()) {
					tl.add(a, iterNormal.next());
				}
			} else if (a_name.equals(JSONWindow.aut_pioche)) {
				Iterator<Entity> iterPioche = liste_pioche.iterator();
				while (iterPioche.hasNext()) {
					tl.add(a, iterPioche.next());
				}
			} else if (a_name.equals(JSONWindow.aut_porte)) {
				Iterator<Entity> iterPorte = liste_porte.iterator();
				while (iterPorte.hasNext()) {
					tl.add(a, iterPorte.next());
				}
			} else if (a_name.equals(JSONWindow.aut_potion)) {
				Iterator<Entity> iterPotion = liste_potion.iterator();
				while (iterPotion.hasNext()) {
					tl.add(a, iterPotion.next());
				}
			} else if (a_name.equals(JSONWindow.aut_sable)) {
				Iterator<Entity> iterSable = liste_sable.iterator();
				while (iterSable.hasNext()) {
					tl.add(a, iterSable.next());
				}
			} else if (a_name.equals(JSONWindow.aut_squelette)) {
				Iterator<Entity> iterSquelette = liste_squelette.iterator();
				while (iterSquelette.hasNext()) {
					tl.add(a, iterSquelette.next());
				}
			} else if (a_name.equals(JSONWindow.aut_teleporteur)) {
				Iterator<Entity> iterTP = liste_teleporteur.iterator();
				while (iterTP.hasNext()) {
					tl.add(a, iterTP.next());
				}
			} else if (a_name.equals(JSONWindow.aut_zombie)) {
				Iterator<Entity> iterZombie = liste_zombie.iterator();
				while (iterZombie.hasNext()) {
					tl.add(a, iterZombie.next());
				}
			}
		}

		// ajout d'un Keylistener
		Key_Listener k = new Key_Listener(terrain, kp);
		w.addKeyListener(k);

	}

	public String getname(int num_joueur) {
		String name = null;
		if (num_joueur == 1) {
			if (name1.getText().length() == 0)
				name = "Joueur 1";
			else
				name = name1.getText();
		} else if (num_joueur == 2) {
			if (name2.getText().length() == 0)
				name = "Joueur 2";
			else
				name = name2.getText();
		}
		return name;
	}

	public String getjeu() throws IOException {
		if (labyrinthe.isSelected())
			return "labyrinthe";
		else if (arene.isSelected())
			return "arene";
		else
			return null;
	}

}
