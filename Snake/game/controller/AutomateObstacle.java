package controller;

import java.util.LinkedList;

import Model_Snake.Entity;
import toolkit.Direction;
import toolkit.IAction;
import toolkit.ICondition;
import toolkit.State;
import toolkit.Transition;
import toolkit.True;

public class AutomateObstacle {
	public AutomateObstacle(Entity e) {
		State Init = new State("init");
		LinkedList<Transition> Transitions = new LinkedList<Transition>();
		
		Transition trans = null;
		LinkedList<IAction> Actions = null;
		ICondition c = null;
		
		c = new True();
		Actions = new LinkedList<IAction>();
		Actions.add(new Turn(e, Direction.L));
		trans = new Transition(Init, Init, c, Actions);
		Transitions.add(trans);
	}
}
