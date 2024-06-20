package tests;

import draw.DrawWindow;
import draw.WindowInitGame;
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

	public static void main(String[] args) {
		
		WindowInitGame game = new WindowInitGame();
/*
		// initialisation de la grille
		Field terrain = new Field(HAUTEUR, LARGEUR, 10);

		// ajout d'un joueur pour tester
		Joueur j1 = new Joueur(2, 0, 1);
		Joueur j2 = new Joueur(3, 0, 2);
		terrain.add(j1, 2, 0);
		terrain.add(j2, 3, 0);
//		terrain.set_element2(2, 0, j1, terrain.get_labyrinthe());
//		terrain.set_element2(3, 0, j2, terrain.get_labyrinthe());

		// initialisation de la fenêtre
		DrawWindow w = new DrawWindow(terrain.get_colonne(), terrain.get_ligne(), terrain, T_case, visibility);

		Viewport v1 = new Viewport(w.get_dt1(), T_case, visibility);
		Viewport v2 = new Viewport(w.get_dt2(), T_case, visibility);
		w.init_Window(v1, v2, w.get_invent());
		v1.centrerViewport(j1);
		v2.centrerViewport(j2);

		// ajout d'un Keylistener
		Key_Listener k = new Key_Listener(j1, j2, w.get_dt1(), w.get_dt2(), v1, v2, terrain);
		w.addKeyListener(k);
*/

	}

}
