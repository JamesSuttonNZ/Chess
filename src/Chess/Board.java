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
	private Square[][] board = new Square[8][8];
	
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

}
