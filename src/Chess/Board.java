package Chess;

import java.util.Arrays;

import Chess.Pieces.Piece;

public class Board {
	
	public Square[][] board = new Square[8][8];
	public Board() {
		
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				board[row][col] = new Square(row, col);
			}
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
