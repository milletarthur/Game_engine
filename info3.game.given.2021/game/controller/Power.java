package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Power implements IAction {
	
	private Entity entity;
	private Field terrain;
	private int vie = 0;

	public Power(Entity e, Field f, int vie) {
		entity = e;
		terrain = f;
		this.vie = vie;
	}
	
	public Power(Entity e, Field f) {
		entity = e;
		terrain = f;
	}
	
	public Power(Field f) {
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
	
	public int getVie() {
		return vie;
	}
	
	public void setVie(int vie) {
		this.vie = vie;
	}

	@Override
	public void exec(Entity e) {
		e.power(vie);
	}

}
