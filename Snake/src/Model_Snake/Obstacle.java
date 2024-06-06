package Model_Snake;

public class Obstacle extends Cell {
	
	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	void egg(int x, int y) {
		new Obstacle(x,y);
		
	}

	@Override
	void kill() {
		this.Valid = false;
	}

	@Override
	boolean valid() {
		return this.Valid;
	}

}
