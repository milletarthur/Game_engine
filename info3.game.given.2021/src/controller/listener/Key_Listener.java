package controller.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Labyrinthe.Field;
import controller.KeyPressed;

public class Key_Listener implements KeyListener {
	KeyPressed k;
	Field f;

	public Key_Listener(Field f, KeyPressed k) {
		this.f = f;
		this.k = k;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int touche = e.getKeyCode();
		k.add(touche);
	}

	public void keyReleased(KeyEvent e) {
		int touche = e.getKeyCode();
		k.remove(touche);
	}
}