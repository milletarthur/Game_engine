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
