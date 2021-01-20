package Chess;

import Chess.Pieces.Pawn;
import Chess.Pieces.Piece;
import UI.ChessPanel;

public class EnPassant extends Move {
	
	private Square enPassant;
	
	public EnPassant(Piece movedPiece, Piece takenPiece, Square from, Square to, Square enPassant) {
		super(movedPiece, takenPiece, from, to);
		this.enPassant = enPassant;
	}
	
	public boolean validMove(Board board) {
		//move piece
		movedPiece.movePiece(from,to);
		if(takenPiece != null) {
			enPassant.setPiece(null);
		}
		
		//test for check
		Boolean check = movedPiece.getOwner().inCheck(board);
		
		//undo move
		movedPiece.movePiece(to, from);
		if(takenPiece != null) {
			enPassant.setPiece(takenPiece);
		}
		
		return !check;
	}
	
	public void executeMove(Chess chess, ChessPanel cp) {
		//set piece to new square
		movedPiece.movePiece(from, to);
		
		if(takenPiece != null) {
			takenPiece.setTaken(true);
			enPassant.setPiece(null);
		}
		movedPiece.getMoves().add(this);
		
		movedPiece.drawValidMoves(false);
		chess.getBoard().getMoves().add(this);
		cp.repaint();
		chess.endTurn();
	}

	public void undoMove(Chess chess) {
		//return moved piece to previous square
		movedPiece.movePiece(to,from);
		//remove move from piece
		movedPiece.getMoves().pop();
		
		if(takenPiece != null) {
			takenPiece.setTaken(false);
			enPassant.setPiece(takenPiece);
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
