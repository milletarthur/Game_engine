package Labyrinthe;

import toolkit.Categorie;

public class Fleche extends Entity {
	
	public Fleche(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.category = Categorie.M;
		this.team = 5;
		this.Orientation = dir;
		layer = 3;
	}
	
	@Override
	public Entity egg(int x, int y) {
		return new Fleche(x,y,Orientation);
	}

	@Override
	public int hit() {
		return 3;
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
