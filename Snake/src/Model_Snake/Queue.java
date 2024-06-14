package Model_Snake;

import java.util.Iterator;

public class Queue extends Entity {
	private boolean newQueue = true;

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

	void move(int x, int y, int Direction) {
		if (!newQueue) {
			this.x = x;
			this.y = y;
			this.Orientation = Direction;
		} else {
			newQueue = false;
		}
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

	public void kill() {
		super.kill();
		x = -1;
		y = -1;
	}
}
