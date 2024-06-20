package Labyrinthe;

import toolkit.Categorie;

public class Bombe extends Entity {
	
	private int ticktime = 40;
	
	public Bombe(int ligne, int colonne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}
	
	@Override
	public Entity egg(int ligne, int colonne) {
		return new Bombe(ligne,colonne);
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
		return -5;
	}

}
