package controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import Automates.IAction;
import Labyrinthe.Entity;
import toolkit.Pair;

public class Chance implements IAction {

	LinkedList<Pair<IAction, Integer>> list;

	Chance(LinkedList<Pair<IAction, Integer>> l) {
		this.list = l;
	}

	public void add(IAction act, int chance) {
		Pair<IAction, Integer> p = new Pair<IAction, Integer>(act, chance);
		list.add(p);
	}

	@Override
	public void exec(Entity e) {
		int rnd = new Random().nextInt(100);
		int old_percentage = 0;
		int new_percentage = 0;
		Iterator<Pair<IAction, Integer>> iter = list.iterator();
		while (iter.hasNext()) {
			Pair<IAction, Integer> p = iter.next();
			old_percentage = new_percentage;
			new_percentage = p.geto2();
			if (old_percentage <= rnd && rnd < new_percentage) {
				p.geto1().exec(e);
			}
		}
	}
	
	@Override
	public String toString() {
		String s = "Chance";
		return s;
	}
}
