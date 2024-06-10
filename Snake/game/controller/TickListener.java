package controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import Model_Snake.Entity;
import Model_Snake.Field;
import Model_Snake.Snake;
import toolkit.Automate;
import toolkit.Categorie;
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
		LinkedList<Pair<Integer,Integer>> SnakeList = terrain.getSnakeList();
		if (SnakeList.size() == 0) {
			LinkedList<Pair<Integer,Integer>> VoidList = terrain.getVoidList();
			int rnd = new Random().nextInt(VoidList.size());
		    Pair<Integer,Integer> selected = VoidList.remove(rnd);
		    int x = selected.x();
		    int y = selected.y();
		    Snake snake = new Snake(x,y,0,Categorie.Arobase,terrain);
		    terrain.update(snake, -1, -1, x, y);
		    AutomateSnake auto = new AutomateSnake(snake,terrain,this);
		    add(auto, snake);
		}
//		terrain.print();
	}

}
