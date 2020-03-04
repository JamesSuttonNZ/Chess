package Chess.Pieces;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public class King extends Piece {

	public King(Player owner, Square pos) {
		super(owner, pos);
	}
	
	public String toString() {
		return owner.getName()+" King";
	}

	@Override
	public boolean move(char x, int y, Board board) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
