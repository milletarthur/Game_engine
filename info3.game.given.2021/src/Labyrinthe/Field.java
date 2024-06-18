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
	LinkedList<Pair<Integer, Integer>> l_void = new LinkedList<Pair<Integer, Integer>>();

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
		System.out.println("Labyrinthe initial sans obstacles :\n\n");
		printGame();
		// TEST CHEMIN
		// #########################
		/*System.out.println("CHEMIN :\n");
		LinkedList<Pair<Integer, Integer>> chemin = new LinkedList<Pair<Integer, Integer>>();
		chemin = this.TrouverChemin(new Pair<Integer, Integer>(2, 0), new Pair<Integer, Integer>(this.ligne-3,colonne-1));
		for (int i = 0; i < chemin.size(); i++) {
			System.out.printf("( %d , %d ) \n", chemin.get(i).geto1() , chemin.get(i).geto2() );
		}*/

		// #########################
		Obstacle(densite, "Mine");
		Obstacle(densite, "Sable");
		//porte(densite);
		lave();
		this.pickable(densite, "Pomme"); 
		this.pickable(densite, "Potion");
		this.pickable(densite, "Pioche");
		this.pickable(densite, "Bombe");
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
		//LinkedList<Pair<Integer, Integer>> chemin = new LinkedList<Pair<Integer, Integer>>(); // ############ AJOUTÉ
																								// ############
		Random random = new Random();
		for (int i = 3; i < ligne; i++) {
			for (int j = 3; j < colonne; j++) {
				
				int eval = 1;
				// int eval_interr = 1 ; 
				
				int k_i = i;
				int k_j_min = Math.max(0, j - 1);
				int k_j_max = Math.min(colonne - 1, j + 1);

				// Vérification ligne actuelle
				for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
					if (ContainsInstanceof(getElement(k_i, k_j), (new Sable()).getClass()) == 1
							|| ContainsInstanceof(getElement(k_i, k_j), (new Mine()).getClass()) == 1) {
						eval = 0;
					}/*
					if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
						eval_interr = 0;
					}*/
				}

				// Vérification ligne suivante
				k_i = i + 1;
				if (k_i < ligne) {
					for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
						if (ContainsInstanceof(getElement(k_i, k_j), (new Sable()).getClass()) == 1
								|| ContainsInstanceof(getElement(k_i, k_j), (new Mine()).getClass()) == 1) {
							eval = 0;
						}/*
						if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
							eval_interr = 0;
						}*/
					}
				}

				// Vérification ligne précédente
				k_i = i - 1;
				if (k_i >= 0) {
					for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
						if (ContainsInstanceof(getElement(k_i, k_j), (new Sable()).getClass()) == 1
								|| ContainsInstanceof(getElement(k_i, k_j), (new Mine()).getClass()) == 1) {
							eval = 0;
						}/*
						if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
							eval_interr = 0;
						}*/
					}
				}

				if (ContainsInstanceof(getElement(i, j), (new Teleporteur()).getClass()) == 0) {
					// Case Void pu lave au début
					if (((getElement(i, j)).get(0) instanceof Void || (getElement(i, j)).get(0) instanceof Lave)) {
						int rdm = random.nextInt(100);

						if (rdm <= densite && eval == 1 && ContainsInstanceof(getElement(i, j), Mur.class) == 0) {
							if (e instanceof Sable) {
								getElement(i, j).add(new Sable());
								// ############ AJOUTÉ #############
								// #################################
								int i_interupteur = random.nextInt( this.ligne );
								int j_interupteur = random.nextInt( this.colonne );
								int eval_interr = 1 ; 
								
								k_i = i_interupteur;
								k_j_min = Math.max(0, j_interupteur - 1);
								k_j_max = Math.min(colonne - 1, j_interupteur + 1);

								// Vérification ligne actuelle
								for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
									if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
										eval_interr = 0;
									}
								}

								// Vérification ligne suivante
								k_i = i + 1;
								if (k_i < ligne) {
									for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
										if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
											eval_interr = 0;
										}
									}
								}

								// Vérification ligne précédente
								k_i = i - 1;
								if (k_i >= 0) {
									for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
										if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
											eval_interr = 0;
										}
									}
								}
								while ( eval_interr == 0 ) {
									i_interupteur = random.nextInt( this.ligne );
									j_interupteur = random.nextInt( this.colonne );
									eval_interr = 1 ; 
									
									k_i = i_interupteur;
									k_j_min = Math.max(0, j_interupteur - 1);
									k_j_max = Math.min(colonne - 1, j_interupteur + 1);

									// Vérification ligne actuelle
									for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
										if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
											eval_interr = 0;
										}
									}

									// Vérification ligne suivante
									k_i = i + 1;
									if (k_i < ligne) {
										for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
											if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
												eval_interr = 0;
											}
										}
									}

									// Vérification ligne précédente
									k_i = i - 1;
									if (k_i >= 0) {
										for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
											if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
												eval_interr = 0;
											}
										}
									}
								}
								Pair<Integer, Integer> Case_inter = new Pair<Integer, Integer>( i_interupteur , j_interupteur  );
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
								this.getElement(Case_inter.geto1(), Case_inter.geto2()).add( new Interrupteur(x_lab_courant, y_lab_courant, 1, 1, this, Liste_connexion));
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
								int i_interupteur = random.nextInt( this.ligne );
								int j_interupteur = random.nextInt( this.colonne );
								int eval_interr = 1 ; 
								
								if ( !( this.getElement(i_interupteur, j_interupteur).getLast() instanceof Mur ) ){
									eval_interr = 0;
								}
								
								k_i = i_interupteur;
								k_j_min = Math.max(0, j_interupteur - 1);
								k_j_max = Math.min(colonne - 1, j_interupteur + 1);

								// Vérification ligne actuelle
								for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
									if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
										eval_interr = 0;
									}
								}

								// Vérification ligne suivante
								k_i = i + 1;
								if (k_i < ligne) {
									for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
										if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
											eval_interr = 0;
										}
									}
								}

								// Vérification ligne précédente
								k_i = i - 1;
								if (k_i >= 0) {
									for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
										if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
											eval_interr = 0;
										}
									}
								}
								
								while ( eval_interr == 0 ) {
									i_interupteur = random.nextInt( this.ligne );
									j_interupteur = random.nextInt( this.colonne );
									eval_interr = 1 ; 
									
									k_i = i_interupteur;
									k_j_min = Math.max(0, j_interupteur - 1);
									k_j_max = Math.min(colonne - 1, j_interupteur + 1);

									// Vérification ligne actuelle
									for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
										if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
											eval_interr = 0;
										}
									}

									// Vérification ligne suivante
									k_i = i + 1;
									if (k_i < ligne) {
										for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
											if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
												eval_interr = 0;
											}
										}
									}

									// Vérification ligne précédente
									k_i = i - 1;
									if (k_i >= 0) {
										for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
											if ( !( this.getElement(k_i, k_j).getLast() instanceof Void ) && !( this.getElement(k_i, k_j).getLast() instanceof Mur )  ){
												eval_interr = 0;
											}
										}
									}
									if ( !( this.getElement(i_interupteur, j_interupteur).getLast() instanceof Mur ) ){
										eval_interr = 0;
									}
								}
								Pair<Integer, Integer> Case_inter = new Pair<Integer, Integer>( i_interupteur , j_interupteur  );
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

	/*public void porte(int densite) {
		Random random = new Random();

		// On parcours le labyrinthe
		for (int i = 0; i < this.ligne; i++) {
			for (int j = 0; j < this.colonne; j++) {
				int eval = 0;

				if ((ContainsInstanceof(this.getElement(i, j), (new Normal(i, j, 1, 1, this)).getClass()) == 1)) {
					continue;
				}

				// Placer porte vertical
				int rdm = random.nextInt(100);
				if (rdm <= densite) {
					if (i > 0 && (ContainsInstanceof(this.getElement(i - 1, j),
							(new Normal(i, j, 1, 1, this)).getClass()) == 1)) {
						if (i + 2 < this.ligne && (ContainsInstanceof(this.getElement(i + 2, j),
								(new Normal(i, j, 1, 1, this)).getClass()) == 1)) {

							// On regarde les cases adjacentes
							if (i + 1 < this.ligne && !this.ContientSableMur(i + 1, j)) {
								this.getElement(i + 1, j).add(new Normal(i + 1, j, 1, 1, this));
							}

							eval = 1;
							Entity MaPorte = new Porte(i, j, 1, 1, this);
							this.getElement(i, j).add(MaPorte);

							// ###### Maintenant l'interrupteur ######
							// ########################################################################
							LinkedList<Pair<Integer, Integer>> chemin = new LinkedList<Pair<Integer, Integer>>();
							Pair<Integer, Integer> start = new Pair<Integer, Integer>(2, 0);
							Pair<Integer, Integer> dst = new Pair<Integer, Integer>(i, j);
							chemin = this.TrouverChemin(start, dst);
							if (chemin.isEmpty()) {
								continue;
							}
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
										|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Cassable)
										|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Apple)
										|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Pioche)
										|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Bombe)
										|| (this.getElement(x_lab_courant, y_lab_courant).get(k) instanceof Potion)) {
									break;
								}
								indice++;
							}
							LinkedList<Entity> Liste_connexion = new LinkedList<Entity>();
							Liste_connexion.add(MaPorte);
							this.getElement(Case_inter.geto1(), Case_inter.geto2()).add(indice,
									new Interrupteur(x_lab_courant, y_lab_courant, 1, 1, this, Liste_connexion));
						}
						Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
						l_void.remove(p);
						// ########################################################################

					}
					if (eval == 0) {
						if (j > 0 && (ContainsInstanceof(this.getElement(i, j - 1),
								(new Normal(i, j, 1, 1, this)).getClass()) == 1)) {
							if (j + 2 < this.colonne && (ContainsInstanceof(this.getElement(i, j + 2),
									(new Normal(i, j, 1, 1, this)).getClass()) == 1)) {
								if (j + 1 < this.ligne && !ContientSableMur(i, j + 1)) {
									this.getElement(i, j + 1).add(new Normal(i, j + 1, 1, 1, this));
								}

								eval = 1;
								Entity MaPorte = new Porte(i, j, 1, 1, this);
								this.getElement(i, j).add(MaPorte);
								// ## Maintenant l'interrupteur
								LinkedList<Pair<Integer, Integer>> chemin = new LinkedList<Pair<Integer, Integer>>();
								Pair<Integer, Integer> start = new Pair<Integer, Integer>(2, 0);
								Pair<Integer, Integer> dst = new Pair<Integer, Integer>(i, j);
								chemin = this.TrouverChemin(start, dst);
								if (chemin.isEmpty()) {
									continue;
								}
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
								Liste_connexion.add(MaPorte);
								this.getElement(Case_inter.geto1(), Case_inter.geto2()).add(indice,
										new Interrupteur(x_lab_courant, y_lab_courant, 1, 1, this, Liste_connexion));
							}
						}
						Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
						l_void.remove(p);

					}
				}
			}
		}

	}*/

	private boolean isValidPosition1(int x, int y) {
		return x >= 0 && x < ligne && y >= 0 && y < colonne;
	}

	public LinkedList<Pair<Integer, Integer>> TrouverChemin_abdel(Pair<Integer, Integer> case_depart,
			Pair<Integer, Integer> case_dst) {
		LinkedList<Pair<Integer, Integer>> chemin = new LinkedList<>();
		int x_dst = case_dst.geto1();
		int y_dst = case_dst.geto2();

		// Les cases à explorer
		LinkedList<Pair<Integer, Integer>> A_Explorer = new LinkedList<>();
		// Tableau de boolean afin de voir quelles cases ont été visitées
		boolean[][] visite = new boolean[this.ligne][this.colonne];
		// On initialise toutes les cases à false
		for (int i = 0; i < this.ligne; i++) {
			for (int j = 0; j < this.colonne; j++) {
				visite[i][j] = false;
			}
		}

		int x_start = case_depart.geto1();
		int y_start = case_depart.geto2();
		// Liste chainée pour stocker les parents de chaque case
		LinkedList<Pair<Integer, Integer>> parent = new LinkedList<>();

		// Cases à explorer au début
		Pair<Integer, Integer> CaseInit = new Pair<>(x_start, y_start);
		A_Explorer.add(CaseInit);
		visite[x_start][y_start] = true;

		// Tant qu'il reste des cases à explorer, on rentre dans la boucle
		while (!A_Explorer.isEmpty()) {
			// On récupère le premier élément de la liste
			Pair<Integer, Integer> CaseCourante = A_Explorer.removeFirst();
			int x_courrant = CaseCourante.geto1();
			int y_courrant = CaseCourante.geto2();

			if (x_courrant == x_dst && y_courrant == y_dst) {
				/*if ((ContainsInstanceof(this.getElement(x_courrant, x_courrant),
						(new Normal(1, 1, 1, 1, this)).getClass()) == 0)) {
					chemin.add(CaseCourante);
				}*/
				chemin.add(CaseCourante);

				// On reconstruit le chemin
				Iterator<Pair<Integer, Integer>> iter = parent.iterator();
				while (iter.hasNext()) {
					Pair<Integer, Integer> c = iter.next();
					/*if ((ContainsInstanceof(this.getElement(c.geto1(), c.geto2()),
							(new Normal(1, 1, 1, 1, this)).getClass()) == 0)) {
						chemin.add(c);
					}*/
					chemin.add(c);
				}
				return chemin;
			}

			LinkedList<Pair<Integer, Integer>> direction = new LinkedList<>();
			direction.add(new Pair<>(-1, 0));
			direction.add(new Pair<>(1, 0));
			direction.add(new Pair<>(0, -1));
			direction.add(new Pair<>(0, 1));
			for (int i = 0; i < direction.size(); i++) {
				Pair<Integer, Integer> c_courante = direction.get(i);
				int my_x = x_courrant + c_courante.geto1();
				int my_y = y_courrant + c_courante.geto2();
				if (isValidPosition1(my_x, my_y) && !visite[my_x][my_y] && getElement(my_x, my_y).getLast() instanceof Void) {
					Pair<Integer, Integer> voisin = new Pair<>(my_x, my_y);
					A_Explorer.add(voisin);
					visite[my_x][my_y] = true;
					parent.add(voisin);
				}
			}
		}
		return chemin;
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
