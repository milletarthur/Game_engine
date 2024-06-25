package View.draw;

import java.awt.*;
import javax.swing.*;

import Labyrinthe.Joueur;
import controller.listener.EndGameButtonListener;
import controller.listener.JSONWindow;

public class DrawEndGame extends JFrame {

	private static final long serialVersionUID = 1L;
	Image end;
	private JLabel phrase, gagnant;
	Joueur j1, j2;
	DrawWindow w;

	public DrawEndGame(boolean win, Joueur j1, Joueur j2, DrawWindow w) { // true = gagner, false = perdu
		this.j1 = j1;
		this.j2 = j2;

		w.dispose();

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
		
		JButton rejouer = new JButton("Rejouer");
		JButton quitter = new JButton("Quitter");
		rejouer.addActionListener(new EndGameButtonListener(true, this));
		quitter.addActionListener(new EndGameButtonListener(false, this));

		if (win) {
			if (j1 != null && j2 != null) {
				this.phrase = new JLabel("Well done ! You win !");
				phrase.setForeground(Color.WHITE);
				phrase.setBounds(150, 130, 150, 30);
				this.gagnant = new JLabel("Beau travail d'équipe !");
				gagnant.setForeground(Color.WHITE);
				gagnant.setBounds(145, 150, 160, 30);
				choice.add(phrase);
				choice.add(gagnant);
				rejouer.setBounds(170, 190, 110, 30);
				quitter.setBounds(170, 230, 110, 30);
			} else if (j1 != null && j2 == null) {
				this.phrase = new JLabel("Well done ! You win !");
				phrase.setForeground(Color.WHITE);
				phrase.setBounds(150, 130, 150, 30);
				this.gagnant = new JLabel("Victoire de " + JSONWindow.name1 + " !");
				gagnant.setForeground(Color.WHITE);
				gagnant.setBounds((int) (175 - (JSONWindow.name1.length() * 3.75)+1), 150, 250, 30);
				choice.add(phrase);
				choice.add(gagnant);
				rejouer.setBounds(170, 190, 110, 30);
				quitter.setBounds(170, 230, 110, 30);
			} else if (j1 == null && j2 != null) {
				this.phrase = new JLabel("Well done ! You win !");
				phrase.setForeground(Color.WHITE);
				phrase.setBounds(150, 130, 150, 30);
				this.gagnant = new JLabel("Victoire de " + JSONWindow.name2 + " !");
				gagnant.setForeground(Color.WHITE);
				gagnant.setBounds((int) (175 - (JSONWindow.name2.length() * 3.75)+1), 150, 250, 30);
				choice.add(phrase);
				choice.add(gagnant);
				rejouer.setBounds(170, 190, 110, 30);
				quitter.setBounds(170, 230, 110, 30);
			}
		} else {
			this.phrase = new JLabel("Game Over");
			phrase.setForeground(Color.WHITE);
			phrase.setBounds(184, 130, 100, 30);
			choice.add(phrase);
			rejouer.setBounds(170, 160, 110, 30);
			quitter.setBounds(170, 200, 110, 30);
		}

		choice.add(rejouer);
		choice.add(quitter);

		this.add(choice, BorderLayout.CENTER);
		this.setVisible(true);
	}

}
