package toolkit;

import java.util.Iterator;
import java.util.LinkedList;

import Model_Snake.Entity;

public abstract class Automate {
	protected LinkedList<Transition> Transitions;
	protected State Current;
	
	public void step(Entity e) {
		Iterator<Transition> iter = Transitions.iterator();
//		System.out.print(e.x());
//	    System.out.print(",");
//	    System.out.println(e.y());
		while(iter.hasNext()) {
			Transition trans = iter.next();
			if (Current.equals(trans.getSource()) && trans.CheckCondition(e)) {
				Current = trans.getCible();
				trans.doActions(e);
//				System.out.println("succes");
				return;
			}
		}
	}
}
