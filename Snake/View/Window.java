import java.awt.*;
import javax.swing.*;

/*
 * Classe Window qui gère la fenêtre du snake
 */

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public Window (int LARGEUR, int HAUTEUR) {
		this.getContentPane().setLayout(new BorderLayout());
		// nom de la fenêtre
		this.setTitle("Snake");
		//empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		// application terminé quand utilisateur quitte
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
