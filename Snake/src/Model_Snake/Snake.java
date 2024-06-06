package Model_Snake;

import java.util.LinkedList;

public class Snake extends Entity{
	LinkedList queue;
	
	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
		this.queue = new LinkedList();
	}
	
	void grow() {
		throw new RuntimeException("Not implemented Yet !");
	}
	
	void move() {
		throw new RuntimeException("Not implemented Yet !");
	}
	
	void turn(int dir) {
		throw new RuntimeException("Not implemented Yet !");
	}

	@Override
	void egg(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void kill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean valid() {
		// TODO Auto-generated method stub
		return false;
	}
}
