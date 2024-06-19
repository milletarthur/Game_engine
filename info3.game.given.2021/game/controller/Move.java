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
		terrain.remove(e.x(), e.y(), e);	
		e.move();
		terrain.add(e, e.x(), e.y());
//		System.out.println("Move");
		return;
	}

}