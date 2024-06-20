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
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Epee(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if(e instanceof Epee) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Arc(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if(e instanceof Cassable) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Invisible(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if(e instanceof Normal) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Cassable(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if(e instanceof Invisible) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Normal(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		}
		e.pop();
	}

}
