package Labyrinthe;

public class Porte extends Entity {
	
	private int layer = 2;

	public Porte(int x, int y, int team, int category) {
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
	public void move() {
		super.move();
	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turn(int dir) {
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
	public void power(int vie) {
		// TODO Auto-generated method stub

	}
}
