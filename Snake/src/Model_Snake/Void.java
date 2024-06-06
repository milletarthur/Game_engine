package Model_Snake;

public class Void extends Entity {

	public Void(int x, int y, int team, int category, Field f) {
		this.x = x;
		this.y = y;
		this.category = category;
		this.team = team;
		this.f = f;
	}

	@Override
	void egg(int x, int y) {
		new Void(x, y, this.team, this.category, this.f);
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

}
