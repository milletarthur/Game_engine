package Labyrinthe;

import toolkit.Categorie;

public class Invisible extends Mur {
	public Invisible(int ligne, int colonne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.G;
		this.team = 4;
		this.layer = 4;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Invisible(colonne, ligne);
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

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

}
