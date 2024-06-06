/*
 * Main de test pour la view
 */

public class Game {
	
	/* TODO - HAUTEUR et LARGEUR seront les dimensions de la matrice */
	final static int LARGEUR = 400;
	final static int HAUTEUR = 400;

	public static void main(String[] args) {

		// initialisation de la fenêtre
		Window w = new Window(LARGEUR, HAUTEUR);

		// initialisation du terrain
		DrawTerrain t = new DrawTerrain(LARGEUR, HAUTEUR);

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
