package Labyrinthe;

import toolkit.Categorie;

public class Lave extends Entity {
	
	public Lave(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.D;
		this.team = 5;
		layer = 0;
	}

	@Override
	public Entity egg(int x, int y) {
		return new Lave(x,y);
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
