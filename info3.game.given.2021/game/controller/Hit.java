package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.*;
import toolkit.Categorie;
import toolkit.Direction;

public class Hit implements IAction {

	public Field terrain;

	public Hit(Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		int damage = e.hit(); // renvoie les dégats que fait l'entitée a une autre. (positif)
		int[] spot = terrain.next_to(e, e.direction());
		int x = spot[0];
		int y = spot[1];
		LinkedList<Entity> list = terrain.getElement(x, y);
		int cpt = 0;
		Entity tohit = list.get(cpt);
		int taille = list.size();
		if (list.getLast() instanceof Selection)
			taille--;
		while (!(tohit instanceof Joueur) && !(tohit instanceof Zombie) && !(tohit instanceof Squelette)
				&& cpt < taille) {
			cpt++;
			tohit = list.get(cpt);
		}
		if (damage > 0) {
			// terrain.remove(x, y, tohit);
			if ((tohit instanceof Joueur) || (tohit instanceof Zombie) || (tohit instanceof Squelette)) {
				tohit.power(-damage);
			}
			// terrain.add(tohit, x, y);
		} else if (damage == -1) { // cas arc
			Arc arc = (Arc) e;
			Fleche f;
			if(arc.getTrans()) {
				f = new Fleche(arc.ligne(), arc.colonne(), arc.direction(), true);
			} else {
				f = new Fleche(x, y, e.direction());
			}
			((Arc)e).setFleche(f);
			f.hit();
			terrain.add(f, x, y);
		} else if (damage == -2) { // cas Pioche
			if (tohit instanceof Labyrinthe.Void) {
				Wizz wi = new Wizz(terrain);
				wi.exec(((Joueur) e).picked());
			} else if (tohit instanceof Cassable) {
				Pop po = new Pop(terrain);
				po.exec(((Joueur) e).picked());
			} else if ((tohit instanceof Joueur) || (tohit instanceof Zombie) || (tohit instanceof Squelette)) {
				tohit.power(-2);
			}
		} else if (damage == -3) { // cas Pomme
			Apple apple = (Apple) ((Joueur) e).picked();
			if (apple.poisoned()) { // true == empoisonée
				e.power(-2);
			} else {
				e.power(2);
			}
		} else if (damage == -4) { // cas Potion
			Potion potion = (Potion) ((Joueur) e).picked();
			if (potion.poisoned()) { // true == empoisonée
				e.power(-2);
			} else {
				e.power(2);
			}
		} else if (damage == -5) { // cas bombe
			Explode ex = new Explode(terrain);
			ex.exec(((Joueur) e).picked());
		} else if (damage == -6) { // cas épée avec hitCircle
			for (int i = 0; i < 8; i++) {
				spot = terrain.next_to(e, e.direction());
				x = spot[0];
				y = spot[1];
				list = terrain.getElement(x, y);
				cpt = 0;
				tohit = list.get(cpt);
				taille = list.size();
				if (list.getLast() instanceof Selection)
					taille--;
				while (!(tohit instanceof Joueur) && !(tohit instanceof Zombie) && !(tohit instanceof Squelette)
						&& cpt < taille) {
					cpt++;
					tohit = list.get(cpt);
				}
				if(damage > 0) {
					if ((tohit instanceof Joueur) || (tohit instanceof Zombie) || (tohit instanceof Squelette)) {
						tohit.power(-3);
					}
				}
				e.turn(((e.direction() + 1) % 8) + 1);
			}
			((Epee)e).setHitCircle(false);
		}

	}

}
