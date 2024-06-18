package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Key implements ICondition {
	
	private Field f;
	private int direction;
	private int categorie;
	private KeyBoard key;
	
	public Key(Field f, KeyBoard key) {
		this.f = f;
		this.key = key;
	}
	
	public Key(Field f, int dir, int cat){
		this.f = f;
		direction = dir;
		categorie = cat;
	}
	
	public Key(Field f) {
		this.f = f;
	}

	@Override
	public boolean eval(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDir(int dir) {
		direction = dir;
	}

	@Override
	public void setCat(int cat) {
		categorie = cat;
	}

	@Override
	public int getCategorie() {
		return categorie;
	}

	@Override
	public int getDirection() {
		return direction;
	}

}
