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
	
	public boolean validMove(Board board) {
		//move piece
		movedPiece.movePiece(from,to);
		//set piece to new square
		takenPiece.movePiece(rookStartPos, rookNewPos);
		
		//test for check
		Boolean check = movedPiece.getOwner().inCheck(board);
		
		//undo move
		//return moved piece to previous square
		movedPiece.movePiece(to, from);
		takenPiece.movePiece(rookNewPos, rookStartPos);
		
		return !check;
	}
	
	public void executeMove(Chess chess, ChessPanel cp) {;
		//set piece to new square
		movedPiece.movePiece(from, to);

		//set piece to new square
		takenPiece.movePiece(rookStartPos, rookNewPos);
		
		movedPiece.getMoves().add(this);
		chess.getBoard().getMoves().add(this);
		chess.endTurn();
	}

	public void undoMove(Chess chess) {
		//return moved piece to previous square
		movedPiece.movePiece(to, from);
		takenPiece.movePiece(rookNewPos, rookStartPos);

		//remove move from piece
		movedPiece.getMoves().pop();
		
		chess.getBoard().getUndone().add(this);
		chess.endTurn();
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
