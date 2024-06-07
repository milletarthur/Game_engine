import java.awt.*;
import javax.swing.*;

/*
 * Classe Window qui gère la fenêtre du snake
 */

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	int LARGEUR ;
	int HAUTEUR ;
	
	DrawTerrain dt ;
	DrawTicTac dtt ;
	
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
		
		// initialisation du terrain
		this.dt = new DrawTerrain(LARGEUR, HAUTEUR);
		this.dtt = new DrawTicTac();
	}
	
	public void init_Window(TicTac t) {

		dtt.settimer(t);
		// impose la taille de la fenêtre avec celui du JPanel
		dt.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));

		// ajout du terrain à la fenêtre
		this.add(dt, BorderLayout.CENTER);
		
		//pour timer
		JPanel timer = new JPanel();
		timer.setLayout(new FlowLayout());
		JLabel tick = new JLabel("tick : ");
		timer.add(tick);
		timer.add(dtt);
		this.add(timer, BorderLayout.NORTH);
		
		// fenêtre de la taille du JPanel qu'il contient
		this.pack();

		// rendre la fenêtre visible
		this.setVisible(true);
	}

}
