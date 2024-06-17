package controller;

import Automates.ICondition;
import Labyrinthe.Entity;
import toolkit.Categorie;
import toolkit.Direction;

public class Conjonction implements ICondition {

	private ICondition c1;
	private ICondition c2;
	
	public Conjonction(ICondition c1, ICondition c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	@Override
	public boolean eval(Entity e) {
		return c1.eval(e) && c2.eval(e);
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
