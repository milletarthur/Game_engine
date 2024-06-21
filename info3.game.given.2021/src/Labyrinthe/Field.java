package Labyrinthe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import toolkit.*;

public class Field {

	private int colonne;
	private int ligne;
	private int[][] tmp;
	private int[][] tmp2;
	private int[][] tmp3;
	ArrayList<Object> labyrinthe;
	Random rand;
	LinkedList<Pair<Integer, Integer>> l_void = new LinkedList<Pair<Integer, Integer>>();
	LinkedList<Pair<Integer, Integer>> mur = new LinkedList<Pair<Integer, Integer>>();
	LinkedList<Pair<Integer, Integer>> chemin = new LinkedList<Pair<Integer, Integer>>();
	private LinkedList<Entity> mur_cassable = new LinkedList<Entity>();
	private LinkedList<Entity> mur_invisible = new LinkedList<Entity>();
	private LinkedList<Entity> mur_normal = new LinkedList<Entity>();
	private LinkedList<Entity> liste_interrupteur = new LinkedList<Entity>();
	private LinkedList<Entity> liste_porte = new LinkedList<Entity>();
	private LinkedList<Entity> liste_sable = new LinkedList<Entity>();
	private LinkedList<Entity> liste_squelette = new LinkedList<Entity>();
	private LinkedList<Entity> liste_zombie = new LinkedList<Entity>();

	// pomme, potion, pioche, bombe
	private LinkedList<Entity> ListePommes = new LinkedList<Entity>();
	private LinkedList<Entity> ListePotions = new LinkedList<Entity>();
	private LinkedList<Entity> ListePioches = new LinkedList<Entity>();
	private LinkedList<Entity> ListeBombes = new LinkedList<Entity>();
	private LinkedList<Entity> ListeTeleporteur = new LinkedList<Entity>();

	private LinkedList<Entity> ListeMine = new LinkedList<Entity>();

	public Field(int lig, int col, int densite_field, int densite_pickable, int mine, int pomme, int potion, int pioche,
			int bombe, int cassable, int invisible, int normal, int nb_porte_sable, int nb_ennemis, Random r) {
		if (col % 2 == 0) {
			col++;
		}
		if (lig % 2 == 0) {
			lig++;
		}
		this.rand = r;
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
		detruire_mur(densite_field);
		labyrinthe();
		recup_liste_mur();
		// printLabyrinthe_tmp();
		trouver_chemin_1();
		chemin = trouver_chemin_2();
		// affiche_chemin(chemin);
		for (int i = 0; i < chemin.size(); i++) {
			tmp[chemin.get(i).geto1()][chemin.get(i).geto2()] = -2;
		}
		if (densite_field == 100) {
			depot_mur1(cassable, invisible, normal);
			deposer_Porte(nb_porte_sable);
		} else {
			depot_mur2(cassable, invisible, normal);
		}

		grow();
		//printGame();

		grow_porte();
		depot_teleporteur();
		recup_liste_void_cassable_invisible();
		depot_mine(densite_pickable, mine);
		pickable(densite_pickable, pomme, potion, pioche, bombe);
		depot_ennemis(nb_ennemis);
		printGame();

	}

	public LinkedList<Entity> get_cassable() {
		return mur_cassable;
	}

	public LinkedList<Entity> get_invisible() {
		return mur_invisible;
	}

	public LinkedList<Entity> get_normal() {
		return mur_normal;
	}

	public LinkedList<Entity> get_interrupteur() {
		return liste_interrupteur;
	}

	public LinkedList<Entity> get_porte() {
		return liste_porte;
	}

	public LinkedList<Entity> get_sable() {
		return liste_sable;
	}

	public LinkedList<Entity> get_squelette() {
		return liste_squelette;
	}

	public LinkedList<Entity> get_zombie() {
		return liste_zombie;
	}

	public LinkedList<Entity> get_ListePommes() {
		return this.ListePommes;
	}

	public LinkedList<Entity> get_ListePotions() {
		return this.ListePotions;
	}

	public LinkedList<Entity> get_ListePioche() {
		return this.ListePioches;
	}

	public LinkedList<Entity> get_ListeBombes() {
		return this.ListeBombes;
	}

	public LinkedList<Entity> get_ListeTeleporteur() {
		return this.ListeTeleporteur;
	}

	public LinkedList<Entity> get_ListeMine() {
		return this.ListeMine;
	}

	void depot_ennemis(int nb) {
		int count = 0;
		while (count < nb) {
			int i, j;
			i = rand.nextInt(ligne);
			j = rand.nextInt(colonne - 11) + 10;
			while (!(get_element2(i, j, labyrinthe) instanceof Void)) {
				i = rand.nextInt(ligne - 1);
				j = rand.nextInt(colonne - 11) + 10;
			}
			Squelette s = new Squelette(i, j);
			set_element2(i, j, s, labyrinthe);
			liste_squelette.add(s);
			count++;
		}
		count = 0;
		while (count < nb) {
			int i, j;
			i = rand.nextInt(ligne);
			j = rand.nextInt(colonne - 11) + 10;
			while (!(get_element2(i, j, labyrinthe) instanceof Void)) {
				i = rand.nextInt(ligne - 1);
				j = rand.nextInt(colonne - 11) + 10;
			}
			Zombie z = new Zombie(i, j);
			set_element2(i, j, z, labyrinthe);
			liste_zombie.add(z);
			count++;
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
		if (nb_porte == 0) {
			return;
		}
		int count = 0;
		LinkedList<Pair<Integer, Integer>> chemin2 = new LinkedList<Pair<Integer, Integer>>();
		for (int i = 1; i < chemin.size(); i++) {
			chemin2.addLast(chemin.get(i));
		}
		int i = chemin.size() / nb_porte - 2;
		LinkedList<Pair<Integer, Integer>> chemin3 = new LinkedList<Pair<Integer, Integer>>();
		int len = chemin2.size() / nb_porte;
		// System.out.printf("len = \t%d\n", len);
		while (len < 6) {
			// System.out.println("je suis dedans
			// #####################################################");
			nb_porte--;
			len = chemin2.size() / nb_porte;
		}
		for (int ind = len - 1; ind >= 0; ind--) {
			chemin3.addFirst(chemin2.get(ind));
			chemin2.remove(ind);
		}

		// System.out.printf("len = \t%d\n", len);
		Entity elem = null;
		int condition = 1;
		Entity p = null;
		for (int t = 0; t < nb_porte; t++) {
			if (t % 2 == 1) {
				while (i > 0 && condition == 1) {
					if (get_element2(chemin3.get(i).geto1() - 1, chemin3.get(i).geto2(), labyrinthe) instanceof Mur
							&& get_element2(chemin3.get(i).geto1() + 1, chemin3.get(i).geto2(),
									labyrinthe) instanceof Mur) {
						p = new Porte(chemin3.get(i).geto1(), chemin3.get(i).geto2());
						p.Orientation = 3;
						set_element2(chemin3.get(i).geto1(), chemin3.get(i).geto2(), p, labyrinthe);
						liste_porte.add(p);
						chemin3.remove(i);
						condition = 0;
					}
					if (condition == 1
							&& get_element2(chemin3.get(i).geto1(), chemin3.get(i).geto2() - 1,
									labyrinthe) instanceof Mur
							&& get_element2(chemin3.get(i).geto1(), chemin3.get(i).geto2() + 1,
									labyrinthe) instanceof Mur) {
						p = new Porte(chemin3.get(i).geto1(), chemin3.get(i).geto2());
						p.Orientation = 1;
						set_element2(chemin3.get(i).geto1(), chemin3.get(i).geto2(), p, labyrinthe);
						liste_porte.add(p);
						chemin3.remove(i);
						condition = 0;
					}
					if (condition == 1) {
						chemin3.remove(i);
						i--;
						count++;
					}
				}
			} else {
				while (i > 0 && condition == 1) {
					p = new Sable(chemin3.get(i).geto1(), chemin3.get(i).geto2());
					set_element2(chemin3.get(i).geto1(), chemin3.get(i).geto2(), p, labyrinthe);
					liste_sable.add(p);
					chemin3.remove(i);
					condition = 0;
				}
				if (condition == 1 && i >= 0) {
					chemin3.remove(i);
					i--;
					count++;
				}
			}
			int x;
			int y;
			if (condition == 0) {

				int levier = rand.nextInt(chemin3.size() - 2);
				while (!(get_element2(chemin3.get(levier).geto1(), chemin3.get(levier).geto2(),
						labyrinthe) instanceof Void)
						&& (get_element2(chemin3.get(levier).geto1(), chemin3.get(levier).geto2(),
								labyrinthe) instanceof Porte)
						&& (get_element2(chemin3.get(levier).geto1(), chemin3.get(levier).geto2(),
								labyrinthe) instanceof Sable)) {
					levier = rand.nextInt(chemin3.size() - 2);
				}

				x = chemin3.get(levier).geto1();
				y = chemin3.get(levier).geto2();
				LinkedList<Entity> l = new LinkedList<Entity>();
				l.add(p);
				Interrupteur Int = new Interrupteur(x, y, l);
				set_element2(x, y, Int, labyrinthe);
				liste_interrupteur.add(Int);
			}
			if (chemin2.size() >= len && condition == 0) {
				for (int i3 = chemin3.size() - 1; i3 >= 0; i3--) {
					chemin3.remove(i3);
				}
				for (int i2 = len - 1; i2 >= 0; i2--) {
					chemin3.addFirst(chemin2.get(i2));
					chemin2.remove(i2);
				}
			}

			condition = 1;
			// printGame();
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

	public void set_element3(int indice_i, int indice_j, Entity e, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) lab.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		elem.removeLast();
		elem.addLast(e);
	}

	public void set_element4(int indice_i, int indice_j, Entity e, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) lab.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		elem.add(elem.size() - 2, e);
	}

	public void set_element5(int indice_i, int indice_j, Entity e, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) lab.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		for (int s = 0; s < elem.size(); s++) {
			elem.remove(s);
		}
		elem.add(e);
	}

	public void update_element(int indice_i, int indice_j, LinkedList<Entity> l, ArrayList<Object> lab) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(indice_i);
		row.set(indice_j, l);

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

	public void depot_teleporteur() {
		int i, j;
		i = rand.nextInt(ligne);
		j = rand.nextInt(colonne - 2) + 1;
		while (!(get_element2(i, j, labyrinthe) instanceof Void)) {
			i = rand.nextInt(ligne - 1);
			j = rand.nextInt(colonne - 2) + 1;
		}
		Teleporteur t = new Teleporteur(i, j);
		this.ListeTeleporteur.add(t);
		set_element5(i, j, t, labyrinthe);
		Entity en = get_element2(i, j, labyrinthe);
		i = rand.nextInt(ligne);
		j = rand.nextInt(colonne - 2) + 1;
		while (!(get_element2(i, j, labyrinthe) instanceof Void)) {
			i = rand.nextInt(ligne - 1);
			j = rand.nextInt(colonne - 2) + 1;
		}
		Teleporteur t1 = new Teleporteur(i, j);
		this.ListeTeleporteur.add(t1);
		set_element5(i, j, t1, labyrinthe);
		Entity en1 = get_element2(i, j, labyrinthe);
		((Teleporteur) en).set_voisin(en1);
		((Teleporteur) en1).set_voisin(en);

		i = rand.nextInt(ligne);
		j = rand.nextInt(colonne - 2) + 1;
		while (!(get_element2(i, j, labyrinthe) instanceof Void)) {
			i = rand.nextInt(ligne - 1);
			j = rand.nextInt(colonne - 2) + 1;
		}
		Teleporteur t2 = new Teleporteur(i, j);
		this.ListeTeleporteur.add(t2);
		set_element5(i, j, t2, labyrinthe);
		Entity en2 = get_element2(i, j, labyrinthe);
		i = rand.nextInt(ligne);
		j = rand.nextInt(colonne - 2) + 1;
		while (!(get_element2(i, j, labyrinthe) instanceof Void)) {
			i = rand.nextInt(ligne - 1);
			j = rand.nextInt(colonne - 2) + 1;
		}
		Teleporteur t3 = new Teleporteur(i, j);
		this.ListeTeleporteur.add(t3);
		set_element5(i, j, t3, labyrinthe);
		Entity en3 = get_element2(i, j, labyrinthe);
		((Teleporteur) en2).set_voisin(en3);
		((Teleporteur) en3).set_voisin(en2);

	}

	public void labyrinthe() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (tmp[i][j] == -1) {
					Normal n = new Normal(i, j); 
					set_element(i, j, n, labyrinthe);
					this.mur_normal.add(n);
					set_element(i, j, new Void(i, j), labyrinthe);
					set_element(i, j, new Lave(i, j), labyrinthe);
				} else {
					set_element(i, j, new Void(i, j), labyrinthe);
					set_element(i, j, new Lave(i, j), labyrinthe);
				}
			}
		}
		// set_element(3,0,new Joueur(3,0),labyrinthe);
	}

	public void depot_mur1(int cassable, int invisible, int normal) {
		int len_mur = mur.size();
		// int pourcentage_field = (100*len_void)/(ligne*colonne);
		int nb_pour_cassable = cassable * len_mur / 100;
		// System.out.printf("nb_cassable = \t%d, nb_mur = \t%d\n", nb_pour_cassable,
		// len_mur);
		int count = 0;
		int x, y;
		if (cassable == 100) {
			for (int a = 1; a < ligne - 1; a++) {
				for (int b = 1; b < colonne - 1; b++) {
					if (get_element2(a, b, labyrinthe) instanceof Mur) {
						Cassable m = new Cassable(a, b);
						set_element3(a, b, m, labyrinthe);
						mur_cassable.add(m);
						for ( int i = 0 ; i < this.mur_normal.size() ; i++  ) {
							int i1 = this.mur_normal.get(i).ligne() ;
							int j1 = this.mur_normal.get(i).colonne() ;
							if ( a == i1 && b == j1 ) {
								this.mur_normal.remove(i);
							}
						}
					}
				}
			}
		} else {
			while (count < nb_pour_cassable) {
				x = rand.nextInt(ligne - 2) + 1;
				y = rand.nextInt(colonne - 2) + 1;
				while (!(get_element2(x, y, labyrinthe) instanceof Mur)) {
					x = rand.nextInt(ligne - 2) + 1;
					y = rand.nextInt(colonne - 2) + 1;
				}
				Cassable m = new Cassable(x, y);
				set_element3(x, y, m, labyrinthe);
				mur_cassable.add(m);
				for ( int i = 0 ; i < this.mur_normal.size() ; i++  ) {
					int i1 = this.mur_normal.get(i).ligne() ;
					int j1 = this.mur_normal.get(i).colonne() ;
					if ( x == i1 && y == j1 ) {
						this.mur_normal.remove(i);
					}
				}
				count++;
			}
		}
		count = 0;
		int nb_pour_invisible = invisible * len_mur / 100;
		if (invisible == 100) {
			for (int a = 1; a < ligne - 1; a++) {
				for (int b = 1; b < colonne - 1; b++) {
					if (get_element2(a, b, labyrinthe) instanceof Mur) {
						Invisible m = new Invisible(a, b);
						set_element3(a, b, m, labyrinthe);
						mur_invisible.add(m);
						for ( int i = 0 ; i < this.mur_normal.size() ; i++  ) {
							int i1 = this.mur_normal.get(i).ligne() ;
							int j1 = this.mur_normal.get(i).colonne() ;
							if ( a == i1 && b == j1 ) {
								this.mur_normal.remove(i);
							}
						}
					}
				}
			}
		} else {
			while (count < nb_pour_invisible) {
				x = rand.nextInt(ligne - 2) + 1;
				y = rand.nextInt(colonne - 2) + 1;
				while (!(get_element2(x, y, labyrinthe) instanceof Mur)) {
					x = rand.nextInt(ligne - 2) + 1;
					y = rand.nextInt(colonne - 2) + 1;
				}
				Invisible m = new Invisible(x, y);
				set_element3(x, y, m, labyrinthe);
				mur_invisible.add(m);
				count++;
				for ( int i = 0 ; i < this.mur_normal.size() ; i++  ) {
					int i1 = this.mur_normal.get(i).ligne() ;
					int j1 = this.mur_normal.get(i).colonne() ;
					if ( x == i1 && y == j1 ) {
						this.mur_normal.remove(i);
					}
				}
			}
		}
		count = 0;
		int nb_pour_normal = normal * len_mur / 100;
		while (count < nb_pour_normal) {
			x = rand.nextInt(ligne - 2) + 1;
			y = rand.nextInt(colonne - 2) + 1;
			while (!(get_element2(x, y, labyrinthe) instanceof Mur)) {
				x = rand.nextInt(ligne - 2) + 1;
				y = rand.nextInt(colonne - 2) + 1;
			}
			Normal m = new Normal(x, y);
			for ( int i = 0 ; i < this.mur_normal.size() ; i++  ) {
				int i1 = this.mur_normal.get(i).ligne() ;
				int j1 = this.mur_normal.get(i).colonne() ;
				if ( x == i1 && y == j1 ) {
					this.mur_normal.remove(i);
				}
			}
			set_element3(x, y, m, labyrinthe);
			mur_normal.add(m);
			count++;
		}

	}

	public void depot_mur2(int cassable, int invisible, int normal) {
		int len_mur = mur.size();
		// int pourcentage_field = (100*len_void)/(ligne*colonne);
		int nb_pour_cassable = cassable * len_mur / 100;
		// System.out.printf("nb_cassable = \t%d, nb_mur = \t%d\n", nb_pour_cassable,
		// len_mur);
		int count = 0;
		int x, y;
		if (cassable == 100) {
			for (int a = 0; a < ligne; a++) {
				for (int b = 0; b < colonne; b++) {
					if (get_element2(a, b, labyrinthe) instanceof Mur) {
						Cassable m = new Cassable(a, b);
						set_element3(a, b, m, labyrinthe);
						mur_cassable.add(m);
						for ( int i = 0 ; i < this.mur_normal.size() ; i++  ) {
							int i1 = this.mur_normal.get(i).ligne() ;
							int j1 = this.mur_normal.get(i).colonne() ;
							if ( a == i1 && b == j1 ) {
								this.mur_normal.remove(i);
							}
						}
					}
				}
			}
		} else {
			while (count < nb_pour_cassable) {
				x = rand.nextInt(ligne);
				y = rand.nextInt(colonne);
				while (!(get_element2(x, y, labyrinthe) instanceof Mur)) {
					x = rand.nextInt(ligne);
					y = rand.nextInt(colonne);
				}
				Cassable m = new Cassable(x, y);
				set_element3(x, y, m, labyrinthe);
				mur_cassable.add(m);
				for ( int i = 0 ; i < this.mur_normal.size() ; i++  ) {
					int i1 = this.mur_normal.get(i).ligne() ;
					int j1 = this.mur_normal.get(i).colonne() ;
					if ( x == i1 && y == j1 ) {
						this.mur_normal.remove(i);
					}
				}
				count++;
			}
		}
		count = 0;
		int nb_pour_invisible = invisible * len_mur / 100;
		if (invisible == 100) {
			for (int a = 0; a < ligne; a++) {
				for (int b = 0; b < colonne; b++) {
					if (get_element2(a, b, labyrinthe) instanceof Mur) {
						Invisible m = new Invisible(a, b);
						set_element3(a, b, m, labyrinthe);
						mur_invisible.add(m);
						for ( int i = 0 ; i < this.mur_normal.size() ; i++  ) {
							int i1 = this.mur_normal.get(i).ligne() ;
							int j1 = this.mur_normal.get(i).colonne() ;
							if ( a == i1 && b == j1 ) {
								this.mur_normal.remove(i);
							}
						}
					}
				}
			}
		} else {
			while (count < nb_pour_invisible) {
				x = rand.nextInt(ligne);
				y = rand.nextInt(colonne);
				while (!(get_element2(x, y, labyrinthe) instanceof Mur)) {
					x = rand.nextInt(ligne);
					y = rand.nextInt(colonne);
				}
				Invisible m = new Invisible(x, y);
				set_element3(x, y, m, labyrinthe);
				mur_invisible.add(m);
				count++;
				for ( int i = 0 ; i < this.mur_normal.size() ; i++  ) {
					int i1 = this.mur_normal.get(i).ligne() ;
					int j1 = this.mur_normal.get(i).colonne() ;
					if ( x == i1 && y == j1 ) {
						this.mur_normal.remove(i);
					}
				}
			}
		}
		count = 0;
		int nb_pour_normal = normal * len_mur / 100;
		while (count < nb_pour_normal) {
			x = rand.nextInt(ligne);
			y = rand.nextInt(colonne);
			while (!(get_element2(x, y, labyrinthe) instanceof Mur)) {
				x = rand.nextInt(ligne);
				y = rand.nextInt(colonne);
			}
			Normal m = new Normal(x, y);
			for ( int i = 0 ; i < this.mur_normal.size() ; i++  ) {
				int i1 = this.mur_normal.get(i).ligne() ;
				int j1 = this.mur_normal.get(i).colonne() ;
				if ( x == i1 && y == j1 ) {
					this.mur_normal.remove(i);
				}
			}
			set_element3(x, y, m, labyrinthe);
			mur_normal.add(m);
			count++;
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
				if (e instanceof Porte)
					System.out.print("H");
				if (e instanceof Normal)
					System.out.print("O");
				if (e instanceof Cassable)
					System.out.print("&");
				if (e instanceof Invisible)
					System.out.print("#");
				if (e instanceof Teleporteur)
					System.out.print("+");
				if (e instanceof Squelette)
					System.out.print("§");
				if (e instanceof Zombie)
					System.out.print("%");
			}
			System.out.print("\n");

		}
	}

	public void grow() {
		int nb_ligne = ligne;
		int nb_colonne = 2 * colonne;
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

	public Entity get_element(int indice_i, int indice_j) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		return elem.element();
	}

	public void grow_porte() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				LinkedList<Entity> l = getElement(i, j);
				Entity en = l.getLast();
				if (l.getLast() instanceof Porte) {
					if (en.Orientation == 3) {
						ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i);
						LinkedList<Entity> elem = row.get(j + 1);
						elem.pollLast();
						ArrayList<LinkedList<Entity>> row2 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i + 1);
						LinkedList<Entity> elem2 = row2.get(j + 1);
						elem2.pollLast();
						ArrayList<LinkedList<Entity>> row3 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i + 1);
						LinkedList<Entity> elem3 = row3.get(j);
						elem3.pollLast();
						Normal n = new Normal(i + 1, j);
						elem3.addLast(n);
					}
					if (en.Orientation == 1) {
						ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i + 1);
						LinkedList<Entity> elem = row.get(j);
						elem.pollLast();
						ArrayList<LinkedList<Entity>> row2 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i + 1);
						LinkedList<Entity> elem2 = row2.get(j + 1);
						elem2.pollLast();
						ArrayList<LinkedList<Entity>> row3 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i);
						LinkedList<Entity> elem3 = row3.get(j + 1);
						elem3.pollLast();
						Normal n = new Normal(i, j + 1);
						elem3.addLast(n);
					}
				}
				if (l.getLast() instanceof Interrupteur) {
					ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i + 1);
					LinkedList<Entity> elem = row.get(j);
					elem.pollLast();
					ArrayList<LinkedList<Entity>> row2 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i + 1);
					LinkedList<Entity> elem2 = row2.get(j + 1);
					elem2.pollLast();
					ArrayList<LinkedList<Entity>> row3 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i);
					LinkedList<Entity> elem3 = row3.get(j + 1);
					elem3.pollLast();
				}
				if (l.getLast() instanceof Sable) {
					ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i + 1);
					LinkedList<Entity> elem = row.get(j);
					elem.pollLast();
					ArrayList<LinkedList<Entity>> row2 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i + 1);
					LinkedList<Entity> elem2 = row2.get(j + 1);
					elem2.pollLast();
					ArrayList<LinkedList<Entity>> row3 = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i);
					LinkedList<Entity> elem3 = row3.get(j + 1);
					elem3.pollLast();
				}
			}
		}
	}

	private boolean ContientSableMur(int i, int j) {
		for (int k = 0; k < this.getElement(i, j).size(); k++) {
			Entity elm = this.getElement(i, j).get(k);
			if (elm instanceof Mur || elm instanceof Sable) {
				return true;
			}
		}
		return false;
	}
	// ##########################################################

	private boolean isValidPosition1(int i, int j) {
		return i >= 0 && i < ligne && j >= 0 && j < colonne;
	}

	// ##########################################################

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

	private boolean ContientVoidMur(int x, int y) {
		Entity elm = this.getElement(x, y).getLast();
		if (elm instanceof Mur || elm instanceof Void) {
			return true;
		}

		return false;
	}

	private Boolean ContientMineInterr(int l, int c) {
		Entity elm = this.getElement(l, c).getLast();
		if (elm instanceof Mine || elm instanceof Interrupteur) {
			return true;
		}

		return false;
	}

	public Boolean ContientObs(int l, int c) {
		/*
		 * Pair<Integer, Integer> Case = new Pair<Integer, Integer>(l, c);
		 */
		int k_i = l;
		int k_j_min = Math.max(0, c - 1);
		int k_j_max = Math.min(colonne - 1, c + 1);

		if ((this.get_element2(l, c, labyrinthe) instanceof Void)) {

			// Vérification ligne actuelle
			for (int k_j = k_j_min; k_j <= k_j_max; k_j++) {
				if (k_j != c && (!(this.ContientVoidMur(k_i, k_j)) || (this.ContientMineInterr(k_i, k_j)))) {
					return false;
				}
			}

			if (l > 0 && (!(this.ContientVoidMur(l - 1, c)) || (this.ContientMineInterr(l - 1, c)))) {
				return false;
			}
			if (l < this.ligne - 1) {
				if (!(this.ContientVoidMur(l + 1, c)) || (this.ContientMineInterr(l + 1, c))) {

					return false;
				}
				if (c > 0 && (this.ContientMineInterr(l + 1, c - 1))) {
					return false;
				}
				if (c < this.colonne - 1 && (this.ContientMineInterr(l + 1, c + 1))) {
					return false;
				}
			}

			if (l > 0) {
				if (c > 0 && (this.ContientMineInterr(l - 1, c - 1) || this.ContientMineInterr(l, c - 1))) {

					return false;
				}
				if (c < this.colonne - 1
						&& (this.ContientMineInterr(l - 1, c + 1) || (this.ContientMineInterr(l, c + 1)))) {

					return false;
				}
			}

		}
		return true;
		/*
		 * int eval = 1 ; if ( !( this.get_element2(l, c, labyrinthe) instanceof Void )
		 * ) { eval = 0 ; }
		 */
	}

	boolean verification(int i, int j) {
		boolean bool = true;
		if (!(get_element2(i, j, labyrinthe) instanceof Void)) {
			bool = false;
		}
		if (!(get_element2(i - 1, j - 1, labyrinthe) instanceof Void)
				&& !(get_element2(i - 1, j - 1, labyrinthe) instanceof Mur)) {
			bool = false;
		}
		if (!(get_element2(i, j - 1, labyrinthe) instanceof Void)
				&& !(get_element2(i, j - 1, labyrinthe) instanceof Mur)) {
			bool = false;
		}
		if (!(get_element2(i + 1, j - 1, labyrinthe) instanceof Void)
				&& !(get_element2(i + 1, j - 1, labyrinthe) instanceof Mur)) {
			bool = false;
		}
		if (!(get_element2(i - 1, j, labyrinthe) instanceof Void)
				&& !(get_element2(i - 1, j, labyrinthe) instanceof Mur)) {
			bool = false;
		}
		if (!(get_element2(i + 1, j, labyrinthe) instanceof Void)
				&& !(get_element2(i + 1, j, labyrinthe) instanceof Mur)) {
			bool = false;
		}
		if (!(get_element2(i - 1, j + 1, labyrinthe) instanceof Void)
				&& !(get_element2(i - 1, j + 1, labyrinthe) instanceof Mur)) {
			bool = false;
		}
		if (!(get_element2(i, j + 1, labyrinthe) instanceof Void)
				&& !(get_element2(i, j + 1, labyrinthe) instanceof Mur)) {
			bool = false;
		}
		if (!(get_element2(i + 1, j + 1, labyrinthe) instanceof Void)
				&& !(get_element2(i + 1, j + 1, labyrinthe) instanceof Mur)) {
			bool = false;
		}
		return bool;
	}

	public void depot_mine(int densitepickable, int mine) {
		if (densitepickable == 0) {
			return;
		}
		int len_void = l_void.size();
		int nb_libre_pour_pickable = densitepickable * len_void / 100;
		int nb_mine = (nb_libre_pour_pickable * mine) / densitepickable;
		int count = 0;
		int x, y;
		while (count < nb_mine) {
			x = rand.nextInt(ligne - 3) + 1;
			y = rand.nextInt(colonne - 3) + 1;// - 1);\
			while ((this.verification(x, y)) == false) {
				x = rand.nextInt(ligne - 3) + 1;
				y = rand.nextInt(colonne - 3) + 1;
			}
			Mine m = new Mine(x, y);
			set_element2(x, y, m, labyrinthe);
			ListeMine.add(m);
			count++;
		}
	}

	public void pickable(int densitepickable, int pomme, int potion, int pioche, int bombe) {
		if (densitepickable == 0) {
			return;
		}
		int len_void = l_void.size();
		int nb_libre_pour_pickable = densitepickable * len_void / 100;
		int count = 0;
		int x, y;
		int nb_pomme = (nb_libre_pour_pickable * pomme) / densitepickable;
		while (count < nb_pomme) {
			x = rand.nextInt(ligne - 1);
			y = rand.nextInt(colonne - 2) + 1;
			while (!(get_element2(x, y, labyrinthe) instanceof Void)
					&& !(get_element2(x, y, labyrinthe) instanceof Cassable)
					&& !(get_element2(x, y, labyrinthe) instanceof Invisible)) {
				x = rand.nextInt(ligne - 1);
				y = rand.nextInt(colonne - 2) + 1;
			}
			Apple a = new Apple(x, y);
			this.ListePommes.add(a); // Mise à jour
			if (get_element2(x, y, labyrinthe) instanceof Cassable) {
				set_element4(x, y, a, labyrinthe);
			} else if (get_element2(x, y, labyrinthe) instanceof Invisible) {
				set_element4(x, y, a, labyrinthe);
			} else {
				set_element2(x, y, a, labyrinthe);
			}
			count++;
		}
		count = 0;
		int nb_potion = (nb_libre_pour_pickable * potion) / densitepickable;
		while (count < nb_potion) {
			x = rand.nextInt(ligne - 1);
			y = rand.nextInt(colonne - 2) + 1;
			while (!(get_element2(x, y, labyrinthe) instanceof Void)
					&& !(get_element2(x, y, labyrinthe) instanceof Cassable)
					&& !(get_element2(x, y, labyrinthe) instanceof Invisible)) {
				x = rand.nextInt(ligne - 1);
				y = rand.nextInt(colonne - 2) + 1;
			}
			Potion pot = new Potion(x, y);
			this.ListePotions.add(pot);
			if (get_element2(x, y, labyrinthe) instanceof Cassable) {
				set_element4(x, y, pot, labyrinthe);
			} else if (get_element2(x, y, labyrinthe) instanceof Invisible) {
				set_element4(x, y, pot, labyrinthe);
			} else {
				set_element2(x, y, pot, labyrinthe);
			}
			count++;
		}
		count = 0;
		int nb_pioche = (nb_libre_pour_pickable * pioche) / densitepickable;
		while (count < nb_pioche) {
			x = rand.nextInt(ligne - 1);
			y = rand.nextInt(colonne - 2) + 1;
			while (!(get_element2(x, y, labyrinthe) instanceof Void)
					&& !(get_element2(x, y, labyrinthe) instanceof Cassable)
					&& !(get_element2(x, y, labyrinthe) instanceof Invisible)) {
				x = rand.nextInt(ligne - 1);
				y = rand.nextInt(colonne - 2) + 1;
			}
			Pioche pio = new Pioche(x, y);
			this.ListePioches.add(pio);
			if (get_element2(x, y, labyrinthe) instanceof Cassable) {
				set_element4(x, y, pio, labyrinthe);
			} else if (get_element2(x, y, labyrinthe) instanceof Invisible) {
				set_element4(x, y, pio, labyrinthe);
			} else {
				set_element2(x, y, pio, labyrinthe);
			}
			count++;
		}
		count = 0;
		int nb_bombe = (nb_libre_pour_pickable * bombe) / densitepickable;
		while (count < nb_bombe) {
			x = rand.nextInt(ligne - 1);
			y = rand.nextInt(colonne - 2) + 1;
			while (!(get_element2(x, y, labyrinthe) instanceof Void)
					&& !(get_element2(x, y, labyrinthe) instanceof Cassable)
					&& !(get_element2(x, y, labyrinthe) instanceof Invisible)) {
				x = rand.nextInt(ligne - 1);
				y = rand.nextInt(colonne - 2) + 1;
			}
			Bombe b = new Bombe(x, y);
			this.ListeBombes.add(b);
			if (get_element2(x, y, labyrinthe) instanceof Cassable) {
				set_element4(x, y, b, labyrinthe);
			} else if (get_element2(x, y, labyrinthe) instanceof Invisible) {
				set_element4(x, y, b, labyrinthe);
			} else {
				set_element2(x, y, b, labyrinthe);
			}
			count++;
		}
	}

	public LinkedList<Entity> getElement(int i, int j) {
		@SuppressWarnings("unchecked")
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i);
		LinkedList<Entity> elem = row.get(j);
		return elem;
	}

	public LinkedList<Pair<Integer, Integer>> recup_liste_void_cassable_invisible() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				LinkedList<Entity> l = getElement(i, j);
				if (l.getLast() instanceof Void) {
					Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
					l_void.add(p);
				} else if (l.getLast() instanceof Cassable) {
					Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
					l_void.add(p);
				} else if (l.getLast() instanceof Invisible) {
					Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
					l_void.add(p);
				}
			}
		}
		return l_void;
	}

	public void recup_liste_mur() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				LinkedList<Entity> l = getElement(i, j);
				if (l.getLast() instanceof Mur) {
					Pair<Integer, Integer> m = new Pair<Integer, Integer>(i, j);
					mur.add(m);
				}
			}
		}
	}

	public int[] next_to(Entity e, int d) {
		int[] rv = new int[2];
		rv[0] = e.ligne();
		rv[1] = e.colonne();
		if (d != Direction.H) {
			if (d > 0) {
				switch (d) {
				case Direction.N:
					rv[0]--;
					break;
				case Direction.S:
					rv[0]++;
					break;
				case Direction.E:
					rv[1]++;
					break;
				case Direction.W:
					rv[1]--;
					break;
				case Direction.NE:
					rv[0]--;
					rv[1]++;
					break;
				case Direction.SE:
					rv[0]--;
					rv[1]++;
					break;
				case Direction.NW:
					rv[0]++;
					rv[1]--;
					break;
				case Direction.SW:
					rv[0]++;
					rv[1]--;
					break;	
				default :
					break;
				}
			}
			if (rv[0] == e.ligne() && rv[1] == e.colonne()) {
				switch (e.direction()) {
				case Direction.N:
					switch (d) {
					case Direction.L:
						rv[1]--;
						break;
					case Direction.R:
						rv[1]++;
						break;
					case Direction.B:
						rv[0]++;
						break;
					case Direction.F:
						rv[0]--;
						break;
					case Direction.FR:
						rv[1]++;
						rv[0]--;
						break;
					case Direction.FL:
						rv[1]--;
						rv[0]--;
						break;
					case Direction.BR:
						rv[1]++;
						rv[0]++;
						break;
					case Direction.BL:
						rv[1]--;
						rv[0]++;
						break;
					default:
						break;
					}
					break;
				case Direction.S:
					switch (d) {
					case Direction.L:
						rv[1]++;
						break;
					case Direction.R:
						rv[1]--;
						break;
					case Direction.B:
						rv[0]--;
						break;
					case Direction.F:
						rv[0]++;
						break;
					case Direction.FR:
						rv[1]--;
						rv[0]++;
						break;
					case Direction.FL:
						rv[1]++;
						rv[0]++;
						break;
					case Direction.BR:
						rv[1]--;
						rv[0]--;
						break;
					case Direction.BL:
						rv[1]++;
						rv[0]--;
						break;
					default:
						break;
					}
					break;
				case Direction.E:
					switch (d) {
					case Direction.L:
						rv[0]--;
						break;
					case Direction.R:
						rv[0]++;
						break;
					case Direction.B:
						rv[1]--;
						break;
					case Direction.F:
						rv[1]++;
						break;
					case Direction.FR:
						rv[1]++;
						rv[0]++;
						break;
					case Direction.FL:
						rv[1]++;
						rv[0]--;
						break;
					case Direction.BR:
						rv[1]--;
						rv[0]++;
						break;
					case Direction.BL:
						rv[1]--;
						rv[0]--;
						break;
					default:
						break;
					}
					break;
				case Direction.W:
					switch (d) {
					case Direction.L:
						rv[0]++;
						break;
					case Direction.R:
						rv[0]--;
						break;
					case Direction.B:
						rv[1]++;
						break;
					case Direction.F:
						rv[1]--;
						break;
					case Direction.FR:
						rv[1]--;
						rv[0]--;
						break;
					case Direction.FL:
						rv[1]--;
						rv[0]++;
						break;
					case Direction.BR:
						rv[1]++;
						rv[0]--;
						break;
					case Direction.BL:
						rv[1]++;
						rv[0]++;
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
			}
		}
		if(rv[0] < 0)
			rv[0] = 0;
		if(rv[0] >= ligne)
			rv[0] = ligne-1;
		if(rv[1] < 0)
			rv[1] = 0;
		if(rv[1] >= colonne)
			rv[1] = colonne-1;
		return rv;
	}
	
	
	public boolean presence_Bot_Joueur(LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Joueur || liste.get(i) instanceof Squelette || liste.get(i) instanceof Zombie) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_clue(LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Interrupteur) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_danger(LinkedList<Entity> liste) {
		boolean v = false;
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Fleche || liste.get(i) instanceof Mine || liste.get(i) instanceof Sable) {
				return true;
			}
		}
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Void) {
				v = true;
			}
		}
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Lave && v == false) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_gate(LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Teleporteur || liste.get(i) instanceof Invisible) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_wizz(LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof WizzEntity) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_pop(LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof PopEntity) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_obstacle(LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Cassable || liste.get(i) instanceof Normal || (liste.get(i) instanceof Porte && liste.get(i).category == Categorie.O)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_pickable(LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Apple || liste.get(i) instanceof Arc || liste.get(i) instanceof Bombe || liste.get(i) instanceof Epee || liste.get(i) instanceof Pioche || liste.get(i) instanceof Potion) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_void(LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Void) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_Arobase(Entity en, LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Joueur && en.team == liste.get(i).team) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_Diese(Entity en, LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Joueur && en.team != liste.get(i).team) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presence_Tiret(LinkedList<Entity> liste) {
		if(liste.getLast() instanceof Selection) {
			if(liste.get(liste.size()-2) instanceof Void) {
				return true;
			}
		}
		else if (liste.getLast() instanceof Void){
			return true;
		}
		return false;
	}
	
	public boolean presence_Selection(LinkedList<Entity> liste) {
		for(int i = 0; i<liste.size(); i++) {
			if(liste.get(i) instanceof Selection) {
				return true;
			}
		}
		return false;
	}
	
	

	public int[] next_to_outside(Entity e, int d) {
		int[] rv = new int[2];
		rv[0] = e.ligne();
		rv[1] = e.colonne();
		if (d != Direction.H) {
			if (d > 0) {
				switch (d) {
				case Direction.N:
					rv[0]--;
					break;
				case Direction.S:
					rv[0]++;
					break;
				case Direction.E:
					rv[1]++;
					break;
				case Direction.W:
					rv[1]--;
					break;
				case Direction.NE:
					rv[0]--;
					rv[1]++;
					break;
				case Direction.SE:
					rv[0]--;
					rv[1]++;
					break;
				case Direction.NW:
					rv[0]++;
					rv[1]--;
					break;
				case Direction.SW:
					rv[0]++;
					rv[1]--;
					break;	
				default :
					break;
				}
			}
			if (rv[0] == e.ligne() && rv[1] == e.colonne()) {
				switch (e.direction()) {
				case Direction.N:
					switch (d) {
					case Direction.L:
						rv[1]--;
						break;
					case Direction.R:
						rv[1]++;
						break;
					case Direction.B:
						rv[0]++;
						break;
					case Direction.F:
						rv[0]--;
						break;
					case Direction.FR:
						rv[1]++;
						rv[0]--;
						break;
					case Direction.FL:
						rv[1]--;
						rv[0]--;
						break;
					case Direction.BR:
						rv[1]++;
						rv[0]++;
						break;
					case Direction.BL:
						rv[1]--;
						rv[0]++;
						break;
					default:
						break;
					}
					break;
				case Direction.S:
					switch (d) {
					case Direction.L:
						rv[1]++;
						break;
					case Direction.R:
						rv[1]--;
						break;
					case Direction.B:
						rv[0]--;
						break;
					case Direction.F:
						rv[0]++;
						break;
					case Direction.FR:
						rv[1]--;
						rv[0]++;
						break;
					case Direction.FL:
						rv[1]++;
						rv[0]++;
						break;
					case Direction.BR:
						rv[1]--;
						rv[0]--;
						break;
					case Direction.BL:
						rv[1]++;
						rv[0]--;
						break;
					default:
						break;
					}
					break;
				case Direction.E:
					switch (d) {
					case Direction.L:
						rv[0]--;
						break;
					case Direction.R:
						rv[0]++;
						break;
					case Direction.B:
						rv[1]--;
						break;
					case Direction.F:
						rv[1]++;
						break;
					case Direction.FR:
						rv[1]++;
						rv[0]++;
						break;
					case Direction.FL:
						rv[1]++;
						rv[0]--;
						break;
					case Direction.BR:
						rv[1]--;
						rv[0]++;
						break;
					case Direction.BL:
						rv[1]--;
						rv[0]--;
						break;
					default:
						break;
					}
					break;
				case Direction.W:
					switch (d) {
					case Direction.L:
						rv[0]++;
						break;
					case Direction.R:
						rv[0]--;
						break;
					case Direction.B:
						rv[1]++;
						break;
					case Direction.F:
						rv[1]--;
						break;
					case Direction.FR:
						rv[1]--;
						rv[0]--;
						break;
					case Direction.FL:
						rv[1]--;
						rv[0]++;
						break;
					case Direction.BR:
						rv[1]++;
						rv[0]--;
						break;
					case Direction.BL:
						rv[1]++;
						rv[0]++;
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
			}
		}
		return rv;
	}
	
	public boolean cell(Entity e, int dir, int cat) {
		int[] coo = next_to(e, dir);
		int ligne = coo[0];
		int colonne = coo[1];
		if (!(isValidPosition1(coo[0], coo[1])))
			return false;
		LinkedList<Entity> elem = getElement(ligne, colonne);
		int taille = elem.size();
		if (elem.getLast() instanceof Selection)
			taille--;
		switch (cat) {
		case Categorie.A :
			if(presence_Bot_Joueur(elem)) {
				return true;
			}
			return false;
		case Categorie.C :
			if(presence_clue(elem)) {
				return true;
			}
			return false;
		case Categorie.D :
			if(presence_danger(elem)) {
				return true;
			}
			return false;
		case Categorie.G :
			if(presence_gate(elem)) {
				return true;
			}
			return false;
		case Categorie.J :
			if(presence_wizz(elem)) {
				return true;
			}
			return false;
		case Categorie.M :
			if(presence_pop(elem)) {
				return true;
			}
			return false;
		case Categorie.O :
			if(presence_obstacle(elem)) {
				return true;
			}
			return false;
		case Categorie.P :
			if(presence_pickable(elem)) {
				return true;
			}
			return false;
		case Categorie.V :
			if(presence_void(elem)) {
				return true;
			}
			return false;
		case Categorie.Arobase :
			if(presence_Arobase(e, elem)) {
				return true;
			}
			return false;
		case Categorie.Diese :
			if(presence_Diese(e, elem)) {
				return true;
			}
			return false;
		case Categorie.Tiret :
			if(presence_Tiret(elem)) {
				return false;
			}
			return true;
		case Categorie.T :
			if(presence_Selection(elem)) {
				return true;
			}
			return false;
		default:
			return false;
		}
	}

	public void add(Entity e, int ligne, int colonne) {
		LinkedList<Entity> l_entity = getElement(ligne, colonne);
		int cpt = 0;
		Entity elem = l_entity.get(0);
		while (cpt < l_entity.size() && elem.layer() < e.layer()) {
			elem = l_entity.get(cpt);
			cpt++;
		}
		if (elem.layer() == e.layer()) {
			return;
		} else {
			if (elem.layer() > e.layer())
				l_entity.add(cpt-1, e);
			else
				l_entity.add(cpt, e);
		}
	}

	@SuppressWarnings("unchecked")
	public void setElement(int ligne, int colonne, LinkedList<Entity> l_entity) {
		((ArrayList<Object>) labyrinthe.get(ligne)).add(colonne, l_entity);
	}

	public void remove(int ligne, int colonne, Entity e) {
		LinkedList<Entity> l_entity = getElement(ligne, colonne);
		l_entity.remove(e);
	}

	public Entity getPickable(int ligne, int colonne) {
		LinkedList<Entity> l_entity = getElement(ligne, colonne);
		Entity elem;
		for (int i = 0; i < l_entity.size(); i++) {
			elem = l_entity.get(i);
			if (elem.category() == Categorie.P) {
				return elem;
			}
		}
		return null;
	}

	public Entity getLastnotSelect(int ligne, int colonne) {
		LinkedList<Entity> l_entity = getElement(ligne, colonne);
		Entity elem = l_entity.get(0);
		int taille = l_entity.size();
		Entity select = l_entity.getLast();
		if (select instanceof Selection)
			taille--;
		else
			return select;
		for (int i = 0; i < taille; i++) {
			elem = l_entity.get(i);
		}
		return elem;
	}

	public LinkedList<Pair<Integer, Integer>> getClassList(int layer) {
		LinkedList<Pair<Integer, Integer>> l_class = new LinkedList<Pair<Integer, Integer>>();
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				Entity e = getLastnotSelect(i, j);
				if (e.layer() < layer) {
					Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
					l_class.add(p);
				}
			}
		}
		return l_class;
	}
	
	public boolean hasSameLayer(int ligne, int colonne, int layer) {
		LinkedList<Entity> l_entity = getElement(ligne, colonne);
		Entity elem;
		for(int i=0; i<l_entity.size(); i++) {
			elem = l_entity.get(i);
			if(elem.layer() == layer) {
				return true;
			}
		}
		return false;
	}

	public boolean isHerePossible(int ligne, int colonne, Entity e) {
		Entity here = getLastnotSelect(ligne, colonne);
		String classnamelong = e.getClass().getName();
		String classname = (String) classnamelong.subSequence(classnamelong.indexOf(".")+1,classnamelong.length());
		switch(classname) {
		case "Joueur":
		case "Zombie":
		case "Squelette":
			if (here instanceof Void) {
				return true;
			} else if(here instanceof Invisible) {
				return !hasSameLayer(ligne, colonne, e.layer());
			}
			return false;
		case "Sable":
			if(here instanceof Void) {
				return true;
			} else if(here.category() == Categorie.P || here instanceof Mine) {
				remove(ligne, colonne, here);
				return true;
			}
			return false;
		case "Mine":
		case "Pioche":
		case "Apple":
		case "Potion":
		case "Bombe":
		case "Epee":
		case "Arc":
			if(here instanceof Void) {
				return true;
			} else if (here instanceof Cassable || here instanceof Invisible) {
				return !hasSameLayer(ligne, colonne, e.layer());
			}
			return false;
		case "Porte":
		case "Interrupteur":
			
		case "Normal":
			if(here instanceof Void) {
				// /!\ si on est en dehors du terrain
				LinkedList<Entity> l_entityN = getElement(ligne-1, colonne);
				LinkedList<Entity> l_entityE = getElement(ligne, colonne+1);
				LinkedList<Entity> l_entityS = getElement(ligne+1, colonne);
				LinkedList<Entity> l_entityW = getElement(ligne, colonne-1);
				Entity elem;
				boolean MurN = false;
				boolean MurE = false;
				boolean MurS = false;
				boolean MurW = false;
				for(int i=0; i<l_entityN.size(); i++) {
					elem = l_entityN.get(i);
					if(elem instanceof Normal || elem instanceof Cassable) {
						MurN = true;
					}
				}
				for(int i=0; i<l_entityS.size(); i++) {
					elem = l_entityS.get(i);
					if(elem instanceof Normal || elem instanceof Cassable) {
						MurS = true;
					}
				}
				if(MurN && MurS) {
					return true;
				}
				for(int i=0; i<l_entityE.size(); i++) {
					elem = l_entityE.get(i);
					if(elem instanceof Normal || elem instanceof Cassable) {
						MurE = true;
					}
				}
				for(int i=0; i<l_entityW.size(); i++) {
					elem = l_entityW.get(i);
					if(elem instanceof Normal || elem instanceof Cassable) {
						MurW = true;
					}
				}
				if(MurE && MurW) {
					return true;
				}
				return false;
			}
			return false;
		case "Cassable":
			if(here instanceof Void) {
				// /!\ si on est en dehors du terrain
				LinkedList<Entity> l_entityN = getElement(ligne-1, colonne);
				LinkedList<Entity> l_entityE = getElement(ligne, colonne+1);
				LinkedList<Entity> l_entityS = getElement(ligne+1, colonne);
				LinkedList<Entity> l_entityW = getElement(ligne, colonne-1);
				Entity elem;
				boolean MurN = false;
				boolean MurE = false;
				boolean MurS = false;
				boolean MurW = false;
				for(int i=0; i<l_entityN.size(); i++) {
					elem = l_entityN.get(i);
					if(elem instanceof Normal || elem instanceof Cassable) {
						MurN = true;
					}
				}
				for(int i=0; i<l_entityS.size(); i++) {
					elem = l_entityS.get(i);
					if(elem instanceof Normal || elem instanceof Cassable) {
						MurS = true;
					}
				}
				if(MurN && MurS) {
					return true;
				}
				for(int i=0; i<l_entityE.size(); i++) {
					elem = l_entityE.get(i);
					if(elem instanceof Normal || elem instanceof Cassable) {
						MurE = true;
					}
				}
				for(int i=0; i<l_entityW.size(); i++) {
					elem = l_entityW.get(i);
					if(elem instanceof Normal || elem instanceof Cassable) {
						MurW = true;
					}
				}
				if(MurE && MurW) {
					return true;
				}
				return false;
			} else if(here instanceof Mine || here.category() == Categorie.P) {
				if(here instanceof Void) {
					// /!\ si on est en dehors du terrain
					LinkedList<Entity> l_entityN = getElement(ligne-1, colonne);
					LinkedList<Entity> l_entityE = getElement(ligne, colonne+1);
					LinkedList<Entity> l_entityS = getElement(ligne+1, colonne);
					LinkedList<Entity> l_entityW = getElement(ligne, colonne-1);
					Entity elem;
					boolean MurN = false;
					boolean MurE = false;
					boolean MurS = false;
					boolean MurW = false;
					for(int i=0; i<l_entityN.size(); i++) {
						elem = l_entityN.get(i);
						if(elem instanceof Normal || elem instanceof Cassable) {
							MurN = true;
						}
					}
					for(int i=0; i<l_entityS.size(); i++) {
						elem = l_entityS.get(i);
						if(elem instanceof Normal || elem instanceof Cassable) {
							MurS = true;
						}
					}
					if(MurN && MurS) {
						return !hasSameLayer(ligne, colonne, e.layer());
					}
					for(int i=0; i<l_entityE.size(); i++) {
						elem = l_entityE.get(i);
						if(elem instanceof Normal || elem instanceof Cassable) {
							MurE = true;
						}
					}
					for(int i=0; i<l_entityW.size(); i++) {
						elem = l_entityW.get(i);
						if(elem instanceof Normal || elem instanceof Cassable) {
							MurW = true;
						}
					}
					if(MurE && MurW) {
						return !hasSameLayer(ligne, colonne, e.layer());
					}
					return false;
				}
			}
			return false;
		case "Fleche":
			if(here instanceof Void || here instanceof Sable || here instanceof Mine 
					|| here.category() == Categorie.P || here.layer() == 0) {
				return true;
			}
			return false;
		case "Lave":
		case "Teleporteur":
			if(here instanceof Void) {
				remove(ligne, colonne, here);
				return true;
			}
			return false;
		}
		return false;
	}

	public ArrayList<Object> get_labyrinthe() {
		return this.labyrinthe;
	}
	
	public LinkedList<LinkedList<Entity>> getAround(int ligne, int colonne){
		LinkedList<LinkedList<Entity>> l_around = new LinkedList<LinkedList<Entity>>();
		l_around.addLast(getElement(ligne-1, colonne));
		l_around.addLast(getElement(ligne-1, colonne+1));
		l_around.addLast(getElement(ligne, colonne+1));
		l_around.addLast(getElement(ligne+1, colonne+1));
		l_around.addLast(getElement(ligne+1, colonne));
		l_around.addLast(getElement(ligne+1, colonne-1));
		l_around.addLast(getElement(ligne, colonne-1));
		l_around.addLast(getElement(ligne-1, colonne-1));
		return l_around;
	}
	
	public LinkedList<Entity> ListEntity(Class<?> c){
		LinkedList<Entity> l_entity = new LinkedList<Entity>();
		LinkedList<Entity> elem;
		Entity entity;
		for(int i=0; i<ligne; i++) {
			for(int j=0; j<colonne; j++) {
				elem = getElement(i,j);
				for(int k=0; k<elem.size(); k++) {
					entity = elem.get(k);
					if(c.isInstance(entity)) {
						l_entity.add(entity);
					}
				}
			}
		}
		return l_entity;
	}
}
