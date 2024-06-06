package Model_Snake;

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
				grid[i][j] = new Void(i, j);
			}
		}
		grid[4][4] = new Apple(4, 4);
		grid[0][0] = new Snake(0, 0);
		grid[1][0] = new Queue(1, 0);
		grid[1][1] = new Queue(1, 1);
		grid[2][2] = new Obstacle(2, 2);
	}
	
	public Entity elementAt(int x, int y) {
		// throw new RuntimeException("Not implemented Yet !");
		return grid[x][y];

	}

	void c_init(int c) {
		this.colonne = c;
	}

	void l_init(int l) {
		this.ligne = l;
	}

}
