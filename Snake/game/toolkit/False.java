package toolkit;

import Model_Snake.Entity;

public class False implements ICondition{

	@Override
	public boolean eval(Entity e) {
		return false;
	}

}
