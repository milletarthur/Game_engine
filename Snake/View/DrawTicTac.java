import java.awt.*;
import javax.swing.*;

public class DrawTicTac extends JPanel {
	
	TicTac t ;
	JLabel DTicTac ;
	
	public DrawTicTac(TicTac t) {
		this.DTicTac = new JLabel (""+t.getTick()+"");
		this.add(DTicTac);
	}
	
	public void PaintComponent (Graphics x) {
		super.paintComponent(x);
		DTicTac.setText(""+t.getTick()+"");
	}

}
