package Labyrinthe;

import java.util.LinkedList;

public class Interrupteur extends Entity {
	
	private LinkedList<Entity> liste_elem;
	private int layer = 2;
	
	public Interrupteur(int x, int y, int team, int category, LinkedList<Entity> elem) {
		this.x = x;
		this.y = y;
		this.category = category;
		this.team = team;
		this.liste_elem = elem;
	}

	public LinkedList<Entity> get_entity() {
		return this.liste_elem;
	}
	
	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move() {
		super.move();
	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turn(int dir) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void power(int vie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hit() {
		return 1;
	}

}
