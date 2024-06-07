package controller;

import Model_Snake.Entity;
import Model_Snake.Field;
import ViewWindow.DrawTerrain;
import toolkit.IAction;

public class Move implements IAction {
	
	public Entity entity;
	public Field terrain;
	
	public Move (Entity entity, Field terrain) {
		this.entity = entity;
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		int old_x = e.x();
		int old_y = e.y();
		e.move();
		terrain.update(e, old_x, old_y, e.x(), e.y());
		System.out.println("Move");
		return ;		
	}
}
