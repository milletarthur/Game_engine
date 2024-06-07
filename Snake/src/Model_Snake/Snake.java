package Model_Snake;

import java.util.Iterator;
import java.util.LinkedList;

import toolkit.Categorie;
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
		Queue q = null;
		int last_x = this.x;
		int last_y = this.y;
		if (queue.size() > 0) {
			Queue last = queue.getLast();
			last_x = last.x();
			last_y = last.y();
		} 
		q = new Queue(last_x, last_y, this.team, Categorie.T, this.f);
		queue.addLast(q);
		f.set_elementAt(q);
		length++;
	}

	public void move() {
		int a = this.x;
		int o = this.y;
		int a1, o1;
		int new_x = this.x;
		int new_y = this.y;
		switch (Orientation) {
		case Direction.N:
			new_y--;
			this.y -= 1;
			break;
		case Direction.S:
			new_y++;
			this.y += 1;
			break;
		case Direction.E:
			new_x++;
			this.x += 1;
			break;
		case Direction.W:
			new_x--;
			this.x -= 1;
			break;
		default:
			break;
		}
		f.set_elementAt(this);
		f.set_elementAt(new Void(a, o, 0, Categorie.V, this.f));
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
			break;
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
			break;
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
			break;
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
			break;
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
		Snake s = new Snake(x, y, this.team, this.category, this.f);
		f.set_elementAt(s);
	}
}
