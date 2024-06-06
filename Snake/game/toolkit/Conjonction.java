package toolkit;

import Model_Snake.Entity;

public class Conjonction implements ICondition {

	private ICondition c1;
	private ICondition c2;
	
	public Conjonction(ICondition c1, ICondition c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	@Override
	public boolean eval(Entity e) {
		return c1.eval(e) && c2.eval(e);
	}

}
