package draw;

import java.awt.*;
import javax.swing.*;

public class ImageJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image, item;
	private int x, y, taille;
	private boolean type; // false pour main, true pour inventaire

	public ImageJPanel(boolean type) {
		this.setPreferredSize(new Dimension(65, 65));
		this.type = type;
	}

	public void setImage(Image image, Image item, int x, int y, int taille) {
		this.image = image;
		this.item = item;
		this.x = x;
		this.y = y;
		this.taille = taille;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, this.x, this.y, this.taille, this.taille, this);
		if (item != null) {
			g.drawImage(item, this.x, this.y, this.taille, this.taille, this);
		}
		g.setColor(Color.WHITE);
		if (type)
			g.drawString("inventaire", 1, 60);
		else
			g.drawString("main", 1, 60);
	}
}
