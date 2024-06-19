package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import toolkit.Categorie;
import toolkit.Direction;

public class Cell implements ICondition {
	private Field f;
	private int dir;
	private int cat;
	
	public Cell(Field f) {
		this.f = f;
	}

	public Cell(Field f, int dir, int cat) {
		this.f = f;
		this.dir = dir;
		this.cat = cat;
	}

	@Override
	public boolean eval(Entity e) {
		return f.cell(e,dir,cat);
	}

	@Override
	public void setDir(int dir) {
		this.dir = dir;
	}

	@Override
	public void setCat(int cat) {
		this.cat = cat;
	}

	@Override
	public int getCategorie() {
		return cat;
	}

	@Override
	public int getDirection() {
		return dir;
	}

}
