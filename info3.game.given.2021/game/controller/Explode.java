package controller;


import java.util.Iterator;
import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.Bombe;
import Labyrinthe.Cassable;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;
import Labyrinthe.Mine;
import Labyrinthe.Squelette;
import Labyrinthe.Zombie;

public class Explode implements IAction {
	
	private Field terrain;
	
	public Explode (Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		if (e instanceof Mine || e instanceof Bombe) {
			if (e instanceof Mine)
				((Mine) e).changeState();
			if (e instanceof Bombe)
				((Bombe) e).changeState();
			for (int i = 0; i > -9; i--) {
				int[] cell = terrain.next_to(e,i);
				int x = cell[0];
				int y = cell[1];
				if (cell[0] < 0 || cell[1] < 0 || cell[0] > terrain.get_colonne() || cell[1] > terrain.get_ligne())
					continue;
				LinkedList<Entity> l = terrain.getElement(x, y);
				Iterator<Entity> iter = l.iterator();
				while (iter.hasNext()) {
					Entity elem = iter.next();
					if (elem instanceof Mine && ((Mine) elem).exploded())
						continue;						
					if (elem instanceof Bombe && ((Bombe) elem).exploded())
						continue;
					if (elem instanceof Mine || elem instanceof Bombe || elem instanceof Cassable) {
						Explode ex = new Explode(terrain);
						ex.exec(elem);
					} else if (elem instanceof Joueur || elem instanceof Zombie || elem instanceof Squelette){
						elem.power(-5);
					}
				}
			}
		}
		e.explode();
		terrain.remove(e.ligne(), e.colonne(), e);
	}
}
