package Checkers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Checkers.Board;
import Checkers.Player;
import UI.CheckersOptions;
import UI.CheckersPanel;
import UI.ChessOptions;

public class Checkers {

	
	//chess panel
	private CheckersPanel checkersPanel;
	//options
	private CheckersOptions checkersOptions;
	
	//players
	private Player white = new Player("White");
	private Player black = new Player("Black");
	//board
	private Board board = new Board();
	//pieces
	private ArrayList<CheckerPiece> pieces = new ArrayList<CheckerPiece>();
	//turn
	private boolean whitesTurn = true;
	//current turn
	private Turn currentTurn;
	//Move list
	private Stack<Turn> turns = new Stack<Turn>();	
	//Undone Moves
	private Stack<Turn> undoneTurns = new Stack<Turn>();
	
	/**
	 * Setup game of Checkers
	 * @param checkersPanel
	 */
	public Checkers() {
		
		//create pieces and assign to players
		setupPieces();
		
		currentTurn = new Turn(white, board);
	}

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
		
		turns.add(currentTurn);
		
		whitesTurn = !whitesTurn;
		if(whitesTurn) {
			
			if(!white.piecesLeft()) {
				checkersPanel.repaint();
				Object[] choices = {"New Game"};
				JOptionPane.showOptionDialog(checkersPanel,"Black Wins!","Game Over!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,null,choices,choices[0]);
				checkersOptions.newGame();
			}
			
			currentTurn = new Turn(white, board);
		}
		else {
			
			if(!black.piecesLeft()) {
				checkersPanel.repaint();
				Object[] choices = {"New Game"};
				JOptionPane.showOptionDialog(checkersPanel,"White Wins!","Game Over!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,null,choices,choices[0]);
				checkersOptions.newGame();
			}
			
			currentTurn = new Turn(black, board);
		}
		
		checkersOptions.updateTable();
	}
	
	public boolean undoTurn() {
		if(turns.size() > 0) {
			Turn last = turns.pop();
			last.undo(this);
			undoneTurns.add(last);
			if(turns.size() > 0) {
				currentTurn = turns.peek();
			}
			else {
				currentTurn = new Turn(white, board);
			}
			whitesTurn = !whitesTurn;
			checkersPanel.repaint();
			return true;
		}
		return false;
	}
	
	public boolean redoTurn() {
		if(undoneTurns.size() > 0) {
			Turn last = undoneTurns.pop();
			currentTurn = last;
			last.redo(this, checkersPanel);
			checkersPanel.repaint();
			return true;
		}
		return false;
	}
	
	public void removeMovesExcept(CheckerPiece moved) {
		for(CheckerPiece p: pieces) {
			if(p != moved) {
				p.getValidMoves().clear();
			}
		}
	}

	public void addMove(Move m) {
		currentTurn.addMove(m);
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

	public ArrayList<CheckerPiece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<CheckerPiece> pieces) {
		this.pieces = pieces;
	}
	public Stack<Turn> getTurns() {
		return turns;
	}

	public void setTurns(Stack<Turn> turns) {
		this.turns = turns;
	}

	public Stack<Turn> getUndoneTurns() {
		return undoneTurns;
	}

	public void setUndoneTurns(Stack<Turn> undoneTurns) {
		this.undoneTurns = undoneTurns;
	}

	public Turn getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(Turn currentTurn) {
		this.currentTurn = currentTurn;
	}

	public CheckersOptions getCheckersOptions() {
		return checkersOptions;
	}

	public void setCheckersOptions(CheckersOptions checkersOptions) {
		this.checkersOptions = checkersOptions;
	}

	public CheckersPanel getCheckersPanel() {
		return checkersPanel;
	}

	public void setCheckersPanel(CheckersPanel checkersPanel) {
		this.checkersPanel = checkersPanel;
	}

}
