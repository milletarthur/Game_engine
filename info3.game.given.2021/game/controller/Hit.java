package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.*;

public class Hit implements IAction {
	
	private TickListener tl;
	public Field terrain;

	public Hit(Field terrain, TickListener tl) {
		this.terrain = terrain;
		this.tl = tl;
	}

	@Override
	public void exec(Entity e) {
		int damage = e.hit(); // renvoie les dégats que fait l'entitée a une autre. (positif)
		int[] coo = terrain.next_to_outside(e, e.direction());
		if (coo[0] < 0 || coo[0] > terrain.get_ligne() - 1 || coo[1] < 0 || coo[1] > terrain.get_colonne() - 1)
			return;
		int ligne = coo[0];
		int colonne = coo[1];
		LinkedList<Entity> list = terrain.getElement(ligne, colonne);
		int cpt = 0;
		Entity tohit = list.get(cpt);
		int taille = list.size();
		if (list.getLast() instanceof Selection)
			taille--;
		while (!(tohit instanceof Joueur) && !(tohit instanceof Zombie) && !(tohit instanceof Squelette)
				&& cpt < taille) {
			tohit = list.get(cpt);
			cpt++;
		}
		if (damage > 0) {
			// terrain.remove(ligne, colonne, tohit);
			if ((tohit instanceof Joueur) || (tohit instanceof Zombie) || (tohit instanceof Squelette)) {
				tohit.power(-damage);
			}
			// terrain.add(tohit, ligne, colonne);
		} else if (damage == -1) { // cas arc et flèche classique
			Fleche f = new Fleche(ligne, colonne, e.direction());
			terrain.add(f, ligne, colonne);
		} else if (damage == -7) { // cas arc et flèche transpercante
			Fleche f = new Fleche(ligne, colonne, e.direction(), true);
			terrain.add(f, ligne, colonne);
		} else if (damage == -2) { // cas Pioche
			if (tohit instanceof Labyrinthe.Void) {
				Wizz wi = new Wizz(terrain, tl);
				wi.exec(((Joueur) e).picked());
				e.resetpick();
			} else if (tohit instanceof Cassable) {
				Pop po = new Pop(terrain, tl);
				po.exec(((Joueur) e).picked());
				e.resetpick();
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
			e.resetpick();
		} else if (damage == -4) { // cas Potion
			Potion potion = (Potion) ((Joueur) e).picked();
			if (potion.poisoned()) { // true == empoisonée
				e.power(-2);
			} else {
				e.power(2);
			}
			e.resetpick();
		} else if (damage == -5) { // cas bombe
			Explode ex = new Explode(terrain, tl);
			ex.exec(((Joueur) e).picked());
			e.resetpick();
		} else if (damage == -6) { // cas épée avec hitCircle
			for (int i = 0; i < 8; i++) {
				coo = terrain.next_to_outside(e, e.direction());
				if (coo[0] < 0 || coo[0] > terrain.get_ligne() - 1 || coo[1] < 0 || coo[1] > terrain.get_colonne() - 1)
					continue;
				ligne = coo[0];
				colonne = coo[1];
				list = terrain.getElement(ligne, colonne);
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
				if (damage > 0) {
					if ((tohit instanceof Joueur) || (tohit instanceof Zombie) || (tohit instanceof Squelette)) {
						tohit.power(-3);
					}
				}
				e.turn(((e.direction() + 1) % 8) + 1);
			}
			((Epee) e).setHitCircle(false);
		}

	}

	@Override
	public String toString() {
		String s = "Hit";
		return s;
	}

}
