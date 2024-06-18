package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.Apple;
import Labyrinthe.Cassable;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Fleche;
import Labyrinthe.Joueur;
import Labyrinthe.Potion;
import Labyrinthe.Selection;
import Labyrinthe.Squelette;
import Labyrinthe.Zombie;
import Labyrinthe.Void;
import toolkit.Categorie;

public class Hit implements IAction {
	
	public Entity entity;
	public Field terrain;

	public Hit(Entity entity, Field terrain) {
		this.entity = entity;
		this.terrain = terrain;
	}
	
	public Hit(Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Entity e) {
		entity = e;		
	}

	@Override
	public void exec(Entity e) {
		int damage = e.hit(); //renvoie les dégats que fait l'entitée a une autre. (positif)
		int[] spot = terrain.next_to(e, e.direction());
		int x = spot[0];
		int y = spot[1];
		LinkedList<Entity> list = terrain.getElement(x, y);
		int cpt = 0;
		Entity tohit = list.get(cpt);
		int taille = list.size();
		if (list.getLast() instanceof Selection)
			taille--;
		while(!(tohit instanceof Joueur) && !(tohit instanceof Zombie) && !(tohit instanceof Squelette) && cpt < taille) {
			cpt++;
			tohit = list.get(cpt);
		}
		if (damage > 0) {
	//		terrain.remove(x, y, tohit);
			if ((tohit instanceof Joueur) || (tohit instanceof Zombie) || (tohit instanceof Squelette))
				tohit.power(-damage);
	//		terrain.add(tohit, x, y);
		} else if (damage == -1){ // cas arc
			Fleche f = new Fleche(x,y,e.team(),Categorie.M,e.direction());
			terrain.add(f, x, y);
		} else if (damage == -2) { // cas Pioche
			if (tohit instanceof Labyrinthe.Void) {
				Wizz wi = new Wizz(((Joueur)e).picked(),terrain);
				wi.exec(((Joueur)e).picked());
			} else if (tohit instanceof Cassable) {
				Pop po = new Pop(terrain,((Joueur)e).picked());
				po.exec(((Joueur)e).picked());
			} else if ((tohit instanceof Joueur) || (tohit instanceof Zombie) || (tohit instanceof Squelette)) {
				tohit.power(-2);
			}
		} else if (damage == -3) { // cas Pomme
			Apple apple = (Apple) ((Joueur)e).picked();
			if (apple.poisoned()) { // true == enpoisonée
				e.power(-2);
			} else {
				e.power(2);
			}
		} else if (damage == -4) { // cas Potion
			Potion potion = (Potion) ((Joueur)e).picked();
			if (potion.poisoned()) { // true == enpoisonée
				e.power(-2);
			} else {
				e.power(2);
			}
		} else if (damage == -5) { // cas bombe
			Explode ex = new Explode(((Joueur)e).picked(), terrain);
			ex.exec(((Joueur)e).picked());
		}
		
	}

}
