package toolkit;

import Model_Snake.Entity;

import java.util.Iterator;
import java.util.LinkedList;

public class Transition {
	private State Source;
	private State Cible;
	private ICondition c;
	private LinkedList<IAction> Actions;
	
	public Transition(State Source, State Cible, ICondition c, LinkedList<IAction> Actions){
		this.Source = Source;
		this.Cible = Cible;
		this.c = c;
		this.Actions = Actions;
	}
	
	public boolean CheckCondition(Entity e) {
		return c.eval(e);
	}
	
	public void doActions(Entity e) {
		Iterator<IAction> iter = Actions.iterator();
		while(iter.hasNext()) {
			IAction a = iter.next();
			a.exec(e);
		}
	}
	
	public State getSource() {
		return Source;
	}
	
	public State getCible() {
		return Cible;
	}
	
	public ICondition getCondition() {
		return c;
	}
	
}
