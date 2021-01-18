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
	
	public void calculateMoves(Board b) {
		boolean jumpAvailable = false;
		
		for(CheckerPiece p : ownedPieces) {
			
			if(p.checkForJumps(b) && !p.isTaken()) {
				jumpAvailable = true;
			}
		}
		
		for(CheckerPiece p : ownedPieces) {
			if(!p.isTaken()) {
				p.validMoves(b, jumpAvailable);
			}
		}
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
