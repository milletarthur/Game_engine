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
		LinkedList<Entity> elem = terrain.getElement(e.x(), e.y());
		elem.remove(e);
		terrain.updateAt(e.x(), e.y(), elem);
		e.move();
		elem = terrain.getElement(e.x(), e.y());
		elem.add(e);
		terrain.updateAt(e.x(), e.y(), elem);

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
