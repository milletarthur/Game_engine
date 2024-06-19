package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Wizz implements IAction{
	
	private Field terrain;
	
	public Wizz(Field terrain) {
		this.terrain = terrain;
	}
	
	@Override
	public void exec(Entity e) {
		e.wizz();
	}

}
