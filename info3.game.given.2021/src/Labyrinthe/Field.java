package Labyrinthe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import toolkit.Pair;

public class Field {

	private int colonne;
	private int ligne;
	private int[][] tmp;
	private int[][] tmp2;
	private int[][] tmp3;
	ArrayList<Object> labyrinthe;
	LinkedList<Pair<Integer, Integer>> l_void = new LinkedList<Pair<Integer, Integer>>();
	LinkedList<Pair<Integer, Integer>> chemin = new LinkedList<Pair<Integer, Integer>>();

	public Field(int lig, int col) {
		if (col % 2 == 0) {
			col++;
		}
		if (lig % 2 == 0) {
			lig++;
		}
		tmp = new int[lig][col];
		tmp2 = new int[lig][col];
		this.colonne = col;
		this.ligne = lig;
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
	}

	public Field(int lig, int col, int densite) {
		if (col % 2 == 0) {
			col++;
		}
		if (lig % 2 == 0) {
			lig++;
		}
		tmp = new int[lig][col];

		tmp2 = new int[lig][col];
		tmp3 = new int[lig][col];
		this.colonne = col;
		this.ligne = lig;
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
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				tmp2[i][j] = tmp[i][j];
			}
		}

		labyrinthe();
		recup_liste_void();
		grow();
		System.out.println("Labyrinthe initial sans obstacles :\n\n");
		printGame();
		// Obstacle(densite, "Mine");
		Obstacle(densite, "Sable");
		/*
		 * lave(); this.pickable(densite, "Pomme"); this.pickable(densite, "Potion");
		 * this.pickable(densite, "Pioche"); this.pickable(densite, "Bombe");
		 */
		System.out.println("\n\nLabyrinthe Avec obstacles :\n\n");
	}

	public Field(int lig, int col, int densite, int joker) {
		if (col % 2 == 0) {
			col++;
		}
		if (lig % 2 == 0) {
			lig++;
		}
		tmp = new int[lig][col];

		tmp2 = new int[lig][col];
		tmp3 = new int[lig][col];
		this.colonne = col;
		this.ligne = lig;
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
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				tmp2[i][j] = tmp[i][j];
			}
		}
		labyrinthe();
		printLabyrinthe_tmp();
		trouver_chemin_1();
		chemin = trouver_chemin_2();
		affiche_chemin(chemin);
		for (int i = 0; i < chemin.size(); i++) {
			tmp[chemin.get(i).geto1()][chemin.get(i).geto2()] = -2;
		}
		deposer_Porte(joker);
		printLabyrinthe_tmp();
		System.out.println("#################################");
		// detruire_mur(densite);

		 recup_liste_void();
		 grow();
		 printGame();
		 grow_porte();
		 Obstacle(densite, "Mine");
	}

	int calcul_nombre_mur() {
		int count = 0;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (tmp[i][j] == -1) {
					count++;
				}
			}
		}
		int total = ligne * colonne;
		return count;
	}

	void detruire_mur(int densite) {
		Random rand = new Random();
		int nb_mur_totale = calcul_nombre_mur();
		int new_nb_mur = nb_mur_totale;
		int d = new_nb_mur * 100 / nb_mur_totale;
		int x;
		int y;
		while (densite < d) {
			x = rand.nextInt(ligne);
			y = rand.nextInt(colonne);
			while (tmp[x][y] != -1) {
				x = rand.nextInt(ligne);
				y = rand.nextInt(colonne);
			}
			tmp[x][y] = 0;
			new_nb_mur = calcul_nombre_mur();
			d = new_nb_mur * 100 / nb_mur_totale;
		}
	}

	void trouver_chemin_1() {
		tmp2[1][0] = 0;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (tmp2[i][j] >= 0) {
					tmp2[i][j] = 0;
				}
			}
		}
		int distance = 1;
		tmp2[ligne - 2][colonne - 1] = 1;

		while (tmp2[1][1] == 0) {
			// printLabyrinthe_tmp();
			for (int i = 0; i < ligne; i++) {
				for (int j = 0; j < colonne; j++) {
					tmp3[i][j] = tmp2[i][j];
				}
			}

			distance++;

			for (int i = ligne - 2; i > 0; i--) {
				for (int j = colonne - 2; j > 0; j--) {
					if (tmp2[i][j] == 0) {
						if (tmp2[i - 1][j] > 0 || tmp2[i + 1][j] > 0 || tmp2[i][j - 1] > 0 || tmp2[i][j + 1] > 0) {
							tmp3[i][j] = distance;
						}
					}
				}
			}
			for (int i = 0; i < ligne; i++) {
				for (int j = 0; j < colonne; j++) {
					tmp2[i][j] = tmp3[i][j];
				}
			}
		}
		tmp2[1][0] = distance + 20;

		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (tmp2[i][j] == 0) {
					tmp2[i][j] = distance + 1;
				}

				if (tmp2[i][j] == -1) {
					tmp2[i][j] = distance + 10;
				}
			}
		}
	}

	LinkedList<Pair<Integer, Integer>> trouver_chemin_2() {
		LinkedList<Pair<Integer, Integer>> res = new LinkedList<Pair<Integer, Integer>>();
		int x = 1;
		int y = 1;
		int up, down, left, right;
		Pair<Integer, Integer> p = new Pair<Integer, Integer>(1, 0);
		res.addLast(p);
		p = new Pair<Integer, Integer>(1, 1);
		res.addLast(p);
		while (x != ligne - 2 || y != colonne - 2) {
			// System.out.println("#################################");
			up = tmp2[x][y - 1];
			down = tmp2[x][y + 1];
			left = tmp2[x - 1][y];
			right = tmp2[x + 1][y];

			if (up <= down && up <= left && up <= right) {
				y--;
			} else if (down <= up && down <= left && down <= right) {
				y++;
			} else if (left <= up && left <= down && left <= right) {
				x--;
			} else if (right <= up && right <= down && right <= left) {
				x++;
			}
			p = new Pair<Integer, Integer>(x, y);
			res.addLast(p);

		}
		p = new Pair<Integer, Integer>(ligne - 2, colonne - 1);
		res.addLast(p);
		return res;
	}

	public void affiche_chemin(LinkedList<Pair<Integer, Integer>> chemin) {
		int len = chemin.size();
		for (int i = len - 1; i >= 0; i--) {
			System.out.print(" x : ");
			System.out.print(chemin.get(i).geto1());
			System.out.print(" y : ");
			System.out.print(chemin.get(i).geto2());
			System.out.print("\n");
		}
	}

	void deposer_Porte(int nb_porte) {
		Random r = new Random();
		int count = 0;
		LinkedList<Pair<Integer, Integer>> chemin2 = new LinkedList<Pair<Integer, Integer>>();
		for (int i = 1; i < chemin.size(); i++) {
			chemin2.addLast(chemin.get(i));
		}
		int i = chemin.size() / nb_porte - 2;
		LinkedList<Pair<Integer, Integer>> chemin3 = new LinkedList<Pair<Integer, Integer>>();
		int len = chemin2.size() / nb_porte;
		for (int ind = len - 1; ind >= 0; ind--) {
			chemin3.addFirst(chemin2.get(ind));
			chemin2.remove(ind);
		}
		

		System.out.printf("len = \t%d\n", i);
		Entity elem = null;
		int condition = 1;
		Porte p = null;
		for (int t = 0; t < nb_porte; t++) {
			while (i > 0 && condition == 1) {
				System.out.printf("indice i = \t%d y = \t%d x = \t%d\t%d \n", i, chemin3.getFirst().geto2(),
						chemin3.getFirst().geto1() - 1, chemin3.getFirst().geto1() + 1);
				if (get_element2(chemin3.get(i).geto1() - 1, chemin3.get(i).geto2(), labyrinthe) instanceof Mur
						&& get_element2(chemin3.get(i).geto1() + 1, chemin3.get(i).geto2(),
								labyrinthe) instanceof Mur) {
					p = new Porte(chemin3.get(i).geto1(), chemin3.get(i).geto2(), 1, 1, this);
					p.Orientation = 3;
					set_element2(chemin3.get(i).geto1(), chemin3.get(i).geto2(), p, labyrinthe);
					chemin3.remove(i);
					condition = 0;
				}
				System.out.printf("i = \t%d x = \t%d y = \t%d\t%d \n", i, chemin3.getFirst().geto1(),
						chemin3.getFirst().geto2() - 1, chemin3.getFirst().geto2() + 1);
				if (condition == 1
						&& get_element2(chemin3.get(i).geto1(), chemin3.get(i).geto2() - 1, labyrinthe) instanceof Mur
						&& get_element2(chemin3.get(i).geto1(), chemin3.get(i).geto2() + 1,
								labyrinthe) instanceof Mur) {
					p = new Porte(chemin3.get(i).geto1(), chemin3.get(i).geto2(), 1, 1, this);
					p.Orientation = 1;
					set_element2(chemin3.get(i).geto1(), chemin3.get(i).geto2(), p, labyrinthe);
					chemin3.remove(i);
					condition = 0;
				}
				if (condition == 1) {
					chemin3.remove(i);
					i--;
					count++;
				}
			}
			int x;
			int y;
			if (condition == 0) {

				int levier = r.nextInt(chemin3.size()-2);
				// levier = i + levier -1;
				while (!(get_element2(chemin3.get(levier).geto1(), chemin3.get(levier).geto2(),
						labyrinthe) instanceof Void)
						&& (get_element2(chemin3.get(levier).geto1(), chemin3.get(levier).geto2(),
								labyrinthe) instanceof Porte)) {
					levier = r.nextInt(chemin3.size()-2);
					// levier = i + levier - 1;

				}
				System.out.printf("random = \t%d\n", levier);

				x = chemin3.get(levier).geto1();
				y = chemin3.get(levier).geto2();
				LinkedList<Entity> l = new LinkedList<Entity>();
				l.add(p);
				Interrupteur Int = new Interrupteur(x, y, 1, 1, this, l);
				set_element2(x, y, Int, labyrinthe);
			}
			if (chemin2.size() >= len && condition == 0) {
				for (int i3 = chemin3.size()-1; i3 >= 0; i3--) {
					chemin3.remove(i3);
				}
				for (int i2 = len - 1; i2 >= 0; i2--) {
					chemin3.addFirst(chemin2.get(i2));
					chemin2.remove(i2);
				}
			}

			condition = 1;
			printGame();
			i = chemin.size() / nb_porte - 2;
			count = 0;
		}

	}

	public void updateAt(int i, int j, LinkedList<Entity> e) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i);
		LinkedList<Entity> elem = row.get(j);
		elem = e;
	}
	
	public void set_element(int indice_i, int indice_j, Entity e, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) lab.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		elem.addFirst(e);
	}

	public void set_element2(int indice_i, int indice_j, Entity e, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) lab.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		elem.addLast(e);
	}

	public Entity get_element(int indice_i, int indice_j, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) lab.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		return elem.element();
	}

	public Entity get_element2(int indice_i, int indice_j, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) lab.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		return elem.getLast();
	}

	public void labyrinthe() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (tmp[i][j] == -1) {
					set_element(i, j, new Normal(i, j, 1, 1, this), labyrinthe);
					set_element(i, j, new Void(i, j, 1, 1, this), labyrinthe);
					set_element(i, j, new Lave(i, j, 1, 1, this), labyrinthe);
				} else {
					set_element(i, j, new Void(i, j, 1, 1, this), labyrinthe);
					set_element(i, j, new Lave(i, j, 1, 1, this), labyrinthe);
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

	////////// algorithme d'accessibilité
	public void grille2(int l, int c) {

		Random rand = new Random();
		int c1 = tmp[1][0];
		int c2;
		int count = 0;
		while (is_finished(c1) == 0 && count <= ligne * colonne * 2) {
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
				} else if (tmp[i][j] == -2) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}

	public void printGame() {
		/*
		 * for(int i1 = 0; i1 < ligne*2 ; i1++) { System.out.print(i1); }
		 */
		System.out.print("\n");
		for (int i = 0; i < this.ligne; i++) {
			// System.out.print(i);
			for (int j = 0; j < this.colonne; j++) {
//				Entity e = get_element(i, j, labyrinthe);
				Entity e = getElement(i, j).getLast();
				if (e instanceof Void)
					System.out.print(" ");
				if (e instanceof Mur)
					System.out.print("O");
				if (e instanceof Mine)
					System.out.print("*");
				if (e instanceof Sable)
					System.out.print("S");
				if (e instanceof Lave)
					System.out.print("L");
				if (e instanceof Apple)
					System.out.print("A");
				if (e instanceof Potion)
					System.out.print("P");
				if (e instanceof Pioche)
					System.out.print("<");
				if (e instanceof Bombe)
					System.out.print("B");
				if (e instanceof Interrupteur)
					System.out.print("~");
				if (e instanceof Porte) {
					System.out.print("H");
				}
			}
			System.out.print("\n");

		}
	}

	public void grow() {
		int nb_ligne = ligne;
		int nb_colonne = 2 * colonne;
//		Random rdm = new Random();
//		Entity e;
		LinkedList<Entity> l;
		ArrayList<Object> new_labyrinthe = new ArrayList<Object>();
		for (int i = 0; i < ligne; i++) {
			ArrayList<LinkedList<Entity>> row = new ArrayList<>();
			for (int j = 0; j < nb_colonne; j++) {
				row.add(new LinkedList<Entity>());
			}
			new_labyrinthe.add(row);
		}
		for (int i = 0; i < ligne; i++) {
			int cpt = 0;
			for (int j = 0; j < colonne; j++) {
//				e = get_element(i, j, labyrinthe);
				l = getElement(i, j);
//				int val = rdm.nextInt(2);
//				set_element(i, cpt, e, new_labyrinthe);
				for (int k = 0; k < l.size(); k++) {
					set_element(i, cpt, l.get(k), new_labyrinthe);
				}
				cpt++;
//				e = get_element(i, j, labyrinthe);
				l = getElement(i, j);
//				set_element(i, cpt, e, new_labyrinthe);
				for (int k = 0; k < l.size(); k++) {
					set_element(i, cpt, l.get(k), new_labyrinthe);
				}
				cpt++;
			}
		}
		labyrinthe = new_labyrinthe;
		colonne = nb_colonne;
		nb_ligne = 2 * ligne;
		nb_colonne = colonne;
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
//				e = get_element(i, j, new_labyrinthe);
				l = getElement(i, j);
//				set_element(cpt, j, e, new_labyrinthe2);
				for (int k = 0; k < l.size(); k++) {
					set_element(cpt, j, l.get(k), new_labyrinthe2);
				}
			}
			cpt++;
			for (int j = 0; j < colonne; j++) {
//				int val = rdm.nextInt(2);
//				e = get_element(i, j, labyrinthe);
				l = getElement(i, j);
//				set_element(cpt, j, e, new_labyrinthe2);
				for (int k = 0; k < l.size(); k++) {
					set_element(cpt, j, l.get(k), new_labyrinthe2);
				}
			}
			cpt++;
		}
		labyrinthe = new_labyrinthe2;
		ligne = nb_ligne;
	}
	
	public void grow_porte() {
		int len;
		for(int i = 0; i<ligne; i++) {
			for(int j = 0; j<colonne ; j++) {
				LinkedList<Entity> l = getElement(i,j);
				Entity en = l.getLast();
				if(l.getLast() instanceof Porte) {
					if(en.Orientation == 3) {
						ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i);
						LinkedList<Entity> elem = row.get(j+1);
						elem.pollLast();
						ArrayList<LinkedList<Entity>> row2 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i+1);
						LinkedList<Entity> elem2 = row2.get(j+1);
						elem2.pollLast();
						ArrayList<LinkedList<Entity>> row3 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i+1);
						LinkedList<Entity> elem3 = row3.get(j);
						elem3.pollLast();
						Normal n = new Normal(i+1,j,1,1,this);
						elem3.addLast(n);
					}
					if(en.Orientation == 1) {
						ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i+1);
						LinkedList<Entity> elem = row.get(j);
						elem.pollLast();
						ArrayList<LinkedList<Entity>> row2 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i+1);
						LinkedList<Entity> elem2 = row2.get(j+1);
						elem2.pollLast();
						ArrayList<LinkedList<Entity>> row3 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i);
						LinkedList<Entity> elem3 = row3.get(j+1);
						elem3.pollLast();
						Normal n = new Normal(i,j+1,1,1,this);
						elem3.addLast(n);
					}
				}
				if(l.getLast() instanceof Interrupteur) {
					ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i+1);
					LinkedList<Entity> elem = row.get(j);
					elem.pollLast();
					ArrayList<LinkedList<Entity>> row2 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i+1);
					LinkedList<Entity> elem2 = row2.get(j+1);
					elem2.pollLast();
					ArrayList<LinkedList<Entity>> row3 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i);
					LinkedList<Entity> elem3 = row3.get(j+1);
					elem3.pollLast();
				}
			}
		}
	}

	public void Obstacle(int densite, String s) {
		if (densite == 0)
			return;
		Entity e;
		switch (s) {
		case "Sable":
			e = new Sable();
			break;
		case "Mine":
			e = new Mine();
			break;
		default:
			e = null;
			break;
		}
		LinkedList<Pair<Integer, Integer>> chemin = new LinkedList<Pair<Integer, Integer>>(); // ############ AJOUTÉ
																								// ############
		Random random = new Random();
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {

				System.out.println("###########################################");
				chemin.add(new Pair<Integer, Integer>(i, j)); // ############ AJOUTÉ ############
				int eval = 1;

				int k_i = i;
				int k_j_min = Math.max(0, j - 1);
				int k_j_max = Math.min(colonne - 1, j + 1);

				// Vérification ligne actuelle
				for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
					if (ContainsInstanceof(getElement(k_i, k_j), (new Sable()).getClass()) == 1
							|| ContainsInstanceof(getElement(k_i, k_j), (new Mine()).getClass()) == 1) {
						eval = 0;
					}
				}

				// Vérification ligne suivante
				k_i = i + 1;
				if (k_i < ligne) {
					for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
						if (ContainsInstanceof(getElement(k_i, k_j), (new Sable()).getClass()) == 1
								|| ContainsInstanceof(getElement(k_i, k_j), (new Mine()).getClass()) == 1) {
							eval = 0;
						}
					}
				}

				// Vérification ligne précédente
				k_i = i - 1;
				if (k_i >= 0) {
					for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
						if (ContainsInstanceof(getElement(k_i, k_j), (new Sable()).getClass()) == 1
								|| ContainsInstanceof(getElement(k_i, k_j), (new Mine()).getClass()) == 1) {
							eval = 0;
						}
					}
				}

				if (ContainsInstanceof(getElement(i, j), (new Teleporteur()).getClass()) == 0) {
					// Case Void pu lave au début
					if (((getElement(i, j)).get(0) instanceof Void || (getElement(i, j)).get(0) instanceof Lave)) {
						int rdm = random.nextInt(100);

						if (rdm <= densite && eval == 1 && ContainsInstanceof(getElement(i, j), Mur.class) == 0) {
							if (e instanceof Sable) {
								getElement(i, j).add(new Sable());
								// ############ AJOUTÉ ############
								// #################################
								int position_interupteur = random.nextInt(chemin.size());
								while (ContainsInstanceof(this.getElement(chemin.get(position_interupteur).geto1(),
										chemin.get(position_interupteur).geto2()), Mur.class) == 1) {
									position_interupteur = random.nextInt(chemin.size());
								}
								Pair<Integer, Integer> Case_inter = chemin.get(position_interupteur);
								int x_lab_courant = Case_inter.geto1();
								int y_lab_courant = Case_inter.geto2();
								int indice = 0;
								for (int k = 0; k < this.getElement(x_lab_courant, y_lab_courant).size(); k++) {
									if ((this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Invisible)
											|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Joueur)
											|| (this.getElement(x_lab_courant, y_lab_courant)
													.get(k) instanceof Cassable)
											|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Apple)
											|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Pioche)
											|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Bombe)
											|| (this.getElement(x_lab_courant, y_lab_courant)
													.get(k) instanceof Potion)) {
										break;
									}
									indice++;
								}
								LinkedList<Entity> Liste_connexion = new LinkedList<Entity>();
								Liste_connexion.add(e);
								this.getElement(Case_inter.geto1(), Case_inter.geto2()).add(indice,
										new Interrupteur(x_lab_courant, y_lab_courant, 1, 1, this, Liste_connexion));
								// #################################
								// #################################
								Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
								l_void.remove(p);
							} else if (e instanceof Mine) {
								getElement(i, j).add(new Mine());
								Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
								l_void.remove(p);
							}
						}
					} else {
						int rdm = random.nextInt(100);
						if (rdm <= densite && eval == 1 && ContainsInstanceof(getElement(i, j), Mur.class) == 0) {
							if (e instanceof Sable) {
								getElement(i, j).add(new Sable());
								// ############ AJOUTÉ ############
								// #################################
								int position_interupteur = random.nextInt(chemin.size());
								while (ContainsInstanceof(this.getElement(chemin.get(position_interupteur).geto1(),
										chemin.get(position_interupteur).geto2()), Mur.class) == 1) {
									position_interupteur = random.nextInt(chemin.size());
								}
								Pair<Integer, Integer> Case_inter = chemin.get(position_interupteur);
								int x_lab_courant = Case_inter.geto1();
								int y_lab_courant = Case_inter.geto2();
								int indice = 0;
								for (int k = 0; k < this.getElement(x_lab_courant, y_lab_courant).size(); k++) {
									if ((this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Invisible)
											|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Joueur)
											|| (this.getElement(x_lab_courant, y_lab_courant)
													.get(k) instanceof Cassable)
											|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Apple)
											|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Pioche)
											|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Bombe)
											|| (this.getElement(x_lab_courant, y_lab_courant)
													.get(k) instanceof Potion)) {
										break;
									}
									indice++;
								}
								LinkedList<Entity> Liste_connexion = new LinkedList<Entity>();
								Liste_connexion.add(e);
								this.getElement(Case_inter.geto1(), Case_inter.geto2()).add(indice,
										new Interrupteur(x_lab_courant, y_lab_courant, 1, 1, this, Liste_connexion));
								// #################################
								// #################################
								Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
								l_void.remove(p);
							} else if (e instanceof Mine) {
								getElement(i, j).add(new Mine());
								Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
								l_void.remove(p);
							}
						}
					}
				}
			}
			printGame();
		}
	}// ##########
		// ############### AJOUTÉ ###################################

	private boolean ContientSableMur(int x, int y) {
		for (int i = 0; i < this.getElement(x, y).size(); i++) {
			Entity elm = this.getElement(x, y).get(i);
			if (elm instanceof Mur || elm instanceof Sable) {
				return true;
			}
		}
		return false;
	}
	// ##########################################################

	public void lave() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if ((ContainsInstanceof(this.getElement(i, j), (new Void(i, j, 1, 1, this)).getClass()) == 1)
						|| (((ContainsInstanceof(this.getElement(i, j), (new Teleporteur()).getClass()) == 0)
								&& (ContainsInstanceof(this.getElement(i, j),
										(new Normal(0, 0, 0, 0, this)).getClass()) == 0))
								&& ((ContainsInstanceof(this.getElement(i, j), (new Cassable()).getClass()) == 1)
										|| (ContainsInstanceof(this.getElement(i, j),
												(new Invisible()).getClass()) == 1)))) {
					this.getElement(i, j).add(0, new Lave(i, j, 1, 1, this));
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

	}// ###############################################################
		// ################################################################
		// MODIF ABDEL

	public void pickable(int densite, String s) {
		// LinkedList<Pair<Integer,Integer>> l_void = new
		// LinkedList<Pair<Integer,Integer>>();
		if (densite == 0)
			return;
		Entity e;
		// int i_entite = 0 ;
		// int j_entite = 0 ;
		switch (s) {
		case "Pomme":
			e = new Apple(0, 0, 1, 1, this);
			break;
		case "Potion":
			e = new Potion();
			break;
		case "Bombe":
			e = new Bombe();
			break;
		case "Pioche":
			e = new Pioche();
			break;
		default:
			e = null;
			break;
		}
		if (e == null) {
			return; // Si e est null, on sort de la méthode
		}
		Random random = new Random();
		for (int i = 0; i < this.l_void.size(); i++) {
			int rdm = random.nextInt(100);
			if (rdm <= densite) {
				Pair<Integer, Integer> PaireCourante = this.l_void.get(i);
				int x_lab = PaireCourante.geto1();
				int y_lab = PaireCourante.geto2();
				if (ContainsInstanceof(this.getElement(x_lab, y_lab), Mur.class) == 1) {
					continue; // Passer à la prochaine itération si c'est un mur
				}
				int indice = 0;
				for (int k = 0; k < this.getElement(x_lab, y_lab).size(); k++) {
					if ((this.getElement(x_lab, y_lab).get(k) instanceof Invisible)
							|| (this.getElement(x_lab, y_lab).get(k) instanceof Joueur)
							|| (this.getElement(x_lab, y_lab).get(k) instanceof Cassable)) {
						break;
					}
					indice++;
				}
				if (e instanceof Apple) {
					e = new Apple(x_lab, y_lab, 1, 1, this);
				}
				(this.getElement(x_lab, y_lab)).add(indice, e);
				// Supprimer la case de l_void après l'ajout de l'entité
				this.l_void.remove(i);
				i--; // Ajuster l'indice pour compenser la suppression
			}
		}
	}
	// ################################################################
	// ################################################################

	public LinkedList<Entity> getElement(int x, int y) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(x);
		LinkedList<Entity> elem = row.get(y);
		return elem;
	}

	public void recup_liste_void() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				LinkedList<Entity> l = getElement(i, j);
				if (l.getLast() instanceof Void) {
					Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
					l_void.add(p);
				}
			}
		}
	}

}
