package Labyrinthe;

import toolkit.Categorie;

public class Teleporteur extends Entity {
	
	boolean TP_random;
	
	
	public Teleporteur(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.G;
		this.team = 6;
		this.layer = 0;
		TP_random = false;
	}

	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
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

}
