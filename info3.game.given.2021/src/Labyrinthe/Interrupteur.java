package Labyrinthe;

import java.util.LinkedList;

import toolkit.Categorie;

public class Interrupteur extends Entity {
	
	private LinkedList<Entity> liste_elem;
	
	public Interrupteur(int x, int y, int team, LinkedList<Entity> elem) {
		this.x = x;
		this.y = y;
		this.category = Categorie.C;
		this.layer = 2;
		this.team = team;
		this.liste_elem = elem;
	}

	public LinkedList<Entity> get_entity() {
		return this.liste_elem;
	}
	
	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
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
