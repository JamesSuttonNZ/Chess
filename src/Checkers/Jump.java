package Checkers;

import java.util.ArrayList;

public class Jump {
	
	public CheckerPiece movedPiece;
	public CheckerPiece takenPiece;
	public Square from, to;
	
	public Jump(CheckerPiece movedPiece, CheckerPiece takenPiece, Square from, Square to) {
		this.movedPiece = movedPiece;
		this.takenPiece = takenPiece;
		this.from = from;
		this.to = to;
	}

	public String toString() {
		return "x"+to.toString();
	}

	public void executeJump() {
		takenPiece.setTaken(true);
		takenPiece.getCurrentSquare().setPiece(null);
	}

	public void undoJump() {
		takenPiece.setTaken(false);
		takenPiece.getCurrentSquare().setPiece(takenPiece);
	}
}
