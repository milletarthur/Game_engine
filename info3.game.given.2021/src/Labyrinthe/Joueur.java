package Labyrinthe;

import toolkit.Categorie;
import toolkit.Direction;

/*
 * Classe Joueur
 * 
 * ligne et colonne sont les coordonnées de ce joueur dans la matrice
 */
public class Joueur extends Entity {
	
	boolean modeSelection = false;
	
	public Joueur(int ligne, int colonne, int team) {
		this.ligne = ligne;
		this.colonne = colonne;
		super.vie = 10;
		if (team == 1)
			this.category = Categorie.Arobase;
		else
			this.category = Categorie.Diese;
		this.team = team;
		layer = 3;
		inventory = new Inventory();
	}
	
	public Entity egg(int ligne, int colonne) {
		return new Joueur(ligne,colonne,team);
	}

	public Entity picked() {
		return picked;
	}

	@Override
	public void pop() {
		modeSelection = !modeSelection;
	}

	@Override
	public void wizz() {
		super.turn(Direction.B);
	}

	public int hit() {
		if (picked == null)
			return 1;
		return picked.hit();
	}

	@Override
	public void get() {
		switch (team()) {
		case 1:
			picked = inventory.popJ1();
			break;
		case 2:
			picked = inventory.popJ2();
			break;
		default:
			break;
		}
	}

	public void setOrientation(int or) {
		this.Orientation = or;
	}

	public int getX() {
		return this.ligne;
	}

	public int getY() {
		return this.colonne;
	}

	@Override
	public void jump() {
		if(inventory != null && inventory.size() <= 0) {
			if(team() == 1) {
				inventory.NextCurrentJ1();
			} else if (team() == 2) {
				inventory.NextCurrentJ2();
			}
		}
	}
}
