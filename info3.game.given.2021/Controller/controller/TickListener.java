package controller;

import java.util.LinkedList;

import Automates.Automate;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;
import toolkit.Pair;


public class TickListener {
	LinkedList<Pair<Automate,Entity>> auto_list;
	
	public TickListener(Field terrain) {
		auto_list = new LinkedList<Pair<Automate,Entity>>();
	}
	
	public void add(Automate a, Entity e) {
		e.setCurrent(a.getInit());
		Pair<Automate,Entity> p = new Pair<Automate,Entity>(a,e);
		auto_list.add(p);
	}
	
	public void step() {
		int length = auto_list.size();
		for (int i = 0; i < length; i++) {
			Pair<Automate,Entity> p = auto_list.get(i);
			Automate auto = p.geto1();
			Entity e = p.geto2();
			auto.step(e);
		}
	}

}
