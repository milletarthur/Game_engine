package draw;

import java.awt.*;
import javax.swing.*;

import Labyrinthe.Joueur;
import listener.JSONWindow;

public class PVJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String name ;
	Joueur j;
	private static int T_case = WindowInitGame.T_case;
	private static int visibility = JSONWindow.visibility;
	private static int pv_tot = JSONWindow.pv;
	// pv ci-dessous correspond au dessin de la barre sur le JPanel
	private static int pv = (T_case * visibility * 2 + T_case) - 130 ;

	public PVJPanel(String name, Joueur j) {
		this.setPreferredSize(new Dimension((T_case * visibility * 2 + T_case) - 110, 65));
		this.setBackground(Color.black);
				
		this.j = j ;
		this.name = name;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int p = pv_duper();
		g.setColor(Color.white);
		g.drawString(name, 15, 23);
		if (p <= (pv * 0.1))
			g.setColor(Color.red);
		else 
			g.setColor(Color.green);
		g.drawRoundRect(10, 35, pv, 20, 10, 10);
		g.fillRoundRect(10, 35, p, 20, 10, 10);
	}
	
	public int pv_duper() {
		int perdu = pv * j.getVie() / pv_tot;
		if (perdu < 0)
			perdu = 0;
		return perdu;
	}

}
