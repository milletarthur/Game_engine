package Labyrinthe;

import toolkit.Categorie;

public class Porte extends Entity {
	
	private boolean isOpen;
	
	public Porte(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.G;
		this.team = 6;
		layer = 2;
		isOpen = false;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Porte(ligne,colonne);
	}

	@Override
	public void pop() {
		isOpen = true;
	}

	@Override
	public void wizz() {
		isOpen = false;
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
}
