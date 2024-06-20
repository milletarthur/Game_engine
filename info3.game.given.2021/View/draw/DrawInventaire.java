package draw;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

import controller.TicTac;

public class DrawInventaire extends JPanel {

	private static final long serialVersionUID = 1L;
	TicTac t;
	private ImageJPanel invent1;
	private PVJPanel pdv1;
	private JLabel timer;
	private PVJPanel pdv2;
	private ImageJPanel invent2;

	private Sprite INVENTAIRE;
	private BufferedImage img_inventaire;
	
	private static final int pv_total = 20;
	
	// TODO - pv_perdu a recuperer / variable ci-dessous temp pour test
	// il y aura pour les deux joueurs
	private static final int pv_perdu = 5;
	
	private static final int temps = 5 ; // en minutes

	public DrawInventaire(int T_case, int visibility) throws IOException {

		// TODO - lui donner l'inventaire de chaque joueur pour avoir l'objet courant

		this.Image();

		this.setPreferredSize(new Dimension(65, 65));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// inventaire 1
		this.invent1 = new ImageJPanel();
		this.add(invent1);

		// points de vie 1
		this.pdv1 = new PVJPanel(T_case, visibility, pv_perdu, pv_total, "Gruber");
		this.add(pdv1);

		// timer
		this.timer = new JLabel("" + 0 + "");
		timer.setPreferredSize(new Dimension(100, 65));
		timer.setHorizontalAlignment(SwingConstants.CENTER);
		timer.setVerticalAlignment(SwingConstants.CENTER);
		this.add(timer);

		// points de vie 2
		this.pdv2 = new PVJPanel(T_case, visibility, pv_perdu, pv_total, "Périn");
		this.add(pdv2);

		// inventaire 2
		this.invent2 = new ImageJPanel();
		this.add(invent2);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.paintTimer();
		
		// TODO - donner objet courant à la place de DrawTerrain...
		invent1.setImage(img_inventaire, DrawTerrain.bombe, 0, 0, 65);
		invent2.setImage(img_inventaire, DrawTerrain.arc, 0, 0, 65);
	}

	public void settimer(TicTac t) {
		this.t = t;
	}
	
	public int calculminutes(long tick) {
		return (int) tick / 60 ;
	}
	
	public int calculsecondes(long tick) {
		return (int) tick % 60 ;
	}
	
	public void paintTimer() {
		long tick = t.getTick();
		int sec = 60 - calculsecondes(tick);
		int min = temps - 1 - calculminutes(tick);
		int init = temps - calculminutes(tick);
		if (min < 10) {
			if (init < 10) {
				if (sec == 60)
					timer.setText("0"+init+" : 00");
				else if (sec < 10)
					timer.setText("0"+min+" : 0"+sec+"");
				else 
					timer.setText("0"+min+" : "+sec+"");
			} else {
				if (sec == 60)
					timer.setText(init+" : 00");
				else if (sec < 10)
					timer.setText("0"+min+" : 0"+sec+"");
				else 
					timer.setText("0"+min+" : "+sec+"");
			}
		} else {
			if (sec == 60)
				timer.setText(init+" : 00");
			else if (sec < 10)
				timer.setText(min+" : 0"+sec+"");
			else 
				timer.setText(min+" : "+sec+"");
		}
	}

	public void Image() throws IOException {
		this.INVENTAIRE = new Sprite("resources/graphisme/Structures/final_room.png", 24, 24);
		this.img_inventaire = INVENTAIRE.getSprite(2, 0);
	}

}
