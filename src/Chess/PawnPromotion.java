package Chess;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Chess.Pieces.Bishop;
import Chess.Pieces.Knight;
import Chess.Pieces.Pawn;
import Chess.Pieces.Piece;
import Chess.Pieces.Queen;
import Chess.Pieces.Rook;
import UI.ChessPanel;

public class PawnPromotion extends Move {
	
	public Piece p;

	public PawnPromotion(Piece movedPiece, Piece takenPiece, Square from, Square to) {
		super(movedPiece, takenPiece, from, to);
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
		movedPiece.movePiece(from, to);
		
		if(takenPiece != null) {
			takenPiece.setTaken(true);
		}
		
		from.setPressed(false);
		movedPiece.drawValidMoves(false);
		
		cp.repaint();
		
		Object[] choices = {"Queen", "Rook", "Bishop", "Knight"};
		int choice = -1;
		while(choice == -1) {
			choice = JOptionPane.showOptionDialog(cp,"Please Choose Piece","Pawn Promotion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,choices,choices[0]);
		}
		
		switch(choice) {
			case 0:
				p = new Queen(movedPiece.getOwner(), to);
				cp.getChess().getPieces().add(p);
				break;
			case 1:
				p = new Rook(movedPiece.getOwner(), to);
				cp.getChess().getPieces().add(p);
				break;
			case 2:
				p = new Bishop(movedPiece.getOwner(), to);
				cp.getChess().getPieces().add(p);
				break;
			case 3:
				p = new Knight(movedPiece.getOwner(), to);
				cp.getChess().getPieces().add(p);
				break;
		}
		
		movedPiece.setTaken(true);
		movedPiece.drawValidMoves(false);
		movedPiece.getMoves().add(this);
		chess.getBoard().getMoves().add(this);
		cp.repaint();
		chess.endTurn();
	}

	public void undoMove(Chess chess) {
		//return moved piece to previous square
		movedPiece.movePiece(to, from);
		movedPiece.setTaken(false);
		
		p.setTaken(true);
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
		//remove piece from old square
		from.setPiece(null);
		
		//set piece to new square;
		movedPiece.movePiece(from, to);
		movedPiece.setTaken(true);
		
		p.getCurrentSquare().setPiece(p);
		p.setTaken(false);
			
		if(takenPiece != null) {
			takenPiece.setTaken(true);
		}
		
		movedPiece.getMoves().add(this);
		chess.getBoard().getMoves().add(this);
		chess.endTurn();
	}
	
	public String toString() {
		if(takenPiece == null) {
			return to.toString()+"="+p.toString();
		}
		char[] x = {'a','b','c','d','e','f','g','h'};
		return x[from.getCol()]+"x"+to.toString()+"="+p.toString();
	}

}
