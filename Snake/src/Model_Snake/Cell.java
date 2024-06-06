package Model_Snake;

public abstract class Cell {
	public static final int NORTH = 1;
	public static final int SOUTH = 2;
	public static final int EAST = 3;
	public static final int WEST = 4;
	
	protected int x;
	protected int y;
	
	protected int Orientation = NORTH;
	
	protected boolean Valid = true;
	
	
	abstract void egg(int x, int y);


	void kill() {
		this.Valid = false;
	}

	boolean valid() {
		return this.Valid;
	}
	
	int x() {
		return x;
	}
	
	int y() {
		return y;
	}
}
