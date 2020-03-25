package Chess;

import Chess.Pieces.Piece;

public class EnPassant extends Move {
	
	public Square enPassant;
	
	public EnPassant(Piece movedPiece, Piece takenPiece, Square from, Square to, Square enPassant) {
		super(movedPiece, takenPiece, from, to);
		this.enPassant = enPassant;
	}
	
	public void executeMove(Chess chess) {
		//remove piece from old square
		from.setPiece(null);
		//set piece to new square
		movedPiece.setPos(to);
		if(takenPiece != null) {
			takenPiece.setTaken(true);
			enPassant.setPiece(null);
		}
		movedPiece.getMoves().add(this);
		chess.getBoard().getMoves().add(this);
		chess.setWhitesTurn(!chess.isWhitesTurn());
	}

	public void undoMove(Chess chess) {
		//return moved piece to previous square
		movedPiece.setPos(from);
		//set to square piece to null
		to.setPiece(null);
		//remove move from piece
		movedPiece.getMoves().pop();
		
		if(takenPiece != null) {
			takenPiece.setTaken(false);
			takenPiece.getCurrentSquare().setPiece(takenPiece);
		}
		chess.getBoard().getUndone().add(this);
		chess.setWhitesTurn(!chess.isWhitesTurn());
	}

}
