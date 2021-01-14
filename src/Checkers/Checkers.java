package Checkers;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Checkers.Board;
import Checkers.Player;
import UI.CheckersPanel;

public class Checkers {

	
	//chess panel
	CheckersPanel checkersPanel;
	
	//players
	public Player white = new Player("White");
	public Player black = new Player("Black");
	//board
	public Board board = new Board();
	//pieces
	public ArrayList<CheckerPiece> pieces = new ArrayList<CheckerPiece>();
	//turn
	boolean whitesTurn = true;

	public Checkers(CheckersPanel checkersPanel) {

		this.checkersPanel = checkersPanel;
		
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
		pieces.add(new CheckerPiece(white, board.getSquare(7, 0)));
		pieces.add(new CheckerPiece(white, board.getSquare(7, 2)));
		pieces.add(new CheckerPiece(white, board.getSquare(7, 4)));
		pieces.add(new CheckerPiece(white, board.getSquare(7, 6)));
		pieces.add(new CheckerPiece(white, board.getSquare(6, 1)));
		pieces.add(new CheckerPiece(white, board.getSquare(6, 3)));
		pieces.add(new CheckerPiece(white, board.getSquare(6, 5)));
		pieces.add(new CheckerPiece(white, board.getSquare(6, 7)));
		pieces.add(new CheckerPiece(white, board.getSquare(5, 0)));
		pieces.add(new CheckerPiece(white, board.getSquare(5, 2)));
		pieces.add(new CheckerPiece(white, board.getSquare(5, 4)));
		pieces.add(new CheckerPiece(white, board.getSquare(5, 6)));
		
		//black
		pieces.add(new CheckerPiece(black, board.getSquare(0, 1)));
		pieces.add(new CheckerPiece(black, board.getSquare(0, 3)));
		pieces.add(new CheckerPiece(black, board.getSquare(0, 5)));
		pieces.add(new CheckerPiece(black, board.getSquare(0, 7)));
		pieces.add(new CheckerPiece(black, board.getSquare(1, 0)));
		pieces.add(new CheckerPiece(black, board.getSquare(1, 2)));
		pieces.add(new CheckerPiece(black, board.getSquare(1, 4)));
		pieces.add(new CheckerPiece(black, board.getSquare(1, 6)));
		pieces.add(new CheckerPiece(black, board.getSquare(2, 1)));
		pieces.add(new CheckerPiece(black, board.getSquare(2, 3)));
		pieces.add(new CheckerPiece(black, board.getSquare(2, 5)));
		pieces.add(new CheckerPiece(black, board.getSquare(2, 7)));
	}
	
	public void endTurn() {
		whitesTurn = !whitesTurn;
		if(whitesTurn) {
			white.calculateMoves(board);
		}
		else {
			black.calculateMoves(board);
		}
	}

	public Board getBoard() {
		return board;
	}

	public void drawPieces(Graphics g) {
		for(CheckerPiece p : pieces) {
			p.drawPiece(g);
		}
	}

	public boolean isWhitesTurn() {
		return whitesTurn;
	}

	public void setWhitesTurn(boolean whitesTurn) {
		this.whitesTurn = whitesTurn;
	}

	public ArrayList<CheckerPiece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<CheckerPiece> pieces) {
		this.pieces = pieces;
	}
	

}
