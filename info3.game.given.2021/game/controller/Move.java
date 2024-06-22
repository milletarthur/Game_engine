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

	public Move(Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		int direc = e.direction();
		int ligne = e.ligne();
		int colonne = e.colonne();
		int[] spot = terrain.next_to_outside(e, Direction.F);
		int go_to_ligne = spot[0];
		int go_to_colonne = spot[1];
//		System.out.print("(");
//		System.out.print(ligne);
//		System.out.print(";");
//		System.out.print(colonne);
//		System.out.print(")");
//		System.out.println(direc);
		if (go_to_ligne < 0 || go_to_ligne > terrain.get_ligne() - 1 || go_to_colonne < 0
				|| go_to_colonne > terrain.get_colonne() - 1)
			return;
//		Entity e_go_to = terrain.getLastnotSelect(go_to_ligne, go_to_colonne);
//		if (e_go_to.category() == Categorie.O || e_go_to.category() == Categorie.G || e_go_to.category() == Categorie.C)
//			return;
		terrain.remove(ligne, colonne, e);
		e.move();
		terrain.add(e, e.ligne(), e.colonne());
		if (e.picked() != null) {
			e.picked().set_ligne(e.ligne());
			e.picked().set_colonne(e.colonne());
			e.picked().turn(e.direction());
		}
//		System.out.println("Move"); 
		return;
	}

	@Override
	public String toString() {
		String s = "Move";
		return s;
	}

}
