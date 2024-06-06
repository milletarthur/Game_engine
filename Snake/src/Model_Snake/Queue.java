package Model_Snake;

public class Queue extends Entity {

	public Queue(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	void egg(int x, int y) {
		new Queue(x, y);
	}

	void move(int x, int y) {
		this.x = x;
		this.y = y;
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
