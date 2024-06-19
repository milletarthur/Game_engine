package Labyrinthe;

import toolkit.Categorie;

public class Cassable extends Mur {
	
	public Cassable(int colonne, int ligne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.G;
		this.team = 4;
		layer = 3;
	}

	@Override
	public Entity egg(int colonne, int ligne) {
		return new Cassable(colonne,ligne);
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

}
