package Labyrinthe;

import toolkit.Categorie;

public class Squelette extends Entity {
	
	private int otherTeam = 0;
	
	public Squelette(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.A;
		this.team = 0;
		layer = 3;
	}

	@Override
	public Entity egg(int ligne, int colonne) {
		return new Squelette(ligne,colonne);
	}

	@Override
	public void pop() {
		setTeam(otherTeam);
	}

	@Override
	public void wizz() {
		team = 0;
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public void setOtherTeam(int team) {
		otherTeam = team;
	}

}
