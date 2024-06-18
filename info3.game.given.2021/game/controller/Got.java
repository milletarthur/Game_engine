package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Got implements ICondition {

	private Field f;
	private int value;
	
	public Got(Field f, int value) {
		this.f = f;
		this.value = value;
	}
	
	public Got(Field f) {
		this.f = f;
	}

	@Override
	public boolean eval(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDir(int dir) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCat(int cat) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCategorie() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDirection() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setValue(int v) {
		value = v;
	}
	
	public int getvalue() {
		return value;
	}
}
