package tests;

import draw.WindowInitGame;
import sound.SimpleAudioPlayer;

/*
 * Main de test pour la view
 */
public class Game {

	public static void main(String[] args) {
		
		WindowInitGame game = new WindowInitGame();
		
		try {
			SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(
					"resources/jeuantoniajuin2024_versionmartinofficielle.wav");

			audioPlayer.play();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();

		}
		
	}

}
