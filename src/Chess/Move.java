package Chess;

import Chess.Pieces.Piece;

public class Move {

	public Piece movedPiece, takenPiece;
	public Square from, to;
	
	public Move(Piece movedPiece, Piece takenPiece, Square from, Square to) {
		this.movedPiece = movedPiece;
		this.takenPiece = takenPiece;
		this.from = from;
		this.to = to;
	}
	
	public int getRowsMoved() {
		return from.getRow()-to.getRow();
	}

	public Piece getMovedPiece() {
		return movedPiece;
	}

	public void setMovedPiece(Piece movedPiece) {
		this.movedPiece = movedPiece;
	}

	public Piece getTakenPiece() {
		return takenPiece;
	}

	public void setTakenPiece(Piece takenPiece) {
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
