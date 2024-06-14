package Automates;

import java.util.Iterator;
import java.util.LinkedList;

import Labyrinthe.Entity;


public class Automate {
	protected LinkedList<Transition> Transitions;
	protected LinkedList<State> States;
	protected State Current;
	
	public void step(Entity e) {
		Iterator<Transition> iter = Transitions.iterator();
		while(iter.hasNext()) {
			Transition trans = iter.next();
			if (Current.equals(trans.getSource()) && trans.CheckCondition(e)) {
				Current = trans.getCible();
				trans.doActions(e);
				return;
			}
		}
	}
}
