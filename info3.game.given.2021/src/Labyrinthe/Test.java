package Labyrinthe;

import java.io.IOException;
import java.io.PrintStream;

public class Test {
	public static void main(String args[]) throws IOException {
		System.out.println("Hello!");
		Field f = new Field(20, 25, 100, 30, 10, 5, 5, 5, 5, 25, 25, 50, 10);
		// Field f = new Field(10, 10, 50);
		f.printGame();
		// f.printLabyrinthe_tmp();
	}
}
