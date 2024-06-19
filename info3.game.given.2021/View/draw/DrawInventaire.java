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
	private JPanel pdv1;
	private JLabel timer;
	private JPanel pdv2;
	private ImageJPanel invent2;
	
	private Sprite INVENTAIRE ;
	private BufferedImage img_inventaire ;

	public DrawInventaire(int T_case, int visibility) throws IOException {
		
		this.Image();
		
		this.setPreferredSize(new Dimension(65, 65));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// inventaire 1
		this.invent1 = new ImageJPanel();
		invent1.setPreferredSize(new Dimension(65, 65));
		this.add(invent1);

		// points de vie 1
		this.pdv1 = new JPanel();
		pdv1.setPreferredSize(new Dimension((T_case * visibility * 2 + T_case) - 110, 65));
		pdv1.setBackground(Color.black);
		this.add(pdv1);

		// timer
		this.timer = new JLabel(""+0+"");
		timer.setPreferredSize(new Dimension(100, 65));
        timer.setHorizontalAlignment(SwingConstants.CENTER);
        timer.setVerticalAlignment(SwingConstants.CENTER);
		this.add(timer);

		// points de vie 2
		this.pdv2 = new JPanel();
		pdv2.setPreferredSize(new Dimension((T_case * visibility * 2 + T_case) - 110, 65));
		pdv2.setBackground(Color.black);
		this.add(pdv2);

		// inventaire 2
		this.invent2 = new ImageJPanel();
		invent2.setPreferredSize(new Dimension(65, 65));
		this.add(invent2);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		timer.setText(""+t.getTick()+"");
		invent1.setImage(img_inventaire, DrawTerrain.bombe, 0, 0, 65);
		invent2.setImage(img_inventaire, DrawTerrain.arc, 0, 0, 65);
	}
	
	public void settimer(TicTac t) {
		this.t = t ;
	}
	
	public void Image() throws IOException {
		this.INVENTAIRE = new Sprite("resources/graphisme/Structures/final_room.png", 24, 24);
		this.img_inventaire = INVENTAIRE.getSprite(2, 0);
		
	}

}
