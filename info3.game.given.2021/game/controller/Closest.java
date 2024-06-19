package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import toolkit.Direction;

public class Closest implements ICondition {

	private Field terrain;
	private int direction;
	private int categorie;
	private int distance_vision = 3;
	
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
		int i = e.y();
		int j = e.x();
		switch(e.direction()) {
		case Direction.N:
			for(int k=0; k<=distance_vision; k++) {
				for(int j_min=i; j_min<i+2*k; j_min++) {
					if(terrain.cell(e, Direction.H, categorie)) {
						return true;
					}
				}
				i--;
			}
			return false;
		case Direction.S:
			for(int k=0; k<=distance_vision; k++) {
				for(int j_min=i; j_min<i+2*k; j_min++) {
					if(terrain.cell(e, Direction.H, categorie)) {
						return true;
					}
				}
				i++;
			}
			return false;
		case Direction.E:
			for(int k=0; k<=distance_vision; k++) {
				for(int i_min=j; i_min<j+2*k; i_min++) {
					if(terrain.cell(e, Direction.H, categorie)) {
						return true;
					}
				}
				j++;
			}
			return false;
		case Direction.W:
			for(int k=0; k<=distance_vision; k++) {
				for(int i_min=j; i_min<j+2*k; i_min++) {
					if(terrain.cell(e, Direction.H, categorie)) {
						return true;
					}
				}
				j--;
			}
			return false;
		case Direction.NE:
			for(int m=i; m>=i-distance_vision; m--) {
				for(int n=j; n<=j+distance_vision; j++) {
					if(terrain.cell(e, Direction.H, categorie)) {
						return true;
					}
				}
			}
			return false;
		case Direction.NW:
			for(int m=i; m>=i-distance_vision; m--) {
				for(int n=j; n>=j-distance_vision; j--) {
					if(terrain.cell(e, Direction.H, categorie)) {
						return true;
					}
				}
			}
			return false;
		case Direction.SE:
			for(int m=i; m<=i+distance_vision; m++) {
				for(int n=j; n<=j+distance_vision; j++) {
					if(terrain.cell(e, Direction.H, categorie)) {
						return true;
					}
				}
			}
			return false;
		case Direction.SW:
			for(int m=i; m<=i+distance_vision; m++) {
				for(int n=j; n>=j-distance_vision; j--) {
					if(terrain.cell(e, Direction.H, categorie)) {
						return true;
					}
				}
			}
			return false;
		default:
			return false;
		}
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

}
