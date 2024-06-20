package info3.game;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import Automates.Automate;
import Automates.AutomatonLoader;
import Labyrinthe.Apple;
import Labyrinthe.Field;
import Labyrinthe.Joueur;
import controller.KeyPressed;
import controller.TicTac;
import controller.TickListener;
import draw.DrawWindow;
import draw.Viewport;
import listener.Key_Listener;
import toolkit.Pair;

public class GameUS {
	/* TODO - HAUTEUR et LARGEUR seront les dimensions de la matrice */
	private final static int LARGEUR = 30;
	private final static int HAUTEUR = 40;
	private final static int DENSITE = 10;
	private static final int T_case = 30;
	private static final int visibility = 5; // nb de cases visible autour des joueurs

	// TODO - remplir/créer les champs du fichier config

	public static void main(String[] args) throws IOException {
		File f = null; // TODO - initialiser
		lire_fichier_config(f);

		// initialisation de la grille
		Field terrain = new Field(HAUTEUR, LARGEUR, DENSITE);
		
		KeyPressed kp = new KeyPressed();

		// ajout d'un joueur pour tester
		Joueur j1 = new Joueur(2, 0, 1);
		Joueur j2 = new Joueur(3, 0, 2);
		terrain.add(j1, 2, 0);
		terrain.add(j2, 3, 0);
		
		//ajout d'un automate
		AutomatonLoader al = new AutomatonLoader(terrain, kp);
		LinkedList<Automate> l_aut = al.loadAutomata("resources/automata/apple.gal");

		//Initialisation de la fenêtre
		DrawWindow w = new DrawWindow(terrain.get_colonne(), terrain.get_ligne(), terrain, T_case, visibility);
		
		Viewport v1 = new Viewport(w.get_dt1(), T_case, visibility);
		Viewport v2 = new Viewport(w.get_dt2(), T_case, visibility);
		
		TickListener tl = new TickListener(terrain);
		TicTac tt = new TicTac(tl, j1, j2, v1, v2);
		tt.add_window(w);
		
		w.init_Window(v1, v2, w.get_invent(),tt);
		v1.centrerViewport(j1);
		v2.centrerViewport(j2);
		
		//Création du lien entre Entity et Automate
		tl.add(l_aut.getLast(), j1);

		// ajout d'un Keylistener
		Key_Listener k = new Key_Listener(terrain, kp);
		w.addKeyListener(k);

	}

	/*
	 * Rempli les champs de la classe avec les informations du fichier config.
	 */
	private static void lire_fichier_config(File f) {
		// TODO
	}

	public int getLARGEUR() {
		return LARGEUR;
	}

	public int getHAUTEUR() {
		return HAUTEUR;
	}
}
