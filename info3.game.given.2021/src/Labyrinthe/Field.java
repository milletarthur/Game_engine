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
		// Code test
		// ####################################################
		//  int r1 = rand.nextInt(1000);
		/*int x1 = 1;
		int y1 = 0;
		int j = 0 ;
		while ( j < ligne ) {
			labyrinthe[x1][y1] = new Apple(x1, y1, 1, 1, this);
			int eval  = rand.nextInt(2);
			if ( eval == 1 && y1 < colonne - 1 ) {
				y1++;
			} else if ( eval == 0 && x1 < ligne - 1 ) {
				x1++;
				j++;
			}
		}
		*/
		
		int x1 = 1;
		int y1 = 0;

		while (x1 < ligne && y1 < colonne) {
		    labyrinthe[x1][y1] = new Apple(x1, y1, 1, 1, this);
		    
		    // Générez une direction aléatoire
		    int eval = rand.nextInt(2);
		    
		    // Vérifiez si vous pouvez aller à droite
		    if (eval == 1 && y1 < colonne - 1) {
		        y1++;
		    } 
		    // Sinon, essayez de descendre
		    else if (eval == 0 && x1 < ligne - 1) {
		        x1++;
		    }
		    // Si vous ne pouvez ni aller à droite ni descendre, essayez l'autre direction
		    else if (y1 >= colonne - 1 && x1 < ligne - 1) {
		        x1++;
		    } 
		    else if (x1 >= ligne - 1 && y1 < colonne - 1) {
		        y1++;
		    }
		    // Si les deux directions sont bloquées (au cas où), sortez de la boucle
		    else {
		        break;
		    }
		}

		
		
		// #################################################
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
				if (e instanceof Apple)
					ps.print("*");
			}
			ps.print("\n");
		}
	}

}
