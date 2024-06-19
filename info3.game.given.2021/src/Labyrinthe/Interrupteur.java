package Labyrinthe;

import java.util.LinkedList;

import toolkit.Categorie;

public class Interrupteur extends Entity {
	
	private LinkedList<Entity> liste_elem;
	
	public Interrupteur(int x, int y, LinkedList<Entity> elem) {
		this.x = x;
		this.y = y;
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
	public Entity egg(int x, int y) {
		return new Interrupteur(x,y,new LinkedList<Entity>());
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
	public int hit() {
		return 1;
	}

}
