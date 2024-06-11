package Model_Snake;

import java.io.IOException;
import java.io.PrintStream;

public class Test {
	public static void main(String args[]) throws IOException {
		System.out.println("Hello!");
		Field f = new Field(10,10);
		f.printGame(System.out);
	}

}
