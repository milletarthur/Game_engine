package draw;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

import controller.TicTac;
import listener.JSONWindow;

public class DrawInventaire extends JPanel {

	private static final long serialVersionUID = 1L;
	TicTac t;
	private ImageJPanel invent1, invent2;
	private PVJPanel pdv1, pdv2;
	private JLabel timer;

	private Sprite INVENTAIRE;
	private BufferedImage img_inventaire;

	private static final int pv_total = 20;

	// TODO - pv_perdu a recuperer / variable ci-dessous temp pour test
	// il y aura pour les deux joueurs
	private static final int pv_perdu = 5;

	// TODO - temps à récupérer dans le fichier de config
	private static final int temps = 300; // en secondes
	private int temps_actuel, cpt;
	
	public DrawInventaire(int T_case, int visibility) throws IOException {

		// TODO - lui donner l'inventaire de chaque joueur pour avoir l'objet courant

		this.Image();

		this.setPreferredSize(new Dimension(65, 65));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// inventaire 1
		this.invent1 = new ImageJPanel();
		this.add(invent1);

		// points de vie 1
		this.pdv1 = new PVJPanel(T_case, visibility, pv_perdu, pv_total, JSONWindow.name1);
		this.add(pdv1);

		// timer
		this.timer = new JLabel("05 : 00");
		timer.setPreferredSize(new Dimension(100, 65));
		timer.setHorizontalAlignment(SwingConstants.CENTER);
		timer.setVerticalAlignment(SwingConstants.CENTER);
		this.add(timer);

		// points de vie 2
		this.pdv2 = new PVJPanel(T_case, visibility, pv_perdu, pv_total, JSONWindow.name2);
		this.add(pdv2);

		// inventaire 2
		this.invent2 = new ImageJPanel();
		this.add(invent2);

		this.temps_actuel = temps;
		this.cpt = 0;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.gestionTime();
		if (cpt == 10)
			this.paintTimer();

		// TODO - donner objet courant à la place de DrawTerrain...
		invent1.setImage(img_inventaire, DrawTerrain.bombe, 0, 0, 65);
		invent2.setImage(img_inventaire, DrawTerrain.arc, 0, 0, 65);
	}

	public void settimer(TicTac t) {
		this.t = t;
	}

	public void paintTimer() {
		temps_actuel -= 1;
		int min = temps_actuel / 60;
		int sec = temps_actuel % 60;
		if (min < 10) {
			if (sec < 10)
				timer.setText("0" + min + " : 0" + sec + "");
			else
				timer.setText("0" + min + " : " + sec + "");
		} else {
			if (sec < 10)
				timer.setText(min + " : 0" + sec + "");
			else
				timer.setText(min + " : " + sec + "");
		}
	}

	public void Image() throws IOException {
		this.INVENTAIRE = new Sprite("resources/graphisme/Structures/final_room.png", 24, 24);
		this.img_inventaire = INVENTAIRE.getSprite(2, 0);
	}

	public void gestionTime() {
		cpt++;
		if (cpt == 11)
			cpt = 0;
	}

}
