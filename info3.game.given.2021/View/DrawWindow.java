import java.awt.*;
import java.io.IOException;

import javax.swing.*;

/*
 * Classe qui gère la fenêtre
 */

public class DrawWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	int LARGEUR ;
	int HAUTEUR ;
	
	DrawTerrain dt ;
	
	public DrawWindow (int LARGEUR, int HAUTEUR) throws IOException {
		this.LARGEUR = LARGEUR ;
		this.HAUTEUR = HAUTEUR ;
		this.getContentPane().setLayout(new BorderLayout());
		// nom de la fenêtre
		this.setTitle("Labyrinth"); // TODO - mettre le nom dans le fichier de config pour le nom du jeu (Labyrinth / Arène)
		
		// empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		
		// application terminé quand utilisateur quitte
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// initialisation du terrain
		this.dt = new DrawTerrain(LARGEUR, HAUTEUR);
		
		// TODO - rajouter le timer ?
		
	}
	
	public void init_Window() {
		// impose la taille de la fenêtre avec celui du JPanel
		dt.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		
		// ajout du terrain à la fenêtre
		this.add(dt, BorderLayout.CENTER);
		
		// fenêtre de la taille du JPanel qu'il contient
		this.pack();
		
		// rendre la fenêtre visible
		this.setVisible(true);
	}

}
