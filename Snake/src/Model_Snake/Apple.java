package Model_Snake;

public class Apple extends Cell {

	public Apple(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	void egg(int x, int y) {
		// TODO Auto-generated method stub
		new Apple(x, y);
	}

	@Override
	void kill() {
		// TODO Auto-generated method stub
		this.Valid = false;

	}

	@Override
	boolean valid() {
		// TODO Auto-generated method stub
		return this.Valid;
	}

}
