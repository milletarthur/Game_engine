package Labyrinthe;

import Automates.State;
import toolkit.Direction;

public abstract class Entity {

	protected Inventory inventory = new Inventory();

	protected int time = 0;

	protected int distance_vision = 3;

	protected Entity picked;

	protected int ligne;
	protected int colonne;
	
	public void set_ligne( int l ) {
		this.ligne = l ;
	}
	
	public void set_colonne( int c ) {
		this.colonne = c ; 
	}

	protected int vie = 5;

	protected int Orientation = Direction.N;

	protected boolean Valid = true;

	protected int team;

	protected int category;

	protected int layer;
	
	protected boolean selected = false;
	
	protected State current;
	
	public void setCurrent(State current) {
		this.current = current;
	}
	
	public State getCurrent() {
		return this.current;
	}

	public abstract Entity egg(int x, int y);

	public void move() {
		switch (Orientation) {
		case Direction.N:
			ligne--;
			break;
		case Direction.S:
			ligne++;
			break;
		case Direction.E:
			colonne++;
			break;
		case Direction.W:
			colonne--;
			break;
		default:
			break;
		}
	}

	abstract public int hit();

	public boolean pick(Entity e) {
		if (picked == null) {
			picked = e;
			return true;
		}
		return false;
	}
	
	public void resetpick() {
		picked = null;
	}

	public void turn(int dir) {
		if (1 <= dir && dir <= 8) {
			Orientation = dir;
		} else {
			// positions relatives
			switch (dir) {
			case Direction.L:
				switch (Orientation) {
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
				switch (Orientation) {
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
				switch (Orientation) {
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

	public void explode() {
		kill();
	}

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
		Entity e = picked;
		if (e instanceof Bombe)
			e.power(1);
		picked = null;
	}

	public void wait_(int value) {
		time += value;
	}
	
	public abstract void jump();

	public void kill() {
		this.Valid = false;
	}

	public boolean valid() {
		return this.Valid;
	}

	public int ligne() {
		return ligne;
	}

	public int colonne() {
		return colonne;
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
	
	public void setpicked(Entity e) {
		picked = e;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public int getVie() {
		return vie;
	}

	public int getTime() {
		return time;
	}
	
	public void setTeam(int team) {
		this.team = team;
	}
}
