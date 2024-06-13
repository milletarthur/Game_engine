package toolkit;

public class Pair<A, T> {
	private A o1;
	private T o2;

	public Pair(A o1, T o2) {
		this.o1 = o1;
		this.o2 = o2;
	}
	
	public void getPair(A o1, T o2) {
		o1 = this.o1;
		o2 = this.o2;
	}
}
