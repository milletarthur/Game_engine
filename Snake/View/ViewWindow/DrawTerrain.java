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
import Model_Snake.Queue;


/*
 * Classe DrawTerrain qui gère la génération du terrain
 */

public class DrawTerrain extends JPanel {

	private static final long serialVersionUID = 1L;
	int LARGEUR;
	int HAUTEUR;
	
	public static final int T_case = 20 ; //taille d'une case

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
	
	public void drawFunkySnake(int x, int y, Graphics g, String spec, Boolean head) {
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
		g.drawImage(im, x*T_case, y*T_case, T_case, T_case, null);
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
		
		x.setColor(OBSTACLE);
		x.fillRect(64,46,4,12);
		x.fillRect(72, 46, 4, 12);
		x.setColor(obstacle);
		x.fillRect(62,42,16,4);
		x.fillRect(62,50,16,2);*/

		//avec et [i,j] position dans la matrice
		
		for (int i = 0 ; i < LARGEUR ; i++) {
			for (int j = 0 ; j < HAUTEUR ; j++) {
				Entity elem = terrain.elementAt(i,j);
				if (elem instanceof Apple) {
				
					//dessiner pomme
					x.setColor(Pomme);
					x.fillOval(i*T_case, j*T_case, T_case, T_case);
					x.setColor(Color.GREEN);
					x.fillRect(i*T_case+5,j*T_case,10,5);
					
				}
				else if (elem instanceof Obstacle) {
					
					//dessiner obstacle
					x.setColor(OBSTACLE);
					x.fillRect(i*T_case+4,j*T_case+6,4,12);
					x.fillRect(i*T_case+12, j*T_case+6, 4, 12);
					x.setColor(obstacle);
					x.fillRect(i*T_case+2,j*T_case+2,16,4);
					x.fillRect(i*T_case+2,j*T_case+10,16,2);
			
				}
				else if (elem instanceof Snake) {
				
					//dessiner snake
//					x.setColor(Snake);
//					x.fillRect(i*T_case, j*T_case, T_case, T_case);
					drawFunkySnake(i, j, x, "Enderman", true);
				}
				else if (elem instanceof Queue) {
					drawFunkySnake(i, j, x, "Enderman", false);
					
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
