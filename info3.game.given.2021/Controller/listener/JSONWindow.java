package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// bibliothèque org.json pour lire et écrire les fichiers JSON
import org.json.*;

import draw.WindowInitGame;

public class JSONWindow implements ActionListener {

	private WindowInitGame f;
	private String filePath;

	public JSONWindow(WindowInitGame f) {
		this.f = f;
	}

	// lorsque l'on exécute le bouton JOUER
	public void actionPerformed(ActionEvent evenement) {
		try {
			if (f.getjeu() == "labyrinthe") {
				filePath = "resources/config/config_labyrinthe.json";
				f.initGame();
				f.dispose();
			} else if (f.getjeu() == "arene") {
				filePath = "resources/config/config_labyrinthe.json";
				f.initGame();
				f.dispose();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void WriteJSON() throws IOException {
		
		// TODO - les informations ne s'écrivent pas dans l'autre dans le fichier JSON (je pense pas que ce soit un pb)
		
		// objet JSON principal
		JSONObject param = new JSONObject();
		param.put("jeu", f.getjeu());
		param.put("name1", f.getname(1));
		param.put("name2", f.getname(2));
		param.put("hauteur", f.getSlider("HAUTEUR"));
		param.put("largeur", f.getSlider("LARGEUR"));
		param.put("visibility", f.getSlider("VISIBILITY"));

		
		/*
        jsonObject.put("cooperative", true);
        jsonObject.put("duration", 3600);
        jsonObject.put("germe", 732);
        jsonObject.put("difficulty", 2);

        // Créer le tableau JSON pour les entités
        JSONArray entities = new JSONArray();

        // Ajouter PLAYER 1
        JSONObject player1 = new JSONObject();
        player1.put("name", "PLAYER 1");
        player1.put("view", 1);
        player1.put("behaviour", "player1.gal");
        player1.put("features", "Warrior.class");
        player1.put("sprite", "warrior.png");
        player1.put("pickable", false);
        entities.put(player1);

        // Ajouter PLAYER 2
        JSONObject player2 = new JSONObject();
        player2.put("name", "PLAYER 2");
        player2.put("view", 2);
        player2.put("behaviour", "player2.gal");
        player2.put("features", "Wizard.class");
        player2.put("sprite", "wizard.png");
        player2.put("pickable", false);
        entities.put(player2);

        // Ajouter WALL
        JSONObject wall = new JSONObject();
        wall.put("name", "WALL");
        wall.put("%density", 23);
        wall.put("behaviour", "");
        wall.put("features", "Wall.class");
        wall.put("sprite", "wall.png");
        wall.put("pickable", false);
        entities.put(wall);

        // Ajouter ROCK
        JSONObject rock = new JSONObject();
        rock.put("name", "ROCK");
        rock.put("%density", 5);
        rock.put("behaviour", "rock.gal");
        rock.put("features", "Rock.class");
        rock.put("sprite", "rock.png");
        rock.put("pickable", true);
        entities.put(rock);

        // Ajouter FROG
        JSONObject frog = new JSONObject();
        frog.put("name", "FROG");
        frog.put("%density", 10);
        frog.put("behaviour", "frog.gal");
        frog.put("features", "Frog.class");
        frog.put("sprite", "frog.png");
        frog.put("pickable", true);
        entities.put(frog);

        // Ajouter le tableau d'entités à l'objet JSON principal
        jsonObject.put("entities", entities);*/

        // écrire l'objet JSON dans le fichier config_manuelle.json
        try (FileWriter file = new FileWriter("resources/config/config_manuelle.json")) {
            file.write(param.toString(4)); // --> indentation pour une meilleure lisibilité
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	private void ReadJSON(String filePath) {
		
		// Lire le fichier JSON en une chaîne
        String param = "";
        try {
            param = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Analyser le contenu du fichier JSON
        JSONObject jsonObject = new JSONObject(param);
        
        String jeu = jsonObject.getString("jeu");
        System.out.println("Jeu: " + jeu);
        String name1 = jsonObject.getString("name1");
        System.out.println("Joueur 1: " + name1);
        String name2 = jsonObject.getString("name2");
        System.out.println("Joueur 2: " + name2);
        int hauteur = jsonObject.getInt("hauteur");
        System.out.println("hauteur : " + hauteur);
        int largeur = jsonObject.getInt("largeur");
        System.out.println("largeur : " + largeur);
        int visibility = jsonObject.getInt("visibility");
        System.out.println("visibility : " + visibility);


       /* // Récupérer et afficher les valeurs du JSON
        boolean cooperative = jsonObject.getBoolean("cooperative");
        int duration = jsonObject.getInt("duration");
        int germe = jsonObject.getInt("germe");
        int difficulty = jsonObject.getInt("difficulty");
        double viscosity = jsonObject.getDouble("viscosity");

        System.out.println("Cooperative: " + cooperative);
        System.out.println("Duration: " + duration);
        System.out.println("Germe: " + germe);
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Viscosity: " + viscosity);

        // Récupérer et afficher les entités
        JSONArray entities = jsonObject.getJSONArray("entities");
        for (int i = 0; i < entities.length(); i++) {
            JSONObject entity = entities.getJSONObject(i);
            String name = entity.getString("name");
            String features = entity.getString("features");
            String sprite = entity.getString("sprite");
            boolean pickable = entity.getBoolean("pickable");

            System.out.println("\nEntity " + (i + 1) + ":");
            System.out.println("Name: " + name);
            if (entity.has("view")) {
                int view = entity.getInt("view");
                System.out.println("View: " + view);
            }
            if (entity.has("behaviour")) {
                String behaviour = entity.getString("behaviour");
                System.out.println("Behaviour: " + behaviour);
            }
            if (entity.has("%density")) {
                int density = entity.getInt("%density");
                System.out.println("Density: " + density);
            }
            System.out.println("Features: " + features);
            System.out.println("Sprite: " + sprite);
            System.out.println("Pickable: " + pickable);
        }*/
	}
}