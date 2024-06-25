package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;
import toolkit.Categorie;
import toolkit.Direction;

public class Move implements IAction {

	public Field terrain;
	private TickListener tl;

	public Move(Field terrain, TickListener tl) {
		this.terrain = terrain;
		this.tl = tl;
	}

	@Override
	public void exec(Entity e) {
		int direc = e.direction();
		int ligne = e.ligne();
		int colonne = e.colonne();
		int[] spot = terrain.next_to_outside(e, Direction.F);
		int go_to_ligne = spot[0];
		int go_to_colonne = spot[1];
		if (go_to_ligne < 0 || go_to_ligne > terrain.get_ligne() - 1 || go_to_colonne < 0
				|| go_to_colonne > terrain.get_colonne() - 1)
			return;
		terrain.remove(ligne, colonne, e);
		e.move();
		terrain.add(e, e.ligne(), e.colonne());
		if (e instanceof Joueur) {
			terrain.updateJoueur(e);
		}
		if (e.picked() != null) {
			e.picked().set_ligne(e.ligne());
			e.picked().set_colonne(e.colonne());
			e.picked().turn(e.direction());
		}
		return;
	}

	@Override
	public String toString() {
		String s = "Move";
		return s;
	}

}
