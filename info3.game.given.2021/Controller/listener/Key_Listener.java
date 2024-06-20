package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Labyrinthe.Field;
//import Labyrinthe.Joueur;
import controller.KeyPressed;
//import controller.Move;
//import controller.Turn;
//import draw.DrawTerrain;
//import draw.Viewport;
//import toolkit.Direction;

public class Key_Listener implements KeyListener {

//	Joueur j1;
//	Joueur j2;
//	DrawTerrain dt1;
//	DrawTerrain dt2;
//	Viewport v1;
//	Viewport v2;
	
	KeyPressed k;
	Field f;

	public Key_Listener(Field f, KeyPressed k) {
//		this.j1 = j1;
//		this.j2 = j2;
//		this.dt1 = dt1;
//		this.dt2 = dt2;
//		this.v1 = v1;
//		this.v2 = v2;
		this.f = f;
		this.k = k;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int touche = e.getKeyCode();
		k.add(touche);
//		Turn t = new Turn(f);
//		Move m = new Move(f);
//		switch (touche) {
//		case KeyEvent.VK_Z: // haut j1
////			j1.setOrientation(Direction.N);
////			j1.move();
//			t.setDirection(Direction.N);
//			t.exec(j1);
//			m.exec(j1);
//			v1.centrerViewport(j1);
//			dt2.repaint();
//			break;
//		case KeyEvent.VK_Q: // gauche j1
////			j1.setOrientation(Direction.W);
////			j1.move();
//			t.setDirection(Direction.W);
//			t.exec(j1);
//			m.exec(j1);
//			v1.centrerViewport(j1);
//			dt2.repaint();
//			break;
//		case KeyEvent.VK_D: // droite j1
////			j1.setOrientation(Direction.E);
////			j1.move();
//			t.setDirection(Direction.E);
//			t.exec(j1);
//			m.exec(j1);
//			v1.centrerViewport(j1);
//			dt2.repaint();
//			break;
//		case KeyEvent.VK_S: // bas j1
////			j1.setOrientation(Direction.S);
////			j1.move();
//			t.setDirection(Direction.S);
//			t.exec(j1);
//			m.exec(j1);
//			v1.centrerViewport(j1);
//			dt2.repaint();
//			break;
//		case KeyEvent.VK_UP: // haut j2
////			j2.setOrientation(Direction.N);
////			j2.move();
//			t.setDirection(Direction.N);
//			t.exec(j2);
//			m.exec(j2);
//			v2.centrerViewport(j2);
//			dt1.repaint();
//			break;
//		case KeyEvent.VK_DOWN: // bas j2
////			j2.setOrientation(Direction.S);
////			j2.move();
//			t.setDirection(Direction.S);
//			t.exec(j2);
//			m.exec(j2);
//			v2.centrerViewport(j2);
//			dt1.repaint();
//			break;
//		case KeyEvent.VK_LEFT: // gauche j2
////			j2.setOrientation(Direction.W);
////			j2.move();
//			t.setDirection(Direction.W);
//			t.exec(j2);
//			m.exec(j2);
//			v2.centrerViewport(j2);
//			dt1.repaint();
//			break;
//		case KeyEvent.VK_RIGHT: // droite j2
////			j2.setOrientation(Direction.E);
////			j2.move();
//			t.setDirection(Direction.E);
//			t.exec(j2);
//			m.exec(j2);
//			v2.centrerViewport(j2);
//			dt1.repaint();
//			break;
//		}

	}

	public void keyReleased(KeyEvent e) {
		int touche = e.getKeyCode();
		k.remove(touche);
	}
}