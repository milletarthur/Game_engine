package info3.game;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Random;

import Model_Snake.Apple;
import Model_Snake.Entity;
import Model_Snake.Field;
import Model_Snake.Obstacle;
import Model_Snake.Snake;
import ViewWindow.DrawTerrain;
import ViewWindow.Window;
import controller.AutomateSnake;
import controller.TicTac;
import controller.TickListener;
import toolkit.Automate;
import toolkit.Categorie;
import toolkit.Pair;


public class GameUS {
	/* TODO - HAUTEUR et LARGEUR seront les dimensions de la matrice */
	private final static int LARGEUR = 3;
	private final static int HAUTEUR = 3;
	
	


	public static void main(String[] args) {

		Field terrain = new Field(LARGEUR, HAUTEUR);

		// initialisation de la fenêtre
		Window w = new Window(LARGEUR, HAUTEUR, terrain);

		// initialisation du terrain
//		DrawTerrain t = new DrawTerrain(LARGEUR, HAUTEUR, terrain);
		
		TickListener List = new TickListener();
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
		
		LinkedList<Pair<Integer,Integer>> VoidList = terrain.getVoidList();
		int rnd = new Random().nextInt(VoidList.size());
	    Pair<Integer,Integer> selected = VoidList.remove(rnd);
	    int x = selected.x();
	    int y = selected.y();
	    Snake snake = new Snake(x,y,0,Categorie.Arobase,terrain);
//	    terrain.set_elementAt(snake);
	    AutomateSnake auto = new AutomateSnake(snake,terrain);
	    
	    List.add(auto, snake);
	    
	    rnd = new Random().nextInt(VoidList.size());
	    selected = VoidList.remove(rnd);
	    x = selected.x();
	    y = selected.y();
//	    terrain.set_elementAt(new Apple(x,y,-1,Categorie.P,terrain));
	    
	    for (int i = 0; i < 1 ; i++) {
	    	rnd = new Random().nextInt(VoidList.size());
		    selected = VoidList.remove(rnd);
		    x = selected.x();
		    y = selected.y();
//		    terrain.set_elementAt(new Obstacle(x,y,-1,Categorie.O,terrain));
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
