package controller;

import Automates.ICondition;
import Labyrinthe.Entity;

public class Not implements ICondition {
	private ICondition c;
	
	public Not(ICondition c) {
		this.c = c;
	}
	
	@Override
	public boolean eval(Entity e) {
		return !(c.eval(e));
	}

}
