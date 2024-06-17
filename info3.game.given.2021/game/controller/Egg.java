package controller;

import java.util.LinkedList;
import java.util.Random;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import toolkit.Pair;

public class Egg implements IAction {

	private Entity entity;
	private Field terrain;
	
	public Egg(Field f) {
		terrain = f;
	}
	
	public Egg(Field f, Entity e) {
		terrain = f;
		entity = e;
	}
	
	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Entity e) {
		entity = e;
	}

	@Override
	public void exec(Entity e) {
		LinkedList<Pair<Integer,Integer>> l_void = terrain.recup_liste_void();
		Random rand = new Random();
		Pair<Integer,Integer> c = l_void.get(rand.nextInt(l_void.size()));		
		Entity e2 = e.egg(c.x(),c.y());
		terrain.add(e2, e2.x(), e2.y());
	}

}
