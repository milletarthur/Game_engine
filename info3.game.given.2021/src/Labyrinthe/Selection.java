package Labyrinthe;

import toolkit.Categorie;

public class Selection extends Entity {

	public Selection(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.T;
		this.team = 8;
		layer = 5;
	}
	
	@Override
	public Entity egg(int ligne, int colonne) {
		return new Selection(ligne,colonne);
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}
}
