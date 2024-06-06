package Model_Snake;

public class Obstacle extends Entity {

	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	void egg(int x, int y) {
		new Obstacle(x, y);

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
