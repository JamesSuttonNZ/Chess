package Chess;

import java.awt.Graphics;
import java.util.Arrays;

import Chess.Pieces.Piece;

public class Board {
	
	public Square[][] board = new Square[8][8];
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
	
	public void printBoard() {
		for(int row = 7; row >= 0; row--) {
			for(int col = 0; col < 8; col++) {
				System.out.print(board[row][col].toString()+" ");
			}
			System.out.println();
		}
	}
	
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

	public Piece getPiece(char x, int y) {
		String val = "ABCDEFGH";
		Character.toUpperCase(x);
		int col = val.indexOf(x);
		Square s = getSquare(y-1,col);
		return s.getPiece();
	}

}
