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
	
    //ui
	public ChessPanel chessPanel;
	public CheckersPanel checkersPanel;
	public ChessOptions chessOptions;
	public CheckersOptions checkersOptions;
	
	//games
	
	public Container c;
	
	public MainFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		c = getContentPane();
		
		checkersPanel = new CheckersPanel(/*checkersOptions*/);
		
		chessOptions = new ChessOptions(this);
		checkersOptions = new CheckersOptions(this);
		
		checkersPanel.setOptions(checkersOptions);
		
		chessPanel = new ChessPanel(chessOptions);
		
		checkersPanel.getCheckers().setCheckersOptions(checkersOptions);
		
		
		JPanel chessContainer = new JPanel();
		chessContainer.add(chessPanel);
		chessContainer.add(chessOptions);
		
		JPanel checkersContainer = new JPanel();
		checkersContainer.add(checkersPanel);
		checkersContainer.add(checkersOptions);
		
		//tabbed pane
		JTabbedPane tp = new JTabbedPane();
		tp.addTab(CHESS, chessContainer);
		tp.addTab(CHECKERS, checkersContainer);
		c.add(tp,BorderLayout.CENTER);
		
	}

	public ChessPanel getChessPanel() {
		return chessPanel;
	}

	public void setChessPanel(ChessPanel chessPanel) {
		this.chessPanel = chessPanel;
	}

	public CheckersPanel getCheckersPanel() {
		return checkersPanel;
	}

	public void setCheckersPanel(CheckersPanel checkersPanel) {
		this.checkersPanel = checkersPanel;
	}


}
