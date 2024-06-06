package controller;

import Model_Snake.Entity;
import ViewWindow.DrawTerrain;
import toolkit.IAction;

public class Pick implements IAction {
	
	public Entity entity ;
	public DrawTerrain dt ;
	
	public Pick (Entity entity) {
		this.entity = entity ;
//		this.dt =  ;
	}

	@Override
	public void exec(Entity e) {
		// TODO - besoin de faire une méthode Pick dans Snake
		// pour que cette classe s'adapte à n'importe quelle entité
		e.pick();
		this.dt.repaint();	
		return ;
	}
}