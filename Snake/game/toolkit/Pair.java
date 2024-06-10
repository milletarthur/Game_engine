package toolkit;

public class Pair<X,Y> {
	private X x;
	private Y y;

	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public X x() {
		return x;
	}

	public Y y() {
		return y;
	}
	
	public void setx(X x) {
		this.x = x;
	}

	public void sety(Y y) {
		this.y = y;
	}
}
