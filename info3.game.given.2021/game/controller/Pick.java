package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;

public class Pick implements IAction {

	private Field terrain;
	private TickListener tl;

	public Pick(Field terrain, TickListener tl) {
		this.terrain = terrain;
		this.tl = tl;
	}

	@Override
	public void exec(Entity e) {
		Entity pick = terrain.getPickable(e.ligne(), e.colonne());
		boolean success = e.pick(pick);
		if (pick != null && success)
			terrain.remove(e.ligne(), e.colonne(), pick);

	}

	@Override
	public String toString() {
		String s = "Pick";
		return s;
	}

}