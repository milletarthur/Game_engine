package Labyrinthe;

import java.util.LinkedList;

import toolkit.Categorie;

public class Interrupteur extends Entity {

	private LinkedList<Entity> liste_elem;

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

	@Override
	public void pop() {
		// TODO Auto-generated method stub
		
	}
}