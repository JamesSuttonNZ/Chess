package UI;

import javax.swing.*;
import java.awt.*;

public class Main {
	
	public static void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new MainFrame("5 Games in Java");
				frame.setResizable(false);
//				frame.setSize(1200, 1000);
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
			
		});
	}
}
