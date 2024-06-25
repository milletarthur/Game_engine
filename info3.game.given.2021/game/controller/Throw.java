package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.Bombe;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import toolkit.Direction;

public class Throw implements IAction {

	private Field terrain;
	private TickListener tl;

	public Throw(Field terrain, TickListener tl) {
		this.terrain = terrain;
		this.tl = tl;
	}

	@Override
	public void exec(Entity e) {
		Entity picked = e.picked();
		if (picked == null)
			return;
		int[] coo = terrain.next_to_outside(e, e.direction());
		if (coo[0] < 0 || coo[1] < 0 || coo[0] > terrain.get_colonne() || coo[1] > terrain.get_ligne())
			return;
		if (!terrain.isHerePossible(coo[0], coo[1], picked))
			return;
		e.throw_();
		picked.set_ligne(coo[0]);
		picked.set_colonne(coo[1]);
		terrain.add(picked, coo[0], coo[1]);
		if (picked instanceof Bombe)
			tl.add(picked);
	}

	@Override
	public String toString() {
		String s = "Throw";
		return s;
	}

}
