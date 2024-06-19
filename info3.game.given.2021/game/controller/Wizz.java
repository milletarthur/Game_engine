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
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Normal(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if(e instanceof Normal) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Invisible(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if(e instanceof Invisible) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Cassable(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if (e instanceof Sable) {
			terrain.remove(e.ligne(), e.colonne(), e);
			// suppr les autres sables à côté ???
		}
		e.wizz();
	}

}
