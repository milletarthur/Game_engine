package Labyrinthe;

import java.io.IOException;
import java.io.PrintStream;

public class Test {
	public static void main(String args[]) throws IOException {
		//for (int i = 0; i < 3; i++) {
			System.out.println("Hello!");
			Field f = new Field(20, 20);
			f.printLabyrinthe();
		}
	//}
}
