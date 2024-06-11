package draw;

import java.awt.*;
import javax.swing.*;

import java.io.IOException;

import Labyrinthe.Field ;

/*
 * Classe qui gère la fenêtre
 */

public class DrawWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private int LARGEUR ;
	private int HAUTEUR ;
	private int T_case ;
	
	private DrawTerrain dt ;
	
	public DrawWindow (int LARGEUR, int HAUTEUR, Field terrain, int T_case) throws IOException {
		this.LARGEUR = LARGEUR ;
		this.HAUTEUR = HAUTEUR ;
		this.T_case = T_case ;
		this.getContentPane().setLayout(new BorderLayout());
		// nom de la fenêtre
		this.setTitle("Labyrinth"); // TODO - mettre le nom dans le fichier de config pour le nom du jeu (Labyrinth / Arène)
		
		// empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		
		// application terminé quand utilisateur quitte
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// initialisation du terrain
		this.dt = new DrawTerrain(terrain, T_case);
		
		// TODO - rajouter le timer ?
		
	}
	
	public void init_Window() {
		// impose la taille de la fenêtre avec celui du JPanel
		dt.setPreferredSize(new Dimension(LARGEUR*T_case, HAUTEUR*T_case));
		
		// ajout du terrain à la fenêtre
		this.add(dt, BorderLayout.CENTER);
		
		// fenêtre de la taille du JPanel qu'il contient
		this.pack();
		
		// rendre la fenêtre visible
		this.setVisible(true);
	}

}
