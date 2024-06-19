package controller;

import Automates.IAction;
import Labyrinthe.*;

public class Wizz implements IAction{
	
	private Field terrain;
	
	public Wizz(Field terrain) {
		this.terrain = terrain;
	}
	
	@Override
	public void exec(Entity e) {
		if(e instanceof Cassable) {
			terrain.remove(e.x(), e.y(), e);
			terrain.add(new Normal(e.x(), e.y()), e.x(), e.y());
		} else if(e instanceof Normal) {
			terrain.remove(e.x(), e.y(), e);
			terrain.add(new Invisible(e.x(), e.y()), e.x(), e.y());
		} else if(e instanceof Invisible) {
			terrain.remove(e.x(), e.y(), e);
			terrain.add(new Cassable(e.x(), e.y()), e.x(), e.y());
		} else if (e instanceof Sable) {
			terrain.remove(e.x(), e.y(), e);
			// suppr les autres sables à côté ???
		}
		e.wizz();
	}

}
