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
			aut_sable, aut_selection, aut_squelette, aut_teleporteur, aut_void, aut_zombie;
	public static int time, pv, hauteur, largeur, visibility, densite;
	public static Random seed;
	
	private WindowInitGame f;
	private String filePath;
	private int germe ;
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

		// récupération des automates du fichier de config
		JSONArray entities = param.getJSONArray("entities");
		for (int i = 0; i < entities.length(); i++) {
			JSONObject entity = entities.getJSONObject(i);
			String temp = entity.getString("name");
			switch (temp) {
			case "Apple":
				aut_apple = entity.getString("automate");
				break;
			case "Arc":
				aut_arc = entity.getString("automate");
				break;
			case "Bombe":
				aut_bombe = entity.getString("automate");
				break;
			case "Cassable":
				aut_cassable = entity.getString("automate");
				break;
			case "Epee":
				aut_epee = entity.getString("automate");
				break;
			case "Fleche":
				aut_fleche = entity.getString("automate");
				break;
			case "Interrupteur":
				aut_interrupteur = entity.getString("automate");
				break;
			case "Invisible":
				aut_invisible = entity.getString("automate");
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
				break;
			case "Normal":
				aut_normal = entity.getString("automate");
				break;
			case "Pioche":
				aut_pioche = entity.getString("automate");
				break;
			case "Porte":
				aut_porte = entity.getString("automate");
				break;
			case "Potion":
				aut_potion = entity.getString("automate");
				break;
			case "Sable":
				aut_sable = entity.getString("automate");
				break;
			case "Selection":
				aut_selection = entity.getString("automate");
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

	}
}