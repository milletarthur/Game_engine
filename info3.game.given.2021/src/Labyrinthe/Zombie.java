package Labyrinthe;

import toolkit.Categorie;

public class Zombie extends Entity {
	
	public Zombie(int x, int y) {
		this.x = x;
		this.y = y;
		this.category = Categorie.A;
		this.team = 0;
		layer = 3;
	}

	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
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
	public int hit() {
		// TODO Auto-generated method stub
		return 3;
	}

}
