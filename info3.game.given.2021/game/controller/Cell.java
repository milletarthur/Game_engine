package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Cell implements ICondition {
	private Field f;
	private int dir;
	private int cat;
	
	public Cell(Field f, int dir, int cat) {
		this.f = f;
		this.dir = dir;
		this.cat = cat;
	}

	@Override
	public boolean eval(Entity e) {
//		return f.cell(e,dir,cat);
		return true;
	}

}
