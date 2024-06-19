package Labyrinthe;

import toolkit.Categorie;

public class Arc extends Entity {
	
	public Arc(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}

	@Override
	public Entity egg(int x, int y) {
		return new Arc(x,y);

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
		return -1;
	}
	
}
