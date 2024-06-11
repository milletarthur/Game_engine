/*
 * Main de test pour la view
 */

import java.io.IOException;

public class Game {
	
	final static int LARGEUR = 500 ;
	final static int HAUTEUR = 500 ;
	
	public static void main(String[] args) throws IOException {
		
		//initialisation de la fenêtre
		DrawWindow w = new DrawWindow(LARGEUR, HAUTEUR);
		w.init_Window();
	}

}
