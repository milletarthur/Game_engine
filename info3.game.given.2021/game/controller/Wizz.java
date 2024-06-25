package controller;

import java.util.Iterator;
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
			tl.remove(e);
			Normal to_add = new Normal(e.ligne(), e.colonne());
			terrain.add(to_add, e.ligne(), e.colonne());
			tl.add(to_add);
		} else if(e instanceof Normal) {
			terrain.remove(e.ligne(), e.colonne(), e);
			tl.remove(e);
			Invisible to_add = new Invisible(e.ligne(), e.colonne());
			terrain.add(to_add, e.ligne(), e.colonne());
			tl.add(to_add);
		} else if(e instanceof Invisible) {
			terrain.remove(e.ligne(), e.colonne(), e);
			tl.remove(e);
			Cassable to_add = new Cassable(e.ligne(), e.colonne());
			terrain.add(to_add, e.ligne(), e.colonne());
			tl.add(to_add);
		} else if (e instanceof Interrupteur) {
			LinkedList<Entity> l_levier = ((Interrupteur) e).get_entity();
			for(int i=0; i<l_levier.size(); i++) {
				Entity selected = l_levier.get(i);
				Entity wizz = new WizzEntity(selected.ligne(), selected.colonne(), e.team());
				terrain.add(wizz, selected.ligne(), selected.colonne());
				exec(selected);
				if (selected instanceof Sable) {
					((Interrupteur) e).remove_entity(selected);
				}
			}
		} else if (e instanceof Sable) {
			terrain.remove(e.ligne(), e.colonne(), e);
			tl.remove(e);
			// suppr les autres sables à côté 
			for (int i = 1; i < 9; i++) {
				int coo[] = terrain.next_to_outside(e, i);
				if (coo[0] < 0 || coo[0] > terrain.get_ligne()-1 || coo[1] < 0 || coo[1] > terrain.get_colonne()-1)
					continue;
				LinkedList<Entity> l = terrain.getElement(coo[0], coo[1]);
				Iterator<Entity> iter = l.iterator();
				while(iter.hasNext()) {
					Entity el = iter.next();
					if (el instanceof Sable) {
						Wizz cmd = new Wizz(terrain, tl);
						cmd.exec(el);
					}
				}
			}
		} else if(e instanceof Pioche) {
			int coo[] = terrain.next_to_outside(e, e.direction());
			if (coo[0] < 0 || coo[0] > terrain.get_ligne()-1 || coo[1] < 0 || coo[1] > terrain.get_colonne()-1)
				return;
			Entity elem = terrain.getLastnotSelect(coo[0], coo[1]);
			terrain.remove(coo[0], coo[1], elem);
			tl.remove(elem);
			elem = terrain.getLastnotSelect(coo[0], coo[1]);
			if (elem instanceof Lave)
				tl.add(elem);
		}
		e.wizz();
		LinkedList<Entity> l_entity = terrain.getElement(e.ligne(), e.colonne());
		if(l_entity.getFirst() instanceof WizzEntity) {
			terrain.remove(e.ligne(), e.colonne(), l_entity.getFirst());
		}
		if(l_entity.getLast() instanceof Selection) {
			terrain.remove(e.ligne(), e.colonne(), l_entity.getLast());
		}
	}
	
	@Override
	public String toString() {
		String s = "Wizz";
		return s;
	}

}
