package Labyrinthe;

import toolkit.Categorie;

public class Bombe extends Entity {
	
	private int time = 4;
	
	public Bombe(int colonne, int ligne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}
	
	@Override
	public Entity egg(int colonne, int ligne) {
		return new Bombe(colonne,ligne);
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
