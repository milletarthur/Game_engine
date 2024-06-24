package Labyrinthe;

import toolkit.Categorie;

public class Porte extends Entity {
	private boolean isOpen;
	
	public Porte(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.O;
		this.team = 6;
		layer = 2;
		isOpen = false;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Porte(ligne, colonne);
	}

	@Override
	public void pop() {
		isOpen = true;
		this.category = Categorie.V;
	}

	@Override
	public void wizz() {
		isOpen = false;
		this.category = Categorie.O;
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean isOpen() {
		return isOpen;
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}
}
