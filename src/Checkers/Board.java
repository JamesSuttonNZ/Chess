package Checkers;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.JTextArea;

import Checkers.CheckerPiece;
import UI.ChessOptions;
import UI.CheckersPanel;

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
		int w = 1;
		int b = 1;
		for(int row = 0; row < 8; row++) {
			int x = 0;
			for(int col = 0; col < 8; col++) {
				if(white) {
					board[row][col] = new Square(white, row, col, x, y, w);
					w++;
				}
				if(!white) {
					board[row][col] = new Square(white, row, col, x, y, b);
					b++;
				}
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
	
	public boolean undoMove(Checkers checkers) {
		if(moves.size() > 0) {
			Move last = moves.pop();
			last.undoMove(checkers);
			return true;
		}
		return false;
	}
	
	public boolean redoMove(Checkers checkers, CheckersPanel cp) {
		if(undone.size() > 0) {
			Move last = undone.pop();
			last.redoMove(checkers, cp);
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
		
		String format = "%1$3s %2$9s";
		String format2 = "%1$11s";
		String line;
		
		
		for(Move m : moves) {
			if(turn) {
				line = String.format(format, turnNum+".", m.toString());
				ml.append(line);
				turn = !turn;
			}
			else {
				line = String.format(format2, m.toString());
				ml.append(line+"\n");
				turn = !turn;
				turnNum++;
			}
		}
	}
}
