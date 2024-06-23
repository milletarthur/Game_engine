package controller;

import java.util.LinkedList;
import java.util.Random;

import Automates.IAction;
import Labyrinthe.*;
import Labyrinthe.Void;
import toolkit.Direction;
import toolkit.Pair;

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
			// suppr les autres sables à côté 
			Entity elem;
			LinkedList<Entity> l_entity;
			l_entity = terrain.getElement(e.ligne()-1, e.colonne()-1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()-1, e.colonne()-1, elem);
				}
			}
			l_entity = terrain.getElement(e.ligne(), e.colonne()-1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne(), e.colonne()-1, elem);
				}
			}
			l_entity = terrain.getElement(e.ligne()+1, e.colonne()-1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()+1, e.colonne()-1, elem);
				}
			}
			l_entity = terrain.getElement(e.ligne()-1, e.colonne()+1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()-1, e.colonne()+1, elem);
				}
			}
			l_entity = terrain.getElement(e.ligne(), e.colonne()+1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne(), e.colonne()+1, elem);
				}
			}
			l_entity = terrain.getElement(e.ligne()+1, e.colonne()+1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()+1, e.colonne()+1, elem);
				}
			}
			l_entity = terrain.getElement(e.ligne()+1, e.colonne());
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()+1, e.colonne(), elem);
				}
			}
			l_entity = terrain.getElement(e.ligne()-1, e.colonne());
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()-1, e.colonne(), elem);
				}
			}
		} else if(e instanceof Pioche) {
			int coo[] = terrain.next_to_outside(e, e.direction());
			if (coo[0] < 0 || coo[0] > terrain.get_ligne()-1 || coo[1] < 0 || coo[1] > terrain.get_colonne()-1)
				return;
			Entity elem = terrain.getLastnotSelect(coo[0], coo[1]);
			terrain.remove(coo[0], coo[1], elem);
		}
		e.wizz();
	}
	
	@Override
	public String toString() {
		String s = "Wizz";
		return s;
	}

}
