package controller;

import java.util.Iterator;
import java.util.LinkedList;

import Model_Snake.Entity;
import toolkit.Automate;
import toolkit.Pair;

public class TickListener {
	LinkedList<Pair<Automate,Entity>> auto_list;
	
	public TickListener() {
		auto_list = new LinkedList<Pair<Automate,Entity>>();
	}
	
	public void add(Automate a, Entity e) {
		Pair<Automate,Entity> p = new Pair<Automate,Entity>(a,e);
		auto_list.add(p);
	}
	
	public void step() {
		Iterator<Pair<Automate,Entity>> iter;
		iter = auto_list.iterator();
		while(iter.hasNext()) {
			Pair<Automate,Entity> p = iter.next();
			Automate auto = p.x();
			Entity e = p.y();
			auto.step(e);
		}
	}

}
