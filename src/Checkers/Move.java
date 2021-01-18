package Checkers;

import java.util.ArrayList;

import Checkers.Board;
import Checkers.Checkers;
import Checkers.Square;
import Checkers.CheckerPiece;
import UI.CheckersPanel;

public class Move {
	
	public CheckerPiece movedPiece;
	public CheckerPiece takenPiece;
	public Square from, to;
	
	public Move(CheckerPiece movedPiece, CheckerPiece takenPiece, Square from, Square to) {
		this.movedPiece = movedPiece;
		this.takenPiece = takenPiece;
		this.from = from;
		this.to = to;
	}
	
	public Move(CheckerPiece movedPiece, Square from, Square to) {
		this.movedPiece = movedPiece;
		this.from = from;
		this.to = to;
	}
	
	public void executeMove(Checkers checkers, CheckersPanel cp) {
		//set piece to new square;
		movedPiece.movePiece(from,to);
		
		if(takenPiece != null) {
			takenPiece.setTaken(true);
			takenPiece.getCurrentSquare().setPiece(null);
		}
		movedPiece.getMoves().add(this);
		
		movedPiece.drawValidMoves(false);
		
		
		
		checkers.addMove(this);
		
		
		
		cp.repaint();
		
		if(takenPiece != null) {
			if(!movedPiece.checkForJumps(checkers.getBoard(), to)){
				checkers.endTurn();
			}
			else {
				movedPiece.validMoves(checkers.getBoard());
			}
		}
		else {
			checkers.endTurn();
		}
	}

	public void undoMove(Checkers checkers) {
		//return moved piece to previous square
		movedPiece.movePiece(to, from);
		//remove move from piece
		movedPiece.getMoves().pop();
		
		if(takenPiece != null) {
			takenPiece.setTaken(false);
			takenPiece.getCurrentSquare().setPiece(takenPiece);
		}
		
		
		
		
//		checkers.getBoard().getUndoneTurns().add(this);
		
		
		
		
		checkers.endTurn();
	}

	public void redoMove(Checkers checkers, CheckersPanel cp) {
		executeMove(checkers, cp);
	}
	
	public boolean isJump() {
		if(takenPiece == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public String toString() {
		if(takenPiece == null) {
			return "-"+to.toString();
		}
		else {
			return "x"+to.toString();
		}
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

	public CheckerPiece getTakenPiece() {
		return takenPiece;
	}

	public void setTakenPiece(CheckerPiece takenPiece) {
		this.takenPiece = takenPiece;
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
