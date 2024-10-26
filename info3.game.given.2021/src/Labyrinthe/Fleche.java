package Labyrinthe;

import toolkit.Categorie;

public class Fleche extends Entity {

	private boolean transpercant;

	public Fleche(int ligne, int colonne, int dir, boolean trans) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.D;
		this.team = 5;
		this.Orientation = dir;
		transpercant = trans;
		if (transpercant)
			layer = 4;
		else
			layer = 3;
		vie = 0;
	}

	public Fleche(int ligne, int colonne, int dir) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.D;
		this.team = 5;
		this.Orientation = dir;
		layer = 3;
		transpercant = false;
		vie = 0;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Fleche(ligne, colonne, Orientation, transpercant);
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

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}
}
