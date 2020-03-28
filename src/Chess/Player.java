package Chess;

import java.util.ArrayList;

import Chess.Pieces.King;
import Chess.Pieces.Piece;

public class Player {
	
	public ArrayList<Piece> ownedPieces = new ArrayList<Piece>();
	public King king;
	public String name;
	public boolean white;
	
	public Player(String name) {
		this.name = name;
		if(name == "White") {
			this.white = true;
		}
		else {
			this.white = false;
		}
	}
	
	public void calculateMoves(Board b) {
		for(Piece p : ownedPieces) {
			if(!p.isTaken()) {
				p.validMoves(b);
			}
		}
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

}
