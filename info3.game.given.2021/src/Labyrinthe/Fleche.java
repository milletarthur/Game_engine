package Labyrinthe;

public class Fleche extends Entity {
	
	public Fleche(int x, int y, int team, int category, int dir) {
		this.x = x;
		this.y = y;
		this.category = category;
		this.team = team;
		this.Orientation = dir;
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
	public void pick() {
		// TODO Auto-generated method stub
		
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
	public void explode() {
		// TODO Auto-generated method stub
		
	}
	
}
