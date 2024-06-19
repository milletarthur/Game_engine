package Labyrinthe;

import toolkit.Categorie;

public class Epee extends Entity {
	
	private boolean hitCircle;
	
	public Epee(int colonne, int ligne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
		hitCircle = false;
	}
	
	@Override
	public Entity egg(int colonne, int ligne) {
		return new Epee(colonne,ligne);
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
		if(hitCircle)
			return -6;
		return 3;
	}
	
	public boolean getHitCircle() {
		return hitCircle;
	}
	
	public void setHitCircle(boolean bool) {
		hitCircle = bool;
	}
}
