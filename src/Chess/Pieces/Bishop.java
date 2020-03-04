package Chess.Pieces;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public class Bishop extends Piece {

	public Bishop(Player owner, Square pos) {
		super(owner, pos);
	}
	
	public String toString() {
		return owner.getName()+" Bishop";
	}

	@Override
	public boolean move(char x, int y, Board board) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
