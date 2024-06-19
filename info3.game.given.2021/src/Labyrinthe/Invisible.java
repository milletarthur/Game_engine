package Labyrinthe;

import toolkit.Categorie;

public class Invisible extends Mur {
	
	public Invisible(int colonne, int ligne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.J;
		this.team = 4;
		this.layer = 4;
	}

	@Override
	public Entity egg(int colonne, int ligne) {
		return new Invisible(colonne,ligne);
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
		return 0;
	}

}
