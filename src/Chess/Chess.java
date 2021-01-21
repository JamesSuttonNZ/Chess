package Chess;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JOptionPane;

import Chess.Pieces.*;
import UI.ChessOptions;
import UI.ChessPanel;

public class Chess {
	
	//chess panel
	private ChessPanel chessPanel;
	//chess options
	private ChessOptions chessOptions;
	
	//players
	private Player white = new Player("White");
	private Player black = new Player("Black");
	//board
	private Board board = new Board();
	//pieces
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	//turn
	private boolean whitesTurn = true;
	//Move list
	private Stack<Move> moves = new Stack<Move>();
	//Undone Moves
	private Stack<Move> undone = new Stack<Move>();

	public Chess() {

		//create pieces and assign to players
		setupPieces();
		
		white.calculateMoves(this);
	}

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
			black.setCheck(false);
			//in check
			if(white.inCheck(board)) {
				
				//draw red square around king
				System.out.println("white in check");
				
				white.setCheck(true);
				
				if(!white.calculateMoves(this)) {
					System.out.println("Checkmate");
					//game over
					Object[] choices = {"New Game"};
					JOptionPane.showOptionDialog(chessPanel,"Checkmate: Black Wins!","Game Over!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,null,choices,choices[0]);
					chessOptions.newGame();
				}
				
				//if no valid moves then checkmate
			}
			//not in check
			else {
				if(!white.calculateMoves(this)) {
					System.out.println("Stalemate");
					//game over
					Object[] choices = {"New Game"};
					JOptionPane.showOptionDialog(chessPanel,"Stalemate: Draw!","Game Over!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,null,choices,choices[0]);
					chessOptions.newGame();
				}
				
				//if not valid moves then stalemate
			}
			
		}
		//blacks turn
		else {
			white.setCheck(false);
			
			//in check
			if(black.inCheck(board)) {
				System.out.println("black in check");
				//draw red square around king
				
				black.setCheck(true);
				
				if(!black.calculateMoves(this)){
					System.out.println("Checkmate");
					//game over
					
					Object[] choices = {"New Game"};
					JOptionPane.showOptionDialog(chessPanel,"Checkmate: White Wins!","Game Over!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,null,choices,choices[0]);
					chessOptions.newGame();
				}
				
				//if no valid moves then checkmate
			}
			//not in check
			else {
				if(!black.calculateMoves(this)) {
					System.out.println("Stalemate");
					//game over
					Object[] choices = {"New Game"};
					JOptionPane.showOptionDialog(chessPanel,"Stalemate: Draw!","Game Over!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,null,choices,choices[0]);
					chessOptions.newGame();
				}
				
				//if not valid moves then stalemate
			}
		}
		System.out.println("test");
		chessOptions.updateTable();
	}
	
	public boolean undoMove() {
		if(moves.size() > 0) {
			Move last = moves.pop();
			last.undoMove(this);
			chessPanel.repaint();
			return true;
		}
		return false;
	}
	
	public boolean redoMove() {
		if(undone.size() > 0) {
			Move last = undone.pop();
			last.redoMove(this, chessPanel);
			chessPanel.repaint();
			return true;
		}
		return false;
	}

	public Board getBoard() {
		return board;
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

	public ChessPanel getChessPanel() {
		return chessPanel;
	}

	public void setChessPanel(ChessPanel chessPanel) {
		this.chessPanel = chessPanel;
	}

	public ChessOptions getChessOptions() {
		return chessOptions;
	}

	public void setChessOptions(ChessOptions chessOptions) {
		this.chessOptions = chessOptions;
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
	
}
