package controller;

import Model_Snake.Entity;
import Model_Snake.Field;
import toolkit.ICondition;

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
		f.cell(e,dir,cat);
	}

}
