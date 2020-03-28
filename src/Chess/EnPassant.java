package Chess;

import Chess.Pieces.Pawn;
import Chess.Pieces.Piece;
import UI.ChessPanel;

public class EnPassant extends Move {
	
	public Square enPassant;
	
	public EnPassant(Piece movedPiece, Piece takenPiece, Square from, Square to, Square enPassant) {
		super(movedPiece, takenPiece, from, to);
		this.enPassant = enPassant;
	}
	
	public void executeMove(Chess chess, ChessPanel cp) {
		//set piece to new square
		movedPiece.movePiece(from, to);
		
		if(takenPiece != null) {
			takenPiece.setTaken(true);
			enPassant.setPiece(null);
		}
		movedPiece.getMoves().add(this);
		chess.getBoard().getMoves().add(this);
		chess.endTurn();
	}

	public void undoMove(Chess chess) {
		//return moved piece to previous square
		movedPiece.movePiece(to,from);
		//remove move from piece
		movedPiece.getMoves().pop();
		
		if(takenPiece != null) {
			takenPiece.setTaken(false);
			takenPiece.getCurrentSquare().setPiece(takenPiece);
		}
		chess.getBoard().getUndone().add(this);
		chess.endTurn();
	}
	
	public void redoMove(Chess chess, ChessPanel cp) {
		executeMove(chess,cp);
	}
	
	public String toString() {
		char[] x = {'a','b','c','d','e','f','g','h'};
		return x[from.getCol()]+"x"+to.toString()+"e.p";
	}
}
