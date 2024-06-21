package draw;

import javax.swing.*;

import Automates.Automate;
import Automates.AutomatonLoader;
import Labyrinthe.Field;
import Labyrinthe.Joueur;
import controller.KeyPressed;
import controller.TicTac;
import controller.TickListener;
import listener.JSONWindow;
import listener.Key_Listener;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

/*
 * première fenêtre du jeu pour remplir le fichier de config
 */
public class WindowInitGame extends JFrame {

	public static int T_case = 40;

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
		name.setBounds(280,155,200,20);
		labyrinthe.setBounds(20, 180, 110, 30);
		arene.setBounds(20, 215, 110, 30);
		labyrinthe.setForeground(Color.WHITE);
		labyrinthe.setOpaque(false);
		arene.setForeground(Color.WHITE);
		arene.setOpaque(false);
		name1.setBounds(280,180,135,20);
		name2.setBounds(280,215,135,20);

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
		Field terrain = new Field(JSONWindow.hauteur, JSONWindow.largeur, JSONWindow.densite, 10, 2, 2, 2, 2, 2, 25, 25, 50, 10, 2, JSONWindow.seed);
		
		KeyPressed kp = new KeyPressed();

		// ajout d'un joueur pour tester
		Joueur j1 = new Joueur(2, 0, 1);
		Joueur j2 = new Joueur(3, 0, 2);
		terrain.add(j1, 2, 0);
		terrain.add(j2, 3, 0);
		// terrain.printGame();

		// ajout d'un automate
		AutomatonLoader al = new AutomatonLoader(terrain, kp);
		LinkedList<Automate> l_aut = al.loadAutomata("resources/automata/test_cond.gal");

		// Initialisation de la fenêtre
		DrawWindow w = new DrawWindow(terrain.get_colonne(), terrain.get_ligne(), terrain, T_case,
				JSONWindow.visibility, j1, j2);

		Viewport v1 = new Viewport(w.get_dt1(), T_case, JSONWindow.visibility);
		Viewport v2 = new Viewport(w.get_dt2(), T_case, JSONWindow.visibility);

		TickListener tl = new TickListener(terrain);
		TicTac tt = new TicTac(tl, j1, j2, v1, v2);
		tt.add_window(w);

		w.init_Window(v1, v2, w.get_invent(), tt);
		v1.centrerViewport(j1);
		v2.centrerViewport(j2);

		// création du lien entre Entity et Automate
		for (int i = 0; i < l_aut.size(); i++) {
			if (l_aut.get(i).get_name().equals(JSONWindow.aut_j1)) { // si le nom de l'automate à la position i correspond à
																// l'automate associé au joueur 1 dans le fichier de
																// config
				tl.add(l_aut.get(i), j1); // automate attribué à j1
			} else if (l_aut.get(i).get_name().equals(JSONWindow.aut_j2)) {
				tl.add(l_aut.get(i), j2);
			}
		}

		// ajout d'un Keylistener
		Key_Listener k = new Key_Listener(terrain, kp);
		w.addKeyListener(k);

	}

	public String getname(int num_joueur) {
		String name = null ;
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
		return name ;
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
