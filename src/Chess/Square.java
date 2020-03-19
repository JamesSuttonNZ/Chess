package Chess;

import java.awt.Color;
import java.awt.Graphics;

import Chess.Pieces.Piece;

public class Square {
	
	public int row, col, x, y;
	public boolean white, pressed, valid;
	public Piece piece;
	public Color color;
	
	public Square(boolean white, int row, int col, int x, int y) {
		this.white = white;
		this.row = row;
		this.col = col;
		this.x = x;
		this.y = y;
		if(white) {
			color = new Color(225,200,175);
		}
		else {
			color = new Color(150,100,100);
		}
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

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
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
		if(pressed) {
			g.setColor(new Color(255,200,75));
			g.fillRect(x, y, 100, 100);
		}
		if(valid && piece != null) {
			g.setColor(new Color(175,225,0));
			g.fillRect(x, y, 100, 100);
		}
		g.setColor(color);
		if(pressed || valid && piece != null) {
			g.fillRect(x+7, y+7, 86, 86);
		}
		else {
			g.fillRect(x, y, 100, 100);
		}
		if(valid && piece == null) {
			g.setColor(new Color(175,225,0));
			g.fillOval(x+40, y+40, 20, 20);
		}
	}

}
