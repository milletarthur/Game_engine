package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;
import toolkit.Direction;

public class Pick implements IAction {

	private Entity entity;
	private Field terrain;

	public Pick(Entity entity, Field terrain) {
		this.entity = entity;
		this.terrain = terrain;
	}
	
	public Pick(Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		if (e instanceof Joueur) {
			Entity pick = terrain.getPickable(e.x(),e.y());
			if (pick != null)
				terrain.remove(e.x(),e.y(),pick);
		}
		e.pick();
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Entity e) {
		entity = e;
	}
}