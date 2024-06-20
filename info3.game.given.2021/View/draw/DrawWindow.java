package draw;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import Labyrinthe.Field;

import controller.TicTac;

/*
 * Classe qui gère la fenêtre
 */

public class DrawWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private int T_case;
	private DrawTerrain dt1;
	private DrawTerrain dt2;
	private DrawInventaire inventaire;
	private int visibility;

	public DrawWindow(int LARGEUR, int HAUTEUR, Field terrain, int T_case, int visibility) throws IOException {
		this.T_case = T_case;
		this.visibility = visibility;
		this.getContentPane().setLayout(new BorderLayout());
		// nom de la fenêtre
		this.setTitle("Labyrinth"); // TODO - mettre le nom dans le fichier de config pour le nom du jeu (Labyrinth
									// / Arène)

		// empêcher le redimensionnement de la fenêtre
		this.setResizable(false);

		// application terminé quand utilisateur quitte
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// initialisation du terrain
		this.dt1 = new DrawTerrain(HAUTEUR, LARGEUR, terrain, T_case);
		this.dt2 = new DrawTerrain(HAUTEUR, LARGEUR, terrain, T_case);
		this.inventaire = new DrawInventaire(T_case, visibility);

	}

	public void init_Window(Viewport v1, Viewport v2, DrawInventaire inventaire, TicTac t) {
		inventaire.settimer(t);

		this.add(v1, BorderLayout.WEST);

		JPanel middle = new JPanel();
		middle.setPreferredSize(new Dimension(10, this.T_case * this.visibility + this.T_case));
		middle.setBackground(Color.BLACK);
		this.add(middle, BorderLayout.CENTER);

		this.add(inventaire, BorderLayout.NORTH);

		this.add(v2, BorderLayout.EAST);

		// fenêtre de la taille du JPanel qu'il contient
		this.pack();

		// rendre la fenêtre visible
		this.setVisible(true);
	}

	public DrawTerrain get_dt1() {
		return this.dt1;
	}

	public DrawTerrain get_dt2() {
		return this.dt2;
	}

	public DrawInventaire get_invent() {
		return this.inventaire;
	}

}
