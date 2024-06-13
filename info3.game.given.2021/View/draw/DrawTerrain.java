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
	private BufferedImage player1, player2, pioche, pomme, arc, potion, porte_fermee, porte_ouverte, teleporte, epee,
			zombie, squelette;

	private Image lave, sand, bombe, mur, fragile, int_pop, int_wizz, int_neutre;

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
		Random random = new Random();
		for (int i = 0; i < terrain.get_colonne(); i++) {
			for (int j = 0; j < terrain.get_ligne(); j++) {
				LinkedList<Entity> temp = terrain.getElement(j, i);
				for (int k = 0; k < temp.size(); k++) {
					Entity e = temp.get(k);
					if (e instanceof Apple) {
						g.drawImage(pomme, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Arc) {
						g.drawImage(arc, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Bombe) {
						g.drawImage(bombe, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Cassable) { // mur fragile
						g.drawImage(fragile, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Epee) {
						g.drawImage(epee, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Interrupteur) {
						// TODO - savoir et rajouter la position de l'interrupteur
						g.drawImage(int_pop, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Invisible) { // mur magique
						// TODO - rendre opaque si joueur dessus
						g.drawImage(mur, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Joueur) {
						// TODO - gérer les cas ou le joueur a une arme et quel joueur c'est
						g.drawImage(player1, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Lave) {
						g.drawImage(lave, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Mine) {
						int y = random.nextInt((17 - 3) + 1) + 3;
						int z = random.nextInt((17 - 3) + 1) + 3;
						g.setColor(new Color(90, 90, 90));
						g.fillRect(i * T_case + y, j * T_case + z, 2, 2);
					} else if (e instanceof Normal) { // mur normal
						g.drawImage(mur, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Pioche) {
						g.drawImage(pioche, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Porte) {
						// TODO - savoir et rajouter quand la porte est ouverte ou fermée
						g.drawImage(porte_fermee, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Potion) {
						g.drawImage(potion, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Sable) {
						g.drawImage(sand, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Squelette) {
						// TODO - gérer quand le squelette attaque / meurt / fixe
						g.drawImage(squelette, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Teleporteur) {
						g.drawImage(teleporte, i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Void) { // chemin
						int x = random.nextInt(6);
						g.drawImage(chemin[x], i * T_case, j * T_case, T_case, T_case, null);
					} else if (e instanceof Zombie) {
						// TODO - gérer quand le zombie attaque / meurt / fixe
						g.drawImage(zombie, i * T_case, j * T_case, T_case, T_case, null);
					}
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

		this.bombe = drawEntity("bombe");

		this.ITEM = new Sprite("resources/graphisme/items.png", 16, 16);
		this.arc = ITEM.getSprite(6, 8);
		this.potion = ITEM.getSprite(0, 6);
		this.epee = ITEM.getSprite(9, 11);

		// déplacements
		this.DEPLAC = new Sprite("resources/graphisme/actives.png", 14, 14);
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

}
