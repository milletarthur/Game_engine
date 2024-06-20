package Labyrinthe;

import java.util.LinkedList;

import toolkit.Categorie;

public class Arc extends Entity {
	
	public Arc(int ligne, int colonne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.P;
		this.team = 3;
		layer = 2;
		flecheTrans = false;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
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
