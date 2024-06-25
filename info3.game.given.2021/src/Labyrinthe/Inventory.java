package Labyrinthe;

import java.util.Iterator;
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

	public boolean isEmpty() {
		if (inventory.size() == 0) {
			return true;
		}
		return false;
	}

	public void add(Entity e) {
		if(inventory.size() == 0) {
			current_j1 = e;
			current_j2 = e;
		}
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
		if(len == 0) {
			current_j1 = null;
			return;
		}
		if (current_j1 == null) {
			current_j1 = inventory.get(0);
		} else {
			int i = inventory.indexOf(current_j1);
			i = (i + 1) % len;
			current_j1 = inventory.get(i);
		}
	}

	public void NextCurrentJ2() {
		int len = inventory.size();
		if(len == 0) {
			current_j2 = null;
			return;
		}
		if (current_j2 == null) { 
			current_j2 = inventory.get(0);
		} else {
			int i = inventory.indexOf(current_j2);
			i = (i + 1) % len;
			current_j2 = inventory.get(i);
		}
	}

	public Entity popJ1() {
		Entity e = getCurrentJ1();
		inventory.remove(e);
		current_j1 = null;
		current_j2 = null;
		NextCurrentJ2();
		NextCurrentJ1();
		return e;
	}

	public Entity popJ2() {
		Entity e = getCurrentJ2();
		inventory.remove(e);
		current_j1 = null;
		current_j2 = null;
		NextCurrentJ2();
		NextCurrentJ1();
		return e;
	}
	
	public int size() {
		return inventory.size();
	}
	
	public String toString() {
		String s = "Inventory : ";
		int taille = inventory.size();
		String classnamelong;
		String classname;
		for(int i = 0; i < taille; i++) {
			taille = inventory.size();
			classnamelong = inventory.get(i).getClass().getName();
			classname = (String) classnamelong.subSequence(classnamelong.indexOf(".")+1,classnamelong.length());
			s += classname + " ";
		}
		s += "\n";
		if (current_j1 != null) {
			classnamelong = current_j1.getClass().getName();
			classname = (String) classnamelong.subSequence(classnamelong.indexOf(".")+1,classnamelong.length());
			s += "Current J1 : " + classname + "\n";
		} else 
			s += "Current J1 : none\n";
		if (current_j2 != null) {
			classnamelong = current_j2.getClass().getName();
			classname = (String) classnamelong.subSequence(classnamelong.indexOf(".")+1,classnamelong.length());
			s += "Current J2 : " + classname + "\n";
		} else 
			s += "Current J2 : none\n";
		return s;
	}
}
