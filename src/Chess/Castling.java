package Chess;

import Chess.Pieces.Pawn;
import Chess.Pieces.Piece;
import UI.ChessPanel;

public class Castling extends Move {
	
	public Square rookNewPos, rookStartPos;

	public Castling(Piece movedPiece, Piece takenPiece, Square from, Square to, Square rookNewPos, Square rookStartPos) {
		super(movedPiece, takenPiece, from, to);
		this.rookNewPos = rookNewPos;
		this.rookStartPos = rookStartPos;
	}
	
	public void executeMove(Chess chess, ChessPanel cp) {
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

	public void undoMove(Chess chess) {
		//return moved piece to previous square
		movedPiece.setPos(from);
		takenPiece.setPos(rookStartPos);
		rookNewPos.setPiece(null);
		to.setPiece(null);
		//remove move from piece
		movedPiece.getMoves().pop();
		
		chess.getBoard().getUndone().add(this);
		chess.setWhitesTurn(!chess.isWhitesTurn());
	}

	public void redoMove(Chess chess, ChessPanel cp) {
		executeMove(chess, cp);
	}
	
	public String toString() {
		if(rookStartPos.getCol() == 0) {
			return "0-0-0";
		}
		return "0-0";
	}

}
