package toolkit;

import Model_Snake.Entity;

public class True implements ICondition{

	@Override
	public boolean eval(Entity e) {
		return true;
	}
	
}
