package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Labyrinthe.Joueur;
import draw.DrawTerrain;
import draw.Viewport;
import toolkit.Direction;

public class Key_Listener implements KeyListener {

	Joueur j1;
	DrawTerrain dt;
	Viewport v;

	public Key_Listener(Joueur j1, DrawTerrain dt, Viewport v) {
		this.j1 = j1;
		this.dt = dt;
		this.v = v;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int touche = e.getKeyCode();
		switch (touche) {
		case KeyEvent.VK_UP: // haut
			j1.setOrientation(Direction.N);
			break;
		case KeyEvent.VK_LEFT: // droite
			j1.setOrientation(Direction.W);
			break;
		case KeyEvent.VK_RIGHT: // gauche
			j1.setOrientation(Direction.E);
			break;
		case KeyEvent.VK_DOWN: // bas
			j1.setOrientation(Direction.S);
			break;
		}
		j1.move();
		v.centrerViewport(j1);
	}

	public void keyReleased(KeyEvent e) {

	}
}