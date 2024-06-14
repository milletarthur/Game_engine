package Labyrinthe;

public class Apple extends Entity {

	public Apple(int x, int y, int team, int category, Field f) {
		this.x = x;
		this.y = y;
		this.category = category;
		this.team = team;
		this.f = f;
	}

	@Override
	void egg(int x, int y) {
		new Apple(x, y, this.team, this.category, this.f);
	}

	@Override
	public void move() {
	}

	@Override
	public void pick() {
	}

	@Override
	public void turn(int dir) {
	}

	@Override
	public void pop() {
	}

	@Override
	public void wizz() {
	}

	@Override
	public void explode() {
	}

}
