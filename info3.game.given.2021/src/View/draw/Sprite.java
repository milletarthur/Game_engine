package View.draw;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * Classe qui permet de découper une grille de sprite et d'accéder à différentes images dans cette grille
 */

public class Sprite {

	private BufferedImage[][] sprites;
    private int spriteWidth; // largeur d'un sprite
    private int spriteHeight; //hauteur d'un sprite
    private int rows;
    private int cols;

    public Sprite(String filePath, int spriteWidth, int spriteHeight) throws IOException {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        loadSpriteSheet(filePath);
    }

    // méthode pour charger la feuille de sprites et découper les sprites individuels
    private void loadSpriteSheet(String filePath) throws IOException {

    	BufferedImage spriteSheet = ImageIO.read(new File(filePath));
        rows = spriteSheet.getHeight() / spriteHeight;
        cols = spriteSheet.getWidth() / spriteWidth;
        
        sprites = new BufferedImage[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                sprites[row][col] = spriteSheet.getSubimage(col * spriteWidth,row * spriteHeight,spriteWidth,spriteHeight);
            }
        }
    }

    // méthode pour obtenir un sprite à une position spécifique (ligne, colonne)
    public BufferedImage getSprite(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return sprites[row][col];
        } else {
            throw new IndexOutOfBoundsException("Position invalide");
        }
    }
}
