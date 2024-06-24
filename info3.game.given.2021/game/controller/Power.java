package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Power implements IAction {

	private Field terrain;
	private int vie = 0;
	private TickListener tl;

	public Power(Field f, int vie) {
		terrain = f;
		this.vie = vie;
	}

	public Power(Field f, TickListener tl) {
		terrain = f;
		this.tl = tl;
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	@Override
	public void exec(Entity e) {
		e.power(vie);
	}

	@Override
	public String toString() {
		String s = "Power";
		return s;
	}

}
