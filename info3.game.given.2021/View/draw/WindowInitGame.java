package draw;

import javax.swing.*;

import Labyrinthe.Field;
import Labyrinthe.Joueur;
import listener.JSONWindow;
import listener.Key_Listener;
import listener.SliderListener;

import java.awt.*;
import java.io.IOException;

/*
 * première fenêtre du jeu pour remplir le fichier de config
 */
public class WindowInitGame extends JFrame {

	private final static int LARGEUR = 30;
	private final static int HAUTEUR = 40;
	private static final int T_case = 30;
	private static final int visibility = 5; // nb de cases visible autour des joueurs

	private static final long serialVersionUID = 1L;

	JTextField name1, name2;
	JRadioButton labyrinthe, arene;
	JSlider s_HAUTEUR, s_LARGEUR, s_VISIBILITY;

	public WindowInitGame() {
		this.setTitle("Bienvenue dans ce moteur de Jeux");
		this.setSize(450, 400);

		// JPanel de la fenetre
		JPanel G = new JPanel();
		G.setBackground(Color.GREEN);
		G.setLayout(new FlowLayout());

		// choix du jeu
		JLabel jeu = new JLabel("Sélection du jeu : ");
		labyrinthe = new JRadioButton("Labyrinthe");
		arene = new JRadioButton("Arène");
		ButtonGroup group = new ButtonGroup();
		group.add(labyrinthe);
		group.add(arene);

		G.add(jeu);
		G.add(labyrinthe);
		G.add(arene);

		// nom des joueurs
		JLabel inst_name1 = new JLabel("Entrer le nom du joueur 1 : ");
		JLabel inst_name2 = new JLabel("Entrer le nom du joueur 2 : ");

		name1 = new JTextField();
		name1.setPreferredSize(new Dimension(150, 20));
		name2 = new JTextField();
		name2.setPreferredSize(new Dimension(150, 20));

		G.add(inst_name1);
		G.add(name1);
		G.add(inst_name2);
		G.add(name2);

		// Sélection taille du terrain
		s_HAUTEUR = new JSlider(20, 100, 80);
		JLabel haut = new JLabel("HAUTEUR du terrain : " + s_HAUTEUR.getValue());
		s_HAUTEUR.addChangeListener(new SliderListener(haut, s_HAUTEUR, 1, this));
		G.add(haut);
		G.add(s_HAUTEUR);

		s_LARGEUR = new JSlider(20, 100, 80);
		JLabel larg = new JLabel("LARGEUR du terrain : " + s_LARGEUR.getValue());
		s_LARGEUR.addChangeListener(new SliderListener(larg, s_LARGEUR, 2, this));
		G.add(larg);
		G.add(s_LARGEUR);

		s_VISIBILITY = new JSlider(3, 10, 8);
		JLabel visi = new JLabel("Visibilité autour du joueur : " + s_VISIBILITY.getValue());
		s_VISIBILITY.addChangeListener(new SliderListener(visi, s_VISIBILITY, 3, this));
		G.add(visi);
		G.add(s_VISIBILITY);

		// lancement du jeu
		JButton jouer = new JButton("Jouer");
		jouer.addActionListener(new JSONWindow(this));
		G.add(jouer);

		this.add(G, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void initGame() throws IOException {

		// initialisation de la grille
		Field terrain = new Field(HAUTEUR, LARGEUR, 10);

		// ajout d'un joueur pour tester
		Joueur j1 = new Joueur(20, 20, terrain);
		Joueur j2 = new Joueur(10, 10, terrain);
		terrain.set_element(20, 20, j1, null);
		terrain.set_element(10, 10, j2, null);

		// initialisation de la fenêtre
		DrawWindow w = new DrawWindow(terrain.get_colonne(), terrain.get_ligne(), terrain, T_case, visibility);

		Viewport v1 = new Viewport(w.get_dt1(), T_case, visibility);
		Viewport v2 = new Viewport(w.get_dt2(), T_case, visibility);
		w.init_Window(v1, v2, w.get_invent());
		v1.centrerViewport(j1);
		v2.centrerViewport(j2);

		// ajout d'un Keylistener
		Key_Listener k = new Key_Listener(j1, j2, w.get_dt1(), w.get_dt2(), v1, v2);
		w.addKeyListener(k);

	}

	public String getname(int num_joueur) throws IOException {
		if (num_joueur == 1) {
			return name1.getText();
		} else if (num_joueur == 2) {
			return name2.getText();
		} else {
			throw new IOException("pas de nom");
		}
	}

	public String getjeu() throws IOException {
		if (labyrinthe.isSelected()) {
			return "labyrinthe";
		} else if (arene.isSelected()) {
			return "arene";
		} else {
			throw new IOException("pas de selection");
		}

	}

	public int getSlider(String champs) {
		if (champs == "HAUTEUR") {
			return s_HAUTEUR.getValue();
		} else if (champs == "LARGEUR") {
			return s_LARGEUR.getValue();
		} else if (champs == "VISIBILITY") {
			return s_VISIBILITY.getValue();
		}
		return -1;
	}

}
