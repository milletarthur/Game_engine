package controller;

import java.util.LinkedList;
import java.util.Random;

import Model_Snake.Entity;
import Model_Snake.Field;
import Model_Snake.Snake;
import ViewWindow.DrawTerrain;
import toolkit.Categorie;
import toolkit.IAction;
import toolkit.Pair;

public class Pick implements IAction {
	
	private Entity entity;
	private Field terrain;
	private TickListener List;
	
	public Pick (Entity entity, Field terrain, TickListener List) {
		this.entity = entity ;
		this.terrain = terrain;
		this.List = List;
	}

	@Override
	public void exec(Entity e) {
		int old_x = e.x();
		int old_y = e.y();
//		int rnd = new Random().nextInt(2);
		int rnd = 0; // egg not working
		if (rnd == 0)
			e.pick();
		else {
			LinkedList<Pair<Integer,Integer>> VoidList = terrain.getVoidList();
			int rndbis = new Random().nextInt(VoidList.size());
		    Pair<Integer,Integer> selected = VoidList.remove(rndbis);
		    int x = selected.x();
		    int y = selected.y();
		    Snake snake = new Snake(x,y,e.team()+1,Categorie.Arobase,terrain);
		    terrain.update(snake, -1, -1, x, y);
		    AutomateSnake auto = new AutomateSnake(snake,terrain,List);
		    List.add(auto, snake);
		}
		System.out.println("Pick");
		terrain.update(e, old_x, old_y, -1, -1);
		return ;
	}
}