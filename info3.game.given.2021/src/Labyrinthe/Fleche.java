package Labyrinthe;

import toolkit.Categorie;

public class Fleche extends Entity {
	
	private boolean transpercant;
	
	public Fleche(int x, int y, int dir, boolean trans) {
		this.x = x;
		this.y = y;
		this.category = Categorie.M;
		this.team = 5;
		this.Orientation = dir;
		layer = 3;
		transpercant = trans;
	}
	
	public Fleche(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.category = Categorie.M;
		this.team = 5;
		this.Orientation = dir;
		layer = 3;
		transpercant = false;
	}
	
	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
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
	
	public void setTrans(boolean value) {
		transpercant = value;
	}
	
	public boolean getTrans() {
		return transpercant;
	}
	
}
