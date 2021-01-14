package Checkers;

import java.util.ArrayList;

import Checkers.Board;
import Checkers.CheckerPiece;

public class Player {
	
	public ArrayList<CheckerPiece> ownedPieces = new ArrayList<CheckerPiece>();
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
	
	public boolean calculateMoves(Board b) {
		Boolean validMove = false;
		for(CheckerPiece p : ownedPieces) {
			if(!p.isTaken()) {
				if(p.validMoves(b)) validMove = true;
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

	public ArrayList<CheckerPiece> getOwnedPieces() {
		return ownedPieces;
	}

	public void setOwnedPieces(ArrayList<CheckerPiece> ownedPieces) {
		this.ownedPieces = ownedPieces;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}

	public void addPiece(CheckerPiece piece) {
		ownedPieces.add(piece);
	}

}
