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
		Boolean t1 = c1.eval(e);
		Boolean t2 = c2.eval(e);
		Boolean t3 = t1 && t2;
		System.out.println(toString());
//		System.out.print(t1);
//		System.out.print(t2);
//		System.out.println(t3);
		return t3;
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

	@Override
	public String toString() {
		String s = c1.toString();
		s += " && ";
		s += c2.toString();
		return s;
		}

}
