package Labyrinthe;

import toolkit.Categorie;

public class Arc extends Entity {
	
	boolean FlecheTrans;

	public Arc(int ligne, int colonne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
		FlecheTrans = false;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Arc(colonne, ligne);

	}

	@Override
	public void pop() {
		explode();
	}

	@Override
	public void wizz() {
		FlecheTrans = true;
	}

	@Override
	public int hit() {
		if(FlecheTrans) {
			FlecheTrans = false;
			return -7;
		}
		return -1;
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

}
