package controller;

import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;
import controller.listener.JSONWindow;
import View.draw.DrawEndGame;
import View.draw.DrawInventaire;
import View.draw.DrawWindow;

public class End {
	Field field;
	Joueur j1;
	Joueur j2;
	DrawInventaire di;
	DrawWindow w;
	private boolean time;

	public End(Field f, Joueur j1, Joueur j2, DrawWindow w) {
		this.field = f;
		this.j1 = j1;
		this.j2 = j2;
		this.w = w;
		this.di = w.get_invent();
		this.time = false;
	}

	public void fin() {
		int f = endGame();
		DrawEndGame fg;
		switch(f) {
		case -1 :
			di.getTimer().stopTimer();
			fg = new DrawEndGame(false, null, null,w);
			return;
		case 0 :
			return;
		case 1 :
			di.getTimer().stopTimer();
			fg = new DrawEndGame(true, j1, null,w);
			return;
		case 2 :
			di.getTimer().stopTimer();
			fg = new DrawEndGame(true, null, j2,w);
			return;
		case 3 :
			di.getTimer().stopTimer();
			fg = new DrawEndGame(true, j1, j2,w);
			return;
		}
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

	public int endGameArene() {
		// On vérifie la vie des deux joueurs
		if (j1.getVie() == 0 || j2.getVie() == 0) {
			// Si l'un des deux joueurs est mort, on renvoie -1
			return -1;
		}
		// Sinon, on renvoie 0.
		return 0;
	}

	/*
	 * -1 : partie perdue 0 : partie pas finie 1 : joueur1 gagne 2 : joueur2 gagne 3 : les deux joueurs gagnent
	 */
	public int endGame() {
		if (JSONWindow.jeu.equals("Labyrinthe")) {
			int temp = di.gettemp();
			if (field.presence_sable(field.getElement(j1.getX(), j1.getY())) && field.presence_sable(field.getElement(j2.getX(), j2.getY()))) {
				return -1;
			}
			else if (temp > 0) {
				if (j1.getVie() > 0 && j2.getVie() > 0) { // si les deux joueurs sont en vie
					if (((j1.getX() == field.get_ligne() - 4 || j1.getX() == field.get_ligne() - 3)
							&& j1.getY() == field.get_colonne() - 1) && ((j2.getX() == field.get_ligne() - 4 || j2.getX() == field.get_ligne() - 3)
							&& j2.getY() == field.get_colonne() - 1)) // les deux joueurs arrivent à la fin
						return 3;
					else if (((j1.getX() == field.get_ligne() - 4 || j1.getX() == field.get_ligne() - 3)
							&& j1.getY() == field.get_colonne() - 1)) { // j1 est à la fin
						if (j2.getVie() <= 0)
							return 1;
						if (!time) { // attend j2
							di.settemp(31);
							di.setcpt();
							time = true;
						}
						return 0;
						
					}
					else if ((j2.getX() == field.get_ligne() - 4 || j2.getX() == field.get_ligne() - 3)
							&& j2.getY() == field.get_colonne() - 1) { // j2 est à la fin
						if (j1.getVie() <= 0)
							return 2;
						if (!time) { // attend j1
							di.settemp(31);
							di.setcpt();
							time = true;
						}
						return 0;}
					else
						return 0;
				} else if (j1.getVie() > 0 && j2.getVie() <= 0) {
					if (((j1.getX() == field.get_ligne() - 4 || j1.getX() == field.get_ligne() - 3)
							&& j1.getY() == field.get_colonne() - 1)) { // j1 est à la fin
						return 1;
					}
					
				} else if (j1.getVie() <= 0 && j2.getVie() > 0) {
					if ((j2.getX() == field.get_ligne() - 4 || j2.getX() == field.get_ligne() - 3)
							&& j2.getY() == field.get_colonne() - 1) { // j2 est à la fin
						return 2;
					}
				}
				else if (j2.getVie() <= 0 && j1.getVie() <= 0) { // les deux sont morts
					return -1;
				}
			} else { // temp < 0
				if ((j1.getX() == field.get_ligne() - 4 || j1.getX() == field.get_ligne() - 3)
						&& j1.getY() == field.get_colonne() - 1)
					return 1;
				else if ((j2.getX() == field.get_ligne() - 4 || j2.getX() == field.get_ligne() - 3)
						&& j2.getY() == field.get_colonne() - 1)
					return 2;
				else
					return -1;
			}
		} else if (JSONWindow.jeu.equals("Arène")) {
			if ( j1.getVie() <= 0 ) { // j1 mort donc j2 gagne
				return 2 ; 
			} else if (j2.getVie() <= 0) { // j2 mort donc j1 gagne
				return 1;
			}
			return 0 ;
		} 
		return 0;
	}

}
