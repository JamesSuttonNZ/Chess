package Chess;

import java.util.ArrayList;

import Chess.Pieces.Piece;

public class Player {
	
	public ArrayList<Piece> ownedPieces = new ArrayList<Piece>();
	public String name;
	
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addPiece(Piece piece) {
		ownedPieces.add(piece);
	}
}
