package Labyrinthe;

import toolkit.Categorie;

public class Zombie extends Entity {
	
	private int otherTeam = 0;
	
	public Zombie(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.category = Categorie.A;
		this.team = 0;
		layer = 3;
	}

	@Override
	public Entity egg(int lign, int colonne) {
		return new Zombie(ligne,colonne);
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
		return 3;
	}
	
	public void setOtherTeam(int team) {
		otherTeam = team;
	}
	
	public int getOtherTeam() {
		return otherTeam;
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

}
