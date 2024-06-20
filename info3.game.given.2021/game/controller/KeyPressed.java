package controller;

import java.util.LinkedList;

public class KeyPressed {

	LinkedList<Integer> keys = new LinkedList<Integer>();
	
	public KeyPressed() {
	}
	
	public void add(int key) {
		if (!keys.contains((Object) key))
			keys.addFirst(key);
	}
	
	public void remove(int key) {
		keys.remove((Object) key);
	}
	
	public boolean ispressed(int key) {
		return keys.contains((Object) key);
	}
}
