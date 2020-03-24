package Chess;

import Chess.Pieces.Piece;

public class Castling extends Move {
	
	public Square rookNewPos, rookStartPos;

	public Castling(Piece movedPiece, Piece takenPiece, Square from, Square to, Square rookNewPos, Square rookStartPos) {
		super(movedPiece, takenPiece, from, to);
		this.rookNewPos = rookNewPos;
		this.rookStartPos = rookStartPos;
	}
	
	public void executeMove(Chess chess) {
		//remove piece from old square
		from.setPiece(null);
		//set piece to new square
		movedPiece.setPos(to);

		//remove piece from old square
		rookStartPos.setPiece(null);
		//set piece to new square
		takenPiece.setPos(rookNewPos);
		
		movedPiece.getMoves().add(this);
		chess.getBoard().getMoves().add(this);
		chess.setWhitesTurn(!chess.isWhitesTurn());
	}

	public void undoMove() {
		//return moved piece to previous square
		movedPiece.setPos(from);
		takenPiece.setPos(rookStartPos);
		rookNewPos.setPiece(null);
		to.setPiece(null);
		//remove move from piece
		movedPiece.getMoves().pop();
	}

}
