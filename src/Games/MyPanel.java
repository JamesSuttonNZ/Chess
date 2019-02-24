package Games;

import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel{

	public void drawing() {
		repaint();
	}
	
//	public void paint(Graphics g) {
//		g.setColor(Color.BLUE);
//		g.fillRect(100, 100, 20, 50);
//	}
	
	public void PaintComponent(Graphics g) {
		//super.paintComponent(g);
		
		g.setColor(Color.BLUE);
		g.fillRect(100, 100, 20, 50);
		
	}
}
