package Labyrinthe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import toolkit.*;

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
		// detruire_mur(densite);
		labyrinthe();

		// depotPorte(5);
		// printGame();
		// Obstacle(densite);

		depotPorte(1);
		// bulldozer(10);
		// LinkedList<Entity> liste = null;
		// deposer_Porte_2(liste);

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

				if (e instanceof Porte)
					System.out.print("+");
				if (e instanceof Interrupteur)
					System.out.print("<");

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

	public LinkedList<LinkedList<Entity>> croisement() {
		LinkedList<LinkedList<Entity>> RES = null;
		for (int i = 0; i < ligne; i++) {
			ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(i);
			for (int j = 0; j < colonne; j++) {
				LinkedList<Entity> col = row.get(j);
				int count = 0;
				if (col.getFirst().x > 0 && col.getFirst() instanceof Void
						&& (get_element(col.getFirst().x - 1, col.getFirst().y) instanceof Void)) {
					count++;
				}
				if (col.getFirst().y < ligne - 1 && col.getFirst() instanceof Void
						&& (get_element(col.getFirst().x + 1, col.getFirst().y) instanceof Void)) {
					count++;
				}
				if (col.getFirst().y > 0 && col.getFirst() instanceof Void
						&& (get_element(col.getFirst().x, col.getFirst().y - 1) instanceof Void)) {
					count++;
				}
				if (col.getFirst().y < colonne - 1 && col.getFirst() instanceof Void
						&& (get_element(col.getFirst().x, col.getFirst().y + 1) instanceof Void)) {
					count++;
				}

				if (count >= 2 && RES != null) {
					RES.add(col);
				}
			}
		}
		return RES;
	}

	public void depotPorte(int Nombre_Porte) {
		LinkedList<LinkedList<Entity>> RES = croisement();
		LinkedList<Entity> chemin_enregistre = new LinkedList<Entity>();
		int x = 1;
		int y = 0;
		for (int t = 0; t < 1; t++) {
			Triple<LinkedList<Entity>, Entity, LinkedList<Entity>> liste = new Triple<LinkedList<Entity>, Entity, LinkedList<Entity>>(
					null, null, null);
			liste = deposer_Porte_2(x, y, chemin_enregistre);
			for (int g = 0; g < liste.x().size(); g++) {
				chemin_enregistre.add(liste.x().get(g));
			}
			for (int i = 0; i < liste.x().size(); i++) {
				if (!(liste.x().get(i) instanceof Mur)) {
					System.out.print(" x : ");
					System.out.print(liste.x().get(i).x);
					System.out.print(" y : ");
					System.out.print(liste.x().get(i).y);
					System.out.print("\n");
				} else {
					System.out.println("C'est un Mur");
				}
			}
			x = liste.z().getFirst().x;
			y = liste.z().getFirst().y;
		}

	}

	Triple<LinkedList<Entity>, Entity, LinkedList<Entity>> deposer_Porte_2(int x, int y,
			LinkedList<Entity> chemin_enregistre) {
		int len = 10;
		Random r = new Random();
		LinkedList<Entity> liste = bulldozer(len, x, y, chemin_enregistre);
		while (liste.size() != 10) {
			liste = bulldozer(len, x, y, chemin_enregistre);
		}
		int i = liste.size() - 1;
		x = liste.getLast().x;
		y = liste.getLast().y;
		Triple<LinkedList<Entity>, Entity, LinkedList<Entity>> res = new Triple<LinkedList<Entity>, Entity, LinkedList<Entity>>(
				null, null, null);
		res.setx(liste);
		Entity elem = null;
		int condition = 1;
		Porte p = null;
		while (i > 0 && condition == 1) {
			if ((get_element(liste.get(i).x - 1, y) instanceof Mur)
					&& (get_element(liste.get(i).x + 1, y) instanceof Mur)) {
				p = new Porte(liste.get(i).x, liste.get(i).y, 1, 1, this);
				p.Orientation = 3;
				set_element(liste.get(i).x, liste.get(i).y, p, labyrinthe);
				elem = liste.remove(i);
				condition = 0;
			} else if ((get_element(liste.get(i).x, liste.get(i).y - 1) instanceof Mur)
					&& (get_element(liste.get(i).x, liste.get(i).y + 1) instanceof Mur)) {
				p = new Porte(liste.get(i).x, liste.get(i).y, 1, 1, this);
				p.Orientation = 1;
				set_element(liste.get(i).x, liste.get(i).y, p, labyrinthe);
				elem = liste.remove(i);
				condition = 0;
			}
			i = i - 1;
		}
		if (condition == 0) {
			int levier = r.nextInt(liste.size());
			while (!(liste.get(levier) instanceof Void) && !(liste.get(levier) instanceof Porte)) {
				levier = r.nextInt(liste.size());
			}
			LinkedList<Entity> l = new LinkedList<Entity>();
			l.add(p);
			Interrupteur Int = new Interrupteur(liste.getLast().x, liste.getLast().y, 1, 1, this, l);
			set_element(liste.get(levier).x, liste.get(levier).y, Int, labyrinthe);
			res.setz(l);
		}
		res.sety(elem);
		return res;

	}

	public Entity get_element(int indice_i, int indice_j) {
		ArrayList<LinkedList<Entity>> row = (ArrayList<LinkedList<Entity>>) labyrinthe.get(indice_i);
		LinkedList<Entity> elem = row.get(indice_j);
		return elem.element();
	}

	public LinkedList<Entity> bulldozer(int longueur, int x, int y, LinkedList<Entity> chemin_enregistre) {
		Random r = new Random();
		LinkedList<Entity> liste = new LinkedList<Entity>();
		Entity elemxy = null;
		// int x = 1;
		// int y = 0;
		int x2 = x;
		int y2 = y;
		int condition = 1;
		for (int i = 0; i < longueur; i++) {
			if (condition != 1) {
				x = x2;
				y = y2;
			}
			condition = 1;
			LinkedList<Integer> cases = new LinkedList<Integer>();
			cases.add(0);
			cases.add(1);
			cases.add(2);
			cases.add(3);
			while (condition == 1 && cases.size() != 0) {
				int direction = cases.get((new Random()).nextInt(cases.size()));
				switch (direction) {
				case 0: // Haut
					x2 = x;
					y2 = y - 1;
					if (y2 > 0 && (get_element(x2, y2) instanceof Void)) {
						elemxy = get_element(x2, y2);
					}
					break;
				case 1: // Bas
					x2 = x;
					y2 = y + 1;
					if (y2 < colonne - 1 && (get_element(x2, y2) instanceof Void)) {
						elemxy = get_element(x2, y2);
					}
					break;
				case 2: // Gauche
					x2 = x - 1;
					y2 = y;
					if (x2 > 0 && (get_element(x2, y2) instanceof Void)) {
						elemxy = get_element(x2, y2);
					}
					break;
				case 3: // Droite
					x2 = x + 1;
					y2 = y;
					if (y2 < ligne - 1 && (get_element(x2, y2) instanceof Void)) {
						elemxy = get_element(x2, y2);
					}
					break;
				}
				if (chemin_enregistre.contains(elemxy) || liste.contains(elemxy) || elemxy == null) {
					cases.remove((Object) direction);
					condition = 1;
				} else {
					liste.add(elemxy);
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
	
	public boolean cell(Entity e, int dir, Categorie cat) {
		int x = e.x();
		int y = e.y();
		switch(dir) {
			default : 
				return false;
		}
	}

}
