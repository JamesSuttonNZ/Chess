package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Chess.Chess;

public class MainFrame extends JFrame {
	
	public ChessPanel chessPanel = new ChessPanel();;
	public Options options;
	public Container c;
	
	public MainFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		c = getContentPane();
		
		options = new Options(this);
		
		c.add(options, BorderLayout.EAST);
		
		chessPanel = new ChessPanel();
		
		c.add(chessPanel, BorderLayout.CENTER);
		
	}

	public ChessPanel getChessPanel() {
		return chessPanel;
	}

	public void setChessPanel(ChessPanel chessPanel) {
		this.chessPanel = chessPanel;
	}


}
