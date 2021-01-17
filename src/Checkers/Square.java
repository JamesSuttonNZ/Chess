package Checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Checkers.CheckerPiece;

public class Square {
	public int row, col, x, y, num;
	public boolean white, pressed, valid;
	public CheckerPiece piece;
	public Color color;
	public Color oppColor;
	
	public Square(boolean white, int row, int col, int x, int y, int num) {
		this.white = white;
		this.row = row;
		this.col = col;
		this.x = x;
		this.y = y;
		this.num = num;
		if(white) {
			color = new Color(225,200,175);
			oppColor = new Color(150,100,100);
		}
		else {
			color = new Color(150,100,100);
			oppColor = new Color(225,200,175);
		}
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public CheckerPiece getPiece() {
		return piece;
	}

	public void setPiece(CheckerPiece piece) {
		this.piece = piece;
	}
	
	public boolean isEmpty() {
		if(piece == null) {
			return true;
		}else {
			return false;
		}
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
		return Integer.toString(num);
	}

	public void drawSquare(Graphics g) {
		
		//outline pressed square orange
		if(pressed) {
			g.setColor(new Color(255,200,75));
			g.fillRect(x, y, 100, 100);
		}
		
		//outline takeable piece green
		if(valid && piece != null) {
			g.setColor(new Color(175,225,0));
			g.fillRect(x, y, 100, 100);
		}
		
		g.setColor(color);
		//draw square
		if(pressed || valid && piece != null) {
			g.fillRect(x+7, y+7, 86, 86);
		}
		else {
			g.fillRect(x, y, 100, 100);
		}
		
		//draw green circle at valid move square
		if(valid && piece == null) {
			g.setColor(new Color(175,225,0));
			g.fillOval(x+40, y+40, 20, 20);
		}
		
		g.setColor(oppColor);
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		//letters
//		if(row == 7) {
//			String[] c = {"a","b","c","d","e","f","g","h"};
//			g.drawString(c[col], x+87, y+95);
//		}
		//numbers
//		if(col == 0) {
//			g.drawString(Integer.toString(8-row), x+2, y+20);
//		}
//		
		//draw black square number
		if(!white) {
			g.drawString(Integer.toString(num), x+2, y+20);
		}
	}
}
