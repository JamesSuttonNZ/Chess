package Chess.Pieces;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public class Rook extends Piece{

	public Rook(Player owner, Square pos) {
		super(owner, pos);
	}
	
	public String toString() {
		return owner.getName()+" Rook";
	}

	@Override
	public boolean move(char x, int y, Board board) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
