package tests;

import java.io.IOException;

import draw.DrawWindow;
import Labyrinthe.Field;

/*
 * Main de test pour la view
 */

public class Game {
	
	private final static int LARGEUR = 50 ;
	private final static int HAUTEUR = 30 ;
	private static final int T_case = 10 ;
	
	public static void main(String[] args) throws IOException {
		
		Field terrain = new Field(HAUTEUR, LARGEUR);
		
		//initialisation de la fenêtre
		DrawWindow w = new DrawWindow(terrain.get_colonne(), terrain.get_ligne(), terrain, T_case);
		w.init_Window();
	}

}
