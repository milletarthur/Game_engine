package Labyrinthe;

import toolkit.Categorie;

public class Epee extends Entity {

	private boolean hitCircle;

	public Epee(int ligne, int colonne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Epee(ligne, colonne);
	}

	@Override
	public void pop() {
		explode();
	}

	@Override
	public void wizz() {
		hitCircle = true;
	}

	@Override
	public int hit() {
		if (hitCircle)
			return -6;
		return 3;
	}

	public boolean getHitCircle() {
		return hitCircle;
	}

	public void setHitCircle(boolean bool) {
		hitCircle = bool;
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}
}
