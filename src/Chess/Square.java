package Chess;

import Chess.Pieces.Piece;

public class Square {
	
	public int row;
	public int col;
	public Piece piece;

	public Square(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String toString() {
		char[] x = {'A','B','C','D','E','F','G','H'};
		if(piece != null) {
			return ""+x[col]+(row+1)+" "+piece.toString();
		}
		else {
			return ""+x[col]+(row+1);
		}
	}

}
