package ViewWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model_Snake.Apple;
import Model_Snake.Entity;
import Model_Snake.Field;
import Model_Snake.Obstacle;
import Model_Snake.Snake;
import toolkit.Direction;
import Model_Snake.Queue;


/*
 * Classe DrawTerrain qui gère la génération du terrain
 */

public class DrawTerrain extends JPanel {

	private static final long serialVersionUID = 1L;
	int LARGEUR;
	int HAUTEUR;
	
	public static final int T_case = 50 ; //taille d'une case

	// Couleurs
	Color VERT = new Color(0, 100, 0);
	Color vert = new Color(50, 205, 50);
	Color Snake = Color.BLUE;
	Color Eyes = Color.BLACK;
	Color Pomme = new Color(240,42,42);
	Color green = new Color(87,185,23);
	Color OBSTACLE = new Color(113,32,14);
	Color obstacle = new Color(203,89,39);
	Field terrain;

	public DrawTerrain(int LARGEUR, int HAUTEUR, Field terrain) {
		this.LARGEUR = LARGEUR;
		this.HAUTEUR = HAUTEUR;
		this.setBackground(VERT);
		this.terrain = terrain;
		
	}

	public void paintComponent(Graphics x) {
		super.paintComponent(x);
		this.damier(x);
		// TODO - fournir la matrice
		this.drawElements(x); 
	}

	// TODO - gestion du damier à corriger avec la taille de la matrice
	public void damier(Graphics x) {
		x.setColor(vert);
		for (int i = 0; i < LARGEUR*T_case ; i += T_case*2) {
			for (int j = 0; j < HAUTEUR*T_case; j += T_case) {
				if ((j / T_case) % 2 == 0) {
					x.fillRect(i, j, T_case, T_case);
				} else {
					x.fillRect(i + T_case, j, T_case, T_case);
				}
			}
		}
	}
	
	public Image loadImage(InputStream is) {
	    // File imageFile = new File("game.sample/sprites/winchester.png");
	    BufferedImage img = null;
	    try {
	      img = ImageIO.read(is);
	      return img;
	    } catch (IOException ex) {
	      ex.printStackTrace();
	    }
	    return null;
	  }
	
	public static Image rotate(Image image, int rotation){
        // Getting Dimensions of image
		BufferedImage img = (BufferedImage) image;
        int width = img.getWidth();
        int height = img.getHeight();
 
        // Creating a new buffered image
        BufferedImage newImage = new BufferedImage(
            img.getWidth(), img.getHeight(), img.getType());
 
        // creating Graphics in buffered image
        Graphics2D g2 = newImage.createGraphics();
 
        // Rotating image by degrees using toradians()
        // method
        // and setting new dimension t it
        g2.rotate(Math.toRadians(rotation), width / 2, height / 2);
        g2.drawImage(img, null, 0, 0);
 
        // Return rotated buffer image
        return newImage;
    }
	
	
	public void drawFunkySnake(int x, int y, Graphics g, String spec, Boolean head, int rotation) {
		File f = null;
		if (head)
			f = new File("resources/SnakesSkin/" + spec + "Head.png");
		else
			f = new File("resources/SnakesSkin/" + spec + "Body.png");
		FileInputStream is = null;
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			return;
		}
		Image im = loadImage(is);
		g.drawImage(rotate(im,rotation), x*T_case, y*T_case, T_case, T_case, null);
	}
	
	// TODO - lui donner la matrice
	public void drawElements (Graphics x) {
		/*x.setColor(Snake);
		x.fillRect(180, 120, 20, 20);
		x.fillRect(180, 140, 20, 20);
		x.fillRect(180, 160, 20, 20);
		x.fillRect(160, 160, 20, 20);
		x.fillRect(160, 170, 20, 20);
		x.fillRect(160, 180, 20, 20);
		x.setColor(Pomme);
		x.fillOval(40, 20, 20, 20);
		x.setColor(green);
		x.fillRect(45,20,10,5);
		*/
		

		//avec et [i,j] position dans la matrice
		
		for (int i = 0 ; i < HAUTEUR ; i++) {
			for (int j = 0 ; j < LARGEUR ; j++) {
				Entity elem = terrain.elementAt(i,j);
				if (elem instanceof Apple) {
				
					//dessiner pomme
					x.setColor(Pomme);
			        x.fillOval(i*T_case, j*T_case, T_case, T_case);
			        x.setColor(Color.GREEN);
			        x.fillRect(i*T_case+((T_case*5)/20),j*T_case,T_case/2,T_case/4);
					
				}
				else if (elem instanceof Obstacle) {
					
				      // dessiner obstacle
			        x.setColor(OBSTACLE);
			        x.fillRect(i*T_case+((T_case*4)/20),j*T_case+((T_case*6)/20),T_case/5,(T_case/5)*3);
			        x.fillRect(i*T_case+((T_case*12)/20), j*T_case+((T_case*6)/20), T_case/5, (T_case/5)*3);
			        x.setColor(obstacle);
			        x.fillRect(i*T_case+((T_case*2)/20),j*T_case+((T_case*2)/20),(T_case/5)*4,T_case/5);
			        x.fillRect(i*T_case+((T_case*2)/20),j*T_case+((T_case*10)/20),(T_case/5)*4,T_case/10);
				}
				else if (elem instanceof Snake) {
				
					int rotation = 0;
					switch(elem.direction()){
						case Direction.N:
							break;
						case Direction.E:
							rotation = 90;
							break;
						case Direction.S:
							rotation = 180;
							break;
						case Direction.W:
							rotation = 270;
							break;
						default :
							rotation = 45;
							break;
					}
					//dessiner snake
//					x.setColor(Snake);
//					x.fillRect(i*T_case, j*T_case, T_case, T_case);
					drawFunkySnake(i, j, x, "Enderman", true, rotation);
				}
				else if (elem instanceof Queue) {
					int rotation = 0;
					switch(elem.direction()){
						case Direction.N:
							break;
						case Direction.E:
							rotation = 90;
							break;
						case Direction.S:
							rotation = 180;
							break;
						case Direction.W:
							rotation = 270;
							break;
						default :
							rotation = 45;
							break;
					}
					drawFunkySnake(i, j, x, "Enderman", false, rotation);
					
				}
			}
		}
	
		 /*pb si plusieurs serpents
		//dessiner les yeux
		x.setColor(Eyes);
		//obtenir la direction du snake
		direction = ...
		switch (direction) {
			case (W) :
				x.fillRect(i*20, j*20, 5, 5);
				x.fillRect(i*20, j*20+15, 5, 5);
			case (N) :
				x.fillRect(i*20, j*20, 5, 5);
				x.fillRect(i*20+15, j*20, 5, 5);
			case (E) :
				x.fillRect(i*20+15, j*20, 5, 5);
				x.fillRect(i*20+15, j*20+15, 5, 5);
			case (S) :
				x.fillRect(i*20, j*20+15, 5, 5);
				x.fillRect(i*20+15, j*20+15, 5, 5);
		}
		 */
	}

}
