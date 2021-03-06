package Checkers;

import java.util.ArrayList;

import Checkers.Board;
import Checkers.Checkers;
import Checkers.Square;
import Checkers.CheckerPiece;
import UI.CheckersPanel;

public class Move {
	
	private CheckerPiece movedPiece;
	private CheckerPiece takenPiece;
	private Square from, to;
	
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
		
		//
		if(takenPiece != null) {
			takenPiece.taken(true);
		}
		
		movedPiece.getMoves().add(this);
		
		checkers.addMove(this);
		
		if(takenPiece != null) {
			if(movedPiece.checkForJumps(checkers.getBoard())){
				movedPiece.validMoves(checkers.getBoard(), true);
				checkers.removeMovesExcept(movedPiece);
				checkers.getCheckersOptions().enableUndo(false);
				checkers.getCheckersOptions().enableRedo(false);
			}
			else {
				checkers.endTurn();
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
			takenPiece.taken(false);
		}
		
	}

	public void redoMove(Checkers checkers, CheckersPanel cp) {
		//set piece to new square;
		movedPiece.movePiece(from,to);
		
		if(takenPiece != null) {
			takenPiece.taken(true);
		}
		movedPiece.getMoves().add(this);
		
//		if(takenPiece != null) {
//			if(movedPiece.checkForJumps(checkers.getBoard())){
//				movedPiece.validMoves(checkers.getBoard(), true);
//			}
//			else {
//				checkers.endTurn();
//				
//			}
//		}
//		else {
//			checkers.endTurn();
//		}
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
