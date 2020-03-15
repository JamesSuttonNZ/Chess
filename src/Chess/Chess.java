package Chess;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

import Chess.Pieces.*;

public class Chess {
	
	public Player white = new Player("White");
	public Player black = new Player("Black");
	public Board board;
	public ArrayList<Piece> pieces = new ArrayList<Piece>();

	public Chess() {
		
		//create board
		board = new Board();
		
		//create pieces and assign to players
		setupPieces();
		
		//run game loop
//		GameLoop();
	}

	private void GameLoop() {
		
		boolean gameOver = false;
		boolean whitesTurn = true;
		
		while(!gameOver) {
			
			board.printBoard();
			
			Piece p = null;
			
			if(whitesTurn) {
				Scanner s = new Scanner(System.in);
				
				boolean validPiece = false;
				while(!validPiece) {
					System.out.println("White's turn, choose piece (eg 'A2'): ");
					String from = s.nextLine();
					if(from.length() != 2) {
						System.out.println("invalid position");
						continue;
					}
					p = getPiece(from);
					if(p != null && p.getOwner() == white) {
						validPiece = true;
					}
				}
				
				boolean validMove = false;
				while(!validMove) {
					System.out.println("White's turn, move to (eg 'A4'): ");
					String to = s.nextLine();
					if(to.length() != 2) {
						System.out.println("invalid position");
						continue;
					}
					validMove = move(to, p, board);
				}

				
				whitesTurn = !whitesTurn;
			}
			
			else {
				Scanner s = new Scanner(System.in);
				
				boolean validPiece = false;
				while(!validPiece) {
					System.out.println("Black's turn, choose piece (eg 'A7'): ");
					String from = s.nextLine();
					if(from.length() != 2) {
						System.out.println("invalid position");
						continue;
					}
					p = getPiece(from);
					if(p != null && p.getOwner() == black) {
						validPiece = true;
					}
				}
				
				boolean validMove = false;
				while(!validMove) {
					System.out.println("Black's turn, move to (eg 'A5'): ");
					String to = s.nextLine();
					if(to.length() != 2) {
						System.out.println("invalid position");
						continue;
					}
					validMove = move(to, p, board);
				}

				
				whitesTurn = !whitesTurn;
			}
			
		}
		
	}

	private Piece getPiece(String from) {
		char x = from.charAt(0);
		int y = Character.getNumericValue(from.charAt(1));
		return board.getPiece(x,y);
	}
	
	private boolean move(String to, Piece p, Board board) {
		char x = to.charAt(0);
		int y = Character.getNumericValue(to.charAt(1));
		return p.move(x,y, board);
	}

	public void setupPieces() {
		//white
		pieces.add(new Rook(white, board.getSquare(0, 0)));
		pieces.add(new Rook(white, board.getSquare(0, 7)));
		pieces.add(new Knight(white, board.getSquare(0, 1)));
		pieces.add(new Knight(white, board.getSquare(0, 6)));
		pieces.add(new Bishop(white, board.getSquare(0, 2)));
		pieces.add(new Bishop(white, board.getSquare(0, 5)));
		pieces.add(new King(white, board.getSquare(0, 4)));
		pieces.add(new Queen(white, board.getSquare(0, 3)));
		for(int col = 0; col < 8; col++) {
			pieces.add(new Pawn(white, board.getSquare(1,col)));
		}
		//black
		pieces.add(new Rook(black, board.getSquare(7, 0)));
		pieces.add(new Rook(black, board.getSquare(7, 7)));
		pieces.add(new Knight(black, board.getSquare(7, 1)));
		pieces.add(new Knight(black, board.getSquare(7, 6)));
		pieces.add(new Bishop(black, board.getSquare(7, 2)));
		pieces.add(new Bishop(black, board.getSquare(7, 5)));
		pieces.add(new King(black, board.getSquare(7, 4)));
		pieces.add(new Queen(black, board.getSquare(7, 3)));
		for(int col = 0; col < 8; col++) {
			pieces.add(new Pawn(black, board.getSquare(6,col)));
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
	
}
