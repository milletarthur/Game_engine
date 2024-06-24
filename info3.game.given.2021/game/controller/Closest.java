package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Lave;
import toolkit.Direction;

public class Closest implements ICondition {

	private Field terrain;
	private int direction;
	private int categorie;
	private int distance_vision = 3;
	private int[][] cases = new int[distance_vision+1][distance_vision+1];

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

	@Override
	public boolean eval(Entity e) {
		
		return false;
	}

	@Override
	public int getCategorie() {
		return categorie;
	}

	@Override
	public int getDirection() {
		return direction;
	}

	public void setCatgorie(int cat) {
		categorie = cat;
	}

	public void setDirection(int dir) {
		direction = dir;
	}

	public int getDistanceVision() {
		return distance_vision;
	}

	public void setDistanceVision(int value) {
		distance_vision = value;
	}

	@Override
	public String toString() {
		String s = "Closest(";
		s += Integer.toString(direction);
		s += ";";
		s += Integer.toString(categorie);
		s += ")";
		return s;
	}

}
