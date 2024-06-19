package Labyrinthe;

import toolkit.Categorie;

public class Fleche extends Entity {
	
	private boolean transpercant;
	
	public Fleche(int colonne, int ligne, int dir, boolean trans) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.M;
		this.team = 5;
		this.Orientation = dir;
		layer = 3;
		transpercant = trans;
	}
	
	public Fleche(int colonne, int ligne, int dir) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.M;
		this.team = 5;
		this.Orientation = dir;
		layer = 3;
		transpercant = false;
	}
	
	@Override
	public Entity egg(int colonne, int ligne) {
		return new Fleche(colonne,ligne,Orientation);
	}

	@Override
	public int hit() {
		return 3;
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub
		
	}
	
	public void setTrans(boolean value) {
		transpercant = value;
	}
	
	public boolean getTrans() {
		return transpercant;
	}
	
}
