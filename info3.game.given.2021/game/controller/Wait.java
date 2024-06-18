package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Wait implements IAction {
	
private Field terrain;
	
	public Wait (Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		e.wait_();
	}

}
