package controller;

import java.util.LinkedList;
import java.util.Random;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Mine;
import Labyrinthe.Sable;
import toolkit.Categorie;
import toolkit.Pair;

public class Egg implements IAction {

	private Field terrain;
	private Random rand;
	private TickListener tl;

	public Egg(Field f, TickListener tl) {
		terrain = f;
		rand = new Random();
		this.tl = tl;
	}

	public Egg(Field f, Random r) {
		terrain = f;
		rand = r;
	}

	@Override
	public void exec(Entity e) {
		LinkedList<Pair<Integer, Integer>> cases_possibles = new LinkedList<Pair<Integer, Integer>>();
		if (e instanceof Sable) {
			for (int i = -1; i > -9; i--) {
				int[] cell = terrain.next_to_outside(e, i);
				Pair<Integer, Integer> p = new Pair<Integer, Integer>(cell[0], cell[1]);
				if (cell[0] < 0 || cell[0] > terrain.get_ligne() - 1 || cell[1] < 0
						|| cell[1] > terrain.get_colonne() - 1)
					return;
				if (!terrain.isHerePossible(cell[0], cell[1], e))
					continue;
				cases_possibles.add(p);
			}
		} else {
			for (int i = 0; i < terrain.get_ligne(); i++) {
				for (int j = 0; j < terrain.get_colonne(); j++) {
					if (!terrain.isHerePossible(i, j, e))
						continue;
					Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
					cases_possibles.add(p);
				}
			}
		}
		if (cases_possibles.isEmpty())
			return;
		int random = rand.nextInt(cases_possibles.size());
		Pair<Integer, Integer> p = cases_possibles.get(random);
		int x = p.geto1();
		int y = p.geto2();
		if (e instanceof Sable) {
			Entity here = terrain.getLastnotSelect(x, y);
			if (here.category() == Categorie.P || here instanceof Mine) {
				terrain.getElement(x, y).remove(here);
			}
		}
		Entity to_add = e.egg(x, y);
		terrain.add(to_add, x, y);
		tl.add(to_add);
	}

	@Override
	public String toString() {
		String s = "Egg";
		return s;
	}

}
