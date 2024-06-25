package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;

public class Turn implements IAction {

	private TickListener tl;
	private int Direction;
	private Field terrain;

	public Turn(Field f, TickListener tl) {
		terrain = f;
		this.tl = tl;
	}

	public Turn(Field terrain, int Direction, TickListener tl) {
		this.Direction = Direction;
		this.terrain = terrain;
		this.tl = tl;
	}

	public void print() {
		if (Direction == -3)
			System.out.println("L");
		else
			System.out.println("R");
	}

	@Override
	public void exec(Entity e) {
		e.turn(this.Direction);
		if(e instanceof Joueur) {
			Entity pick = ((Joueur)e).picked();
			if(pick != null) {
				pick.turn(Direction);
			}
		}
		return;
	}

	public int getDirection() {
		return Direction;
	}

	public void setDirection(int dir) {
		Direction = dir;
	}

	@Override
	public String toString() {
		String s = "Turn";
		return s;
	}
}
