import java.awt.*;

/*
 * Main de test pour la view
 */

import ViewWindow.DrawTerrain;
import ViewWindow.Window;

public class Game {
	
	/* TODO - HAUTEUR et LARGEUR seront les dimensions de la matrice */
	final static int LARGEUR = 600;
	final static int HAUTEUR = 600;

	public static void main(String[] args) {

		// initialisation de la fenêtre
		Window w = new Window(LARGEUR, HAUTEUR);

		// initialisation du terrain
//		DrawTerrain t = new DrawTerrain(LARGEUR, HAUTEUR);
		// impose la taille de la fenêtre avec celui du JPanel
//		t.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
//
//		// ajout du terrain à la fenêtre
//		w.add(t);
		// fenêtre de la taille du JPanel qu'il contient
		w.pack();

		// rendre la fenêtre visible
		w.setVisible(true);
	}

	// TODO - temporaire
	public int getLARGEUR() {
		return LARGEUR;
	}

	// TODO - temporaire
	public int getHAUTEUR() {
		return HAUTEUR;
	}

}
