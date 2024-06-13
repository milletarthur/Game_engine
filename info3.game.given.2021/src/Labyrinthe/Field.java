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
	ArrayList<Object> labyrinthe;
	LinkedList<Pair<Integer,Integer>> l_void = new LinkedList<Pair<Integer,Integer>>();

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
		recup_liste_void();
		grow();
		Obstacle(densite, "Mine");
		Obstacle(densite, "Sable");
		lave();
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
		System.out.println("#################################");
		detruire_mur(densite);
		labyrinthe();
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

	public void set_element(int indice_i, int indice_j, Entity e, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) lab.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		elem.addFirst(e);
	}

	public Entity get_element(int indice_i, int indice_j, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) lab.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		return elem.element();
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
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}

	public void printGame() {
		for (int i = 0; i < this.ligne; i++) {
			for (int j = 0; j < this.colonne; j++) {
//				Entity e = get_element(i, j, labyrinthe);
				Entity e = getElement(i,j).getLast();
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
				for(int k=0; k<l.size(); k++) {
					set_element(i, cpt, l.get(k), new_labyrinthe);
				}
				cpt++;
//				e = get_element(i, j, labyrinthe);
				l = getElement(i, j);
//				set_element(i, cpt, e, new_labyrinthe);
				for(int k=0; k<l.size(); k++) {
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
				for(int k=0; k<l.size(); k++) {
					set_element(cpt, j, l.get(k), new_labyrinthe2);	
				}
			}
			cpt++;
			for (int j = 0; j < colonne; j++) {
//				int val = rdm.nextInt(2);
//				e = get_element(i, j, labyrinthe);
				l = getElement(i, j);
//				set_element(cpt, j, e, new_labyrinthe2);
				for(int k=0; k<l.size(); k++) {
					set_element(cpt, j, l.get(k), new_labyrinthe2);
				}
			}
			cpt++;
		}
		labyrinthe = new_labyrinthe2;
		ligne = nb_ligne;
	}

	public void deposer_Porte() {
		int len = 10;
		LinkedList<int[]> liste = bulldozer(len);
		for (int i = 0; i < len; i++) {
			System.out.println("x : ");
			System.out.print(liste.element()[0]);
			System.out.println("y : ");
			System.out.print(liste.element()[1]);
		}
	}

	public LinkedList<int[]> bulldozer(int longueur) {
		Random r = new Random();
		LinkedList<int[]> liste = new LinkedList<int[]>();
		int[] debut = new int[2];
		debut[0] = 1;
		debut[1] = 0;
		int x = 1;
		int y = 0;
		for (int i = 1; i <= longueur; i++) {
			int[] tab = new int[2];
			int condition = 1;
			while (condition == 1) {
				int direction = r.nextInt(4);
				switch (direction) {
				case 0: // Haut
					if (get_element(x, y--, labyrinthe) instanceof Void && y > 0) {
						tab[0] = x;
						tab[1] = y;
					}
					break;
				case 1: // Bas
					if (get_element(x, y++, labyrinthe) instanceof Void && y < colonne - 1) {
						tab[0] = x;
						tab[1] = y;
					}
					break;
				case 2: // Gauche
					if (get_element(x--, y, labyrinthe) instanceof Void && x > 0) {
						tab[0] = x;
						tab[1] = y;
					}
					break;
				case 3: // Droite
					if (get_element(x++, y, labyrinthe) instanceof Void && y < ligne - 1) {
						tab[0] = x;
						tab[1] = y;
					}
					break;
				}
				if (liste.contains(tab)) {
					condition = 1;
				} else {
					liste.add(tab);
					condition = 0;
				}
			}
		}
		return liste;
	}

	public void Obstacle(int densite, String s) {
		if (densite == 0)
			return;
		Entity e;
		switch(s) {
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
		Random random = new Random();
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
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
							if(e instanceof Sable) {
								getElement(i, j).add(new Sable());
								Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
								l_void.remove(p);
							} else if (e instanceof Mine){
								getElement(i, j).add(new Mine());
								Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
								l_void.remove(p);
							}
						}
					} else {
						int rdm = random.nextInt(100);
						if (rdm <= densite && eval == 1 && ContainsInstanceof(getElement(i, j), Mur.class) == 0) {
							if(e instanceof Sable) {
								getElement(i, j).add(new Sable());
								Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
								l_void.remove(p);
							} else if (e instanceof Mine){
								getElement(i, j).add(new Mine());
								Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
								l_void.remove(p);
							}
						}
					}
				}
			}
		}
	}

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

	}

	public LinkedList<Entity> getElement(int x, int y) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(x);
		LinkedList<Entity> elem = row.get(y);
		return elem;
	}
	
	public void recup_liste_void() {
		for(int i=0; i<ligne; i++) {
			for(int j=0; j<colonne; j++) {
				LinkedList<Entity> l = getElement(i, j);
				if(l.getLast() instanceof Void) {
					Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
					l_void.add(p);
				}
			}
		}
	}

}
