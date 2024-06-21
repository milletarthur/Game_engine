package Labyrinthe;

import toolkit.Categorie;

public class Lave extends Entity {

	public Lave(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.D;
		this.team = 5;
		layer = 0;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Lave(ligne, colonne);
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
	public int hit() {
		return 20;
	}

}
