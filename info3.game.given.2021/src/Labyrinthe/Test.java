package Labyrinthe;

import java.io.IOException;
import java.io.PrintStream;

public class Test {
	public static void main(String args[]) throws IOException {
		System.out.println("Hello!");
		Field f = new Field(15, 15, 100, 20, 10, 5, 5, 5, 5, 25, 25, 50, 10);
		f.printGame();
		// f.printLabyrinthe_tmp();
	}
}
