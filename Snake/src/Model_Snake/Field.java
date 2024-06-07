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
	
	public void set_elementAt(Entity e) {
		grid[e.x][e.y] = e;
	}
	
	public Entity elementAt(int x, int y) {
		// throw new RuntimeException("Not implemented Yet !");
		if(x >= ligne || y > colonne || x < 0 || y < 0) {
			return new Obstacle(x,y, -1, Categorie.O, this);
		}
		return grid[x][y];
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
		int[] pos_to_check = next_to(e,d);
		Entity k = elementAt(pos_to_check[0],pos_to_check[1]);
		return k.category() == c;
	}
	
	public LinkedList<Pair> getVoidList() {
		LinkedList<Pair> rv = new LinkedList<Pair>();
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (elementAt(i, j) instanceof Void) {
					rv.add(new Pair(i,j));
				}
			}
		}
		return rv;
	}
}
