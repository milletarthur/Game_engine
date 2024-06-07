package Model_Snake;

import java.util.LinkedList;

import toolkit.Categorie;
import toolkit.Direction;
import toolkit.Pair;

public class Field {
	private int colonne;
	private int ligne;
	private Entity[][] grid;
	private Snake snake;

	public Field(int col, int lig) {
		grid = new Entity[lig][col];
		this.colonne = col;
		this.ligne = lig;
		for (int i = 0; i < lig; i++) {
			for (int j = 0; j < col; j++) {
				grid[i][j] = new Void(i, j, 0, Categorie.V, this);
			}
		}
	}
	
	
	public Entity elementAt(int x, int y) {
		// throw new RuntimeException("Not implemented Yet !");
		if(x >= ligne || y >= colonne || x < 0 || y < 0) {
			return new Obstacle(x,y, -1, Categorie.O, this);
		}
		return grid[x][y];
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

	private int[] next_to(Entity e, int d) {
		int[] rv = new int[2];
		rv[0] = e.x();
		rv[1] = e.y();
		switch (e.direction()) {
		case Direction.N:
			switch (d) {
			case Direction.L:
				rv[0]--;
				break;
			case Direction.R:
				rv[0]++;
				break;
			case Direction.B:
				rv[1]++;
				break;
			case Direction.F:
				rv[1]--;
				break;
			default:
				break;
			}
			break;
		case Direction.S:
			switch (d) {
			case Direction.L:
				rv[0]++;
				break;
			case Direction.R:
				rv[0]--;
				break;
			case Direction.B:
				rv[1]--;
				break;
			case Direction.F:
				rv[1]++;
				break;
			default:
				break;
			}
			break;
		case Direction.E:
			switch (d) {
			case Direction.L:
				rv[1]--;
				break;
			case Direction.R:
				rv[1]++;
				break;
			case Direction.B:
				rv[0]--;
				break;
			case Direction.F:
				rv[0]++;
				break;
			default:
				break;
			}
			break;
		case Direction.W:
			switch (d) {
			case Direction.L:
				rv[1]++;
				break;
			case Direction.R:
				rv[1]--;
				break;
			case Direction.B:
				rv[0]++;
				break;
			case Direction.F:
				rv[0]--;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return rv;
	}

	public boolean cell(Entity e, int d, int c) {
		int[] pos_to_check = next_to(e, d);
		Entity k = elementAt(pos_to_check[0], pos_to_check[1]);
		return k.category() == c;
	}
	
	public LinkedList<Pair<Integer,Integer>> getVoidList() {
		LinkedList<Pair<Integer,Integer>> rv = new LinkedList<Pair<Integer,Integer>>();
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (elementAt(i, j) instanceof Void) {
					rv.add(new Pair<Integer,Integer>(i,j));
				}
			}
		}
		return rv;
	}
	
//	public static final int A = 0;	// un Autre (adversaire ou membre de l'autre équipe)
//	public static final int C = 1;	// un indice d'un précédent passage (Clue)
//	public static final int D = 2;	// un Danger
//	public static final int G = 3;	// un Gate (passage)
//	public static final int J = 4;	// un élément sur lequel on peut sauter (Jumpable)
//	public static final int M = 5;	// un Missile
//	public static final int O = 6;	// un Obstacle
//	public static final int P = 7; 	// un élément que l'on peut Prendre (Pick), stocker, lancer, déposer
//	public static final int T = 8;	// Team = une entité de mon équipe mais pas moi
//	public static final int V = 9;	// Void
//	public static final int Arobase = 10;	// Le joueur de mon équipe 
//	public static final int Diese = 11;	// Le joueur de l'autre équipe
//	public static final int Tiret = 12;	// n'importe quelle entité sauf Void
	
	public void print() {
		for (int i = 0; i < ligne; i++) {
			System.out.print("[");
			for (int j = 0; j < ligne; j++) {
				char toprint = '_';
				switch(grid[i][j].category()){
				case 0 :
					toprint = 'A';
					break;
				case 1 :
					toprint = 'C';
					break;
				case 2 :
					toprint = 'D';
					break;
				case 3 :
					toprint = 'G';
					break;
				case 4 :
					toprint = 'J';
					break;
				case 5 :
					toprint = 'M';
					break;
				case 6 :
					toprint = 'O';
					break;
				case 7 :
					toprint = 'P';
					break;
				case 8 :
					toprint = 'T';
					break;
				case 9 :
					toprint = 'V';
					break;
				case 10 :
					toprint = '@';
					break;
				case 11 :
					toprint = '#';
					break;
				default :
					toprint = '_';
					break;
				}
				System.out.print(toprint + ";");
			}
			System.out.print("]\n");
		}
	}

	/* Fonctionnement de la fonction update : 
	 * Elle doit être appellée à chauqe fois que l'une des actions est faite [move, pick, throw, egg, explode (hit, pop, wizz)]
	 *  - Move : trivial arguments
	 *  - Pick : update(picked entity, old_x, old_y, -1, -1)
	 *  - Throw : update(thrown entity, -1, -1, new_x, new_y)
	 *  - Egg : update(thrown entity, -1, -1, new_x, new_y)
	 *  - Explode : update(exploded entity, old_x, old_y, -1, -1)
	 */
	public void update(Entity e, int old_x, int old_y, int new_x, int new_y) {
		if(!e.valid()) {
			grid[old_x][old_y] = new Void(old_x,old_y,0,Categorie.V, this);
			return;
		}
		if((old_x != new_x || old_y != new_y) && new_x != -1 && new_y != -1) {
			if(old_x != -1 && old_y != -1) 
				grid[old_x][old_y] = new Void(old_x,old_y,0,Categorie.V, this);
			grid[new_x][new_y] = e;
			return;
		}
		if (new_x == -1 && new_y == -1)
			grid[old_x][old_y] = new Void(old_x,old_y,0,Categorie.V, this);
		return;
	}
}
