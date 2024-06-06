package controller;

import java.util.LinkedList;

import Model_Snake.Entity;
import toolkit.Automate;
import toolkit.Conjonction;
import toolkit.IAction;
import toolkit.State;
import toolkit.Transition;
import toolkit.ICondition;
import toolkit.Direction;
import toolkit.Disjonction;

public class AutomateSnake extends Automate {
	
	public AutomateSnake(Entity e) {
		State Init = new State("alive");
		State Pick = new State("pick");
		State Void = new State("");
		LinkedList<Transition> Transitions = new LinkedList<Transition>();
		
		Transition trans = null;
		LinkedList<IAction> Actions = null;
		ICondition c = null;
		
		// *(alive) | Cell(F,A)? Move(): (pick)
		c = new Cell(Direction.F,A);
		Actions = new LinkedList<IAction>();
		Actions.add(new Move(e));
		trans = new Transition(Init, Pick, c, Actions);
		Transitions.add(trans);
		
		// *(alive) | Cell(L,A)? Move(L): (pick)
		c = new Cell(Direction.L,A);
		Actions = new LinkedList<IAction>();
		Actions.add(new Turn(e,Direction.L));
		Actions.add(new Move(e));
		trans = new Transition(Init, Pick, c, Actions);
		Transitions.add(trans);
		
		// *(alive) |  Cell(R,A)? Move(R): (pick)
		c = new Cell(Direction.R,A);
        Actions = new LinkedList<IAction>();
        Actions.add(new Turn(e,Direction.R));
        Actions.add(new Move(e));
        trans = new Transition(Init, Pick, c, Actions);
        Transitions.add(trans);
		
		// *(alive) | (Cell(F,O) || Cell(F,S)) && Cell(L,A)? Move(L): (pick)
		ICondition c1a = new Cell(Direction.F,O);
		ICondition c1b = new Cell(Direction.F,S);
		ICondition c1 = new Disjonction(c1a,c1b);
		ICondition c2 = new Cell(Direction.L,A);
		c = new Conjonction(c1,c2);
		Actions = new LinkedList<IAction>();
		Actions.add(new Turn(e,Direction.L));
		Actions.add(new Move(e));
		trans = new Transition(Init, Pick, c, Actions);
		Transitions.add(trans);
		
		// *(alive) | (Cell(F,O) || Cell(F,S)) && Cell(R,A)? Move(R): (pick)
		c1a = new Cell(Direction.F,O);
		c1b = new Cell(Direction.F,S);
		c1 = new Disjonction(c1a,c1b);
		c2 = new Cell(Direction.R,A);
		c = new Conjonction(c1,c2);
		Actions = new LinkedList<IAction>();
		Actions.add(new Turn(e,Direction.R));
		Actions.add(new Move(e));
		trans = new Transition(Init, Pick, c, Actions);
		Transitions.add(trans);
		
		// *(alive) | (Cell(F,O) || Cell(F,S)) && Cell(L,V)? Move(L): (alive)
		// *(alive) | (Cell(F,O) || Cell(F,S)) && Cell(R,V)? Move(R): (alive)
		
		// *(alive) | Cell(F,V)? Move(F): (alive)
	    c = new Cell(Direction.F,V);
	        Actions = new LinkedList<IAction>();
	        Actions.add(new Turn(Direction.F));
	        Actions.add(new Move(e));
	        trans = new Transition(Init, Init, c, Actions);
	        Transitions.add(trans);
	    
	     // *(alive) | Cell(B,V)? Move(B): (alive)
	        c = new Cell(Direction.B,V);
	        Actions = new LinkedList<IAction>();
	        Actions.add(new Turn(Direction.B));
	        Actions.add(new Move(e));
	        trans = new Transition(Init, Init, c, Actions);
	        Transitions.add(trans);	        
	        
		// *(alive) | true?: ()
		// *(pick) | true? pick(): (alive)
		
		
		this.Current = Current;
		this.Transitions = Transitions;
	}
}
