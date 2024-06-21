package Labyrinthe;

import toolkit.Categorie;

public class Teleporteur extends Entity {

	boolean TP_random;
	Entity voisin;

	public Teleporteur(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.G;
		this.team = 6;
		this.layer = 0;
		TP_random = false;
	}

	public void set_voisin(Entity en1) {
		this.voisin = en1;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Teleporteur(ligne, colonne);
	}

	@Override
	public void pop() {
		TP_random = false;
	}

	@Override
	public void wizz() {
		TP_random = true;
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

}
