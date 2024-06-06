package Model_Snake;

import java.util.LinkedList;

public class Snake extends Entity {
	LinkedList queue = new LinkedList();
	int length = 0;

	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void grow() {
		throw new RuntimeException("Not implemented Yet !");
	}

	public void move() {
		throw new RuntimeException("Not implemented Yet !");
	}

	public void turn(int dir) {
		throw new RuntimeException("Not implemented Yet !");
	}

	public int length() {
		return length;
	}

	@Override
	public void egg(int x, int y) {
		new Snake(x, y);
	}
}
