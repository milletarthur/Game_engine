package Labyrinthe;

import toolkit.Categorie;

public class Potion extends Entity {
	
	boolean poison;
	
	public Potion(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}

	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
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
