import java.awt.*;
import javax.swing.*;

public class DrawTicTac extends JPanel {
	
	private static final long serialVersionUID = 1L;
	TicTac t ;
	JLabel DTicTac ;
	
	public DrawTicTac() {
		this.DTicTac = new JLabel (""+0+"");
		this.add(DTicTac);
	}
	
	public void settimer(TicTac t) {
		this.t = t ;
	}
	
	public void paintComponent(Graphics x) {
		super.paintComponent(x);
		DTicTac.setText(""+t.getTick()+"");
	}

}
