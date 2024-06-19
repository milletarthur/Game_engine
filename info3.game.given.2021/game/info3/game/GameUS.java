package info3.game;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Random;

import Labyrinthe.Apple;
import Labyrinthe.Field;
import controller.TicTac;
import draw.DrawWindow;
import toolkit.Pair;



public class GameUS {
	/* TODO - HAUTEUR et LARGEUR seront les dimensions de la matrice */
	private final static int LARGEUR = 20;
	private final static int HAUTEUR = 20;
	
	


	public static void main(String[] args) {
		
		LinkedList<Pair<Integer,Integer>> VoidList;
		int rnd;
	    Pair<Integer,Integer> selected;
	    int x;
	    int y;

		Field terrain = new Field(LARGEUR, HAUTEUR);

		// initialisation de la fenêtre
		DrawWindow w = new DrawWindow(LARGEUR, HAUTEUR, terrain, 40, 5);

		// initialisation du terrain
//		DrawTerrain t = new DrawTerrain(LARGEUR, HAUTEUR, terrain);
		
		TickListener List = new TickListener(terrain);
		TicTac t = new TicTac(w,List);
		w.init_Window(t);

//		// impose la taille de la fenêtre avec celui du JPanel
//		t.setPreferredSize(new Dimension(LARGEUR * DrawTerrain.T_case, HAUTEUR * DrawTerrain.T_case));
//
//		// ajout du terrain à la fenêtre
//		w.add(t);
//		// fenêtre de la taille du JPanel qu'il contient
//		w.pack();
//
//		// rendre la fenêtre visible
//		w.setVisible(true);
		
		VoidList = terrain.getVoidList();
//		rnd = new Random().nextInt(VoidList.size());
//	    selected = VoidList.remove(rnd);
//	    x = selected.x();
//	    y = selected.y();
//	    Snake snake = new Snake(x,y,0,Categorie.Arobase,terrain);
//	    terrain.update(snake, -1, -1, x, y);
//	    AutomateSnake auto = new AutomateSnake(snake,terrain,List);
//	    
//	    List.add(auto, snake);
	    
	    for (int i = 0; i < 10 ; i++) {
		    rnd = new Random().nextInt(VoidList.size());
		    selected = VoidList.remove(rnd);
		    x = selected.x();
		    y = selected.y();
		    Apple apple = new Apple(x,y,-2,Categorie.P,terrain);
		    terrain.update(apple, -1, -1, x, y);
	    }
	    
	    for (int i = 0; i < 40 ; i++) {
	    	rnd = new Random().nextInt(VoidList.size());
		    selected = VoidList.remove(rnd);
		    x = selected.x();
		    y = selected.y();
		    Obstacle obstacle = new Obstacle(x,y,-1,Categorie.O,terrain);
		    terrain.update(obstacle, -1, -1, x, y);
	    }
		
	    
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
