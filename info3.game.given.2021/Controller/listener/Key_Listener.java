package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Labyrinthe.Joueur;
import draw.DrawTerrain;
import draw.Viewport;
import toolkit.Direction;

public class Key_Listener implements KeyListener {

	Joueur j1;
	Joueur j2;
	DrawTerrain dt1;
	DrawTerrain dt2;
	Viewport v1;
	Viewport v2;

	public Key_Listener(Joueur j1, Joueur j2, DrawTerrain dt1, DrawTerrain dt2, Viewport v1, Viewport v2) {
		this.j1 = j1;
		this.j2 = j2;
		this.dt1 = dt1;
		this.dt2 = dt2;
		this.v1 = v1;
		this.v2 = v2;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int touche = e.getKeyCode();
		switch (touche) {
		case KeyEvent.VK_UP: // haut j1
			j1.setOrientation(Direction.N);
			j1.move();
			v1.centrerViewport(j1);
			dt2.repaint();
			break;
		case KeyEvent.VK_LEFT: // droite j1
			j1.setOrientation(Direction.W);
			j1.move();
			v1.centrerViewport(j1);
			dt2.repaint();
			break;
		case KeyEvent.VK_RIGHT: // gauche j1
			j1.setOrientation(Direction.E);
			j1.move();
			v1.centrerViewport(j1);
			dt2.repaint();
			break;
		case KeyEvent.VK_DOWN: // bas j1
			j1.setOrientation(Direction.S);
			j1.move();
			v1.centrerViewport(j1);
			dt2.repaint();
			break;
		case KeyEvent.VK_Z: // haut j2
			j2.setOrientation(Direction.N);
			j2.move();
			v2.centrerViewport(j2);
			dt1.repaint();
			break;
		case KeyEvent.VK_S: // bas j2
			j2.setOrientation(Direction.S);
			j2.move();
			v2.centrerViewport(j2);
			dt1.repaint();
			break;
		case KeyEvent.VK_Q: // gauche j2
			j2.setOrientation(Direction.W);
			j2.move();
			v2.centrerViewport(j2);
			dt1.repaint();
			break;
		case KeyEvent.VK_D: // droite j2
			j2.setOrientation(Direction.E);
			j2.move();
			v2.centrerViewport(j2);
			dt1.repaint();
			break;
		}

	}

	public void keyReleased(KeyEvent e) {

	}
}