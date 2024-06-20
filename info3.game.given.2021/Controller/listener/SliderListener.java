package listener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import draw.WindowInitGame;

import javax.swing.*;

//cette classe implemente les sliders pour leur donner leur role
//une classe suffit pour les 3 (ou 4) sliders

public class SliderListener implements ChangeListener {

	private JLabel texte ;
	private JSlider slider ;
	private int numero ;

	private WindowInitGame f ;
	
	public SliderListener(JLabel t, JSlider s, int numero, WindowInitGame f) {
		this.texte = t ;
		this.slider = s ;
		this.numero = numero ;
		this.f = f ;
	}
	
	public void stateChanged(ChangeEvent e) {
		switch (numero) {
		case 1 : //slider hauteur
			texte.setText("HAUTEUR du terrain : "+ slider.getValue());
			break;
		case 2 : //slider largeur
			texte.setText("LARGEUR du terrain : "+ slider.getValue());
			break;
		case 3 : //slider visibilité
			texte.setText("Visibilité autour du joueur : " + slider.getValue());
			break;
		/*case 4 :
			texte.setText("Nb de joueur : " + slider.getValue());
			break;*/
		default:
			break;
		}
	}
}

