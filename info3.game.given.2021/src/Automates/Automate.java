package Automates;

import java.util.Iterator;
import java.util.LinkedList;

import Labyrinthe.Entity;


public class Automate {
	protected LinkedList<Transition> Transitions;
	//protected LinkedList<State> States;
	protected State Init;
	protected State Current;
	
	public Automate() {
		Transitions = new LinkedList<Transition>();
	}
	
	public Automate(State c, LinkedList<Transition> t) {
		Transitions = t;
		Current = c;
	}
	
	public void add_transition (Transition t) {
		Transitions.add(t);
	}
	
	public void add_init_state(State s) {
		Init = s;
	}
	
	public void change_current(State s) {
		Current = s;
	}
	
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
