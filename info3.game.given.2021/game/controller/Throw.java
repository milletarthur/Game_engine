package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Throw implements IAction{
	
private Field terrain;
	
	public Throw (Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		e.throw_();
	}

}
