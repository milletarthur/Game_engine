package controller;

import java.util.Iterator;
import java.util.LinkedList;

import Model_Snake.Entity;
import Model_Snake.Field;
import toolkit.Automate;
import toolkit.Pair;

public class TickListener {
	LinkedList<Pair<Automate,Entity>> auto_list;
	private Field terrain;
	
	public TickListener(Field terrain) {
		auto_list = new LinkedList<Pair<Automate,Entity>>();
		this.terrain = terrain;
	}
	
	public void add(Automate a, Entity e) {
		Pair<Automate,Entity> p = new Pair<Automate,Entity>(a,e);
		auto_list.add(p);
	}
	
	public void step() {
		int length = auto_list.size();
		for (int i = 0; i < length; i++) {
			Pair<Automate,Entity> p = auto_list.get(i);
			Automate auto = p.x();
			Entity e = p.y();
			auto.step(e);
		}
//		terrain.print();
	}

}
