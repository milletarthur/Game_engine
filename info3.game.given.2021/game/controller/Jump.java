package controller;

import java.util.LinkedList;
import java.util.Random;

import Automates.IAction;
import Labyrinthe.*;
import Labyrinthe.Void;
import toolkit.Categorie;
import toolkit.Pair;

public class Jump implements IAction {

	private Field terrain;
	private TickListener tl;

	public Jump(Field terrain, TickListener tl) {
		this.terrain = terrain;
		this.tl = tl;
	}

	@Override
	public void exec(Entity e) {
		if (e instanceof Zombie) {
			LinkedList<Entity> l_entity = terrain.getElement(e.ligne(), e.colonne());
			Entity entity = l_entity.getLast();
			if (entity instanceof Selection) {
				int team = entity.team();
				((Zombie) e).setOtherTeam(team);
			}
		} else if (e instanceof Squelette) {
			LinkedList<Entity> l_entity = terrain.getElement(e.ligne(), e.colonne());
			Entity entity = l_entity.getLast();
			if (entity instanceof Selection) {
				int team = entity.team();
				((Squelette) e).setOtherTeam(team);
			}
		} else if(e instanceof Teleporteur) {
//			if(!((Teleporteur)e).IsActivate()) {
//				return;
//			}
			if(((Teleporteur)e).IsTpRandom()) {
				Random rand = new Random();
				LinkedList<Pair<Integer, Integer>> l_void = terrain.recup_liste_void_cassable_invisible();
				int value = rand.nextInt(l_void.size());
				Pair<Integer, Integer> p = l_void.get(value);
				while(!(terrain.getElement(p.geto1(), p.geto2()).getLast() instanceof Void ||
						terrain.getElement(p.geto1(), p.geto2()).getLast() instanceof Invisible)) {
					value = rand.nextInt(l_void.size());
					p = l_void.get(value);
				}
				LinkedList<Entity> l_entity = terrain.getElement(e.ligne(), e.colonne());
				Entity elem;
				for(int i=0; i<l_entity.size(); i++) {
					elem = l_entity.get(i);
					if(elem instanceof Joueur || elem instanceof Squelette || elem instanceof Zombie) {
						elem.set_ligne(p.geto1());
						elem.set_colonne(p.geto2());
						terrain.remove(e.ligne(), e.colonne(), elem);
						terrain.add(elem, elem.ligne(), elem.colonne());
					}
				}
			} else {
				Entity voisin = ((Teleporteur) e).getVoisin();
				LinkedList<Entity> l_entity = terrain.getElement(e.ligne(), e.colonne());
				LinkedList<Entity> l_voisin = terrain.getElement(voisin.ligne(), voisin.colonne());
				Entity elem;
				Entity nextToVoisin = null;
				for(int i=0; i<l_entity.size(); i++) {
					elem = l_entity.get(i);
					if(elem instanceof Joueur || elem instanceof Squelette || elem instanceof Zombie) {
						int ligne = 0;
						int colonne = 0;
						int[] around;
						Entity entity;
						for(int j=1; j<5; j++) {
							around = terrain.next_to(voisin, j);
							ligne = around[0];
							colonne = around[1];
							entity = terrain.getLastnotSelect(ligne, colonne);
							if(entity instanceof Void || entity.category() == Categorie.P) {
								break;
							} else if (entity instanceof Invisible) {
								l_voisin = terrain.getElement(ligne, colonne);
								for(int k=0; k<l_voisin.size(); k++) {
									nextToVoisin = l_voisin.get(k);
									if(nextToVoisin.layer() == 3) {
										break;
									}
								}
								if(nextToVoisin != null && nextToVoisin.layer() == 3) {
									continue;
								} else if(nextToVoisin != null && nextToVoisin.layer() != 3) {
									break;
								}
								
							} else if(entity.layer() == 3) {
								continue;
							}
						}
						elem.set_ligne(ligne);
						elem.set_colonne(colonne);
						terrain.remove(e.ligne(), e.colonne(), elem);
						terrain.add(elem, elem.ligne(), elem.colonne());
						e.jump();
						return;
					}
				}
			}
			e.jump();
			return;
		}
		e.jump();
		if (e instanceof Joueur)
			System.out.print(e.getInventory().toString());
	}

	@Override
	public String toString() {
		String s = "Jump";
		return s;
	}

}
