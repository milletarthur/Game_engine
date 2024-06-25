package controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.draw.*;

public class EndGameButtonListener implements ActionListener {
	
	boolean decision; // true = rejouer, false = quitter
	DrawEndGame f;
	
	public EndGameButtonListener(boolean decision, DrawEndGame f) {
		this.decision = decision;
		this.f = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (decision) {
			WindowInitGame game = new WindowInitGame();
			f.dispose();
		} else {
			f.dispose();
		}
	}

}
