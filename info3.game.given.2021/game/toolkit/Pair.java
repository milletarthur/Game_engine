package toolkit;

public class Pair<A, T> {
    private A o1;
    private T o2;

    public Pair(A o1, T o2) {
        this.o1 = o1;
        this.o2 = o2;
    }
    
    public A x() {
    	return o1;
    }
    
    public T y() {
    	return o2;
    }
    
    public void setx(A x) {
    	o1 = x;
    }
    
    public void sety(T y) {
    	o2 = y;
    }
}
