package Labyrinthe;

import toolkit.Categorie;

public class Cassable extends Mur {
	
	public Cassable(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.G;
		this.team = 4;
		layer = 3;
	}

	@Override
	public Entity egg(int x, int y) {
		return new Cassable(x,y);
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
		// TODO Auto-generated method stub
		return 0;
	}

}
