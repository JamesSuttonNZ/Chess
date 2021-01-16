package Checkers;

import java.util.ArrayList;

import Checkers.Board;
import Checkers.Checkers;
import Checkers.Square;
import Checkers.CheckerPiece;
import UI.CheckersPanel;

public class Move {
	
	public CheckerPiece movedPiece;
	public ArrayList<CheckerPiece> takenPieces;
	public Square from, to;
	
	public Move(CheckerPiece movedPiece, ArrayList<CheckerPiece> takenPieces, Square from, Square to) {
		this.movedPiece = movedPiece;
		this.takenPieces = takenPieces;
		this.from = from;
		this.to = to;
	}
	
	public Move(CheckerPiece movedPiece, Square from, Square to) {
		this.movedPiece = movedPiece;
		this.takenPieces = new ArrayList<CheckerPiece>();
		this.from = from;
		this.to = to;
	}

	public boolean validMove(Board board) {
		return to.isEmpty();
	}
	
	public void executeMove(Checkers checkers, CheckersPanel cp) {
		//set piece to new square;
		movedPiece.movePiece(from,to);
		
		if(!takenPieces.isEmpty()) {
			for(CheckerPiece p: takenPieces) {
				p.setTaken(true);
				p.getCurrentSquare().setPiece(null);
			}
		}
		movedPiece.getMoves().add(this);
		
		movedPiece.drawValidMoves(false);
		checkers.getBoard().getMoves().add(this);
		cp.repaint();
		checkers.endTurn();
	}

	public void undoMove(Checkers checkers) {
		//return moved piece to previous square
		movedPiece.movePiece(to, from);
		//remove move from piece
		movedPiece.getMoves().pop();
		
		if(!takenPieces.isEmpty()) {
			for(CheckerPiece p: takenPieces) {
				p.setTaken(false);
				p.getCurrentSquare().setPiece(p);
			}
		}
		checkers.getBoard().getUndone().add(this);
		checkers.endTurn();
	}
	
	public void redoMove(Checkers checkers, CheckersPanel cp) {
		executeMove(checkers, cp);
	}
	
	public String toString() {
		if(takenPieces.isEmpty()) {
			return movedPiece.toString()+to.toString();
		}
		return movedPiece.toString()+"x"+to.toString();
	}
	
	public void setValid(Boolean valid) {
		to.setValid(valid);
	}
	
	public int getRowsMoved() {
		return from.getRow()-to.getRow();
	}

	public CheckerPiece getMovedPiece() {
		return movedPiece;
	}

	public void setMovedPiece(CheckerPiece movedPiece) {
		this.movedPiece = movedPiece;
	}

	public ArrayList<CheckerPiece> getTakenPieces() {
		return takenPieces;
	}

	public void setTakenPieces(ArrayList<CheckerPiece> takenPieces) {
		this.takenPieces = takenPieces;
	}

	public Square getFrom() {
		return from;
	}

	public void setFrom(Square from) {
		this.from = from;
	}

	public Square getTo() {
		return to;
	}

	public void setTo(Square to) {
		this.to = to;
	}
}
