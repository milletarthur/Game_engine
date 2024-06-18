package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Store implements IAction {
	
	private Entity entity;
	private Field terrain;
	
	public Store(Entity e, Field f) {
		entity = e;
		terrain = f;
	}
	
	public Store(Field f) {
		terrain = f;
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Entity e) {
		entity = e;
	}

	@Override
	public void exec(Entity e) {
		e.store();
	}

}
