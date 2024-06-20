package draw;

import java.awt.*;
import javax.swing.*;

public class PVJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static int pv, perdu;
	private String name ;

	public PVJPanel(int T_case, int visibility, int pv_perdu, int pv_total, String name) {
		this.setPreferredSize(new Dimension((T_case * visibility * 2 + T_case) - 110, 65));
		this.setBackground(Color.black);
		
		// pv ci-dessous correspond au dessin de la barre sur le JPanel
		this.pv = (T_case * visibility * 2 + T_case) - 130 ;
		
		// pv perdu
		this.perdu = pv * pv_perdu / pv_total;
		if (this.perdu < 0)
			this.perdu = 0;
		
		this.name = name;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.green);
		g.drawString(name, 15, 23);
		g.drawRoundRect(10, 35, pv, 20, 10, 10);
		g.fillRoundRect(10, 35, pv-perdu, 20, 10, 10);
	}

}
