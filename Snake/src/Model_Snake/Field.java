package Model_Snake;

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
			for (int j = 0; i < col; j++) {
				grid[i][j] = new Void(i, j, 1, 1, this);
			}
		}
		grid[5][5] = new Apple(5, 5, 1, 1, this);
	}
	
	Entity elementAt(int x, int y) {
		return grid[x][y];
	}

	void c_init(int c) {
		this.colonne = c;
	}

	void l_init(int l) {
		this.ligne = l;
	}
	
	private int[] next_to (Entity e, Direction d) {
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
				rv = Direction.E;
				break;
			case Direction.R:
				rv = Direction.W;
				break;
			case Direction.B:
				rv = Direction.N;
				break;
			default:
				break;
			}
		case Direction.E:
			switch (d) {
			case Direction.L:
				rv = Direction.N;
				break;
			case Direction.R:
				rv = Direction.S;
				break;
			case Direction.B:
				rv = Direction.W;
				break;
			default:
				break;
			}
		case Direction.W:
			switch (d) {
			case Direction.L:
				rv = Direction.S;
				break;
			case Direction.R:
				rv = Direction.N;
				break;
			case Direction.B:
				rv = Direction.E;
				break;
			default:
				break;
			}
		default:
			break;
		}
	}
	
	public boolean cell(Entity e, Direction d, Categorie c) {
		int x = e.x();
		int y = e.y();
		
		
	}
}
