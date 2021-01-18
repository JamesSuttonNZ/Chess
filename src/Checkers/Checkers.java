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
	public CheckersPanel checkersPanel;
	//options
	public CheckersOptions checkersOptions;
	
	//players
	public Player white = new Player("White");
	public Player black = new Player("Black");
	//board
	public Board board = new Board();
	//pieces
	public ArrayList<CheckerPiece> pieces = new ArrayList<CheckerPiece>();
	//turn
	public boolean whitesTurn = true;
	//current turn
	public Turn currentTurn;
	//Move list
	public Stack<Turn> turns = new Stack<Turn>();	
	//Undone Moves
	public Stack<Turn> undoneTurns = new Stack<Turn>();
	
	/**
	 * Setup game of Checkers
	 * @param checkersPanel
	 */
	public Checkers(CheckersPanel checkersPanel) {

		this.checkersPanel = checkersPanel;
		
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
			currentTurn = new Turn(white, board);
		}
		else {
			currentTurn = new Turn(black, board);
		}
	}
	
	public boolean undoTurn() {
		if(turns.size() > 0) {
			Turn last = turns.pop();
			last.undo(this);
			return true;
		}
		return false;
	}
	
	public boolean redoTurn() {
		if(undoneTurns.size() > 0) {
			Turn last = undoneTurns.pop();
			last.redo(this, checkersPanel);
			return true;
		}
		return false;
	}
	
	public void logMoves(CheckersOptions options) {
		JTextArea ml = options.getMoveLog();
		ml.setText("");
		boolean turn = true;
		int turnNum = 1;
		
		String format = "%1$3s %2$9s";
		String format2 = "%1$11s";
		String line;
		
		
		for(Turn t : turns) {
			if(turn) {
				line = String.format(format, turnNum+".", t.toString());
				ml.append(line);
				turn = !turn;
			}
			else {
				line = String.format(format2, t.toString());
				ml.append(line+"\n");
				turn = !turn;
				turnNum++;
			}
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

	public void addMove(Move m) {
		currentTurn.addMove(m);
	}

}
