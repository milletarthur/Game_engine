package Labyrinthe;

import java.util.LinkedList;

import controller.listener.JSONWindow;
import toolkit.Categorie;

public class Interrupteur extends Entity {

	private LinkedList<Entity> liste_elem;
	private int state = 0; // -1 := pop ; 1 := wizz

	public Interrupteur(int ligne, int colonne, LinkedList<Entity> elem) {
		this.colonne = colonne;
		this.ligne = ligne;
		if (JSONWindow.ispick_interrupteur)
			this.category = Categorie.P;
		else 
			this.category = Categorie.C;
		this.layer = 2;
		this.team = 7;
		this.liste_elem = elem;
	}

	public LinkedList<Entity> get_entity() {
		return this.liste_elem;
	}
	
	public void remove_entity(Entity e) {
		liste_elem.remove(e);
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
		if(JSONWindow.jeu.equals("arene")) {
			Entity elem;
			LinkedList<Entity> new_l = new LinkedList<Entity>();
			for(int i=0; i<liste_elem.size(); i++) {
				elem = liste_elem.get(i);
				if(elem instanceof Teleporteur || elem instanceof Joueur) {
					continue;
				}
				elem.pop();
				if(elem instanceof Porte) {
					new_l.add(elem);
				}
			}
			liste_elem = new_l;
		} else if(JSONWindow.jeu.equals("labyrinthe")) {
			Entity elem;
			for(int i=0; i<liste_elem.size(); i++) {
				elem = liste_elem.get(i);
				if(elem instanceof Teleporteur || elem instanceof Joueur) {
					continue;
				}
				elem.pop();
			}
		}
		
	}

	@Override
	public void wizz() {
		if(JSONWindow.jeu.equals("arene")) {
			Entity elem;
			LinkedList<Entity> new_l = new LinkedList<Entity>();
			for(int i=0; i<liste_elem.size(); i++) {
				elem = liste_elem.get(i);
				if(elem instanceof Teleporteur || elem instanceof Joueur) {
					continue;
				}
				elem.wizz();
				if(elem instanceof Porte) {
					new_l.add(elem);
				}
			}
			liste_elem = new_l;
		} else if(JSONWindow.jeu.equals("labyrinthe")) {
			Entity elem;
			for(int i=0; i<liste_elem.size(); i++) {
				elem = liste_elem.get(i);
				if(elem instanceof Teleporteur || elem instanceof Joueur) {
					continue;
				}
				elem.wizz();
			}
		}
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

	@Override
	public void jump() {
		// TODO Auto-generated method stub

	}
}