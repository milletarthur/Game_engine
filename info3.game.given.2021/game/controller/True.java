package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import toolkit.Categorie;
import toolkit.Direction;

public class True implements ICondition{

	@Override
	public boolean eval(Entity e) {
		return true;
	}

	@Override
	public void setDir(int dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCat(int cat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCategorie() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int getDirection() {
		// TODO Auto-generated method stub
		return -5;
	}
	
}
