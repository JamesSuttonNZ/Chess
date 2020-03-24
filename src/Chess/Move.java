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
		to.setValid(true);
	}
	
	public void setInvalid() {
		to.setValid(false);
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
	
	public void executeMove(Chess chess) {
		//remove piece from old square
		from.setPiece(null);
		//set piece to new square;
		movedPiece.setPos(to);
		if(takenPiece != null) {
			takenPiece.setTaken(true);
		}
		movedPiece.getMoves().add(this);
		chess.getBoard().getMoves().add(this);
		chess.setWhitesTurn(!chess.isWhitesTurn());
	}

	public void undoMove() {
		//return moved piece to previous square
		movedPiece.setPos(from);;
		//set to square piece to null
		to.setPiece(null);
		//remove move from piece
		movedPiece.getMoves().pop();
		
		if(takenPiece != null) {
			takenPiece.setTaken(false);
			takenPiece.getCurrentSquare().setPiece(takenPiece);
		}
	}
	
}
