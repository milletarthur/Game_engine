package Labyrinthe;

public class Apple extends Entity {

	public Apple(int x, int y) {
		this.x = x;
		this.y = y;

	}

	@Override
	void egg(int x, int y) {
		new Apple(x, y);
	}

	@Override
	public void move() {
	}

	@Override
	public void pick() {
	}

	@Override
	public void turn(int dir) {
	}

	@Override
	public void pop() {
	}

	@Override
	public void wizz() {
	}

	@Override
	public void explode() {
	}

}
