package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Move implements IAction {
	
	public Entity entity;
	public Field terrain;
	
	public Move (Entity entity, Field terrain) {
		this.entity = entity;
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		e.move();
//		System.out.println("Move");
		return ;		
	}
}
