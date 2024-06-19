package Labyrinthe;

import toolkit.Categorie;

public class Epee extends Entity {
	
	public Epee(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}
	
	@Override
	public Entity egg(int x, int y) {
		return new Epee(x,y);
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
		return 3;
	}

}
