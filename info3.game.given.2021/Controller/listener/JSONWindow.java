package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.*;
import java.util.Random;

// bibliothèque org.json pour lire et écrire les fichiers JSON
import org.json.*;

import draw.WindowInitGame;

/*
 * Classe activer lorsque le bouton Jouer en cliquer
 * elle contient les informations du fichier de configuration
 */
public class JSONWindow implements ActionListener {

	public static String name1, name2, jeu;
	public static String aut_j1, aut_j2, aut_apple, aut_arc, aut_bombe, aut_cassable, aut_epee, aut_fleche,
			aut_interrupteur, aut_invisible, aut_lave, aut_mine, aut_normal, aut_pioche, aut_porte, aut_potion,
			aut_sable, aut_selection1, aut_selection2, aut_squelette, aut_teleporteur, aut_void, aut_zombie;
	public static int time, pv, hauteur, largeur, visibility, densite, d_pickable, d_mine, d_pomme, d_potion, d_pioche,
			d_bombe, d_cassable, d_invisible, d_normal, nb_obstacles, nb_ennemis;
	public static Random seed;

	public static boolean ispick_interrupteur;

	public static String sprite_j1, sprite_j2, spirte_pomme, sprite_arc, sprite_bombe, sprite_cassable, sprite_epee,
			sprite_fleche, sprite_int_neutre, sprite_int_pop, sprite_int_wizz, sprite_invisible, sprite_lave, sprite_mine,
			sprite_normal, sprite_pioche, sprite_porte, sprite_potion, sprite_sable, sprite_selec1, sprite_selec2, 
			sprite_squelette, sprite_teleporteur, sprite_void, sprite_zombie;

	private WindowInitGame f;
	private String filePath;
	private int germe;
	private boolean use_seed;

	public JSONWindow(WindowInitGame f) {
		this.f = f;
	}

	public void actionPerformed(ActionEvent evenement) {
		try {
			if (f.getjeu() == "labyrinthe") {
				filePath = "resources/config/config_labyrinthe.json";
				recup_info(filePath);
				f.initGame();
				f.dispose();
			} else if (f.getjeu() == "arene") {
				filePath = "resources/config/config_arene.json";
				recup_info(filePath);
				f.initGame();
				f.dispose();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void recup_info(String filePath) {

		String contenu = "";
		try {
			contenu = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject param = new JSONObject(contenu);

		jeu = param.getString("jeu");
		name1 = f.getname(1);
		name2 = f.getname(2);
		time = param.getInt("duration");
		pv = param.getInt("pv");
		hauteur = param.getInt("hauteur");
		largeur = param.getInt("largeur");
		visibility = param.getInt("visibility");
		densite = param.getInt("%density");
		germe = param.getInt("seed");
		use_seed = param.getBoolean("use_seed");

		if (use_seed)
			seed = new Random(germe);
		else
			seed = new Random();

		nb_obstacles = param.getInt("nb_obstacles");
		nb_ennemis = param.getInt("nb_ennemis") / 2; // divisé par deux car nb ennemis par entité ennemis

		int d_cas = 0, d_inv = 0;
		int d_pom = 0, d_bom = 0, d_pio = 0, d_min = 0, d_pot = 0;
		// récupération des automates du fichier de config
		JSONArray entities = param.getJSONArray("entities");
		for (int i = 0; i < entities.length(); i++) {
			JSONObject entity = entities.getJSONObject(i);
			String temp = entity.getString("name");
			switch (temp) {
			case "Apple":
				aut_apple = entity.getString("automate");
				d_pom = entity.getInt("%densite");
				break;
			case "Arc":
				aut_arc = entity.getString("automate");
				break;
			case "Bombe":
				aut_bombe = entity.getString("automate");
				d_bom = entity.getInt("%densite");
				break;
			case "Cassable":
				aut_cassable = entity.getString("automate");
				d_cas = entity.getInt("%densite");
				break;
			case "Epee":
				aut_epee = entity.getString("automate");
				break;
			case "Fleche":
				aut_fleche = entity.getString("automate");
				break;
			case "Interrupteur":
				aut_interrupteur = entity.getString("automate");
				ispick_interrupteur = entity.getBoolean("pickable");
				break;
			case "Invisible":
				aut_invisible = entity.getString("automate");
				d_inv = entity.getInt("%densite");
				break;
			case "Joueur1":
				aut_j1 = entity.getString("automate");
				break;
			case "Joueur2":
				aut_j2 = entity.getString("automate");
				break;
			case "Lave":
				aut_lave = entity.getString("automate");
				break;
			case "Mine":
				aut_mine = entity.getString("automate");
				d_min = entity.getInt("%densite");
				break;
			case "Normal":
				aut_normal = entity.getString("automate");
				break;
			case "Pioche":
				aut_pioche = entity.getString("automate");
				d_pio = entity.getInt("%densite");
				break;
			case "Porte":
				aut_porte = entity.getString("automate");
				break;
			case "Potion":
				aut_potion = entity.getString("automate");
				d_pot = entity.getInt("%densite");
				break;
			case "Sable":
				aut_sable = entity.getString("automate");
				break;
			case "Selection1":
				aut_selection1 = entity.getString("automate");
				break;
			case "Selection2":
				aut_selection2 = entity.getString("automate");
				break;
			case "Squelette":
				aut_squelette = entity.getString("automate");
				break;
			case "Teleporteur":
				aut_teleporteur = entity.getString("automate");
				break;
			case "Void":
				aut_void = entity.getString("automate");
				break;
			case "Zombie":
				aut_zombie = entity.getString("automate");
				break;
			default:
				System.out.println("Pas de correspondance (recupinfo de JSONWindow)");
				break;
			}
		}

		verification_densite_mur(d_cas, d_inv);

		verification_densite_pickable(d_pom, d_bom, d_pio, d_min, d_pot);

	}

	public void verification_densite_mur(int d_cas, int d_inv) {
		int d_mur = d_inv + d_cas;
		if (d_mur <= 100) {
			d_cassable = d_cas;
			d_invisible = d_inv;
			d_normal = 100 - d_mur; // gestion du cas où d_mur < 100
		} else { // d_mur > 100
			d_cassable = d_cas * 100 / d_mur;
			d_invisible = d_inv * 100 / d_mur;
			d_normal = 100 - d_cassable - d_invisible;
		}

	}

	public void verification_densite_pickable(int d_pom, int d_bom, int d_pio, int d_min, int d_pot) {
		int d_pick = d_pom + d_bom + d_pio + d_min + d_pot;
		if (d_pick <= 60) {
			d_pickable = d_pick;
			d_pomme = d_pom;
			d_bombe = d_bom;
			d_pioche = d_pio;
			d_mine = d_min;
			d_potion = d_pot;
		} else { // d_pick > 25
			d_pomme = d_pom * 60 / d_pick;
			d_bombe = d_bom * 60 / d_pick;
			d_pioche = d_pio * 60 / d_pick;
			d_mine = d_min * 60 / d_pick;
			d_potion = d_pio * 60 / d_pick;
			d_pickable = 60 - d_pomme - d_bombe - d_pioche - d_mine - d_potion;
		}
	}
}