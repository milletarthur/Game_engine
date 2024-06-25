package View.draw;

import java.awt.*;
import javax.swing.*;

import Labyrinthe.Joueur;
import controller.listener.JSONWindow;

public class PVJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String name;
	Joueur j;
	private static int T_case = WindowInitGame.T_case;
	private static int visibility = JSONWindow.visibility;
	private static int pv_tot = JSONWindow.pv;
	// pv ci-dessous correspond au dessin de la barre sur le JPanel
	private int pv;

	public PVJPanel(String name, Joueur j, int var) {
		this.pv = (T_case * visibility * 2 + T_case) - 130 - var * 65;
		this.setPreferredSize(new Dimension((T_case * visibility * 2 + T_case) - 110 - var * 65, 65));
		this.setBackground(Color.black);

		this.j = j;
		this.name = limitString(name, 20);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int p = pv_duper();
		g.setColor(Color.white);
		g.drawString(name, 15, 23);
		if (p <= (pv * 0.25))
			g.setColor(Color.red);
		else
			g.setColor(Color.green);
		g.drawRoundRect(10, 35, pv, 20, 10, 10);
		g.fillRoundRect(10, 35, p, 20, 10, 10);
	}

	public int pv_duper() {
		int pv_j = j.getVie();
		if (pv_j > pv_tot)
			pv_j = pv_tot;
		int perdu = pv * pv_j / pv_tot;
		if (perdu < 0)
			perdu = 0;
		return perdu;
	}
	
	// récupère une taille max de caractères
	public static String limitString(String input, int maxLength) {
        if (input == null) {
            return null;
        }
        return input.length() > maxLength ? input.substring(0, maxLength) : input;
    }

}
