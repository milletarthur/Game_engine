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

import java.util.LinkedList;
import java.util.Random;

/*
 * Classe qui gère le dessin du terrain
 */

public class DrawTerrain extends JPanel {

	private static final long serialVersionUID = 1L;
	private int T_case;
	private Field terrain;

	private Sprite CHEMIN, PLAYER, OBJET, ITEM, DEPLAC, ZOMBIE, SQUELETTE;

	private BufferedImage[] chemin = new BufferedImage[6];
	private BufferedImage player1, player2, porte_fermee, porte_ouverte, teleporte, zombie, squelette;

	private Image lave, sand, mur, fragile, int_pop, int_wizz, int_neutre;
	
	// partager avec DrawInventaire
	public static BufferedImage pioche, pomme, arc, potion, epee, bombe;

	Random random;

	private int[][] rand_chemin, rand_mine_x, rand_mine_y;

	public DrawTerrain(int HAUTEUR, int LARGEUR, Field terrain, int T_case) throws IOException {
		this.terrain = terrain;
		this.T_case = T_case;
		this.chargement_Image();
		this.random = new Random(); // TODO - seed a ajouté
		this.rand_chemin = new int[HAUTEUR][LARGEUR];
		this.rand_mine_x = new int[HAUTEUR][LARGEUR];
		this.rand_mine_y = new int[HAUTEUR][LARGEUR];

		for (int i = 0; i < HAUTEUR; i++) {
			for (int j = 0; j < LARGEUR; j++) {
				rand_chemin[i][j] = -1;
				rand_mine_x[i][j] = -1;
				rand_mine_y[i][j] = -1;
			}
		}

		// impose la taille de la fenêtre avec celui du JPanel
		this.setPreferredSize(new Dimension(LARGEUR * T_case, HAUTEUR * T_case));
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

	public Image drawEntity(String filepath) {
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
					} else if (e instanceof Interrupteur) {
						// TODO - savoir et rajouter la position de l'interrupteur
						g.drawImage(int_pop, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Invisible) { // mur magique
						// TODO - rendre opaque si joueur dessus
						g.drawImage(mur, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Joueur) {
						// TODO - gérer les cas ou le joueur a une arme et quel joueur c'est (avec les teams / positions)
						g.drawImage(player1, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Lave) {
						g.drawImage(lave, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Mine) {
						g.setColor(new Color(90, 90, 90));
						g.fillRect(j * T_case + x_mine(i, j), i * T_case + y_mine(i, j), 2, 2);
					} else if (e instanceof Normal) { // mur normal
						g.drawImage(mur, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Pioche) {
						g.drawImage(pioche, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Porte) {
						// TODO - savoir et rajouter quand la porte est ouverte ou fermée
						g.drawImage(porte_ouverte, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Potion) {
						g.drawImage(potion, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Sable) {
						g.drawImage(sand, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Squelette) {
						// TODO - gérer quand le squelette attaque / meurt / fixe
						g.drawImage(squelette, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Teleporteur) {
						g.drawImage(teleporte, j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Void) { // chemin
						g.drawImage(chemin[this.donnees_chemin(i, j)], j * T_case, i * T_case, T_case, T_case, null);
					} else if (e instanceof Zombie) {
						// TODO - gérer quand le zombie attaque / meurt / fixe
						g.drawImage(zombie, j * T_case, i * T_case, T_case, T_case, null);
					}
					// TODO - rajouter la sélection
				}
			}
		}
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
		this.mur = drawEntity("resources/graphisme/mur.png");
		this.fragile = drawEntity("resources/graphisme/fragile.png");

		// joueurs
		this.PLAYER = new Sprite("resources/graphisme/Personnages/sprites_weaponless.png", 26, 26);
		this.player1 = PLAYER.getSprite(0, 20);
		this.player2 = PLAYER.getSprite(0, 0);

		// sol
		this.lave = drawEntity("resources/graphisme/lave.png");
		this.sand = drawEntity("resources/graphisme/sand.png");

		// objets
		this.OBJET = new Sprite("resources/graphisme/minecraft.png", 128, 128);
		this.pioche = OBJET.getSprite(6, 1);
		this.pomme = OBJET.getSprite(0, 10);

		this.bombe = (BufferedImage) drawEntity("resources/graphisme/bombe.png");

		this.ITEM = new Sprite("resources/graphisme/items.png", 16, 16);
		this.arc = ITEM.getSprite(8, 6);
		this.potion = ITEM.getSprite(0, 6);
		this.epee = ITEM.getSprite(9, 11);

		// déplacements
		this.DEPLAC = new Sprite("resources/graphisme/actives.png", 26, 26);
		this.porte_fermee = DEPLAC.getSprite(0, 9);
		this.porte_ouverte = DEPLAC.getSprite(0, 10);
		this.teleporte = DEPLAC.getSprite(0, 13);

		// interrupteurs
		this.int_pop = drawEntity("resources/graphisme/levier1.png");
		this.int_neutre = drawEntity("resources/graphisme/levier2.png");
		this.int_wizz = drawEntity("resources/graphisme/levier3.png");

		// monstres
		this.ZOMBIE = new Sprite("resources/graphisme/Personnages/Zombie_idle.png", 32, 32);
		this.zombie = ZOMBIE.getSprite(0, 0);
		this.SQUELETTE = new Sprite("resources/graphisme/Personnages/Skeleton_enemy.png", 64, 64);
		this.squelette = SQUELETTE.getSprite(0, 0);

	}

	private int donnees_chemin(int x, int y) {
		if (rand_chemin[x][y] == -1) {
			rand_chemin[x][y] = random.nextInt(6);
		}
		return rand_chemin[x][y];
	}

	private int x_mine(int x, int y) {
		if (rand_mine_x[x][y] == -1) {
			rand_mine_x[x][y] = random.nextInt((17 - 3) + 1) + 3;
		}
		return rand_mine_x[x][y];
	}

	private int y_mine(int x, int y) {
		if (rand_mine_y[x][y] == -1) {
			rand_mine_y[x][y] = random.nextInt((17 - 3) + 1) + 3;
		}
		return rand_mine_y[x][y];
	}

}
