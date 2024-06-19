package Labyrinthe;

import toolkit.Categorie;

public class Selection extends Entity {

	public Selection(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.T;
		this.team = 8;
		layer = 5;
	}
	
	@Override
	public Entity egg(int x, int y) {
		return new Selection(x,y);
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
}
