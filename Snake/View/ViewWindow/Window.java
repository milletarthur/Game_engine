package ViewWindow;

import java.awt.*;
import javax.swing.*;

/*
 * Classe Window qui gère la fenêtre du snake
 */

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	Color VERT = new Color(0,100,0);
	
	public Window (int LARGEUR, int HAUTEUR) {
        this.setSize(LARGEUR, HAUTEUR);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(VERT); 
		// nom de la fenêtre
		this.setTitle("Snake");
		// application terminé quand utilisateur quitte
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
