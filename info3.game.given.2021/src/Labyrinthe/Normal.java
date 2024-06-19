package Labyrinthe;

import toolkit.Categorie;

public class Normal extends Mur {
	
	public Normal(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.O;
		this.team = 4;
		layer = 2;
	}

	@Override
	public Entity egg(int x, int y) {
		return new Normal(x,y);
	}

	@Override
	public void pop() {
		explode();
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
