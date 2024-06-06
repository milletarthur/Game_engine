package toolkit;

public class Not implements ICondition {
	ICondition c;
	
	public Not(ICondition c) {
		this.c = c;
	}
	
	@Override
	public boolean eval(Entity e) {
		return !(c.eval(e));
	}

}
