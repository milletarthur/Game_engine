package Labyrinthe;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Field {
	private int colonne;
	private int ligne;
	private int[][] labyrinthe;
	
	public final static int Mur = -1;
	public final static int Void = 0;
	public final static int Pomme = 1;
	public final static int Potion = 2;
	public final static int Epee = 3;
	public final static int Arc = 4;

	public Field(int lig, int col) {
		if (col % 2 == 0) {
			col++;
		}
		if (lig % 2 == 0) {
			lig++;
		}
		labyrinthe = new int[lig][col];
		this.colonne = col;
		this.ligne = lig;
		grille(lig, col);
		grille2(lig, col);
	}
	
	public void grille(int l, int c) {
		int nombre = 0;
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < c; j++) {
				if (i % 2 == 0) {
					labyrinthe[i][j] = -1;
				} else if (i % 2 != 0 && j % 2 != 0) {
					labyrinthe[i][j] = nombre;
					nombre++;

				} else {
					labyrinthe[i][j] = -1;
				}
			}
		}
	}

	public void grille2(int l, int c) {

		Random rand = new Random();
		int tmp[][] = labyrinthe;
		int c1 = labyrinthe[1][0];
		int c2;
		int count = 0;
		while (is_finished(c1) == 0 && count <= ligne * colonne * 2) {
			// System.out.println("################################################################");
			// System.out.println(count);
			// printLabyrinthe();
			int x = rand.nextInt(l - 2) + 1;
			int y;

			if (x % 2 == 0) {
				y = (rand.nextInt((c - 1) / 2)) * 2 + 1;
			} else {
				y = (rand.nextInt((c - 2) / 2)) * 2 + 2;
			}

			if (labyrinthe[x - 1][y] == -1) {
				c1 = labyrinthe[x][y - 1];
				c2 = labyrinthe[x][y + 1];
			} else {
				c1 = labyrinthe[x - 1][y];
				c2 = labyrinthe[x + 1][y];
			}
			if (c1 != c2) {
				labyrinthe[x][y] = 0;
				for (int i = 1; i < l - 1; i = i + 2) {
					for (int j = 1; j < c - 1; j = j + 2) {
						if (labyrinthe[i][j] == c2) {
							labyrinthe[i][j] = c1;
						}

						labyrinthe[1][0] = c1;
						labyrinthe[ligne - 2][colonne - 1] = c1;
					}
				}
			}
			count++;
		}
	}

	/*
	 * Entity elementAt(int x, int y) { return labyrinthe[x][y]; }
	 */

	public int get_ligne() {
		return this.ligne;
	}

	public int get_colonne() {
		return this.colonne;
	}

	void c_init(int c) {
		this.colonne = c;
	}

	void l_init(int l) {
		this.ligne = l;
	}

	/*
	 * public void printGame(PrintStream ps) { for (int i = 0; i < this.ligne; i++)
	 * { for (int j = 0; j < this.colonne; j++) { Entity e = elementAt(i, j); if (e
	 * instanceof Void) ps.print(" "); if (e instanceof Mur) ps.print("O"); if (e
	 * instanceof Apple) ps.print("*"); } } }
	 */
	public int is_finished(int c) {
		for (int i = 1; i < ligne; i++) {
			for (int j = 1; j < colonne; j++) {
				if (!((labyrinthe[i][j] == -1 || labyrinthe[i][j] == c))) {
					return 0;
				}
			}
		}
		return 1;
	}

	public void printLabyrinthe() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (labyrinthe[i][j] == -1) {
					System.out.print("O");
				} else if (labyrinthe[i][j] == -3) {
					System.out.print("*");
				} else {
					// System.out.print(labyrinthe[i][j]);
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}

	public void grow() {
		int nb_ligne = ligne;
		int nb_colonne = 2 * colonne;
		int[][] new_labyrinthe = new int[nb_ligne][nb_colonne];
		for (int i = 0; i < ligne; i++) {
			int cpt = 0;
			for (int j = 0; j < colonne; j++) {
				new_labyrinthe[i][cpt] = labyrinthe[i][j];
				if(labyrinthe[i][j] != -3) {
					new_labyrinthe[i][++cpt] = labyrinthe[i][j];
				} else {
					new_labyrinthe[i][++cpt] = 0;
				}
				cpt++;
			}
		}
		labyrinthe = new_labyrinthe;
		colonne = nb_colonne;
		nb_ligne = 2 * ligne;
		nb_colonne = colonne;
		new_labyrinthe = new int[nb_ligne][nb_colonne];
		int cpt = 0;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				new_labyrinthe[cpt][j] = labyrinthe[i][j];
			}
			cpt++;
			for (int j = 0; j < colonne; j++) {
				if(labyrinthe[i][j] != -3) {
					new_labyrinthe[cpt][j] = labyrinthe[i][j];
				} else {
					new_labyrinthe[cpt][j] = 0;
				}
			}
			cpt++;
		}
		labyrinthe = new_labyrinthe;
		ligne = nb_ligne;
	}
	
	public void Obstacle(int densite, int val) {
		Random random = new Random();
		for(int i=0; i<ligne; i++) {
			for(int j=0; j<colonne; j++) {
				if(labyrinthe[i][j] != -1) {
					int rdm = random.nextInt(100);
					if(rdm <= densite) {
						labyrinthe[i][j] = val;
					}
				}
			}
		}
	}

}
