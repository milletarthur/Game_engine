package Labyrinthe;

public class Mine extends Entity {
	
	private int layer = 2;

	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pick() {
		super.move();
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

	@Override
	public int hit() {
		return 5;
	}

}
