package Automates;

import java.util.Iterator;
import java.util.LinkedList;

import Labyrinthe.Entity;


public class Automate {
	protected String name;
	protected LinkedList<TransitionAutomate> Transitions;
	//protected LinkedList<State> States;
	//protected State Init;
	protected State Init;
	
	public Automate() {
		Transitions = new LinkedList<TransitionAutomate>();
	}
	
	public Automate(State c, LinkedList<TransitionAutomate> t) {
		Transitions = t;
		Init = c;
	}
	
	public void add_transition (TransitionAutomate t) {
		Transitions.add(t);
	}
	
	public void set_name(String n) {
		name = n;
	}
	
	public void change_init(State s) {
		Init = s;
	}
	
	public State getInit() {
		return this.Init;
	}
	
	public String get_name() {
		return name;
	}
	
	public void step(Entity e) {
		Iterator<TransitionAutomate> iter = Transitions.iterator();
		while(iter.hasNext()) {
			TransitionAutomate trans = iter.next();
			if (e.getCurrent().equals(trans.getSource()) && trans.CheckCondition(e)) {
				e.setCurrent(trans.getCible());
				trans.doActions(e);
				return;
			}
		}
	}
	
	public String toString() {
		String rv ="Name : " + name +"; " + "Init State : " + Init.toString() + " ; ";
		Iterator<TransitionAutomate> i = Transitions.iterator();
		while(i.hasNext()) {
			rv += i.next().toString();
		}
		return rv;
	}
}
