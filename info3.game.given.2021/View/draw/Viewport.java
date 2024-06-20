package draw;

import java.awt.*;
import javax.swing.*;

import Labyrinthe.Joueur;

public class Viewport extends JViewport {

	private static final long serialVersionUID = 1L;
	private DrawTerrain dt;
	private int T_case;
	private int visibility ;

	public Viewport(DrawTerrain dt, int T_case, int visibility) {
		this.dt = dt;
		this.T_case = T_case;
		this.visibility = visibility * T_case * 2 + T_case ;
		this.setPreferredSize(new Dimension(this.visibility, this.visibility));
		this.setView(dt);
	}

	public void centrerViewport(Joueur j) {

		int x = j.getX() * T_case + T_case / 2 - this.visibility / 2;
		int y = j.getY() * T_case + T_case / 2 - this.visibility / 2;
		dt.repaint();
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		this.setViewPosition(new Point(y, x));		

	}

}
