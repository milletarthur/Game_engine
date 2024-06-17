package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import toolkit.Categorie;
import toolkit.Direction;

public class Cell implements ICondition {
	private Field f;
	private Direction dir;
	private Categorie cat;

	public Cell(Field f, Direction dir, Categorie cat) {
		this.f = f;
		this.dir = dir;
		this.cat = cat;
	}

	@Override
	public boolean eval(Entity e) {
//		return f.cell(e,dir,cat);
		return true;
	}

	@Override
	public void setDir(Direction dir) {
		this.dir = dir;
	}

	@Override
	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	@Override
	public Categorie getCategorie() {
		return cat;
	}

	@Override
	public Direction getDirection() {
		return dir;
	}

}
