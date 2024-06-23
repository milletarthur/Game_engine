package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.*;
import toolkit.Categorie;
import toolkit.Direction;

public class Pop implements IAction {

	private Field terrain;

	public Pop(Field f) {
		terrain = f;
	}

	@Override
	public void exec(Entity e) {
		if(e instanceof Joueur) {
			Entity entity = new Selection(e.ligne(), e.colonne());
			entity.setTeam(e.team());
			terrain.add(entity, e.ligne(), e.colonne());
		} else if (e instanceof Interrupteur) {
			LinkedList<Entity> l_levier = ((Interrupteur) e).get_entity();
			for(int i=0; i<l_levier.size(); i++) {
				exec(l_levier.get(i));
			}
		} else if (e instanceof Arc) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Epee(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if (e instanceof Epee) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Arc(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if (e instanceof Cassable) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Invisible(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if (e instanceof Normal) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Cassable(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if (e instanceof Invisible) {
			terrain.remove(e.ligne(), e.colonne(), e);
			terrain.add(new Normal(e.ligne(), e.colonne()), e.ligne(), e.colonne());
		} else if (e instanceof Zombie) {
			e.setTeam(((Zombie) e).getOtherTeam());
		} else if (e instanceof Squelette) {
			e.setTeam(((Squelette) e).getOtherTeam());
		} else if (e instanceof Sable) {
			LinkedList<Entity> l_entity;
			LinkedList<LinkedList<Entity>> l_around = terrain.getAround(e.ligne(), e.colonne());
			for (int j = 0; j < l_around.size(); j++) {
				l_entity = l_around.get(j);
				Entity elem;
				Entity elem_layer_max = l_entity.getFirst();
				int ligne = l_entity.getFirst().ligne();
				int colonne = l_entity.getFirst().colonne();
				boolean possible = true;
				for (int i = 0; i < l_entity.size(); i++) {
					elem = l_entity.get(i);
					if(elem.layer() == e.layer() && 
							(elem.category() == Categorie.P || elem instanceof Mine)) {
						terrain.remove(ligne, colonne, elem_layer_max);
						terrain.add(e.egg(ligne, colonne), ligne, colonne);
						possible = false;
						break;
					} else if(elem.layer() == e.layer()) {
						possible = false;
						break;
					}
				}
				if(possible) {
					terrain.add(e.egg(ligne, colonne), ligne, colonne);
					e.pop();
				}
			}
		} else if (e instanceof Pioche) {
			int coo[] = terrain.next_to_outside(e, e.direction());
			if (coo[0] < 0 || coo[0] > terrain.get_ligne() - 1 || coo[1] < 0 || coo[1] > terrain.get_colonne() - 1)
				return;
			Entity elem = terrain.getLastnotSelect(coo[0], coo[1]);
			terrain.remove(coo[0], coo[1], elem);
//			Entity elem;
//			switch (e.direction()) {
//			case Direction.N:
//				elem = terrain.getLastnotSelect(e.ligne() - 1, e.colonne());
//				if (elem instanceof Cassable) {
//					terrain.remove(e.ligne() - 1, e.colonne(), elem);
//				}
//				break;
//			case Direction.S:
//				elem = terrain.getLastnotSelect(e.ligne() + 1, e.colonne());
//				if (elem instanceof Cassable) {
//					terrain.remove(e.ligne() + 1, e.colonne(), elem);
//				}
//				break;
//			case Direction.E:
//				elem = terrain.getLastnotSelect(e.ligne(), e.colonne() + 1);
//				if (elem instanceof Cassable) {
//					terrain.remove(e.ligne(), e.colonne() + 1, elem);
//				}
//				break;
//			case Direction.W:
//				elem = terrain.getLastnotSelect(e.ligne(), e.colonne() - 1);
//				if (elem instanceof Cassable) {
//					terrain.remove(e.ligne(), e.colonne() - 1, elem);
//				}
//				break;
//			}
		} else if (e instanceof Bombe || e instanceof Mine) {
			Explode ex = new Explode(terrain);
			ex.exec(e);
		}
		e.pop();
	}

	@Override
	public String toString() {
		String s = "Pop";
		return s;
	}

}
