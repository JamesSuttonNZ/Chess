package Chess.Pieces;

import java.awt.Graphics;
import java.awt.Image;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public abstract class Piece {
	
	public Player owner;
	public Square pos;
	public int x,y;
	
	public Piece(Player owner, Square pos) {
		this.owner = owner;
		this.pos = pos;
		this.x = pos.getX();
		this.y = pos.getY();
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

	public abstract boolean move(char x, int y, Board board);
	
	public abstract Image getSprite();
	
	public abstract String toString();

	public void drawPiece(Graphics g) {
		g.drawImage(this.getSprite(), x, y, 100, 100, null);
	}	
}
