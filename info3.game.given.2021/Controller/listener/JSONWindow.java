package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.*;

// bibliothèque org.json pour lire et écrire les fichiers JSON
import org.json.*;

import draw.WindowInitGame;

/*
 * Classe activer lorsque le bouton Jouer en cliquer
 * elle contient les informations du fichier de configuration
 */
public class JSONWindow implements ActionListener {

	private WindowInitGame f;
	private String filePath;
	
	public static String name1, name2 ;
	public static int time, pv, hauteur, largeur, visibility, densite;

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
        
        name1 = param.getString("name1");
        name2 = param.getString("name2");
        time = param.getInt("duration");
        pv = param.getInt("pv");
        hauteur = param.getInt("hauteur");
        largeur = param.getInt("largeur");
        visibility = param.getInt("visibility");
        densite = param.getInt("%density");
	}
}