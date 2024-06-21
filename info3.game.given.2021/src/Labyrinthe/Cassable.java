package Labyrinthe;

import toolkit.Categorie;

public class Cassable extends Mur {
	public Cassable(int ligne, int colonne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.G;
		this.team = 4;
		layer = 3;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Cassable(ligne, colonne);
	}

	@Override
	public void pop() {
		explode();
	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 0;
	}

}
