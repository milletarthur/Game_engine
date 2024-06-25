package controller;

import java.util.ArrayList;
import java.util.LinkedList;

import Automates.ICondition;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;
import Labyrinthe.Lave;
import Labyrinthe.Squelette;
import Labyrinthe.Zombie;
import toolkit.Categorie;
import toolkit.Direction;
import toolkit.Triple;

public class Closest implements ICondition {

	private Field terrain;
	private int direction;
	private int categorie;
	private int distance_vision = 4;
	private ArrayList<ArrayList<Triple<Integer, Integer, Integer>>> cases = new ArrayList<ArrayList<Triple<Integer, Integer, Integer>>>();

	public Closest(Field f, int dir, int cat) {
		terrain = f;
		direction = dir;
		categorie = cat;
	}

	public Closest(Field f) {
		terrain = f;
	}

	private void setCases(Entity e) {
		int cptLigne = distance_vision;
		boolean lignedec = true;
		int cptColonne = distance_vision;
		boolean colonnedec = true;
		for (int i = 0; i < 2 * distance_vision + 1; i++) {
			ArrayList<Triple<Integer, Integer, Integer>> tmp = new ArrayList<Triple<Integer, Integer, Integer>>();
			cptLigne = distance_vision;
			for (int j = 0; j < 2 * distance_vision + 1; j++) {
				Triple<Integer, Integer, Integer> t;
				if (lignedec && colonnedec)
					t = new Triple<Integer, Integer, Integer>(cptLigne + cptColonne, e.ligne() - cptLigne,
							e.colonne() - cptColonne);
				else if (lignedec && !colonnedec)
					t = new Triple<Integer, Integer, Integer>(cptLigne + cptColonne, e.ligne() - cptLigne,
							e.colonne() + cptColonne);
				else if (!lignedec && colonnedec)
					t = new Triple<Integer, Integer, Integer>(cptLigne + cptColonne, e.ligne() + cptLigne,
							e.colonne() - cptColonne);
				else
					t = new Triple<Integer, Integer, Integer>(cptLigne + cptColonne, e.ligne() + cptLigne,
							e.colonne() + cptColonne);
				tmp.add(t);
				if (lignedec)
					cptLigne--;
				else
					cptLigne++;
				if (cptLigne == 0)
					lignedec = false;
			}
			lignedec = true;
			cases.add(tmp);
			if (colonnedec)
				cptColonne--;
			else
				cptColonne++;
			if (cptColonne == 0)
				colonnedec = false;
		}
	}

	public void setDir(int dir) {
		direction = dir;
	}

	public void setCat(int cat) {
		categorie = cat;
	}

	@Override
	public boolean eval(Entity e) {
		if (categorie == Categorie.Arobase || categorie == Categorie.Diese) {
			LinkedList<Entity> l = terrain.get_joueur();
			Joueur j1 = (Joueur) l.get(0);
			Joueur j2 = (Joueur) l.get(1);
			double dist_j1 = Math
					.sqrt(Math.pow((j1.ligne() - e.ligne()), 2) + Math.pow((j1.colonne() - e.colonne()), 2));
			double dist_j2 = Math
					.sqrt(Math.pow((j2.ligne() - e.ligne()), 2) + Math.pow((j2.colonne() - e.colonne()), 2));
			if (dist_j1 > distance_vision && dist_j2 > distance_vision)
				return false;
			Joueur closest;
			if (dist_j1 < dist_j2)
				closest = j1;
			else
				closest = j2;
			int ligne = closest.ligne() - e.ligne();
			int colonne = closest.colonne() - e.colonne();
			switch (terrain.to_absolute(e, direction)) {
			case Direction.N:
				if (-ligne >= Math.abs(colonne)) {
					return true;
				}
				break;
			case Direction.S:
				if (ligne >= Math.abs(colonne)) {
					return true;
				}
				break;
			case Direction.E:
				if (colonne >= Math.abs(ligne)) {
					return true;
				}
				break;
			case Direction.W:
				if (-colonne >= Math.abs(ligne)) {
					return true;
				}
				break;
			default:
				return false;
			}
		} else {
			setCases(e);
		}
		return false;
	}

	@Override
	public int getCategorie() {
		return categorie;
	}

	@Override
	public int getDirection() {
		return direction;
	}

	public void setCatgorie(int cat) {
		categorie = cat;
	}

	public void setDirection(int dir) {
		direction = dir;
	}

	public int getDistanceVision() {
		return distance_vision;
	}

	public void setDistanceVision(int value) {
		distance_vision = value;
	}

	@Override
	public String toString() {
		String s = "Closest(";
		s += Integer.toString(direction);
		s += ";";
		s += Integer.toString(categorie);
		s += ")";
		return s;
	}

}
