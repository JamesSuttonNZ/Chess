package Chess;

import java.awt.Color;
import java.awt.Graphics;

import Chess.Pieces.Piece;

public class Square {
	
	public int row, col, x, y;
	public boolean white, pressed;
	public Piece piece;

	public Square(boolean white, int row, int col, int x, int y) {
		this.white = white;
		this.row = row;
		this.col = col;
		this.x = x;
		this.y = y;
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
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

	public void drawSquare(Graphics g) {
		g.setColor(new Color(255,200,125));
		g.fillRect(x, y, 100, 100);
		if(white) {
			g.setColor(new Color(225,200,175));
		} else {
			g.setColor(new Color(150,100,100));
		}
		if(pressed) {
			g.fillRect(x+5, y+5, 90, 90);
		}
		else {
			g.fillRect(x, y, 100, 100);
		}
	}

}
