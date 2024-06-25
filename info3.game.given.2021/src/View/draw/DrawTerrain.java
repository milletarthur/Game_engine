package View.draw;

import java.awt.*;
import java.awt.geom.AffineTransform;

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
import controller.listener.JSONWindow;

import java.util.LinkedList;
import java.util.Random;

/*
 * Classe qui gère le dessin du terrain
 */
public class DrawTerrain extends JPanel {

	private static final long serialVersionUID = 1L;
	private int T_case, int_team, l;
	private Field terrain;

	private Sprite CHEMIN;

	private BufferedImage[] chemin = new BufferedImage[6];
	private BufferedImage player1, player1_flip, player2, player2_flip, zombie, zombie_flip, squelette, squelette_flip,
			invisible, fleche;

	private Image lave, sand, mur, fragile, selection1, selection2, porte_fermee, porte_ouverte, teleporte,
			teleporte_desac;

	// partager avec DrawInventaire
	public static Image pomme, pioche, bombe, int_pop, int_wizz, int_neutre, arc, potion, epee, pop, wizz;

	Random random;

	private int[][] rand_chemin, rand_mine_x, rand_mine_y;

	public DrawTerrain(int HAUTEUR, int LARGEUR, Field terrain, int T_case, int int_team) throws IOException {
		this.terrain = terrain;
		this.T_case = T_case;
		this.int_team = int_team; // team du joueur
		this.chargement_Image();
		this.random = new Random(0);
		this.rand_chemin = new int[HAUTEUR][LARGEUR];
		this.rand_mine_x = new int[HAUTEUR][LARGEUR];
		this.rand_mine_y = new int[HAUTEUR][LARGEUR];

		this.l = T_case / 4;
		for (int i = 0; i < HAUTEUR; i++) {
			for (int j = 0; j < LARGEUR; j++) {
				rand_chemin[i][j] = random.nextInt(6);
				rand_mine_x[i][j] = random.nextInt(T_case - l * 2) + 5;
				rand_mine_y[i][j] = random.nextInt(T_case - l * 2) + 5;
			}
		}

		// impose la taille de la fenêtre avec celui du JPanel
		this.setPreferredSize(new Dimension(LARGEUR * T_case, HAUTEUR * T_case));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawElements(g);
	}

	public static Image loadImage(InputStream is) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(is);
			return img;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Image drawEntity(String filepath) {
		File f = new File(filepath);
		FileInputStream is = null;
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			return null;
		}
		Image im = loadImage(is);
		return im;
	}

	public void drawElements(Graphics g) {
		for (int i = 0; i < terrain.get_ligne(); i++) {
			for (int j = 0; j < terrain.get_colonne(); j++) {
				LinkedList<Entity> temp = terrain.getElement(i, j);
				for (int k = 0; k < temp.size(); k++) {
					Entity e = temp.get(k);
					if (e instanceof Apple) {
						g.drawImage(pomme, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Arc) {
						g.drawImage(arc, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Bombe) {
						g.drawImage(bombe, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Cassable) { // mur fragile
						g.drawImage(fragile, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Epee) {
						g.drawImage(epee, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Fleche) {
						g.drawImage(rotate(fleche, e.direction()), j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Interrupteur) {
						Interrupteur inter = (Interrupteur) e;
						if (inter.State() == -1) {
							g.drawImage(int_pop, j * T_case, i * T_case, T_case, T_case, null);
						} else if (inter.State() == 1) {
							g.drawImage(int_wizz, j * T_case, i * T_case, T_case, T_case, null);
						} else {
							g.drawImage(int_neutre, j * T_case, i * T_case, T_case, T_case, null);
						}
					} else if (e instanceof Invisible) { // mur magique
						int h = 0;
						boolean inv = false;
						while (h < temp.size()) {
							if (temp.get(h).team() == this.int_team) {
								g.drawImage(invisible, j * T_case, i * T_case, T_case, T_case, null);
								inv = true;
								break;
							}
							h++;
						}
						if (!inv) {
							g.drawImage(mur, j * T_case, i * T_case, T_case, T_case, null);
						}
					} else if (e instanceof Joueur) {
						// TODO - gérer les cas ou le joueur a une arme
						if (e.team() == 1) {
							if (e.direction() == 1 || e.direction() == 3)
								g.drawImage(player1, j * T_case, i * T_case, T_case, T_case, null);
							else
								g.drawImage(player1_flip, j * T_case, i * T_case, T_case, T_case, null);
						} else if (e.team() == 2) {
							if (e.direction() == 1 || e.direction() == 3)
								g.drawImage(player2, j * T_case, i * T_case, T_case, T_case, null);
							else
								g.drawImage(player2_flip, j * T_case, i * T_case, T_case, T_case, null);
						}
					} else if (e instanceof Lave) {
						g.drawImage(lave, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Mine) {
						Graphics2D g2d = (Graphics2D) g;
						int strokeWidth = 2;
						g2d.setStroke(new BasicStroke(strokeWidth));
						g2d.setColor(new Color(51, 48, 46));
						int x = j * T_case + rand_mine_x[i][j];
						int y = i * T_case + rand_mine_y[i][j];
						g.drawLine(x, y, x + l, y + l);
						g.drawLine(x + l, y, x, y + l);
					} else if (e instanceof Normal) { // mur normal
						g.drawImage(mur, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Pioche) {
						g.drawImage(pioche, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Porte) {
						Porte p = (Porte) e;
						if (p.isOpen()) {
							g.drawImage(porte_ouverte, j * T_case, i * T_case, T_case, T_case, null);
						} else {
							g.drawImage(porte_fermee, j * T_case, i * T_case, T_case, T_case, null);
						}
					} else if (e instanceof Potion) {
						g.drawImage(potion, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Sable) {
						Sable s = (Sable) e;
						if (s.IsActivate())
							g.drawImage(sand, j * T_case, i * T_case, T_case, T_case, null);
						else
							g.drawImage(chemin[rand_chemin[i][j]], j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Selection) {
						if (e.team() == 1)
							g.drawImage(selection1, j * T_case, i * T_case, T_case, T_case, null);
						else if (e.team() == 2)
							g.drawImage(selection2, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Squelette) {
						if (e.direction() == 1 || e.direction() == 3)
							g.drawImage(squelette, j * T_case, i * T_case, T_case, T_case, null);
						else
							g.drawImage(squelette_flip, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Teleporteur) {
						Teleporteur t = (Teleporteur) e;
						if (t.IsActivate())
							g.drawImage(teleporte, j * T_case, i * T_case, T_case, T_case, null);
						else
							g.drawImage(teleporte_desac, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Void) { // chemin
						g.drawImage(chemin[rand_chemin[i][j]], j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Zombie) {
						if (e.direction() == 1 || e.direction() == 3)
							g.drawImage(zombie, j * T_case, i * T_case, T_case, T_case, null);
						else
							g.drawImage(zombie_flip, j * T_case, i * T_case, T_case, T_case, null);
					}
				}
			}
		}
		// affichage de pop et wizz
		for (int i = 0; i < terrain.get_ligne(); i++) {
			for (int j = 0; j < terrain.get_colonne(); j++) {
				LinkedList<Entity> temp = terrain.getElement(i, j);
					Entity e = temp.get(0);
					if (e instanceof PopEntity)
						g.drawImage(selection1, j * T_case, i * T_case, T_case, T_case, null);
					else if (e instanceof WizzEntity)
						g.drawImage(selection1, j * T_case, i * T_case, T_case, T_case, null);
				}
			}
		}
	

	public void chargement_Image() throws IOException {

		this.CHEMIN = new Sprite(JSONWindow.sprite_void, 24, 24); // obtenir le sprite
		chemin[0] = CHEMIN.getSprite(0, 0); // obtenir l'image du chemin
		chemin[1] = CHEMIN.getSprite(0, 1);
		chemin[2] = CHEMIN.getSprite(0, 2);
		chemin[3] = CHEMIN.getSprite(1, 0);
		chemin[4] = CHEMIN.getSprite(1, 1);
		chemin[5] = CHEMIN.getSprite(1, 2);

		this.mur = drawEntity(JSONWindow.sprite_normal);
		this.fragile = drawEntity(JSONWindow.sprite_cassable);

		this.player1 = drawEntityB(JSONWindow.sprite_j1);
		this.player2 = drawEntityB(JSONWindow.sprite_j2);

		this.lave = drawEntity(JSONWindow.sprite_lave);
		this.sand = drawEntity(JSONWindow.sprite_sable);

		this.pioche = drawEntity(JSONWindow.sprite_pioche);
		this.pomme = drawEntity(JSONWindow.sprite_pomme);

		this.bombe = drawEntity(JSONWindow.sprite_bombe);

		this.arc = drawEntity(JSONWindow.sprite_arc);
		this.potion = drawEntity(JSONWindow.sprite_potion);
		this.epee = drawEntity(JSONWindow.sprite_epee);

		this.porte_fermee = drawEntity(JSONWindow.sprite_porte_fer);
		this.porte_ouverte = drawEntity(JSONWindow.sprite_porte_ouv);
		this.teleporte = drawEntity(JSONWindow.sprite_tel_act);
		this.teleporte_desac = drawEntity(JSONWindow.sprite_tel_desac);

		this.int_pop = drawEntity(JSONWindow.sprite_int_pop);
		this.int_neutre = drawEntity(JSONWindow.sprite_int_neutre);
		this.int_wizz = drawEntity(JSONWindow.sprite_int_wizz);

		this.zombie = drawEntityB(JSONWindow.sprite_zombie);
		this.squelette = drawEntityB(JSONWindow.sprite_squelette);

		this.selection1 = drawEntity(JSONWindow.sprite_selec1);
		this.selection2 = drawEntity(JSONWindow.sprite_selec2);

		this.player1_flip = flip(player1);
		this.player2_flip = flip(player2);
		this.squelette_flip = flip(squelette);
		this.zombie_flip = flip(zombie);
		this.invisible = transparent(mur);

		this.fleche = drawEntityB(JSONWindow.sprite_fleche);
		
	}

	public static Image drawPickable(Entity e) {
		if (e instanceof Apple) {
			return pomme;
		} else if (e instanceof Arc) {
			return arc;
		} else if (e instanceof Bombe) {
			return bombe;
		} else if (e instanceof Epee) {
			return epee;
		} else if (e instanceof Pioche) {
			return pioche;
		} else if (e instanceof Potion) {
			return potion;
		} else if (e instanceof Interrupteur) {
			return int_neutre;
		}
		return null;
	}

	// fonction qui sert à retourner une image par rapport à un axe vertical
	private BufferedImage flip(BufferedImage image) {
		BufferedImage flipped = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = flipped.createGraphics();
		g.setComposite(AlphaComposite.Src);
		AffineTransform transform = AffineTransform.getScaleInstance(-1, 1);
		transform.translate(-image.getWidth(), 0);
		g.drawImage(image, transform, null);
		g.dispose();
		return flipped;
	}

	// fonction qui diminue l'opacité d'une image
	public static BufferedImage transparent(Image img) {

		// créer une BufferedImage avec une transparence supportée
		BufferedImage bf = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D tr = bf.createGraphics();
		AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
		tr.setComposite(alphaComposite);
		tr.drawImage(img, 0, 0, null);
		tr.dispose();

		return bf;
	}

	public static BufferedImage rotate(BufferedImage image, int direction) { // par défaut, l'image est à l'EST
		int rotation = 0;
		switch (direction) {
		case 1:
			rotation = 270;
			break;
		case 2:
			rotation = 90;
			break;
		case 3:
			break;
		case 4:
			rotation = 180;
		}

		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		Graphics2D g2 = newImage.createGraphics();
		g2.rotate(Math.toRadians(rotation), image.getWidth() / 2, image.getHeight() / 2);
		g2.drawImage(image, null, 0, 0);

		return newImage;
	}

	public static BufferedImage drawEntityB(String filepath) {
		File f = new File(filepath);
		FileInputStream is = null;
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			return null;
		}
		BufferedImage img = null;
		try {
			img = ImageIO.read(is);
			return img;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return img;
	}

}
