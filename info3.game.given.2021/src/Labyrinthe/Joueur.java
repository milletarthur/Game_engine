package Labyrinthe;

import toolkit.Categorie;
import toolkit.Direction;

/*
 * Classe Joueur
 * 
 * x et y sont les coordonnées de ce joueur dans la matrice
 */
public class Joueur extends Entity {

	public Joueur(int x, int y, int team) {
		this.x = x;
		this.y = y;
		super.vie = 10;
		if (team == 1)
			this.category = Categorie.Arobase;
		else
			this.category = Categorie.Diese;
		this.team = team;
		layer = 3;

	}

	@Override
	public Entity egg(int x, int y) {

		return null;
	}

	public Entity picked() {
		return picked;
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz() {
		super.turn(Direction.B);
	}

	public int hit() {
		if (picked != null)
			return 1;
		return picked.hit();
	}

	@Override
	public void get() {
		switch (team()) {
		case 1:
			picked = inventory.popJ1();
			break;
		case 2:
			picked = inventory.popJ2();
			break;
		default:
			break;
		}
	}
}
