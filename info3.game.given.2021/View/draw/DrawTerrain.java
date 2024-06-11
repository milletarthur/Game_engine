package draw;
import java.awt.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import Labyrinthe.*;
import Labyrinthe.Void;


/*
 * Classe qui gère le dessin du terrain
 */

public class DrawTerrain extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int T_case ;
	private Field terrain ;
	
	private Sprite CHEMIN ;
	private Sprite MUR ;
	private Sprite MINE ;
	private Sprite PLAYER ;
	
	private BufferedImage chemin ;
	private BufferedImage mur ;
	private BufferedImage mine ;
	private BufferedImage player1 ;
	private BufferedImage player2 ;
	
	// temp
	Color black = Color.black ;
	Color blue = Color.blue ;
	
	public DrawTerrain (Field terrain, int T_case) throws IOException {
		this.terrain = terrain ;
		this.T_case = T_case ;
		this.setBackground(blue);
		this.chargement_Image();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawElements(g);
		System.out.println("Finish paint");
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
	
	public Image drawEntity(String entity) {
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
	
	public void drawElements(Graphics g) {
		for (int i = 0 ; i < terrain.get_colonne() ; i++) {
			for (int j = 0 ; j < terrain.get_ligne() ; j++) {
				Entity[] temp = terrain.getElement(j,i); // TODO - à convertir en Entity
				//iterateur
				//for (int k = 0 ; k < 6 ; k++) {
				if (temp[0] instanceof Void) { //chemin
					g.drawImage(chemin,i*T_case,j*T_case,T_case,T_case,null);
				} else if (temp[0] instanceof Normal) { //mur normal
					g.drawImage(mur,i*T_case,j*T_case,T_case,T_case,null);
				} else if (temp[0] instanceof Mine) { //mine
					// TODO - refaire propre avec le tableau complet
					g.drawImage(chemin,i*T_case,j*T_case,T_case,T_case,null);
					g.drawImage(mine,i*T_case,j*T_case,T_case,T_case,null);
				}
			}
		}
		
	}
	
	public void chargement_Image() throws IOException {
		
		//chemins
		this.CHEMIN = new Sprite("resources/graphisme/Structures/stone_room.png", 24, 24); //obtenir le sprite
		chemin = CHEMIN.getSprite(0, 0); //obtenir l'image du chemin
		
		//murs
		this.MUR = new Sprite("resources/graphisme/Structures/stone_wall.png", 24, 24);
		this.mur = MUR.getSprite(0,0);
		
		this.MINE = new Sprite("resources/graphisme/items.png",16,16);
		this.mine = MINE.getSprite(6,21);
		
		this.PLAYER = new Sprite("resources/graphisme/Personnages/sprites_weaponless.png",26,26);
		this.player1 = PLAYER.getSprite(0,20);
		this.player2 = PLAYER.getSprite(0,0);
		
	}


}
