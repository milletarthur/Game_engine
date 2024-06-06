package toolkit;

import java.util.Iterator;
import java.util.LinkedList;

import Model_Snake.Entity;

public abstract class Automate {
	private LinkedList<Transition> Transitions;
	private State Current;
	
	void step(Entity e) {
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
