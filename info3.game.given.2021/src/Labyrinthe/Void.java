package Labyrinthe;

import toolkit.Categorie;

public class Void extends Entity {
	

	public Void(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.V;
		this.team = 6;
		this.layer = 1;
	}

	@Override
	public Entity egg(int x, int y) {
		return new Void(x,y);
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
