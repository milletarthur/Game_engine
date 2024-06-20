package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.*;
import toolkit.Direction;

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
		} else if(e instanceof Pioche) {
			Entity elem;
			switch(e.direction()) {
			case Direction.N:
				elem = terrain.getLastnotSelect(e.ligne()-1, e.colonne());
				if(elem instanceof Joueur || elem instanceof Squelette || elem instanceof Zombie) {
					elem.power(-2);
				} else {
					terrain.remove(e.ligne()-1, e.colonne(), elem);
				}
				break;
			case Direction.S:
				elem = terrain.getLastnotSelect(e.ligne()+1, e.colonne());
				if(elem instanceof Joueur || elem instanceof Squelette || elem instanceof Zombie) {
					elem.power(-2);
				} else {
					terrain.remove(e.ligne()+1, e.colonne(), elem);
				}
				break;
			case Direction.E:
				elem = terrain.getLastnotSelect(e.ligne(), e.colonne()+1);
				if(elem instanceof Joueur || elem instanceof Squelette || elem instanceof Zombie) {
					elem.power(-2);
				} else {
					terrain.remove(e.ligne(), e.colonne()+1, elem);
				}
				break;
			case Direction.W:
				elem = terrain.getLastnotSelect(e.ligne(), e.colonne()-1);
				if(elem instanceof Joueur || elem instanceof Squelette || elem instanceof Zombie) {
					elem.power(-2);
				} else {
					terrain.remove(e.ligne(), e.colonne()-1, elem);
				}
				break;
			}
		}
		e.wizz();
	}

}
