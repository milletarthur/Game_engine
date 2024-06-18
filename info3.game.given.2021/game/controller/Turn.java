package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Turn implements IAction {

	private int Direction;
	private Field terrain;
	
	public Turn(Field f) {
		terrain = f;
	}
	
	public Turn(Field terrain, int Direction) {
		this.Direction = Direction;
		this.terrain = terrain;
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
//		System.out.println("Turn");
		return;
	}
	
	public int getDirection() {
		return Direction;
	}
	
	public void setDirection(int dir) {
		Direction = dir;
	}
}
