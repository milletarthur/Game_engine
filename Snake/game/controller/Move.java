package controller;

import Model_Snake.Entity;
import ViewWindow.DrawTerrain;
import toolkit.IAction;

public class Move implements IAction {
	
	public Entity entity ;
	public DrawTerrain dt ;
	
	
	public Move (Entity entity) {
		this.entity = entity ;
//		this.dt = ; TODO
	}

	@Override
	public void exec(Entity e) {
		e.move();
		this.dt.repaint();
		return ;		
	}
}
