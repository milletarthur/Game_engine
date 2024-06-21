package Labyrinthe;

import toolkit.Categorie;

public class Bombe extends Entity {
	private boolean exploded;
	
	public Bombe(int ligne, int colonne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
		exploded = false;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Bombe(ligne, colonne);
	}
	
	public void changeState() {
		exploded = true;
	}
	
	public boolean exploded() {
		return exploded;
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

	@Override
	public void jump() {
		// TODO Auto-generated method stub

	}

}
