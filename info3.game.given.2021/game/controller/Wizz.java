package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Wizz implements IAction{
	
	private Entity entity;
	private Field terrain;
	
	public Wizz (Entity entity, Field terrain) {
		this.entity = entity ;
		this.terrain = terrain;
	}
	
	public Wizz(Field terrain) {
		this.terrain = terrain;
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
		e.wizz();
	}

}
