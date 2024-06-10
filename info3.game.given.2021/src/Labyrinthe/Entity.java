package Labyrinthe;

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
	
	abstract public void pop();
	
	abstract public void wizz();
	
	abstract public void wait();
	
	abstract public void explode();

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

}
