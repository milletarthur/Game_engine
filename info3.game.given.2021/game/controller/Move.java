package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;

public class Move implements IAction {

	public Entity entity;
	public Field terrain;

	public Move(Entity entity, Field terrain) {
		this.entity = entity;
		this.terrain = terrain;
	}
	
	public Move(Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		terrain.remove(e.x(), e.y(), e);	
		e.move();
		terrain.add(e, e.x(), e.y());
//		System.out.println("Move");
		return;
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Entity e) {
		this.entity = e;
	}
}
