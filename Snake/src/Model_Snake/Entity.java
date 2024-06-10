package Model_Snake;

import toolkit.Direction;
import toolkit.Pair;

public abstract class Entity {
	protected Field f;

	protected int x;
	protected int y;

	protected int Orientation = Direction.N;

	protected boolean Valid = true;

	protected int team;

	protected int category;

	abstract void egg(int x, int y);

	abstract public void move();

	abstract public void pick();

	abstract public void turn(int dir);

	public void kill() {
		this.Valid = false;
	}

	public boolean valid() {
		return this.Valid;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int direction() {
		return this.Orientation;
	}

	public int category() {
		return this.category;
	}

	public int team() {
		return this.team;
	}

	public Pair<Integer,Integer> case_devant(){
		int x = this.x;
		int y = this.y;
		Pair<Integer,Integer> rv = new Pair<Integer, Integer>(x,y);
		switch (this.Orientation) {
			case Direction.N:
				y--;
				rv.sety(y);
				break;
			case Direction.S:
				y++;
				rv.sety(y);
				break;
			case Direction.E:
				x++;
				rv.setx(x);
				break;
			case Direction.W:
				x--;
				rv.setx(x);
				break;
			default:
				return null;
			}
		return rv;
	}
	
	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}
}
