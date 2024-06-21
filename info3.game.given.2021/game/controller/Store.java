package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Store implements IAction {

	private Field terrain;

	public Store(Field f) {
		terrain = f;
	}

	@Override
	public void exec(Entity e) {
		e.store();
	}

	@Override
	public String toString() {
		String s = "Store";
		return s;
	}

}
