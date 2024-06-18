package controller;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Closest implements IAction {

	private Field terrain;
	private int direction;
	private int categorie;
	
	public Closest(Field f, int dir, int cat) {
		terrain = f;
		direction = dir;
		categorie = cat;
	}

	public Closest(Field f) {
		terrain = f;
	}
	
	public void setDir(int dir) {
		direction = dir;
	}
	
	public void setCat(int cat) {
		categorie = cat;
	}
	
	public int getDir() {
		return direction;
	}
	
	public int getCat() {
		return categorie;
	}

	@Override
	public void exec(Entity e) {
		e.closest();	
	}

}
