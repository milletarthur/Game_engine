import java.awt.*;
import javax.swing.*;

/*
 * Classe Window qui gère la fenêtre du snake
 */

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	int LARGEUR ;
	int HAUTEUR ;
	
	public Window (int LARGEUR, int HAUTEUR) {
		this.LARGEUR = LARGEUR ;
		this.HAUTEUR = HAUTEUR ;
		this.getContentPane().setLayout(new BorderLayout());
		// nom de la fenêtre
		this.setTitle("Snake");
		//empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		// application terminé quand utilisateur quitte
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void init_Window(Window w) {

		// initialisation du terrain
		DrawTerrain dt = new DrawTerrain(LARGEUR, HAUTEUR);
		// impose la taille de la fenêtre avec celui du JPanel
		dt.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));

		// ajout du terrain à la fenêtre
		w.add(dt, BorderLayout.CENTER);
		
		//pour timer
		TicTac t = new TicTac();
		JPanel timer = new JPanel();
		timer.setLayout(new FlowLayout());
		JLabel tick = new JLabel("tick : ");
		timer.add(tick);
		DrawTicTac dtt = new DrawTicTac(t);
		timer.add(dtt);
		w.add(timer, BorderLayout.NORTH);
		
		// fenêtre de la taille du JPanel qu'il contient
		w.pack();

		// rendre la fenêtre visible
		w.setVisible(true);
	}

}
