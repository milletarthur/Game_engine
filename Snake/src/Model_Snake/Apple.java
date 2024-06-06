package Model_Snake;

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
	void kill() {
		this.Valid = false;

	}

	@Override
	boolean valid() {
		return this.Valid;
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

}
