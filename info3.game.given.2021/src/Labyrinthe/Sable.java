package Labyrinthe;

import toolkit.Categorie;

public class Sable extends Entity {
	
	public Sable(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.D;
		this.team = 5;
		layer = 3;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Sable(ligne,colonne);
	}

	@Override
	public void pop() {
		
	}

	@Override
	public void wizz() {
		explode();
	}
	
	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 0;
	}

}
