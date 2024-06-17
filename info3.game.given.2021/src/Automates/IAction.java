package Automates;

import Labyrinthe.Entity;

public interface IAction {
		
	public Entity getEntity();
	
	public void setEntity(Entity e);
	
	public void exec(Entity e);
}
