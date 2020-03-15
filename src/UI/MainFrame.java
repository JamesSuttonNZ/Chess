package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Chess.Chess;

public class MainFrame extends JFrame {
	
	public ChessPanel chessPanel;
	public Options options;
	public Container c;
	
	public MainFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		c = getContentPane();
		
		options = new Options(this);
		
		c.add(options, BorderLayout.WEST);
		
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(800,800));
		c.add(jp, BorderLayout.CENTER);
		
	}

	public void chess() {
		Chess chess = new Chess();
		chessPanel = new ChessPanel(chess);
		
		c.add(chessPanel, BorderLayout.CENTER);
		setVisible(true);
	}

}
