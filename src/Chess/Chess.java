package Chess;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

import Chess.Pieces.*;

public class Chess {
	
	//players
	public Player white = new Player("White");
	public Player black = new Player("Black");
	//board
	public Board board = new Board();
	//pieces
	public ArrayList<Piece> pieces = new ArrayList<Piece>();
	//turn
	boolean whitesTurn = true;

	public Chess() {

		//create pieces and assign to players
		setupPieces();
		
		white.calculateMoves(board);
		
		//run game loop
//		GameLoop();
	}

//	private void GameLoop() {
//		boolean gameOver = false;
//		boolean whitesTurn = true;
//		while(!gameOver) {	
//			//white's turn
//			if(whitesTurn) {
//				whitesTurn = !whitesTurn;
//			}
//			//black's turn
//			else {
//				whitesTurn = !whitesTurn;
//			}
//		}
//	}

	public void setupPieces() {
		//white
		pieces.add(new Rook(white, board.getSquare(7, 0)));
		pieces.add(new Rook(white, board.getSquare(7, 7)));
		pieces.add(new Knight(white, board.getSquare(7, 1)));
		pieces.add(new Knight(white, board.getSquare(7, 6)));
		pieces.add(new Bishop(white, board.getSquare(7, 2)));
		pieces.add(new Bishop(white, board.getSquare(7, 5)));
		pieces.add(new King(white, board.getSquare(7, 4)));
		pieces.add(new Queen(white, board.getSquare(7, 3)));
		for(int col = 0; col < 8; col++) {
			pieces.add(new Pawn(white, board.getSquare(6,col)));
		}
		//black
		pieces.add(new Rook(black, board.getSquare(0, 0)));
		pieces.add(new Rook(black, board.getSquare(0, 7)));
		pieces.add(new Knight(black, board.getSquare(0, 1)));
		pieces.add(new Knight(black, board.getSquare(0, 6)));
		pieces.add(new Bishop(black, board.getSquare(0, 2)));
		pieces.add(new Bishop(black, board.getSquare(0, 5)));
		pieces.add(new King(black, board.getSquare(0, 4)));
		pieces.add(new Queen(black, board.getSquare(0, 3)));
		for(int col = 0; col < 8; col++) {
			pieces.add(new Pawn(black, board.getSquare(1,col)));
		}
	}
	
	public void endTurn() {
		whitesTurn = !whitesTurn;
		
		//whites turn
		if(whitesTurn) {
			//in check
			if(white.inCheck(board)) {
				
				//draw red square around king
				System.out.println("white in check");
				if(!white.calculateMoves(board)) {
					System.out.println("Checkmate");
					//game over
				}
				
				//if no valid moves then checkmate
			}
			//not in check
			else {
				if(!white.calculateMoves(board)) {
					System.out.println("Stalemate");
					//game over
				}
				
				//if not valid moves then stalemate
			}
			
		}
		//blacks turn
		else {
			//in check
			if(black.inCheck(board)) {
				System.out.println("black in check");
				//draw red square around king
				
				if(!black.calculateMoves(board)){
					System.out.println("Checkmate");
					//game over
				}
				
				//if no valid moves then checkmate
			}
			//not in check
			else {
				if(!black.calculateMoves(board)) {
					System.out.println("Stalemate");
					//game over
				}
				
				//if not valid moves then stalemate
			}
		}
	}

	public Board getBoard() {
		return board;
	}

	public void drawPieces(Graphics g) {
		for(Piece p : pieces) {
			p.drawPiece(g);
		}
	}

	public boolean isWhitesTurn() {
		return whitesTurn;
	}

	public void setWhitesTurn(boolean whitesTurn) {
		this.whitesTurn = whitesTurn;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
	
}
