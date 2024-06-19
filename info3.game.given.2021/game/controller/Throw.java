package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import toolkit.Direction;

public class Throw implements IAction{
	
private Field terrain;
	
	public Throw (Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		Entity picked = e.picked();
		if(picked != null) {
			e.throw_();
			switch(e.direction()) {
			case Direction.N:
				terrain.add(picked, e.ligne(), e.colonne()-1);
				break;
			case Direction.S:
				terrain.add(picked, e.ligne(), e.colonne()+1);
				break;
			case Direction.E:
				terrain.add(picked, e.ligne()+1, e.colonne());
				break;
			case Direction.W:
				terrain.add(picked, e.ligne()-1, e.colonne());
				break;
			default:
				break;
			}
		}
	}

}
