package Model_Snake;

import java.util.Iterator;
import java.util.LinkedList;
import toolkit.Direction;

public class Snake extends Entity {
	LinkedList<Queue> queue = new LinkedList<Queue>();
	int length = 1;

	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void grow() {
		Queue last = queue.getLast();
		Queue q = new Queue(last.x(), last.y());
		queue.addLast(q);
		length++;
	}

	public void move() {
		int a = this.x;
		int o = this.y;
		int a1,o1;
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
		case NORTH:
			switch(dir) {
			case Direction.L:
				rv = WEST;
				break;
			case Direction.R:
				rv = EAST;
				break;
			case Direction.B:
				rv = SOUTH;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		case SOUTH:
			switch(dir) {
			case Direction.L:
				rv = EAST;
				break;
			case Direction.R:
				rv = WEST;
				break;
			case Direction.B:
				rv = NORTH;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		case EAST:
			switch(dir) {
			case Direction.L:
				rv = NORTH;
				break;
			case Direction.R:
				rv = SOUTH;
				break;
			case Direction.B:
				rv = WEST;
				break;
			default:
				rv = this.Orientation;
				break;
			}
		case WEST:
			switch(dir) {
			case Direction.L:
				rv = SOUTH;
				break;
			case Direction.R:
				rv = NORTH;
				break;
			case Direction.B:
				rv = EAST;
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
	
	void pick() {
		int alea = (int) Math.random();
		if(alea % 2 == 0) {
			grow();
		} else {
			int x2 = Math.random(10);
			egg(x+2,y+2); //les coordonnées sont arbitraires 
		}
	}

	public int length() {
		return length;
	}

	@Override
	public void egg(int x, int y) {
		new Snake(x, y);
	}
}
