package Labyrinthe;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class Field {
	private int colonne;
	private int ligne;
	private Entity[][] labyrinthe;

	public Field(int lig, int col) {
		labyrinthe = new Entity[lig][col];
		this.colonne = col;
		this.ligne = lig;
		for (int i = 0; i < lig; i++) {
			for (int j = 0; j < col; j++) {
				labyrinthe[i][j] = new Normal(i, j, 1, 1, this);
			}
		}
		this.bulldozer();
	}

	public void bulldozer() {
		Random rand = new Random();
		int x2 = 0;
		int y2 = 0;
		labyrinthe[x2][y2] = new Void(x2, y2, 1, 1, this);
		int r = rand.nextInt(1000);
		for (int i = 0; i < r; i++) {
			int direction = rand.nextInt(4);
			switch (direction) {
			case 0:
				if (y2 > 0)
					y2--;
				break;
			case 1:
				if (y2 < colonne - 1)
					y2++;
				break;
			case 2:
				if (x2 > 0)
					x2--;
				break;
			case 3:
				if (x2 < ligne - 1)
					x2++;
				break;
			}
			labyrinthe[x2][y2] = new Void(x2, y2, 1, 1, this);
			x2 = rand.nextInt(ligne);
			y2 = rand.nextInt(colonne);
		}
	}

	Entity elementAt(int x, int y) {
		return labyrinthe[x][y];
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

	public void printGame(PrintStream ps) {
		for (int i = 0; i < this.ligne; i++) {
			for (int j = 0; j < this.colonne; j++) {
				Entity e = elementAt(i, j);
				if (e instanceof Void)
					ps.print(" ");
				if (e instanceof Mur)
					ps.print("O");
			}
			ps.print("\n");
		}
	}

}
