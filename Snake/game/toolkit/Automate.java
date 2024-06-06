package toolkit;

import java.util.LinkedList;

public abstract class Automate {
	LinkedList<Transition> Transitions;
	State Current;
	
	void step(Entity e);
}
