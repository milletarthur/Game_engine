package Model_Snake;

import java.io.PrintStream;

import toolkit.Categorie;
import toolkit.Direction;

public class Field {
	private int colonne;
	private int ligne;
	private Entity[][] grid;
	private Snake snake;

	public Field(int col, int lig) {
		grid = new Entity[col][lig];
		this.colonne = col;
		this.ligne = lig;
		for (int i = 0; i < lig; i++) {
			for (int j = 0; j < col; j++) {
				grid[i][j] = new Void(i, j, 1, 1, this);
			}
		}
		grid[5][5] = new Apple(5, 5, 1, 1, this);
		grid[0][0] = new Snake(0, 0, 1, 1, this);
		
	}

	Entity elementAt(int x, int y) {
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

	public void printGame(PrintStream ps) {
		for (int i = 0; i < this.ligne; i++) {
			for (int j = 0; j < this.colonne; j++) {
				Entity e = elementAt(i, j);
				if (e instanceof Void)
					ps.print("_");
				if (e instanceof Snake)
					ps.print("S");
				if (e instanceof Queue)
					ps.print("q");
				if (e instanceof Apple)
					ps.print("A");
				if (e instanceof Obstacle)
					ps.print("o");
			}
			ps.print("\n");
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
