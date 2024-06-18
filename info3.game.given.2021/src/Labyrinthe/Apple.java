package Labyrinthe;

import toolkit.Categorie;

public class Apple extends Entity {
	
	boolean poison;

	public Apple(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}

	@Override
	public Entity egg(int x, int y) {
		return new Apple(x, y);
	}

	@Override
	public void pop() {
		poison = false;
	}

	@Override
	public void wizz() {
		poison = true;
	}

	@Override
	public int hit() {
		return -3;
	}
	
	public boolean poisoned() {
		return poison;
	}

}
