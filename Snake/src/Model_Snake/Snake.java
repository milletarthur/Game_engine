package Model_Snake;

import java.util.List;

public class Snake extends Cell{
	List queue;
	
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
