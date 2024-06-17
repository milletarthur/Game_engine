package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Turn implements IAction {

	private Entity entity;
	private int Direction;
	private Field terrain;
	
	public Turn(Field f) {
		terrain = f;
	}
	
	public Turn(Entity entity, Field terrain) {
		this.entity = entity;
		this.terrain = terrain;
	}

	public Turn(Entity entity, int Direction, Field terrain) {
		this.entity = entity;
		this.Direction = Direction;
		this.terrain = terrain;
	}
	
	public void print() {
		if (Direction == -3)
			System.out.println("L");
		else 
			System.out.println("R");
	}

	@Override
	public void exec(Entity e) {
		e.turn(this.Direction);
//		System.out.println("Turn");
		return;
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Entity e) {
		entity = e;
	}
	
	public int getDirection() {
		return Direction;
	}
	
	public void setDirection(int dir) {
		Direction = dir;
	}
}
