package Chess;

import java.util.ArrayList;

import Chess.Pieces.King;
import Chess.Pieces.Piece;

public class Player {
	
	private ArrayList<Piece> ownedPieces = new ArrayList<Piece>();
	private King king;
	private String name;
	private boolean white, check;
	
	public Player(String name) {
		this.name = name;
		if(name == "White") {
			this.white = true;
		}
		else {
			this.white = false;
		}
	}
	
	public boolean calculateMoves(Chess chess) {
		Boolean validMove = false;
		for(Piece p : ownedPieces) {
			if(!p.isTaken()) {
				if(p.validMoves(chess)) validMove = true;
			}
		}
		return validMove;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Piece> getOwnedPieces() {
		return ownedPieces;
	}

	public void setOwnedPieces(ArrayList<Piece> ownedPieces) {
		this.ownedPieces = ownedPieces;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}

	public void addPiece(Piece piece) {
		ownedPieces.add(piece);
	}

	public King getKing() {
		return king;
	}

	public void setKing(King king) {
		this.king = king;
	}

	public boolean inCheck(Board board) {
		return king.inCheck(board);
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}
