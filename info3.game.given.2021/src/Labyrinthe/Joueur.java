package Labyrinthe;

import toolkit.Direction;

/*
 * Classe Joueur
 * 
 * x et y sont les coordonnées de ce joueur dans la matrice
 */
public class Joueur extends Entity {

	public Joueur(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	void egg(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move() {
		switch (Orientation) {
		case Direction.N:
			this.y -= 1;
			break;
		case Direction.S:
			this.y += 1;
			break;
		case Direction.E:
			this.x += 1;
			break;
		case Direction.W:
			this.x -= 1;
			break;
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

}
