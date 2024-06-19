package Labyrinthe;

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

	public void move() {
		switch (Orientation) {
		case Direction.N:
			y = Math.max(0, y - 1);
			break;
		case Direction.S:
			y = Math.min(y + 1, f.get_ligne() - 1);
			break;
		case Direction.E:
			x = Math.min(x + 1, f.get_colonne() - 1);
			break;
		case Direction.W:
			x = Math.max(0, x - 1);
			break;
		default:
			break;
		}
	}

	abstract public void pick();

	abstract public void turn(int dir);

	abstract public void pop();

	abstract public void wizz();

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

	/*
	 * public int direction() { return this.Orientation; }
	 */

	public int category() {
		return this.category;
	}

}
