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
		Queue last = queue.getLast();
		if(last == null)
			last = new Queue(this.x, this.y, this.team, this.category, this.f);
		Queue q = new Queue(last.x(), last.y(), this.team, this.category, this.f);
		switch(this.Orientation) {
		case Direction.N:
			q = new Queue(last.x(), last.y()+1, this.team, this.category, this.f);
			break;
		case Direction.S:
			q = new Queue(last.x(), last.y()-1, this.team, this.category, this.f);
			break;
		case Direction.W:
			q = new Queue(last.x()+1, last.y(), this.team, this.category, this.f);
			break;
		case Direction.E:
			q = new Queue(last.x()-1, last.y(), this.team, this.category, this.f);
			break;
		}
		queue.addLast(q);
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
			int x2 = (int) Math.random() % this.f.get_ligne();
			int y2 = (int) Math.random() % this.f.get_colonne();
			while (!(this.f.elementAt(x2, y2) instanceof Void)) {
				x2 = (int) Math.random() % this.f.get_ligne();
				y2 = (int) Math.random() % this.f.get_colonne();
			}
			egg(x2, y2);
		}
	}

	@Override
	public void egg(int x, int y) {
		Snake s = new Snake(x, y, this.team, this.category, this.f);
	}
}
