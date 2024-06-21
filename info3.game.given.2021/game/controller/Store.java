package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;

public class Store implements IAction {

	private Field terrain;

	public Store(Field f) {
		terrain = f;
	}

	@Override
	public void exec(Entity e) {
		if (e instanceof Joueur && e.picked() == null)
			return;
		e.store();
	}

	@Override
	public String toString() {
		String s = "Store";
		return s;
	}

}
