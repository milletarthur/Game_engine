package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;

public class Move implements IAction {

	public Field terrain;
	
	public Move(Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		terrain.remove(e.ligne(), e.colonne(), e);	
		e.move();
		terrain.add(e, e.ligne(), e.colonne());
//		System.out.println("Move");
		return;
	}

}
