package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

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