package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Chess.Chess;

public class MainFrame extends JFrame {
	
	//tab names
	final static String CHESS = "Chess";
    final static String CHECKERS = "Checkers";
	
	//games
	private ChessTab chessTab;
	private CheckersTab checkersTab;
	
	private JTabbedPane tp;
	
	private Container c;
	
	public MainFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		c = getContentPane();
		
		chessTab = new ChessTab(this);
		
		checkersTab = new CheckersTab(this);
		
		//tabbed pane
		tp = new JTabbedPane();
		tp.addTab(CHESS, chessTab);
		tp.addTab(CHECKERS, checkersTab);
		c.add(tp,BorderLayout.CENTER);
		
	}

	public void newChess() {
		this.chessTab = new ChessTab(this);
		this.tp.setComponentAt(0, chessTab);
	}
	
	public void newCheckers() {
		this.checkersTab = new CheckersTab(this);
		this.tp.setComponentAt(1, checkersTab);
	}


}
