package controller;

import java.util.Iterator;
import java.util.LinkedList;

import Model_Snake.Entity;
import Model_Snake.Field;
import Model_Snake.Queue;
import Model_Snake.Snake;
import toolkit.IAction;
import toolkit.Pair;

public class Explode implements IAction {
	
	private Entity entity;
	private Field terrain;
	
	public Explode (Entity entity, Field terrain) {
		this.entity = entity ;
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		int old_x = e.x();
		int old_y = e.y();
		LinkedList<Pair<Integer,Integer>> cooList = new LinkedList<Pair<Integer,Integer>>();
		if (e instanceof Snake) {
			LinkedList<Queue> queue = ((Snake) e).getQueue();
			Iterator<Queue> iter = queue.iterator();
			while(iter.hasNext()) {
				Entity en = iter.next();
				cooList.add(new Pair<Integer,Integer>(en.x(),en.y()));
			}
		}
		e.kill();
		terrain.update(e, old_x, old_y, -1, -1);
		if (e instanceof Snake) {
			LinkedList<Queue> queue = ((Snake) e).getQueue();
			Iterator<Queue> iter = queue.iterator();
			Iterator<Pair<Integer,Integer>> cooiter = cooList.iterator();
			while(iter.hasNext()) {
				Entity en = iter.next();
				Pair<Integer,Integer> p = cooiter.next();
				terrain.update(en, p.x(), p.y(), -1, -1);
			}
		}
		System.out.println("Kill");
		return ;
	}
}
