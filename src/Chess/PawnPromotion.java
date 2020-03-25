package Chess;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Chess.Pieces.Bishop;
import Chess.Pieces.Knight;
import Chess.Pieces.Piece;
import Chess.Pieces.Queen;
import Chess.Pieces.Rook;
import UI.ChessPanel;

public class PawnPromotion extends Move {
	
	public ChessPanel cp;
	public Piece p;

	public PawnPromotion(Piece movedPiece, Piece takenPiece, Square from, Square to, ChessPanel cp) {
		super(movedPiece, takenPiece, from, to);
		this.cp = cp;
	}
	
	public void executeMove(Chess chess) {
		//remove piece from old square
		from.setPiece(null);
		
		//set piece to new square;
		movedPiece.setPos(to);
		if(takenPiece != null) {
			takenPiece.setTaken(true);
		}
		cp.repaint();
		
		Object[] choices = {"Queen", "Rook", "Bishop", "Knight"};
		int choice = -1;
		while(choice == -1) {
			choice = JOptionPane.showOptionDialog(cp,"Please Choose Piece","Pawn Promotion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,choices,choices[0]);
		}
		
		switch(choice) {
			case 0:
				p = new Queen(movedPiece.getOwner(), to);
				cp.chess.pieces.add(p);
				break;
			case 1:
				p = new Rook(movedPiece.getOwner(), to);
				cp.chess.pieces.add(p);
				break;
			case 2:
				p = new Bishop(movedPiece.getOwner(), to);
				cp.chess.pieces.add(p);
				break;
			case 3:
				p = new Knight(movedPiece.getOwner(), to);
				cp.chess.pieces.add(p);
				break;
		}
		
		movedPiece.setTaken(true);
		
		movedPiece.getMoves().add(this);
		chess.getBoard().getMoves().add(this);
		chess.setWhitesTurn(!chess.isWhitesTurn());
	}

	public void undoMove(Chess chess) {
		//return moved piece to previous square
		movedPiece.setPos(from);
		movedPiece.setTaken(false);
		//set to square piece to null
		to.setPiece(null);
		p.setTaken(true);
		//remove move from piece
		movedPiece.getMoves().pop();
		
		if(takenPiece != null) {
			takenPiece.setTaken(false);
			takenPiece.getCurrentSquare().setPiece(takenPiece);
		}
		chess.getBoard().getUndone().add(this);
		chess.setWhitesTurn(!chess.isWhitesTurn());
	}
	
	public void redoMove(Chess chess) {
		//remove piece from old square
		from.setPiece(null);
		
		//set piece to new square;
		movedPiece.setPos(to);
		p.setPos(to);
		p.setTaken(false);
		
		movedPiece.setTaken(true);
		
		if(takenPiece != null) {
			takenPiece.setTaken(true);
		}
		movedPiece.getMoves().add(this);
		chess.getBoard().getMoves().add(this);
		chess.setWhitesTurn(!chess.isWhitesTurn());
	}

}
