package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import toolkit.Categorie;
import toolkit.Direction;

public class Not implements ICondition {
	private ICondition c;
	
	public Not(ICondition c) {
		this.c = c;
	}
	
	@Override
	public boolean eval(Entity e) {
		return !(c.eval(e));
	}

	@Override
	public void setDir(Direction dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCat(Categorie cat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categorie getCategorie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return null;
	}

}
