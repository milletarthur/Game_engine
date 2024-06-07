package controller;

import java.util.Iterator;
import java.util.LinkedList;

import Model_Snake.Entity;
import Model_Snake.Field;
import Model_Snake.Queue;
import Model_Snake.Snake;
import ViewWindow.DrawTerrain;
import toolkit.IAction;
import toolkit.Pair;

public class Move implements IAction {
	
	public Entity entity;
	public Field terrain;
	
	public Move (Entity entity, Field terrain) {
		this.entity = entity;
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
		e.move();
		terrain.update(e, old_x, old_y, e.x(), e.y());
		if (e instanceof Snake) {
			LinkedList<Queue> queue = ((Snake) e).getQueue();
			Iterator<Queue> iter = queue.iterator();
			Iterator<Pair<Integer,Integer>> cooiter = cooList.iterator();
			while(iter.hasNext()) {
				Entity en = iter.next();
				Pair<Integer,Integer> p = cooiter.next();
				terrain.update(en, p.x(), p.y(), en.x(), en.y());
			}
		}
		System.out.println("Move");
		return ;		
	}
}
