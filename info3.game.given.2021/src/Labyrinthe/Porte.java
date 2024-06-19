package Labyrinthe;

import toolkit.Categorie;

public class Porte extends Entity {
	
	public Porte(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.G;
		this.team = 6;
		layer = 2;
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
		// TODO Auto-generated method stub
		return 0;
	}
}
