package Labyrinthe;

import toolkit.Categorie;

public class Lave extends Entity {
	
	public Lave(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.D;
		this.team = 3;
		layer = 0;
	}

	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
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
		return 20;
	}

}
