package toolkit;

public class Categorie {
	public static final int A = 0;	// un Autre (adversaire ou membre de l'autre équipe)
	public static final int C = 1;	// un indice d'un précédent passage (Clue)
	public static final int D = 2;	// un Danger
	public static final int G = 3;	// un Gate (passage)
	public static final int J = 4;	// un élément sur lequel on peut sauter (Jumpable)
	public static final int M = 5;	// un Missile
	public static final int O = 6;	// un Obstacle
	public static final int P = 7; 	// un élément que l'on peut Prendre (Pick), stocker, lancer, déposer
	public static final int T = 8;	// Team = une entité de mon équipe mais pas moi
	public static final int V = 9;	// Void
	public static final int Arobase = 10;	// Le joueur de mon équipe 
	public static final int Diese = 11;	// Le joueur de l'autre équipe
	public static final int Tiret = 12;	// n'importe quelle entité sauf Void
}
