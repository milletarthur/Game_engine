package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Pop implements IAction {
	
	private Field terrain;
	
	public Pop(Field f) {
		terrain = f;
	}
	
	@Override
	public void exec(Entity e) {
		e.pop();
	}

}
