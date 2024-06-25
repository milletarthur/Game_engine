package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import Labyrinthe.Field;

public class Key implements ICondition {
	
	private Field f;
	private int key;
	private KeyPressed k;
	
	public Key(Field f, int key, KeyPressed k) {
		this.f = f;
		this.key = key;
		this.k = k;
	}
	
	public Key(Field f, KeyPressed k) {
		this.f = f;
		this.k = k;
	}
	
	public Key(Field f, int key) {
		this.f = f;
		this.key = key;
	}
	
	public Key(Field f) {
		this.f = f;
	}

	@Override
	public boolean eval(Entity e) {
		
		if (k.ispressed(key)) {
			System.out.print("check de key : ");
			System.out.print(key);
			System.out.println(" pressé");
			return true;
		} else {
//			System.out.println(" non pressé");
			return false;
		}
	}

	@Override
	public void setDir(int dir) {
	}

	@Override
	public void setCat(int cat) {
	}

	@Override
	public int getCategorie() {
		return 0;
	}

	@Override
	public int getDirection() {
		return 0;
	}
	
	@Override
	public String toString() {
		String s = "Key(";
		s += Integer.toString(key);
		s += ")";
		return s;
		}
}
