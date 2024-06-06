package Model_Snake;

import java.util.Iterator;
import java.util.LinkedList;

import toolkit.Direction;

public class Snake extends Entity {
	LinkedList<Queue> queue = new LinkedList<Queue>();
	int length = 1;

	public Snake(int x, int y, int team, int category, Field f) {
		this.x = x;
		this.y = y;
		this.category = category;
		this.team = team;
		this.f = f;
	}

	public void grow() {
		Queue last = queue.getLast();
		Queue q = new Queue(last.x(), last.y(), this.team, this.category, this.f);;
		queue.addLast(q);
		length++;
	}

	public void move() {
		int a = this.x;
		int o = this.y;
		int a1, o1;
		switch (Orientation) {
		case Direction.N:
			this.y -= 1;
			break;
		case Direction.S:
			this.y += 1;
			break;
		case Direction.E:
			this.x += 1;
			break;
		case Direction.W:
			this.x -= 1;
			break;
		default:
			break;
		}
		Iterator<Queue> i = queue.iterator();
		Queue q;
		while (i.hasNext()) {
			q = i.next();
			a1 = q.x();
			o1 = q.y();
			q.move(a, o);

			a = a1;
			o = o1;

		}
	}

	public void turn(int dir) {
		int rv;
		switch (Orientation) {
		case Direction.N:
			switch (dir) {
			case Direction.L:
				rv = Direction.W;
				break;
			case Direction.R:
				rv = Direction.E;
				break;
			case Direction.B:
				rv = Direction.S;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		case Direction.S:
			switch (dir) {
			case Direction.L:
				rv = Direction.E;
				break;
			case Direction.R:
				rv = Direction.W;
				break;
			case Direction.B:
				rv = Direction.N;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		case Direction.E:
			switch (dir) {
			case Direction.L:
				rv = Direction.N;
				break;
			case Direction.R:
				rv = Direction.S;
				break;
			case Direction.B:
				rv = Direction.W;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		case Direction.W:
			switch (dir) {
			case Direction.L:
				rv = Direction.S;
				break;
			case Direction.R:
				rv = Direction.N;
				break;
			case Direction.B:
				rv = Direction.E;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		default:
			rv = this.Orientation;
			break;
		}
		this.Orientation = rv;
	}

	public int length() {
		return length;
	}

	public void pick() {
		int alea = (int) Math.random();
		if (alea % 2 == 0) {
			grow();
		} else {
			egg(x + 2, y + 2); // les coordonnées sont arbitraires
		}
	}

	@Override
	public void egg(int x, int y) {
		new Snake(x, y, this.team, this.category, this.f);
	}
}
