package Labyrinthe;

public class Potion extends Entity {
	
	boolean poison;
	private int layer = 2;

	@Override
	public Entity egg(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pop() {
		poison = false;
	}

	@Override
	public void wizz() {
		poison = true;
	}
	
	public boolean poisoned() {
		return poison;
	}

	@Override
	public int hit() {
		// TODO Auto-generated method stub
		return -4;
	}

}
