package controller;

import Automates.ICondition;
import Labyrinthe.Entity;

public class False implements ICondition{

	@Override
	public boolean eval(Entity e) {
		return false;
	}

}
