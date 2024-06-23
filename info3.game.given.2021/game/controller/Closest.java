package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Lave;
import toolkit.Direction;

public class Closest implements ICondition {

	private Field terrain;
	private int direction;
	private int categorie;
	private int distance_vision = 3;

	public Closest(Field f, int dir, int cat) {
		terrain = f;
		direction = dir;
		categorie = cat;
	}

	public Closest(Field f) {
		terrain = f;
	}

	public void setDir(int dir) {
		direction = dir;
	}

	public void setCat(int cat) {
		categorie = cat;
	}

	@Override
	public boolean eval(Entity e) {
		int ligne = e.ligne();
		int colonne = e.colonne();
		int l = ligne;
		int c = colonne;
		Entity elem = new Lave(-1, -1);
		switch (e.direction()) {
		case Direction.N:
			for (int k = 0; k <= distance_vision; k++) {
				for (int j_min = c; j_min <= c + 2 * k; j_min++) {
					elem.set_ligne(ligne);
					elem.set_colonne(j_min);
					if (terrain.cell(elem, Direction.H, categorie)) {
						System.out.println("true");
						return true;
					}
				}
				c--;
				ligne--;
			}
			System.out.println("false");
			return false;
		case Direction.S:
			for (int k = 0; k <= distance_vision; k++) {
				for (int j_min = c; j_min <= c + 2 * k; j_min++) {
					elem.set_ligne(ligne);
					elem.set_colonne(j_min);
					if (terrain.cell(elem, Direction.H, categorie)) {
						System.out.println("true");
						return true;
					}
				}
				c--;
				ligne++;
			}
			System.out.println("false");
			return false;
		case Direction.E:
			for (int k = 0; k <= distance_vision; k++) {
				for (int i_min = l; i_min <= l + 2 * k; i_min++) {
					elem.set_ligne(i_min);
					elem.set_colonne(colonne);
					if (terrain.cell(elem, Direction.H, categorie)) {
						System.out.println("true");
						return true;
					}
				}
				l--;
				colonne++;
			}
			System.out.println("false");
			return false;
		case Direction.W:
			for (int k = 0; k <= distance_vision; k++) {
				for (int i_min = l; i_min <= l + 2 * k; i_min++) {
					elem.set_ligne(i_min);
					elem.set_colonne(colonne);
					if (terrain.cell(elem, Direction.H, categorie)) {
						System.out.println("true");
						return true;
					}
				}
				l--;
				colonne--;
			}
			System.out.println("false");
			return false;
		case Direction.NE:
			for (int m = ligne; m >= ligne - distance_vision; m--) {
				for (int n = colonne; n <= colonne + distance_vision; n++) {
					elem.set_ligne(m);
					elem.set_colonne(n);
					if (terrain.cell(elem, Direction.H, categorie)) {
						System.out.println("NEtrue");
						return true;
					}
				}
			}
			System.out.println("NEfalse");
			return false;
		case Direction.NW:
			for (int m = ligne; m >= ligne - distance_vision; m--) {
				for (int n = colonne; n >= colonne - distance_vision; n--) {
					elem.set_ligne(m);
					elem.set_colonne(n);
					if (terrain.cell(elem, Direction.H, categorie)) {
						System.out.println("NWtrue");
						return true;
					}
				}
			}
			System.out.println("NWfalse");
			return false;
		case Direction.SE:
			for (int m = ligne; m <= ligne + distance_vision; m++) {
				for (int n = colonne; n <= colonne + distance_vision; n++) {
					elem.set_ligne(m);
					elem.set_colonne(n);
					if (terrain.cell(elem, Direction.H, categorie)) {
						System.out.println("SEtrue");
						return true;
					}
				}
			}
			System.out.println("SEfalse");
			return false;
		case Direction.SW:
			for (int m = ligne; m <= ligne + distance_vision; m++) {
				for (int n = colonne; n >= colonne - distance_vision; n--) {
					elem.set_ligne(m);
					elem.set_colonne(n);
					if (terrain.cell(elem, Direction.H, categorie)) {
						System.out.println("SWtrue");
						return true;
					}
				}
			}
			System.out.println("SWfalse");
			return false;
		default:
			System.out.println("false");
			return false;
		}
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
