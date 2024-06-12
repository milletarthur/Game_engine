package info3.game;

import Model_Snake.Field;
import ViewWindow.DrawTerrain;
import ViewWindow.Window;

public class GameUS {
	/* TODO - HAUTEUR et LARGEUR seront les dimensions de la matrice */
	private final static int LARGEUR = 10;
	private final static int HAUTEUR = 10;


	public static void main(String[] args) {
		
		Field terrain = new Field(LARGEUR,HAUTEUR);
		
		// initialisation de la fenêtre
		Window w = new Window(LARGEUR*20, HAUTEUR*20);

		// initialisation du terrain
		DrawTerrain t = new DrawTerrain(LARGEUR, HAUTEUR,terrain);

		// ajout du terrain à la fenêtre
		w.add(t);

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