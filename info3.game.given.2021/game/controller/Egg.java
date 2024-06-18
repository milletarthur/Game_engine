package controller;

import java.util.LinkedList;
import java.util.Random;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import toolkit.Pair;

public class Egg implements IAction {

	private Field terrain;
	
	public Egg(Field f) {
		terrain = f;
	}
	
	@Override
	public void exec(Entity e) {
		e.layer();
	}

}
