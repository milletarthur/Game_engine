package Labyrinthe;

import java.util.LinkedList;

import toolkit.Categorie;

public class Arc extends Entity {
	
	private boolean flecheTrans;
	private LinkedList<Entity> list_fleche;
	
	public Arc(int colonne, int ligne, boolean fleche) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
		flecheTrans = fleche;
//		f = new Fleche(x, y, Orientation, fleche);
	}
	
	public Arc(int colonne, int ligne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
		flecheTrans = false;
	}

	@Override
	public Entity egg(int colonne, int ligne) {
		return new Arc(colonne,ligne);

	}

	@Override
	public void pop() {
		explode();
	}

	@Override
	public void wizz() {
		flecheTrans = true;
	}

	@Override
	public int hit() {
		return -1;
	}
	
	public void setListFleche(LinkedList<Entity> l) {
		list_fleche = l;
	}
	
	public LinkedList<Entity> getListFleche() {
		return list_fleche;
	}
	
	public void setTrans(boolean value) {
		flecheTrans = value;
	}
	
	public boolean getTrans() {
		return flecheTrans;
	}
	
}
