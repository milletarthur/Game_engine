package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;

public class Store implements IAction {

	private Field terrain;
	private TickListener tl;

	public Store(Field f, TickListener tl) {
		terrain = f;
		this.tl = tl;
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
