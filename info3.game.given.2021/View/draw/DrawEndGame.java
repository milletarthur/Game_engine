package draw;

import java.awt.*;
import javax.swing.*;

import Labyrinthe.Joueur;
import listener.EndGameButtonListener;
import listener.JSONWindow;

public class DrawEndGame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	Image end;
	private JLabel phrase; 
	Joueur j1, j2;

	public DrawEndGame(boolean win, Joueur j1, Joueur j2) { // true = gagner, false = perdu
		this.j1 = j1;
		this.j2 = j2;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Fin de la partie");
		this.setSize(450, 400);
		
		this.end = DrawTerrain.drawEntity("resources/graphisme/init.png");
		
		JPanel choice = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(end, 0, 0, this.getWidth(), this.getHeight(), this);
			}

		};
		
		choice.setLayout(null);
		
		if (win) {
			if (j1 != null && j2 != null) {
				this.phrase = new JLabel("Well done ! You win ! Beau travail d'équipe !");
				phrase.setForeground(Color.WHITE);
				phrase.setBounds(150,130,150,30);
			} else if (j1 != null && j2 == null) {
				String namej1 = JSONWindow.name1;
				this.phrase = new JLabel("Well done ! You win ! Victoire de " + namej1 + " !");
				phrase.setForeground(Color.WHITE);
				phrase.setBounds(150,130,150,30);
			} else if (j1 == null && j2 != null) {
				String namej2 = JSONWindow.name2;
				this.phrase = new JLabel("Well done ! You win ! Victoire de " + namej2 + " !");
				phrase.setForeground(Color.WHITE);
				phrase.setBounds(150,130,150,30);
			}
		} else {
			this.phrase = new JLabel("Game Over");
			phrase.setForeground(Color.WHITE);
			phrase.setBounds(184,130,100,30);
		}
		
		choice.add(phrase);
		
		JButton rejouer = new JButton("Rejouer");
		JButton quitter = new JButton("Quitter");
		rejouer.addActionListener(new EndGameButtonListener(true,this));
		quitter.addActionListener(new EndGameButtonListener(false,this));
		rejouer.setBounds(170,160,110,30);
		quitter.setBounds(170,200,110,30);
		
		choice.add(rejouer);
		choice.add(quitter);
		
		this.add(choice, BorderLayout.CENTER);
		this.setVisible(true);
	}

}
