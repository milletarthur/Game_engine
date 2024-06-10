package Model_Snake;

import java.io.PrintStream;

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
}
