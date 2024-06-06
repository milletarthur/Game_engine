package toolkit;

import java.util.Iterator;
import java.util.LinkedList;

public class Transition {
	State Source;
	State Cible;
	ICondition C;
	LinkedList<IAction> Actions;
	
	public Transition(State Source, State Cible, ICondition C, LinkedList<IAction> Actions){
		this.Source = Source;
		this.Cible = Cible;
		this.C = C;
		this.Actions = Actions;
	}
	
	public boolean CheckCondition(Entity e) {
		return C.eval(e);
	}
	
	public void doActions(Entity e) {
		Iterator<IAction> iter = Actions.iterator();
		while(iter.hasNext()) {
			IAction a = iter.next();
			a.exec();
		}
	}
	
}
