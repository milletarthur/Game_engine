package Labyrinthe;

import toolkit.Categorie;

public class Potion extends Entity {
	
	boolean poison;
	
	public Potion(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Potion(ligne,colonne);
	}

	@Override
	public void pop() {
		poison = false;
	}

	@Override
	public void wizz() {
		poison = true;
	}
	
	public boolean poisoned() {
		return poison;
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return -4;
	}

}
