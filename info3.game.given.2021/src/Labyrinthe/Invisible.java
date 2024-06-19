package Labyrinthe;

import toolkit.Categorie;

public class Invisible extends Mur {
	
	public Invisible(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.J;
		this.team = 4;
		this.layer = 4;
	}

	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pop() {
		explode();
	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	@Override
	public int hit() {
		return 0;
	}

}
