package Chess.Pieces;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public abstract class Piece {
	
	public Player owner;
	public Square pos;
	
	public Piece(Player owner, Square pos) {
		this.owner = owner;
		this.pos = pos;
		pos.setPiece(this);
		owner.addPiece(this);
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Square getPos() {
		return pos;
	}

	public void setPos(Square pos) {
		this.pos = pos;
	}
	
	public abstract boolean move(char x, int y, Board board);
	
	public abstract String toString();	
}
