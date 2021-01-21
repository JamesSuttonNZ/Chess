package UI;

import java.util.Stack;

import javax.swing.JPanel;

import Chess.Chess;

public class ChessTab extends JPanel {
	
	private MainFrame mf;
	private Chess chess;
	private ChessPanel chessPanel;
	private ChessOptions chessOptions;
	
	public ChessTab(MainFrame mf) {
		
		this.mf = mf;
		this.chess = new Chess();
		this.chessPanel = new ChessPanel(chess);
		this.chessOptions = new ChessOptions(chess,mf);
		this.chess.setChessPanel(chessPanel);
		this.chess.setChessOptions(chessOptions);
		
		add(chessPanel);
		add(chessOptions);
	}
		
}
