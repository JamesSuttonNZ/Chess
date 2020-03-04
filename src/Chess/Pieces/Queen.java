package Chess.Pieces;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public class Queen extends Piece {
	
	public Queen(Player owner, Square pos) {
		super(owner, pos);
	}
	
	public String toString() {
		return owner.getName()+" Queen";
	}

	@Override
	public boolean move(char x, int y, Board board) {
		// TODO Auto-generated method stub
		return false;
	}

}
