package Labyrinthe;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class Test {
	public static void main(String args[]) throws IOException {
		System.out.println("Hello!");
		/* (int lig, int col, int densite_field, int densite_pickable, int mine, int pomme, int potion, int pioche,
		int bombe, int cassable, int invisible, int normal, int nb_porte_sable, int nb_ennemis, Random r)
		*/
		Field f = new Field(20, 25, 95, 0, 0, 0, 0, 0, 0, 50, 25, 25, 10, 2, new Random(0));
		f.printGame();
		// f.printLabyrinthe_tmp();
	}
}
