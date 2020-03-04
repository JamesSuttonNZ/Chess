package Chess.Pieces;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public class Pawn extends Piece {
	
	public Pawn(Player owner, Square pos) {
		super(owner, pos);
	}
	
	public boolean move(char x, int y, Board board) {
		String val = "ABCDEFGH";
		Character.toUpperCase(x);
		int col = val.indexOf(x);
		int row = y-1;
		if(owner.getName() == "White") {
			if(col == pos.getCol() && (row-pos.getRow() == 1 || row-pos.getRow() == 2)) {
				board.getSquare(row, col).setPiece(this);
				pos.setPiece(null);
				return true;
			}
			else {
				return false;
			}
		}
		else{
			if(col == pos.getCol() && (row-pos.getRow() == -1 || row-pos.getRow() == -2)) {
				board.getSquare(row, col).setPiece(this);
				pos.setPiece(null);
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public String toString() {
		return owner.getName()+" Pawn";
	}
}
