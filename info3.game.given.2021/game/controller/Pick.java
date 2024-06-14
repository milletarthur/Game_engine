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

	@Override
	public void exec(Entity e) {
//		int rnd = 1; // egg not working
		e.pick();
//		System.out.println("Pick");
		return;
	}
}