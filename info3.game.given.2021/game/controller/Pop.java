package controller;

import Automates.IAction;
import Labyrinthe.*;
import toolkit.Direction;

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
		} else if (e instanceof Zombie || e instanceof Squelette) {
			e.setTeam();
		} else if (e instanceof Pioche) {
			Entity elem;
			switch(e.direction()) {
			case Direction.N:
				elem = terrain.getLastnotSelect(e.ligne()-1, e.colonne());
				if(elem instanceof Cassable) {
					terrain.remove(e.ligne()-1, e.colonne(), elem);
				}
				break;
			case Direction.S:
				elem = terrain.getLastnotSelect(e.ligne()+1, e.colonne());
				if(elem instanceof Cassable) {
					terrain.remove(e.ligne()+1, e.colonne(), elem);
				}
				break;
			case Direction.E:
				elem = terrain.getLastnotSelect(e.ligne(), e.colonne()+1);
				if(elem instanceof Cassable) {
					terrain.remove(e.ligne(), e.colonne()+1, elem);
				}
				break;
			case Direction.W:
				elem = terrain.getLastnotSelect(e.ligne(), e.colonne()-1);
				if(elem instanceof Cassable) {
					terrain.remove(e.ligne(), e.colonne()-1, elem);
				}
				break;
			}
		}
		e.pop();
	}

}
