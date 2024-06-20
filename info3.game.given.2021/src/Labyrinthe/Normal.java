package Labyrinthe;

import toolkit.Categorie;

public class Normal extends Mur {	
	public Normal(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.O;
		this.team = 4;
		layer = 2;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Normal(ligne,colonne);
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
