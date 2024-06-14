package controller;

import Automates.ICondition;
import Labyrinthe.Entity;

public class True implements ICondition{

	@Override
	public boolean eval(Entity e) {
		return true;
	}
	
}
