package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key_Listener implements KeyListener {

	public Key_Listener () {
		
	}
		
	public void keyTyped(KeyEvent e) {
		
	}
		
	public void keyPressed(KeyEvent e) {
			int touche = e.getKeyCode();
			switch(touche) {
			case KeyEvent.VK_UP : //haut
				break;
			case KeyEvent.VK_LEFT : //droite
				break;
			case KeyEvent.VK_RIGHT : //gauche
				break;
			case KeyEvent.VK_DOWN : //bas
				break;
			}
	}
		
	public void keyReleased(KeyEvent e) {
	
	}
}