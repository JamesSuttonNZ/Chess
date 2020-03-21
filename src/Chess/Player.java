package Chess;

import java.util.ArrayList;

import Chess.Pieces.Piece;

public class Player {
	
	public ArrayList<Piece> ownedPieces = new ArrayList<Piece>();
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
}
