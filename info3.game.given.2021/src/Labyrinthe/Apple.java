package Labyrinthe;

import toolkit.Categorie;

public class Apple extends Entity {

	boolean poison;

	public Apple(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}

	@Override
	public Entity egg(int colonne, int ligne) {
		return new Apple(colonne, ligne);
	}

	@Override
	public void pop() {
		poison = false;
	}

	@Override
	public void wizz() {
		poison = true;
	}

	@Override
	public int hit() {
		return -3;
	}

	public boolean poisoned() {
		return poison;
	}

}
