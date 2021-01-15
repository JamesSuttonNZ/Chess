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

import Chess.Chess;

public class MainFrame extends JFrame {
	
	public ChessPanel chessPanel;
	public CheckersPanel checkersPanel;
	public ChessOptions options;
	
	public JMenuBar menuBar = new JMenuBar();
	
	public JMenu chess = new JMenu("Chess");
	public JMenu checkers = new JMenu("Checkers");
	
	public JMenuItem newChess = new JMenuItem("New Game");
	public JMenuItem newCheckers = new JMenuItem("New Game");
	
	public Container c;
	
	public MainFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		c = getContentPane();
		
		options = new ChessOptions(this);
		c.add(options, BorderLayout.EAST);
		
		chessPanel = new ChessPanel(options);
		checkersPanel = new CheckersPanel(options);
		//c.add(checkersPanel, BorderLayout.CENTER);
		
		setJMenuBar(menuBar);
		menuBar.add(chess);
		menuBar.add(checkers);
		
		chess.add(newChess);
		checkers.add(newCheckers);
		
		
		//card layout
		JPanel cards = new JPanel(new CardLayout());
		cards.add(chessPanel, "chess");
		cards.add(checkersPanel, "checkers");
		
		c.add(cards, BorderLayout.CENTER);
		
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "chess");
		
		newChess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chessPanel.newGame();
				options.getMoveLog().setText("");
				cl.show(cards, "chess");
			}
		});
		
		newCheckers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkersPanel.newGame();
				options.getMoveLog().setText("");
				cl.show(cards, "checkers");
			}
		});
		
	}

	public ChessPanel getChessPanel() {
		return chessPanel;
	}

	public void setChessPanel(ChessPanel chessPanel) {
		this.chessPanel = chessPanel;
	}


}
