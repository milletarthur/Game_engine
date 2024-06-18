package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Get implements IAction {
	
private Field terrain;
	
	public Get (Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		e.get();
	}

}
