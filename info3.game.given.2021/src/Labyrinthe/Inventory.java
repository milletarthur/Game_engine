package Labyrinthe;

import java.util.LinkedList;

public class Inventory {
	private LinkedList<Entity> inventory;
	private Entity current_j1;
	private Entity current_j2;
	
	public Inventory() {
		inventory = new LinkedList<Entity>();
	}
	
	public Inventory(LinkedList<Entity> l) {
		inventory = l;
	}
	
	public void add(Entity e) {
		inventory.add(e);
	}

	public void setCurrentJ1(Entity e) {
		current_j1 = e;
	}
	
	public void setCurrentJ2(Entity e) {
		current_j2 = e;
	}
	
	public Entity getCurrentJ1() {
		return current_j1;
	}
	
	public Entity getCurrentJ2() {
		return current_j2;
	}
	
	public void NextCurrentJ1() {
		int len = inventory.size();
		int i = inventory.indexOf(current_j1);
		i = (i+1)%len;
		current_j1 = inventory.get(i);
	}
	
	public void NextCurrentJ2() {
		int len = inventory.size();
		int i = inventory.indexOf(current_j2);
		i = (i+1)%len;
		current_j2 = inventory.get(i);
	}
}
