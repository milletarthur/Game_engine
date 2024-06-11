package Labyrinthe;

import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Random;

public class Field {
	private int colonne;
	private int ligne;
	private int[][] tmp;
	Entity[][][] labyrinthe;
	int nb_element = 6;

	public Field(int lig, int col) {
		if (col % 2 == 0) {
			col++;
		}
		if (lig % 2 == 0) {
			lig++;
		}
		tmp = new int[lig][col];
		this.colonne = col;
		this.ligne = lig;
		labyrinthe = new Entity[lig][col][nb_element];
		grille(lig, col);
		grille2(lig, col);
		labyrinthe();
		grow();
	}
	
	public Field(int lig, int col, int densite) {
		if (col % 2 == 0) {
			col++;
		}
		if (lig % 2 == 0) {
			lig++;
		}
		tmp = new int[lig][col];
		this.colonne = col;
		this.ligne = lig;
		labyrinthe = new Entity[lig][col][nb_element];
		grille(lig, col);
		grille2(lig, col);
		labyrinthe();
		Obstacle(densite);
		grow();
	}

	public Field(int lig, int col, int densite, int joker) {
		if (col % 2 == 0) {
			col++;
		}
		if (lig % 2 == 0) {
			lig++;
		}
		tmp = new int[lig][col];
		this.colonne = col;
		this.ligne = lig;
		labyrinthe = new Entity[lig][col][nb_element];
		grille(lig, col);
		grille2(lig, col);
		printLabyrinthe();
		System.out.println("#################################");
		detruire_mur(densite);
		labyrinthe();
		grow();
	}

	int calcul_densite() {
		int count = 0;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (tmp[i][j] == -1) {
					count++;
				}
			}
		}
		int total = ligne * colonne;
		return ((100 * count) / total);
	}

	void detruire_mur(int densite) {
		Random rand = new Random();
		int d = calcul_densite();
		int x;
		int y;
		while (densite <= d) {
			x = rand.nextInt(ligne);
			y = rand.nextInt(colonne);
			while (tmp[x][y] != -1) {
				x = rand.nextInt(ligne);
				y = rand.nextInt(colonne);
			}
			tmp[x][y] = 0;
			d = calcul_densite();
		}
	}

	public void labyrinthe() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (tmp[i][j] == -1) {
					labyrinthe[i][j][0] = new Normal(i, j, 1, 1, this);
				} else {
					labyrinthe[i][j][0] = new Void(i, j, 1, 1, this);
				}
			}
		}
	}
	
	public void grille(int l, int c) {
		int nombre = 0;
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < c; j++) {
				if (i % 2 == 0) {
					tmp[i][j] = -1;
				} else if (i % 2 != 0 && j % 2 != 0) {
					tmp[i][j] = nombre;
					nombre++;

				} else {
					tmp[i][j] = -1;
				}
			}
		}
	}

	public void grille2(int l, int c) {

		Random rand = new Random();
		// int tmp[][] = tmp;
		int c1 = tmp[1][0];
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

			if (tmp[x - 1][y] == -1) {
				c1 = tmp[x][y - 1];
				c2 = tmp[x][y + 1];
			} else {
				c1 = tmp[x - 1][y];
				c2 = tmp[x + 1][y];
			}
			if (c1 != c2) {
				tmp[x][y] = 0;
				for (int i = 1; i < l - 1; i = i + 2) {
					for (int j = 1; j < c - 1; j = j + 2) {
						if (tmp[i][j] == c2) {
							tmp[i][j] = c1;
						}

						tmp[1][0] = c1;
						tmp[ligne - 2][colonne - 1] = c1;
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
				if (!((tmp[i][j] == -1 || tmp[i][j] == c))) {
					return 0;
				}
			}
		}
		return 1;
	}

	public void printLabyrinthe() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (tmp[i][j] == -1) {
					System.out.print("O");
				} else if (labyrinthe[i][j][0] instanceof Mine) {
					System.out.print("*");
				} else {
					// System.out.print(tmp[i][j]);
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}

	public void printGame() {
		for (int i = 0; i < this.ligne; i++) {
			for (int j = 0; j < this.colonne; j++) {
				Entity e = labyrinthe[i][j][0];
				if (e instanceof Void)
					System.out.print(" ");
				if (e instanceof Mur)
					System.out.print("O");
				if(e instanceof Mine)
					System.out.print("*");
			}
			System.out.print("\n");
		}
	}

	public void grow() {
		int nb_ligne = ligne;
		int nb_colonne = 2 * colonne;
		Entity[][][] new_labyrinthe = new Entity[nb_ligne][nb_colonne][6];
		for (int i = 0; i < ligne; i++) {
			int cpt = 0;
			for (int j = 0; j < colonne; j++) {
				new_labyrinthe[i][cpt][0] = labyrinthe[i][j][0];
				if(!(labyrinthe[i][j][0] instanceof Mine)) {
					new_labyrinthe[i][++cpt][0] = labyrinthe[i][j][0];
				} else {
					new_labyrinthe[i][++cpt][0] = new Void(i, j, 1, 1, this);
				}
				cpt++;
			}
		}
		labyrinthe = new_labyrinthe;
		colonne = nb_colonne;
		nb_ligne = 2 * ligne;
		nb_colonne = colonne;
		new_labyrinthe = new Entity[nb_ligne][nb_colonne][6];
		int cpt = 0;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				new_labyrinthe[cpt][j][0] = labyrinthe[i][j][0];
			}
			cpt++;
			for (int j = 0; j < colonne; j++) {
				if(!(labyrinthe[i][j][0] instanceof Mine)) {
					new_labyrinthe[cpt][j][0] = labyrinthe[i][j][0];
				} else {
					new_labyrinthe[cpt][j][0] = new Void(i, j, 1, 1, this);
				}
			}
			cpt++;
		}
		labyrinthe = new_labyrinthe;
		ligne = nb_ligne;
	}
	
	public void Obstacle(int densite) {
		Random random = new Random();
		for(int i=0; i<ligne; i++) {
			for(int j=0; j<colonne; j++) {
				if(labyrinthe[i][j][0] instanceof Void) {
					int rdm = random.nextInt(100);
					if(rdm <= densite) {
						labyrinthe[i][j][0] = new Mine();
					}
				}
			}
		}
	}
	
	public Entity[] getElement(int x, int y) {
		return labyrinthe[x][y];
	}

}
