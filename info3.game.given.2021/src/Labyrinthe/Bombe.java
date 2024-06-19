package Labyrinthe;

import toolkit.Categorie;

public class Bombe extends Entity {
	
	private int time = 4;
	
	public Bombe(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
	}
	
	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
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
		return -5;
	}

}
