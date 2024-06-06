package Model_Snake;

import java.util.LinkedList;

public class Snake extends Entity {
	LinkedList<Queue> queue = new LinkedList();
	int length = 1;

	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void grow() {
		Queue last = queue.getLast();
		Queue q = new Queue(last.x(), last.y(), length);
		queue.addLast(q);
		length++;
	}

	public void move() {
		switch (Orientation) {
		case NORTH:
			this.y -= 1;
			break;
		case SOUTH:
			this.y += 1;
			break;
		case EAST:
			this.x += 1;
			break;
		case WEST:
			this.x -= 1;
			break;
		default:
			break;
		}
	}

	public void turn(int dir) {
		int rv;
		switch (Orientation) {
		case NORTH:
			switch(dir) {
			case Dorection.L:
				rv = WEST;
				break;
			case Dorection.R:
				rv = EAST;
				break;
			case Dorection.B:
				rv = SOUTH;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		case SOUTH:
			switch(dir) {
			case Dorection.L:
				rv = EAST;
				break;
			case Dorection.R:
				rv = WEST;
				break;
			case Dorection.B:
				rv = NORTH;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		case EAST:
			switch(dir) {
			case Dorection.L:
				rv = NORTH;
				break;
			case Dorection.R:
				rv = SOUTH;
				break;
			case Dorection.B:
				rv = WEST;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		case WEST:
			switch(dir) {
			case Dorection.L:
				rv = SOUTH;
				break;
			case Dorection.R:
				rv = NORTH;
				break;
			case Dorection.B:
				rv = EAST;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		default:
			break;
		}
		this.Orientation = rv;
	}

	public int length() {
		return length;
	}

	@Override
	public void egg(int x, int y) {
		new Snake(x, y);
	}
}
