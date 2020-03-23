package Chess;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import Chess.Pieces.Piece;

public class Board {
	
	//8x8 board of squares
	public Square[][] board = new Square[8][8];
	
	//Move list
	public Stack<Move> moves = new Stack<Move>();
	
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
	
//	public void printBoard() {
//		for(int row = 7; row >= 0; row--) {
//			for(int col = 0; col < 8; col++) {
//				System.out.print(board[row][col].toString()+" ");
//			}
//			System.out.println();
//		}
//	}
	
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

//	public Piece getPiece(char x, int y) {
//		String val = "ABCDEFGH";
//		Character.toUpperCase(x);
//		int col = val.indexOf(x);
//		Square s = getSquare(y-1,col);
//		return s.getPiece();
//	}

	public ArrayList<Square> getValidMoves(Square selectedSquare, Piece selectedPiece) {
		ArrayList<Square> validMoves = selectedPiece.validMoves(this, selectedSquare);
		return validMoves;
	}

	public void movePiece(Piece selectedPiece, Square selectedSquare, Square newSquare) {
		Piece takenPiece = selectedPiece.movePiece(this, newSquare);
		Move m = new Move(selectedPiece, takenPiece, selectedSquare, newSquare);
		moves.add(m);
		selectedPiece.getMoves().add(m);
	}
	
	public boolean undoMove() {
		if(moves.size() > 0) {
			Move last = moves.pop();
			
			//return moved piece to previous square
			last.getMovedPiece().setPos(last.getFrom());
			last.getTo().setPiece(null);
			last.getFrom().setPiece(last.getMovedPiece());
			
			last.getMovedPiece().getMoves().pop();
			if(last.getTakenPiece() != null) {
				last.getTakenPiece().setTaken(false);
				last.getTakenPiece().getCurrentSquare().setPiece(last.getTakenPiece());
			}
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

}
