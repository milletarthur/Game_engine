package controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import Model_Snake.Entity;
import toolkit.IAction;
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
			new_percentage = p.y();
//			System.out.print(old_percentage);
//			System.out.print(" <= ");
//			System.out.print(rnd);
//			System.out.print(" < ");
//			System.out.println(new_percentage);
//			((Turn) p.x()).print();
			if (old_percentage <= rnd && rnd < new_percentage) {
				p.x().exec(e);
			}
		}
	}

}
