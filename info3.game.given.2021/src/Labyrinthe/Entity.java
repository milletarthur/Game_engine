package Labyrinthe;

import toolkit.Direction;

public abstract class Entity {
	
	protected Inventory inventory;
	
	protected Entity picked;

	protected int x;
	protected int y;
	
	protected int vie = 5;

	protected int Orientation = Direction.N;

	protected boolean Valid = true;

	protected int team;

	protected int category;
	
	protected int layer;

	public abstract Entity egg(int x, int y);

	public void move() {
		switch (Orientation) {
		case Direction.N:
			y--;
			break;
		case Direction.S:
			y++;
			break;
		case Direction.E:
			x++;
			break;
		case Direction.W:
			x--;
			break;
		default:
			break;
		}
	}
	
	abstract public int hit();
	
	public boolean pick(Entity e) {
		if(picked == null) {
			picked = e;
			return true;
		}
		return false;
	}

	public void turn(int dir) {
		// positions absolues SANS prendre en compte NE NW SE SW
		if(1 <= dir && dir <= 4) {
			Orientation = dir;
		} else {
			// positions relatives
			switch(dir) {
			case Direction.L:
				switch(Orientation) {
				case Direction.N:
					Orientation = Direction.W;
					break;
				case Direction.E:
					Orientation = Direction.N;
					break;
				case Direction.S:
					Orientation = Direction.E;
					break;
				case Direction.W:
					Orientation = Direction.S;
					break;
				default:
					break;
				}
			case Direction.R:
				switch(Orientation) {
				case Direction.N:
					Orientation = Direction.E;
					break;
				case Direction.E:
					Orientation = Direction.S;
					break;
				case Direction.S:
					Orientation = Direction.W;
					break;
				case Direction.W:
					Orientation = Direction.N;
					break;
				default:
					break;
				}
			case Direction.B:
				switch(Orientation) {
				case Direction.N:
					Orientation = Direction.S;
					break;
				case Direction.E:
					Orientation = Direction.W;
					break;
				case Direction.S:
					Orientation = Direction.N;
					break;
				case Direction.W:
					Orientation = Direction.E;
					break;
				default:
					break;
				}
			default:
				break;
			}
		}
	}

	abstract public void pop();

	abstract public void wizz();

	abstract public void explode();
	
	public void power(int vie) {
		this.vie += vie;
	}
	
	public void store() {
		inventory.add(picked);
		picked = null;
	}
	
	public void get() {
		return;
	}
	
	public void throw_() {
		
	}
	
	public void wait_() {
		
	}
	
	public void closest() {
		
	}

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
	
	public int layer() {
		return this.layer;
	}
	
	public int team() {
		return this.team;
	}
	
	public Entity picked() {
		return picked;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public int getVie() {
		return vie;
	}
}
