package Labyrinthe;

import toolkit.Categorie;

public class Cassable extends Mur {
	private boolean exploded;
	
	public Cassable(int ligne, int colonne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.O;
		this.team = 4;
		layer = 3;
		exploded = false;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Cassable(ligne, colonne);
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

}
