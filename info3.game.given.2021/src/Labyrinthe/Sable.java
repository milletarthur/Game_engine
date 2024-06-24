package Labyrinthe;

import toolkit.Categorie;

public class Sable extends Entity {
	
	private boolean activate;
	
	public Sable(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.D;
		this.team = 5;
		this.activate = false;
		this.vie = 25;
		layer = 2;
	}
	
	public Sable(int l, int c, boolean act) {
		this.ligne = l;
		this.colonne = c;
		this.activate = act;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Sable(ligne, colonne, activate);
	}

	@Override
	public void pop() {
	}

	@Override
	public void wizz() {
		explode();
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void jump() {
		activate = true;		
	}
	
	public boolean IsActivate() {
		return activate;
	}
	
	public void setActivate(boolean act) {
		activate = act;
	}

}
