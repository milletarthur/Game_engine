package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Wait implements IAction {

	private Field terrain;
	private int time;
	private TickListener tl;

	public Wait(Field terrain, int time, TickListener tl) {
		this.terrain = terrain;
		this.time = time;
		this.tl = tl;
	}

	public Wait(Field terrain, TickListener tl) {
		this.terrain = terrain;
		this.tl = tl;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}

	@Override public void exec(Entity e) {
		e.wait_(time);
	}
	
	@Override
	public String toString() {
		String s = "Wait";
		return s;
	}

}
