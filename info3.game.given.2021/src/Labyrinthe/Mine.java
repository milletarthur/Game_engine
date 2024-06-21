package Labyrinthe;

import toolkit.Categorie;

public class Mine extends Entity {
	public Mine(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.D;
		this.team = 5;
		layer = 2;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Mine(ligne, colonne);
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	@Override
	public int hit() {
		return 5;
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

}
