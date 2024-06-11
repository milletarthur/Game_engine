import java.awt.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import javax.imageio.ImageIO;

/*
 * Classe qui gère le dessin du terrain
 */

public class DrawTerrain extends JPanel {
	
	private static final long serialVersionUID = 1L;
	int LARGEUR;
	int HAUTEUR;
	Sprite spriteGrid ;
	
	private static final int T_case = 50 ;
	
	// temp
	Color black = Color.black ;
	Color blue = Color.blue ;
	
	public DrawTerrain (int LARGEUR, int HAUTEUR) throws IOException {
		this.HAUTEUR = HAUTEUR ;
		this.LARGEUR = LARGEUR ;
		this.setBackground(blue);
		this.spriteGrid = new Sprite("resources/graphisme/sprites_weaponless.png", 26, 26); 
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.grille(g); // ou this.drawElements(g);
	}
	
	public void grille(Graphics g) {
		g.setColor(black);
		for (int i = 0; i < LARGEUR ; i += T_case*2) {
			for (int j = 0; j < HAUTEUR; j += T_case) {
				if ((j / T_case) % 2 == 0) {
					g.drawImage(drawEntity(i,j,g,"mur"),i,j,T_case,T_case,null);
				} else {
					//g.fillRect(i + T_case, j, T_case, T_case);
					BufferedImage sprite = spriteGrid.getSprite(10, 6);
					g.drawImage(sprite, i+T_case, j ,T_case,T_case, null);
				}
			}
		}
	}
	
	public Image loadImage(InputStream is) {
	    BufferedImage img = null;
	    try {
	      img = ImageIO.read(is);
	      return img;
	    } catch (IOException ex) {
	      ex.printStackTrace();
	    }
	    return null;
	  }
	
	public Image drawEntity(int x, int y, Graphics g, String entity) {
		File f = new File("resources/graphisme/" + entity + ".png");
		FileInputStream is = null;
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			return null;
		}
		Image im = loadImage(is);
		return im ;
	}
	
	/*public void drawElements(Graphics g) {
		
		// TODO - fonction possible avec LinkedList<Entity>[][] (= new LinkedList<Entity>(HAUTEUR,LARGEUR);)
		
		//avec [i,j] position dans la matrice
		for (int i = 0 ; i < HAUTEUR ; i++) {
			for (int j = 0 ; j < LARGEUR ; j++) {
				LinkedList liste = terrain.elementAt(i,j); // terrain nom de la grille
				for (int k = 0 ; k < liste.size ; k++) {
					Entity elem = liste. //utilisation de l'itérateur ou du visiteur
					if (elem instanceof Apple) {
					
					}
					if (elem instanceof )
					
				}
			}
			
		}
		
	}*/



}
