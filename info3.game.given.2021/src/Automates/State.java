package Automates;

public class State {
	private String name;
	
	public State(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	boolean equals(State s1, State s2) {
		String name1 = s1.getName();
		String name2 = s2.getName();
		return name1.equals(name2);
	}
}
