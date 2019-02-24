package Games;

import java.awt.*;
import javax.swing.*;

public class Main {
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("Sutt's Oldschool Arcade");
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		MyPanel canvas = new MyPanel();
		//canvas.setSize(500, 500);
		contentPane.add(canvas, BorderLayout.CENTER);
		
		canvas.drawing();
		
		JButton button = new JButton();
		JButton button2 = new JButton();
		JButton button3 = new JButton();
		JButton button4 = new JButton();
		
		
		contentPane.add(button, BorderLayout.LINE_START);
		contentPane.add(button2, BorderLayout.PAGE_START);
		contentPane.add(button3, BorderLayout.LINE_END);
		contentPane.add(button4, BorderLayout.PAGE_END);
		
		
		System.out.println("hello world");
	}
}
