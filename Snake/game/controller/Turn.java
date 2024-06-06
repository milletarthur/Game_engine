package controller;

import Model_Snake.Entity;
import ViewWindow.DrawTerrain;
import toolkit.IAction;

public class Turn implements IAction {
	
	public Entity entity ;
	public int Direction ;
	public DrawTerrain dt ;
	
	public Turn (Entity entity) {
		this.entity = entity ;
//		this.dt = ;
	}
	
	public Turn (Entity entity, int Direction) {
		this.entity = entity ;
		this.Direction = Direction ;
//		this.dt = ;		
	}

	@Override
	public void exec(Entity e) {
		e.turn(this.Direction);
		this.dt.repaint();	
		return ;
	}
}
