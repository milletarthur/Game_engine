package controller;

import java.util.LinkedList;

import Model_Snake.Entity;
import Model_Snake.Field;
import toolkit.Automate;
import toolkit.Conjonction;
import toolkit.IAction;
import toolkit.State;
import toolkit.Transition;
import toolkit.True;
import toolkit.ICondition;
import toolkit.Pair;
import toolkit.Direction;
import toolkit.Categorie;
import toolkit.Disjonction;

public class AutomateSnake extends Automate {

	public AutomateSnake(Entity e, Field f, TickListener List) {
		ICondition c1a;
		ICondition c1b;
		ICondition c1;
		ICondition c2a;
		ICondition c2b;
		ICondition c2;
		
		State Init = new State("alive");
		State Pick = new State("pick");
		State Void = new State("");
		LinkedList<Transition> Transitions = new LinkedList<Transition>();

		Transition trans = null;
		LinkedList<IAction> Actions = null;
		ICondition c = null;

		// *(alive) | Cell(F,P)? : (pick)
		c = new Cell(f,Direction.F, Categorie.P);
		Actions = new LinkedList<IAction>();
//		Actions.add(new Move(e,f));
		trans = new Transition(Init, Pick, c, Actions);
		Transitions.add(trans);

		// *(alive) | Cell(L,P)? Turn(L): (pick)
		c = new Cell(f,Direction.L, Categorie.P);
		Actions = new LinkedList<IAction>();
		Actions.add(new Turn(e, Direction.L,f));
//		Actions.add(new Move(e,f));
		trans = new Transition(Init, Pick, c, Actions);
		Transitions.add(trans);

		// *(alive) | Cell(R,P)? Turn(R): (pick)
		c = new Cell(f,Direction.R, Categorie.P);
		Actions = new LinkedList<IAction>();
		Actions.add(new Turn(e, Direction.R,f));
//		Actions.add(new Move(e,f));
		trans = new Transition(Init, Pick, c, Actions);
		Transitions.add(trans);

		// *(alive) | (Cell(F,O) || Cell(F,@)) && Cell(L,P)? Move(L): (pick)
//		c1a = new Cell(f,Direction.F, Categorie.O);
//		c1b = new Cell(f,Direction.F, Categorie.T);
//		c1 = new Disjonction(c1a, c1b);
//		c2 = new Cell(f,Direction.L, Categorie.P);
//		c = new Conjonction(c1, c2);
//		Actions = new LinkedList<IAction>();
//		Actions.add(new Turn(e, Direction.L));
//		Actions.add(new Move(e,f));
//		trans = new Transition(Init, Pick, c, Actions);
//		Transitions.add(trans);

		// *(alive) | (Cell(F,O) || Cell(F,@)) && Cell(R,P)? Move(R): (pick)
//		c1a = new Cell(f,Direction.F, Categorie.O);
//		c1b = new Cell(f,Direction.F, Categorie.T);
//		c1 = new Disjonction(c1a, c1b);
//		c2 = new Cell(f,Direction.R, Categorie.P);
//		c = new Conjonction(c1, c2);
//		Actions = new LinkedList<IAction>();
//		Actions.add(new Turn(e, Direction.R));
//		Actions.add(new Move(e,f));
//		trans = new Transition(Init, Pick, c, Actions);
//		Transitions.add(trans);
		
		// *(alive) | (Cell(F,O) || Cell(F,@)) && Cell(L,V) && Cell(R,V)? 50% Move(L) 50%Move(R): (alive)
		c1a = new Cell(f,Direction.F, Categorie.O);
		c1b = new Cell(f,Direction.F, Categorie.T);
		c1 = new Disjonction(c1a, c1b);
		c2a = new Cell(f,Direction.L, Categorie.V);
		c2b = new Cell(f,Direction.R, Categorie.V);
		c2 = new Conjonction(c2a, c2b);
		c = new Conjonction(c1, c2);
		Actions = new LinkedList<IAction>();
		Turn l = new Turn(e, Direction.L,f);
		Turn r = new Turn(e, Direction.R,f);
		Chance chance = new Chance(new LinkedList<Pair<IAction,Integer>>());
		chance.add(r, 50);
		chance.add(l, 100);
		Actions.add(chance);
		Actions.add(new Move(e,f));
		trans = new Transition(Init, Init, c, Actions);
		Transitions.add(trans);

		// *(alive) | (Cell(F,O) || Cell(F,@)) && Cell(L,V) && Cell(R,O)? Move(L): (alive)
		c1a = new Cell(f,Direction.F, Categorie.O);
		c1b = new Cell(f,Direction.F, Categorie.T);
		c1 = new Disjonction(c1a, c1b);
		c2a = new Cell(f,Direction.L, Categorie.V);
		c2b = new Cell(f,Direction.R, Categorie.O);
		c2 = new Conjonction(c2a, c2b);
		c = new Conjonction(c1, c2);
		Actions = new LinkedList<IAction>();
		Actions.add(new Turn(e, Direction.L,f));
		Actions.add(new Move(e,f));
		trans = new Transition(Init, Init, c, Actions);
		Transitions.add(trans);

		// *(alive) | (Cell(F,O) || Cell(F,@)) && Cell(L,O) && Cell(R,V)? Move(R): (alive)
		c1a = new Cell(f,Direction.F, Categorie.O);
		c1b = new Cell(f,Direction.F, Categorie.T);
		c1 = new Disjonction(c1a, c1b);
		c2a = new Cell(f,Direction.L, Categorie.O);
		c2b = new Cell(f,Direction.R, Categorie.V);
		c2 = new Conjonction(c2a, c2b);
		c = new Conjonction(c1, c2);
		Actions = new LinkedList<IAction>();
		Actions.add(new Turn(e, Direction.R,f));
		Actions.add(new Move(e,f));
		trans = new Transition(Init, Init, c, Actions);
		Transitions.add(trans);

		// *(alive) | Cell(F,V)? Move(F): (alive)
		c = new Cell(f,Direction.F, Categorie.V);
		Actions = new LinkedList<IAction>();
		Actions.add(new Turn(e, Direction.F,f));
		Actions.add(new Move(e,f));
		trans = new Transition(Init, Init, c, Actions);
		Transitions.add(trans);

		// *(alive) | Cell(B,V)? Move(B): (alive)
		c = new Cell(f,Direction.B, Categorie.V);
		Actions = new LinkedList<IAction>();
		Actions.add(new Turn(e, Direction.B,f));
		Actions.add(new Move(e,f));
		trans = new Transition(Init, Init, c, Actions);
		Transitions.add(trans);

		// *(alive) | true?: ()
		c = new True();
		Actions = new LinkedList<IAction>();
		Actions.add(new Explode(e,f));
		trans = new Transition(Init, Void, c, Actions);
		Transitions.add(trans);
		
		// *(pick) | true? pick(): (alive)
		c = new True();
		Actions = new LinkedList<IAction>();
		Actions.add(new Pick(e,f, List));
		Actions.add(new Move(e,f));
		trans = new Transition(Pick, Init, c, Actions);
		Transitions.add(trans);

		this.Current = Init;
		this.Transitions = Transitions;
	}
}
