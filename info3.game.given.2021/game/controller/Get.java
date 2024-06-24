package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;

public class Get implements IAction {

	private Field terrain;
	TickListener tl;

	public Get(Field terrain, TickListener tl) {
		this.terrain = terrain;
		this.tl = tl;
	}

	@Override
	public void exec(Entity e) {
		if(e.picked() == null && e instanceof Joueur){
			Entity pick = e.picked();
			if(pick != null) {
				pick.turn(e.direction());
				pick.set_ligne(e.ligne());
				pick.set_colonne(e.colonne());
			}
			e.get();
//			System.out.print(e.getInventory().toString());
		}
	}

	@Override
	public String toString() {
		String s = "Get";
		return s;
	}

}
