package Labyrinthe;

import toolkit.Categorie;

public class Pioche extends Entity {
	
	public Pioche(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Pioche(ligne,colonne);
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
		return -2;
	}

}
