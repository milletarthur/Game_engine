package Labyrinthe;

import toolkit.Categorie;

public class Squelette extends Entity {
	
	public Squelette(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.A;
		this.team = 0;
		layer = 3;
	}

	@Override
	public Entity egg(int x, int y) {
		return new Squelette(x,y);
	}

	@Override
	public void pop() {
		
	}

	@Override
	public void wizz() {
		team = 0;
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 2;
	}

}
