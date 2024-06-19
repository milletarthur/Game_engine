package Labyrinthe;

import toolkit.Categorie;

public class Mine extends Entity {
	
	private int time = 4;
	
	public Mine(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.D;
		this.team = 5;
		layer = 2;
	}

	@Override
	public Entity egg(int x, int y) {
		return new Mine(x,y);
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
		return 5;
	}

}
