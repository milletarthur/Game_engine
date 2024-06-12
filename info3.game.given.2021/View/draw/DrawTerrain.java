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

import java.util.Random;

/*
 * Classe qui gère le dessin du terrain
 */

public class DrawTerrain extends JPanel {

	private static final long serialVersionUID = 1L;
	private int T_case;
	private Field terrain;

	private Sprite CHEMIN, MUR, PLAYER, OBJET;

	private BufferedImage[] chemin = new BufferedImage[6];
	private BufferedImage mur, player1, player2, pioche, pomme;

	private Image lave, sand, bombe;

	public DrawTerrain(Field terrain, int T_case) throws IOException {
		this.terrain = terrain;
		this.T_case = T_case;
		this.chargement_Image();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawElements(g);
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
		return im;
	}

	public void drawElements(Graphics g) {
		Random random = new Random();
		for (int i = 0; i < terrain.get_colonne(); i++) {
			for (int j = 0; j < terrain.get_ligne(); j++) {
				Entity[] temp = terrain.getElement(j, i); // TODO - à convertir en Entity
				// iterateur
				// for (int k = 0 ; k < 7 ; k++) {
				if (temp[0] instanceof Void) { // chemin
					int x = random.nextInt(6);
					g.drawImage(chemin[x], i * T_case, j * T_case, T_case, T_case, null);
				} else if (temp[0] instanceof Normal) { // mur normal
					g.drawImage(mur, i * T_case, j * T_case, T_case, T_case, null);
				} else if (temp[0] instanceof Mine) { // mine
					// TODO - refaire propre avec le tableau complet
					int x = random.nextInt(6);
					int y = random.nextInt((17 - 3) + 1) + 3;
					int z = random.nextInt((17 - 3) + 1) + 3;
					g.drawImage(chemin[x], i * T_case, j * T_case, T_case, T_case, null);
					g.setColor(new Color(90, 90, 90));
					g.fillRect(i * T_case + y, j * T_case + z, 2, 2);
				}
			}
		}
		g.drawImage(player1, 1*T_case, 2*T_case, T_case, T_case, null);

	}

	public void chargement_Image() throws IOException {

		// chemins
		this.CHEMIN = new Sprite("resources/graphisme/Structures/temple_room.png", 24, 24); // obtenir le sprite
		chemin[0] = CHEMIN.getSprite(0, 0); // obtenir l'image du chemin
		chemin[1] = CHEMIN.getSprite(0, 1);
		chemin[2] = CHEMIN.getSprite(0, 2);
		chemin[3] = CHEMIN.getSprite(1, 0);
		chemin[4] = CHEMIN.getSprite(1, 1);
		chemin[5] = CHEMIN.getSprite(1, 2);

		// murs
		this.MUR = new Sprite("resources/graphisme/Structures/stone_wall.png", 24, 24);
		this.mur = MUR.getSprite(0, 0);

		this.PLAYER = new Sprite("resources/graphisme/Personnages/sprites_weaponless.png", 26, 26);
		this.player1 = PLAYER.getSprite(0, 20);
		this.player2 = PLAYER.getSprite(0, 0);

		this.lave = drawEntity("lave");
		this.sand = drawEntity("sand");

		this.OBJET = new Sprite("resources/graphisme/minecraft.png", 128, 128);
		this.pioche = OBJET.getSprite(6, 1);
		this.pomme = OBJET.getSprite(0, 10);

		this.bombe = drawEntity("bombe");
	}

}
