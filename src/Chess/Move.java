package Chess;

import Chess.Pieces.Pawn;
import Chess.Pieces.Piece;
import UI.ChessPanel;

public class Move {

	public Piece movedPiece, takenPiece;
	public Square from, to;
	
	public Move(Piece movedPiece, Piece takenPiece, Square from, Square to) {
		this.movedPiece = movedPiece;
		this.takenPiece = takenPiece;
		this.from = from;
		this.to = to;
	}
	
	public boolean validMove(Board board) {
		//move piece
		movedPiece.movePiece(from,to);
		
		//test for check
		Boolean check = movedPiece.getOwner().inCheck(board);
		
		//undo move
		movedPiece.movePiece(to, from);
		if(takenPiece != null) {
			takenPiece.getCurrentSquare().setPiece(takenPiece);
		}
		
		return !check;
	}
	
	public void executeMove(Chess chess, ChessPanel cp) {
		//set piece to new square;
		movedPiece.movePiece(from,to);
		
		if(takenPiece != null) {
			takenPiece.setTaken(true);
		}
		movedPiece.getMoves().add(this);
		chess.getBoard().getMoves().add(this);
		chess.endTurn();
	}

	public void undoMove(Chess chess) {
		//return moved piece to previous square
		movedPiece.movePiece(to, from);
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
		executeMove(chess, cp);
	}
	
	public String toString() {
		if(takenPiece == null) {
			return movedPiece.toString()+to.toString();
		}
		if(movedPiece instanceof Pawn) {
			char[] x = {'a','b','c','d','e','f','g','h'};
			return x[from.getCol()]+"x"+to.toString();
		}
		return movedPiece.toString()+"x"+to.toString();
	}
	
	public void setValid(Boolean valid) {
		to.setValid(valid);
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
