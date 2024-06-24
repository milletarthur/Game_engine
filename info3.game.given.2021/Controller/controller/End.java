package controller;

import java.util.LinkedList;

import Automates.Automate;
import Labyrinthe.Bombe;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;
import listener.JSONWindow;
import toolkit.Pair;
import draw.DrawEndGame;
import draw.DrawInventaire;
import draw.DrawWindow;

public class End {
	Field field;
	Joueur j1;
	Joueur j2;
	DrawInventaire di;
	DrawWindow w;

	public End(Field f, Joueur j1, Joueur j2, DrawWindow w) {
		this.field = f;
		this.j1 = j1;
		this.j2 = j2;
		this.w = w;
		this.di = w.get_invent();
	}

	
	public void fin() {
		int f = endGame();
		if(f == 0) {
			return;
		}
		else if(f == -1) {
			
		}
		else {
			di.getTimer().stopTimer();
			this.fenetrefinpartie();
			return;
		}
	}
	
	public int endGameArene() {
		// On vérifie la vie des deux joueurs
		if ( j1.getVie() == 0 || j2.getVie() == 0 ) {
			// Si l'un des deux joueurs est mort, on renvoie -1
			return -1 ; 
		}
		// Sinon, on renvoie 0.
		return 0 ; 
	}
	
	public Entity gagnant() throws Exception { 
		
		if ( j2.getVie() != 0 && j1.getVie() != 0 ) {
			throw new Exception("Aucun des joueurs n'est mort !");
		}
		
		if ( j2.getVie() == 0 && j1.getVie() != 0 ) { // j1 gagne
			return j1; 
		} else if ( j1.getVie() == 0 && j2.getVie() != 0 ){ // j2 gagne
			return j2  ; 
		} else { // Les deux sont morts 
			return null ;
		}
	}

	
	/*
	 * -1 : partie perdue 0 : partie pas finie 1 : partie gagnée
	 */
	public int endGame() {
		if (JSONWindow.jeu.equals("Labyrinthe")) {
			int temp = di.gettemp();
			if (temp > 0) {
				if (j1.getVie() > 0) {
					if ((j1.getX() == field.get_ligne() - 4 || j1.getX() == field.get_ligne() - 3)
							&& j1.getY() == field.get_colonne() - 1)
						return 1;
					else
						return 0;
				} else if (j2.getVie() > 0) {
					if ((j2.getX() == field.get_ligne() - 4 || j2.getX() == field.get_ligne() - 3)
							&& j2.getY() == field.get_colonne() - 1)
						return 1;
					else
						return 0;
				} else if (j2.getVie() < 0 && j1.getVie() < 0) {
					return -1;
				}

			} else {
				return -1;
			}
		} else {
			return -1;
		}
//		LinkedList<Entity> l_player = new LinkedList<Entity>();
//		LinkedList<Entity> l_entity = new LinkedList<Entity>();
//		l_player = ListEntity(Joueur.class);
//		Entity elem_player;
//		Entity elem;
//		boolean exit1 = false;
//		boolean exit2 = false;
//		// si il n'y a plus de joueur sur le terrain
//		if(l_player == null || l_player.size() == 0) {
//			return -1;
//		}
//		int cpt = 0;
//		for(int i=0; i<l_player.size(); i++) {
//			elem_player = l_player.get(i);
//			if(elem_player.ligne() == ligne -4 && elem_player.colonne() == colonne -1) {
//				exit1 = true;
//			} else if(elem_player.ligne() == ligne -3 && elem_player.colonne() == colonne -1) {
//				exit2 = true;
//			}
//			// les joueurs sont sortis
//			if(exit1 && exit2) {
//				return 1;
//			}
//			l_entity = getElement(elem_player.ligne(), elem_player.colonne());
//			for(int j=0; j<l_entity.size(); j++) {
//				elem = l_entity.get(j);
//				if(elem instanceof Sable) {
//					cpt++;
//				}
//			}
//		}
//		// si tous les joueurs sont dans des sables mouvants
//		if(cpt == l_player.size()) {
//			return -1;
//		}
//		
		// faire cas avec fin du compte à rebours
		/*
		 * fillBomb(); Entity bombe = null; for (int i = 2; i < ligne - 1; i++) { for
		 * (int j = 1; j < colonne - 1; j++) { l_entity = getElement(i, j); for (int k =
		 * 0; k < l_entity.size(); k++) { elem = l_entity.get(k); if (elem instanceof
		 * Bombe) { bombe = elem; Explode ex = new Explode(this); ex.exec(bombe); return
		 * 999; } } } } return 0; }
		 */
		return 0 ;

	}
	
	public void fenetrefinpartie() {
		DrawEndGame fg = new DrawEndGame(true, j1, j2,w);
	}
}
