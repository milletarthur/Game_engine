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
	private TickListener tl;
	
	public Wizz(Field terrain, TickListener tl) {
		this.terrain = terrain;
		this.tl = tl;
	}
	
	@Override
	public void exec(Entity e) {
		if(e instanceof Cassable) {
			terrain.remove(e.ligne(), e.colonne(), e);
			Normal to_add = new Normal(e.ligne(), e.colonne());
			terrain.add(to_add, e.ligne(), e.colonne());
			tl.add(to_add);
		} else if(e instanceof Normal) {
			terrain.remove(e.ligne(), e.colonne(), e);
			Invisible to_add = new Invisible(e.ligne(), e.colonne());
			terrain.add(to_add, e.ligne(), e.colonne());
			tl.add(to_add);
		} else if(e instanceof Invisible) {
			terrain.remove(e.ligne(), e.colonne(), e);
			Cassable to_add = new Cassable(e.ligne(), e.colonne());
			terrain.add(to_add, e.ligne(), e.colonne());
			tl.add(to_add);
		} else if (e instanceof Interrupteur) {
			LinkedList<Entity> l_levier = ((Interrupteur) e).get_entity();
			for(int i=0; i<l_levier.size(); i++) {
				exec(l_levier.get(i));
			}
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
					tl.remove(elem);
				}
			}
			l_entity = terrain.getElement(e.ligne(), e.colonne()-1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne(), e.colonne()-1, elem);
					tl.remove(elem);
				}
			}
			l_entity = terrain.getElement(e.ligne()+1, e.colonne()-1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()+1, e.colonne()-1, elem);
					tl.remove(elem);
				}
			}
			l_entity = terrain.getElement(e.ligne()-1, e.colonne()+1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()-1, e.colonne()+1, elem);
					tl.remove(elem);
				}
			}
			l_entity = terrain.getElement(e.ligne(), e.colonne()+1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne(), e.colonne()+1, elem);
					tl.remove(elem);
				}
			}
			l_entity = terrain.getElement(e.ligne()+1, e.colonne()+1);
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()+1, e.colonne()+1, elem);
					tl.remove(elem);
				}
			}
			l_entity = terrain.getElement(e.ligne()+1, e.colonne());
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()+1, e.colonne(), elem);
					tl.remove(elem);
				}
			}
			l_entity = terrain.getElement(e.ligne()-1, e.colonne());
			for(int i=0; i<l_entity.size(); i++) {
				elem = l_entity.get(i);
				if(elem instanceof Sable) {
					terrain.remove(e.ligne()-1, e.colonne(), elem);
					tl.remove(elem);
				}
			}
		} else if(e instanceof Pioche) {
			int coo[] = terrain.next_to_outside(e, e.direction());
			if (coo[0] < 0 || coo[0] > terrain.get_ligne()-1 || coo[1] < 0 || coo[1] > terrain.get_colonne()-1)
				return;
			Entity elem = terrain.getLastnotSelect(coo[0], coo[1]);
			terrain.remove(coo[0], coo[1], elem);
			tl.remove(elem);
		}
		e.wizz();
	}
	
	@Override
	public String toString() {
		String s = "Wizz";
		return s;
	}

}
