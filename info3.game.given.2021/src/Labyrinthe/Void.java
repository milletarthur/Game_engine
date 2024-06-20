package Labyrinthe;

import toolkit.Categorie;

public class Void extends Entity {
	

	public Void(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.V;
		this.team = 6;
		this.layer = 1;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Void(ligne,colonne);
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
		// TODO Auto-generated method stub
		return 0;
	}

}
