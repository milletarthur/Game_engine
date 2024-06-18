package controller;


import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Explode implements IAction {
	
	private Field terrain;
	
	public Explode (Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		e.explode();
	}
}
