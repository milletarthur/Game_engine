package Model_Snake;

public class Queue extends Entity {

	public Queue(int x, int y, int team, int category, Field f) {
		this.x = x;
		this.y = y;
		this.category = category;
		this.team = team;
		this.f = f;
	}

	@Override
	void egg(int x, int y) {
		new Queue(x, y, this.team, this.category, this.f);
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
