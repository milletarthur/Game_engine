package Model_Snake;

public class Field {
	private int colonne;
	private int ligne;
	private Entity[][] grid;
	private Snake snake;

	Entity elementAt(int x, int y) {
		// throw new RuntimeException("Not implemented Yet !");
		return grid[x][y];

	}

	public Field(int col, int lig) {
		grid = new Entity[col][lig];
		this.colonne = col;
		this.ligne = lig;
		for (int i = 0; i < lig; i++) {
			for (int j = 0; i < col; j++) {
				grid[i][j] = new Void(i, j);
			}
		}
		grid[5][5] = new Apple(5, 5);
	}

	void c_init(int c) {
		this.colonne = c;
	}

	void l_init(int l) {
		this.ligne = l;
	}

}
