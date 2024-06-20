package Labyrinthe;

import java.util.LinkedList;

import toolkit.Categorie;

public class Interrupteur extends Entity {

	private LinkedList<Entity> liste_elem;
	private int state = 0; // -1 := pop ; 1 := wizz

	public Interrupteur(int ligne, int colonne, LinkedList<Entity> elem) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.category = Categorie.C;
		this.layer = 2;
		this.team = 7;
		this.liste_elem = elem;
	}

	public LinkedList<Entity> get_entity() {
		return this.liste_elem;
	}

	public void add(Entity e) {
		liste_elem.add(e);
	}

	public void setListeLien(LinkedList<Entity> l) {
		liste_elem = l;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Interrupteur(ligne, colonne, new LinkedList<Entity>());
	}

	@Override
	public void pop() {
		Entity elem;
		for(int i=0; i<liste_elem.size(); i++) {
			elem = liste_elem.get(i);
			elem.pop();
		}
		liste_elem = null;
	}

	@Override
	public void wizz() {
		Entity elem;
		for(int i=0; i<liste_elem.size(); i++) {
			elem = liste_elem.get(i);
			elem.wizz();
		}
		liste_elem = null;
	}
	
	/*
	 * returns the state -1 := pop; 0 := normal; 1 := wizz
	 */
	public int State() {
		return state;
	}

	@Override
	public int hit() {
		return 1;
	}
}