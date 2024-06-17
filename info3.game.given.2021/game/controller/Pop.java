package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Pop implements IAction {
	
	private Entity entity;
	private Field terrain;
	
	public Pop(Field f) {
		terrain = f;
	}
	
	public Pop(Field f, Entity e) {
		terrain = f;
		entity = e;
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
		e.pop();
	}

}
