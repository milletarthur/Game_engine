package Labyrinthe;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Field {
	private int colonne;
	private int ligne;
	private int[][] tmp;
//	Entity[][][] labyrinthe;
//	ArrayList<ArrayList<LinkedList<Entity>>> Labyrinthe;
	ArrayList<Object> labyrinthe;
	int nb_element = 6;

//	public Field(int l, int c) {
//	labyrinthe = new ArrayList<Object>(new ArrayList<Object>(new LinkedList<Entity>()));
//	}

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
		// labyrinthe = new Entity[lig][col][nb_element];
		labyrinthe = new ArrayList<Object>(new ArrayList<Object>(new LinkedList<Entity>()));

		for (int i = 0; i < lig; i++) {
			ArrayList<LinkedList<Entity>> row = new ArrayList<>();
			for (int j = 0; j < col; j++) {
				row.add(new LinkedList<Entity>());
			}
			labyrinthe.add(row);
		}
		/*
		 * for (int i = 0; i < lig; i++) { ArrayList<LinkedList<Entity>> row =
		 * (ArrayList<LinkedList<Entity>>) labyrinthe.get(i); for (int j = 0; j < col;
		 * j++) { LinkedList<Entity> elem = new LinkedList<Entity>(); Lave elem2 = new
		 * Lave(i, j, 1, 1, this); elem.addLast(elem2); row.add(j, elem); } }
		 */

		grille(lig, col);
		grille2(lig, col);
		labyrinthe();
		// printGame();
		// grow();
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
		// labyrinthe = new Entity[lig][col][nb_element];
		labyrinthe = new ArrayList<Object>(new ArrayList<Object>(new LinkedList<Entity>()));

		for (int i = 0; i < lig; i++) {
			ArrayList<LinkedList<Entity>> row = new ArrayList<>();
			for (int j = 0; j < col; j++) {
				row.add(new LinkedList<Entity>());
			}
			labyrinthe.add(row);
		}
		grille(lig, col);
		grille2(lig, col);
		labyrinthe();
		Obstacle(densite);
		// grow();
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
		// labyrinthe = new Entity[lig][col][nb_element];
		labyrinthe = new ArrayList<Object>(new ArrayList<Object>(new LinkedList<Entity>()));

		for (int i = 0; i < lig; i++) {
			ArrayList<LinkedList<Entity>> row = new ArrayList<>();
			for (int j = 0; j < col; j++) {
				row.add(new LinkedList<Entity>());
			}
			labyrinthe.add(row);
		}
		grille(lig, col);
		grille2(lig, col);
		printLabyrinthe_tmp();
		System.out.println("#################################");
		detruire_mur(densite);
		labyrinthe();
		// grow();
		this.SableMouvant(densite);
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

	public void set_element(int indice_i, int indice_j, Entity e, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		elem.addLast(e);
	}

	public Entity get_element(int indice_i, int indice_j) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		return elem.element();
	}

	public void labyrinthe() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (tmp[i][j] == -1) {
					// labyrinthe[i][j][0] = new Normal(i, j, 1, 1, this);
					Entity e = new Normal(i, j, 1, 1, this);
					set_element(i, j, e, labyrinthe);
				} else {
					// labyrinthe[i][j][0] = new Void(i, j, 1, 1, this);
					Entity e = new Void(i, j, 1, 1, this);
					set_element(i, j, e, labyrinthe);
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

	public void printLabyrinthe_tmp() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (tmp[i][j] == -1) {
					System.out.print("O");
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
				Entity e = get_element(i, j);
				if (e instanceof Void)
					System.out.print(" ");
				if (e instanceof Mur)
					System.out.print("O");
				if (e instanceof Mine)
					System.out.print("*");
				if (e instanceof Sable)
					System.out.print("S");
			}
			System.out.print("\n");
		}
	}

	public void grow() {
		int nb_ligne = ligne;
		int nb_colonne = 2 * colonne;

		ArrayList<Object> new_labyrinthe = new ArrayList<Object>(new ArrayList<Object>(new LinkedList<Entity>()));
		for (int i = 0; i < ligne; i++) {
			ArrayList<LinkedList<Entity>> row = new ArrayList<>();
			for (int j = 0; j < colonne; j++) {
				row.add(new LinkedList<Entity>());
			}
			new_labyrinthe.add(row);
		}
		// Entity[][][] new_labyrinthe = new Entity[nb_ligne][nb_colonne][6];
		for (int i = 0; i < ligne; i++) {
			int cpt = 0;
			for (int j = 0; j < colonne; j++) {
				Entity e = get_element(i, j);
				set_element(i, cpt, e, new_labyrinthe);
				// new_labyrinthe[i][cpt][0] = labyrinthe[i][j][0];
				if (!(get_element(i, j) instanceof Mine)) {
					Entity m = get_element(i, j);
					set_element(i, ++cpt, m, new_labyrinthe);
					// new_labyrinthe[i][++cpt][0] = labyrinthe[i][j][0];
				} else {
					Void v = new Void(i, j, 1, 1, this);
					set_element(i, ++cpt, v, new_labyrinthe);
					// new_labyrinthe[i][++cpt][0] = new Void(i, j, 1, 1, this);
				}
				cpt++;
			}
		}
		labyrinthe = new_labyrinthe;
		colonne = nb_colonne;
		nb_ligne = 2 * ligne;
		nb_colonne = colonne;
		// new_labyrinthe = new Entity[nb_ligne][nb_colonne][6];
		ArrayList<Object> new_labyrinthe2 = new ArrayList<Object>(new ArrayList<Object>(new LinkedList<Entity>()));
		for (int i = 0; i < nb_ligne; i++) {
			ArrayList<LinkedList<Entity>> row = new ArrayList<>();
			for (int j = 0; j < nb_colonne; j++) {
				row.add(new LinkedList<Entity>());
			}
			new_labyrinthe2.add(row);
		}
		int cpt = 0;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				// new_labyrinthe[cpt][j][0] = labyrinthe[i][j][0];
				Entity e = get_element(i, j);
				set_element(cpt, j, e, new_labyrinthe2);
			}
			cpt++;
			for (int j = 0; j < colonne; j++) {
				if (!(get_element(i, j) instanceof Mine)) {
					// new_labyrinthe[cpt][j][0] = labyrinthe[i][j][0];
					Entity mine = get_element(i, j);
					set_element(cpt, j, mine, new_labyrinthe2);
				} else {
					// new_labyrinthe[cpt][j][0] = new Void(i, j, 1, 1, this);
					Void vide = new Void(i, j, 1, 1, this);
					set_element(cpt, j, vide, new_labyrinthe2);

				}
			}
			cpt++;
		}
		labyrinthe = new_labyrinthe2;
		ligne = nb_ligne;
	}

	public void Obstacle(int densite) {
		Random random = new Random();
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (get_element(i, j) instanceof Void) {
					int rdm = random.nextInt(100);
					if (rdm <= densite) {
						// labyrinthe[i][j][0] = new Mine();
						Mine m = new Mine();
						set_element(i, j, m, labyrinthe);
					}
				}
			}
		}
	}

	public void Porte(int densite) {
		Random random = new Random();
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (get_element(i, j) instanceof Void) {
					int rdm = random.nextInt(100);
					if (rdm <= densite) {
						// labyrinthe[i][j][0] = new Porte(i, j, 1, 1, this);
						Porte porte = new Porte(i, j, 1, 1, this);
						set_element(i, j, porte, labyrinthe);
					}
				}
			}
		}
	}

	public void SableMouvant(int densite) {
		Random random = new Random();
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				int eval = 1;

				int k_i = i;
				int k_j_min = Math.max(0, j - 1);
				int k_j_max = Math.min(colonne - 1, j + 1);

				// Vérification ligne actuelle
				for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
					if (ContainsInstanceof(this.getElement(k_i, k_j), (new Sable()).getClass()) == 1) {
						eval = 0;
					}
				}

				// Vérification ligne suivante
				k_i = i + 1;
				if (k_i < ligne) {
					for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
						if (ContainsInstanceof(this.getElement(k_i, k_j), (new Sable()).getClass()) == 1) {
							eval = 0;
						}
					}
				}

				// Vérification ligne précédente
				k_i = i - 1;
				if (k_i >= 0) {
					for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
						if (ContainsInstanceof(this.getElement(k_i, k_j), (new Sable()).getClass()) == 1) {
							eval = 0;
						}
					}
				}

				if (ContainsInstanceof(this.getElement(i, j), (new Teleporteur()).getClass()) == 0) {
					// Case Void pu lave au début
					if ((this.getElement(i, j)).get(0) instanceof Void
							|| (this.getElement(i, j)).get(0) instanceof Lave) {
						int rdm = random.nextInt(100);

						if (rdm <= densite && eval == 1) {
							this.getElement(i, j).add(new Sable());
							//this.getElement(i, j).add(0, new Sable());
						}
					} else {
						int rdm = random.nextInt(100);
						if (rdm <= densite && eval == 1) {
							this.getElement(i, j).add(0, new Sable());
							//this.getElement(i, j).add(new Sable());
						}
					}
				}
			}
		}
	}

	/*
	 * La méthode suivante prend une liste chainé et un objet o en parametre. Elle
	 * renvoie 1 si jamais la liste contient un objet instanceof ( type de o )
	 */
	public int ContainsInstanceof(LinkedList<Entity> liste, Class<?> c) {

		// Parcourir la liste pour voir si elle contient un objet du même type que o
		for (int i = 0; i < liste.size(); i++) {
			if (c.isAssignableFrom(liste.get(i).getClass())) {
				return 1;
			}
		}
		return 0;

	}

	public LinkedList<Entity> getElement(int x, int y) {
		// return labyrinthe[x][y];
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(x);
		LinkedList<Entity> elem = row.get(y);
		return elem;
	}

}
