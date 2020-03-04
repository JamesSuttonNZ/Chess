package UI;

import javax.swing.*;
import java.awt.*;

public class Main {
	
	public static void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new MainFrame("Sutt's Oldschool Arcade");
				frame.setVisible(true);
				frame.setSize(500, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
			}
			
		});
	}
}
