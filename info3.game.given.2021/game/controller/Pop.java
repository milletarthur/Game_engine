package controller;

import Automates.IAction;
import Labyrinthe.*;

public class Pop implements IAction {
	
	private Field terrain;
	
	public Pop(Field f) {
		terrain = f;
	}
	
	@Override
	public void exec(Entity e) {
		if(e instanceof Arc) {
			terrain.remove(e.x(), e.y(), e);
			terrain.add(new Epee(e.x(), e.y()), e.x(), e.y());
		} else if(e instanceof Epee) {
			terrain.remove(e.x(), e.y(), e);
			terrain.add(new Arc(e.x(), e.y()), e.x(), e.y());
		} else if(e instanceof Cassable) {
			terrain.remove(e.x(), e.y(), e);
			terrain.add(new Invisible(e.x(), e.y()), e.x(), e.y());
		} else if(e instanceof Normal) {
			terrain.remove(e.x(), e.y(), e);
			terrain.add(new Cassable(e.x(), e.y()), e.x(), e.y());
		} else if(e instanceof Invisible) {
			terrain.remove(e.x(), e.y(), e);
			terrain.add(new Normal(e.x(), e.y()), e.x(), e.y());
		}
		e.pop();
	}

}
