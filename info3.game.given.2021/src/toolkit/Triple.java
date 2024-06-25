package toolkit;

public class Triple<A, T, Z> {
    private A o1;
    private T o2;
    private Z o3;

    public Triple(A o1, T o2, Z o3) {
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
    }
    
    public A x() {
    	return o1;
    }
    
    public T y() {
    	return o2;
    }
    
    public Z z() {
    	return o3;
    }
    
    public void setx(A x) {
    	o1 = x;
    }
    
    public void sety(T y) {
    	o2 = y;
    }
    
    public void setz(Z z) {
    	o3 = z;
    }
}
