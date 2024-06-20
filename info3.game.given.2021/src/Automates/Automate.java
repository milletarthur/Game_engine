package Automates;

import java.util.Iterator;
import java.util.LinkedList;

import Labyrinthe.Entity;


public class Automate {
	protected LinkedList<TransitionAutomate> Transitions;
	//protected LinkedList<State> States;
	//protected State Init;
	protected State Current;
	
	public Automate() {
		Transitions = new LinkedList<TransitionAutomate>();
	}
	
	public Automate(State c, LinkedList<TransitionAutomate> t) {
		Transitions = t;
		Current = c;
	}
	
	public void add_transition (TransitionAutomate t) {
		Transitions.add(t);
	}
	
//	public void add_init_state(State s) {
//		Init = s;
//	}
	
	public void change_current(State s) {
		Current = s;
	}
	
	public void step(Entity e) {
		Iterator<TransitionAutomate> iter = Transitions.iterator();
		while(iter.hasNext()) {
			TransitionAutomate trans = iter.next();
			if (Current.equals(trans.getSource()) && trans.CheckCondition(e)) {
				Current = trans.getCible();
				trans.doActions(e);
				return;
			}
		}
	}
}
