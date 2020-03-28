package Chess;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.JTextArea;

import Chess.Pieces.Piece;
import UI.ChessOptions;
import UI.ChessPanel;

public class Board {
	
	//8x8 board of squares
	public Square[][] board = new Square[8][8];
	
	//Move list
	public Stack<Move> moves = new Stack<Move>();
	
	//Undone Moves
	public Stack<Move> undone = new Stack<Move>();
	
	/**
	 * setup board
	 */
	public Board() {
		
		//create board
		boolean white = true;
		int y = 0;
		for(int row = 0; row < 8; row++) {
			int x = 0;
			for(int col = 0; col < 8; col++) {
				board[row][col] = new Square(white, row, col, x, y);
				x += 100;
				if(col != 7) {
					white = !white;
				}
			}
			y += 100;
		}
		
	}
	
	/**
	 * draw squares
	 */
	public void drawBoard(Graphics g) {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				board[row][col].drawSquare(g);
			}
		}
	}

	public Square getSquare(int row, int col) {
		return board[row][col];
	}
	
	public boolean undoMove(Chess chess) {
		if(moves.size() > 0) {
			Move last = moves.pop();
			last.undoMove(chess);
			return true;
		}
		return false;
	}
	
	public boolean redoMove(Chess chess, ChessPanel cp) {
		if(undone.size() > 0) {
			Move last = undone.pop();
			last.redoMove(chess, cp);
			return true;
		}
		return false;
	}

	public Stack<Move> getMoves() {
		return moves;
	}

	public void setMoves(Stack<Move> moves) {
		this.moves = moves;
	}

	public Stack<Move> getUndone() {
		return undone;
	}

	public void setUndone(Stack<Move> undone) {
		this.undone = undone;
	}

	public void logMoves(ChessOptions options) {
		JTextArea ml = options.getMoveLog();
		ml.setText("");
		boolean turn = true;
		int turnNum = 1;
		for(Move m : moves) {
			if(turn) {
				ml.append(turnNum+".   "+m.toString());
				turn = !turn;
			}
			else {
				ml.append("     "+m.toString()+"\n");
				turn = !turn;
				turnNum++;
			}
		}
	}
}
