package Labyrinthe;

public class Apple extends Entity {
	
	boolean poison;
	private int layer = 2;

	public Apple(int x, int y, int team, int category) {
		this.x = x;
		this.y = y;
		this.category = category;
		this.team = team;
	}

	@Override
	public Entity egg(int x, int y) {
		return new Apple(x, y, this.team, this.category);
	}

	@Override
	public void move() {
		super.move();
	}

	@Override
	public void pick() {
	}

	@Override
	public void turn(int dir) {
	}

	@Override
	public void pop() {
		poison = false;
	}

	@Override
	public void wizz() {
		poison = true;
	}

	@Override
	public void explode() {
	}

	@Override
	public int hit() {
		return -3;
	}
	
	@Override
	public void power(int vie) {
		// TODO Auto-generated method stub
	}
	
	public boolean poisoned() {
		return poison;
	}

}
