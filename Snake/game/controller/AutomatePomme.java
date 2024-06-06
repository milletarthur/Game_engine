package controller;

import java.util.LinkedList;

import Model_Snake.Entity;
import toolkit.Categorie;
import toolkit.Direction;
import toolkit.Disjonction;
import toolkit.IAction;
import toolkit.ICondition;
import toolkit.State;
import toolkit.Transition;

public class AutomatePomme {
	public AutomatePomme(Entity e) {
		State Init = new State("init");
		State Void = new State("");
		LinkedList<Transition> Transitions = new LinkedList<Transition>();

		Transition trans = null;
		LinkedList<IAction> Actions = null;
		ICondition c = null;

		c = new Disjonction(new Cell(Direction.H, Categorie.T), new Cell(Direction.H, Categorie.A));
		Actions = new LinkedList<IAction>();
		trans = new Transition(Init, Void, c, Actions);

	}
}
