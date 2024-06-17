package Labyrinthe;

import java.util.LinkedList;

import toolkit.Direction;

/*
 * Classe Joueur
 * 
 * x et y sont les coordonnées de ce joueur dans la matrice
 */
public class Joueur extends Entity {

	public Joueur(int x, int y, Field f) {
		this.x = x;
		this.y = y;
		this.f = f;
		this.Orientation = Direction.E;
	}

	@Override
	void egg(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move() {
		LinkedList<Entity> l = f.getElement(y, x);
		switch (Orientation) {
		case Direction.N:
			if (y == 0) {
				break;
			} else {
				l.remove(this);
				f.update_element(y, x, l, null);
				this.y -= 1;
				this.f.set_element(y, x, this, null);
				break;
			}
		case Direction.S:
			if (y == f.get_ligne() - 1) {
				break;
			} else {
				l.remove(this);
				f.update_element(y, x, l, null);
				this.y += 1;
				this.f.set_element(y, x, this, null);
				break;
			}
		case Direction.E:
			if (x == f.get_colonne() - 1) {
				break;
			} else {
				l.remove(this);
				f.update_element(y, x, l, null);
				this.x += 1;
				this.f.set_element(y, x, this, null);
				break;
			}
		case Direction.W:
			if (x == 0) {
				break;
			} else {
				l.remove(this);
				f.update_element(y, x, l, null);
				this.x -= 1;
				this.f.set_element(y, x, this, null);
				break;
			}
		default:
			break;
		}
	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turn(int dir) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub

	}

	public void setOrientation(int or) {
		this.Orientation = or;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
