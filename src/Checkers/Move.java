package Checkers;

import java.util.ArrayList;

import Checkers.Board;
import Checkers.Checkers;
import Checkers.Square;
import Checkers.CheckerPiece;
import UI.CheckersPanel;

public class Move {
	
	public CheckerPiece movedPiece;
	public Square from, to;
	public ArrayList<Jump> jumps = new ArrayList<Jump>();
	
	public Move(CheckerPiece movedPiece, CheckerPiece takenPiece, Square from, Square to) {
		jumps.add(new Jump(movedPiece, takenPiece, from, to));
		this.movedPiece = movedPiece;
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
		
		if(!jumps.isEmpty()) {
			for(Jump j: jumps) {
				j.executeJump();
			}
		}
		movedPiece.getMoves().add(this);
		
		movedPiece.drawValidMoves(false);
		checkers.getBoard().getMoves().add(this);
		cp.repaint();
		
		if(!jumps.isEmpty()) {
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
		
		if(jumps.isEmpty()) {
			for(Jump j : jumps) {
				j.undoJump();
			}
		}
		checkers.getBoard().getUndone().add(this);
		checkers.endTurn();
	}

	public void redoMove(Checkers checkers, CheckersPanel cp) {
		executeMove(checkers, cp);
	}
	
	public boolean isJump() {
		if(jumps.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public String toString() {
		if(jumps.isEmpty()) {
			return from.toString()+"-"+to.toString();
		}
		else {
			String log = from.toString();
			for(Jump j : jumps) {
				log += j.toString();
			}
			return log;
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

	public ArrayList<Jump> getJumps() {
		return jumps;
	}

	public void setJumps(ArrayList<Jump> jumps) {
		this.jumps = jumps;
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
