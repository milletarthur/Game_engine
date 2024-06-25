package Labyrinthe;

import toolkit.Categorie;

public class Selection extends Entity {
	
	private int ligne_joueur;
	private int colonne_joueur;
	private Joueur j;

	public Selection(int ligne, int colonne, int team) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.T;
		this.team = team;
		layer = 5;
		ligne_joueur = ligne;
		colonne_joueur = colonne;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Selection(ligne, colonne, team);
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}
	
	public int getLigneJoueur() {
		return ligne_joueur;
	}
	
	public int getColonneJoueur() {
		return colonne_joueur;
	}
	
	public void setJoueur(Joueur j) {
		this.j = j;
	}
	
	public Joueur getJoueur() {
		return j;
	}
	
}
