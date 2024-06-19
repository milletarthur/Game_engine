package tests;

import java.io.IOException;
import draw.DrawWindow;
import draw.Viewport;
import listener.Key_Listener;
import Labyrinthe.Field;
import Labyrinthe.Joueur;

/*
 * Main de test pour la view
 */

public class Game {

	private final static int LARGEUR = 30;
	private final static int HAUTEUR = 40;
	private static final int T_case = 30;
	private static final int visibility = 5; // nb de cases visible autour des joueurs

	public static void main(String[] args) throws IOException {

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

}
