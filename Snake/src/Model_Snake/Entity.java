package Model_Snake;

import toolkit.Direction;

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
}
