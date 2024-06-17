package controller;


import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Explode implements IAction {
	
	private Entity entity;
	private Field terrain;
	
	public Explode (Entity entity, Field terrain) {
		this.entity = entity ;
		this.terrain = terrain;
	}
	
	public Explode (Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		e.explode();
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
