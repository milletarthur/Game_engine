package Labyrinthe;

import java.util.LinkedList;

import toolkit.Categorie;

public class Interrupteur extends Entity {

	private LinkedList<Entity> liste_elem;

	public Interrupteur(int colonne, int ligne, LinkedList<Entity> elem) {
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
	public Entity egg(int colonne, int ligne) {
		return new Interrupteur(colonne, ligne, new LinkedList<Entity>());
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub
	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub
	}

	@Override
	public int hit() {
		return 1;
	}
}