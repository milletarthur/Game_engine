package Labyrinthe;

import java.io.IOException;
import java.io.PrintStream;

public class Test {
	public static void main(String args[]) throws IOException {
		System.out.println("Hello!");
		//Field f = new Field(15, 20, 20);
		Field f = new Field(15, 20, 20, 0);
		f.printGame();
	}
}
