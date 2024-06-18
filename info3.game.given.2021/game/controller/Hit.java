package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Fleche;
import Labyrinthe.Joueur;
import Labyrinthe.Squelette;
import Labyrinthe.Zombie;
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
		if (damage != -1) {
			LinkedList<Entity> list = terrain.getElement(x, y);
			int cpt = 0;
			Entity tohit = list.get(cpt);
			while(!(tohit instanceof Joueur) && !(tohit instanceof Zombie) && !(tohit instanceof Squelette)) {
				cpt++;
				tohit = list.get(cpt);
			}
	//		terrain.remove(x, y, tohit);
			tohit.power(-damage);
	//		terrain.add(tohit, x, y);
		} else {
			Fleche f = new Fleche(x,y,e.team(),Categorie.M,e.direction());
			terrain.add(f, x, y);
		}
		
	}

}
