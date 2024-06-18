package Labyrinthe;

public class Lave extends Entity {
	
	private int layer = 0;

	public Lave(int x, int y, int team, int category) {
		this.x = x;
		this.y = y;
		this.category = category;
		this.team = team;
	}

	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public int hit() {
		return 20;
	}

}
