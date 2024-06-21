package Labyrinthe;

import toolkit.Categorie;

public class WizzEntity extends Entity {
	
	public WizzEntity(int ligne, int colonne, int team) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.J;
		this.team = team;
		layer = -1;
	}

	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 0;
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
	public void jump() {
		// TODO Auto-generated method stub
		
	}

}
