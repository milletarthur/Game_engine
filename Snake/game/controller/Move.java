package controller;

import Model_Snake.Entity;
import toolkit.IAction;

public class Move implements IAction {
	
	public Entity entity ;
	public DrawTerrain dt ;
	
	public Move (Entity entity) {
		this.entity = entity ;
		this.dt = /// ;
	}

	@Override
	public void exec(Entity e) {
		e.move();
		this.dt.repaint();
		return ;		
	}
}
