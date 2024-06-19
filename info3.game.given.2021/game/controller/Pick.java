package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;

public class Pick implements IAction {

	private Field terrain;

	public Pick(Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		Entity pick = terrain.getPickable(e.x(), e.y());
		boolean success = e.pick(pick);
		if (pick != null && success)
			terrain.remove(e.x(), e.y(), pick);

	}

}