package controller;

import Model_Snake.Entity;
import Model_Snake.Field;
import ViewWindow.DrawTerrain;
import toolkit.IAction;

public class Turn implements IAction {

	private Entity entity;
	private int Direction;
	private Field terrain;
	
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
		int old_x = e.x();
		int old_y = e.y();
		e.turn(this.Direction);
//		System.out.println("Turn");
		terrain.update(e, old_x, old_y, e.x(), e.y());
		return;
	}
}
