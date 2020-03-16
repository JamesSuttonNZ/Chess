package Chess.Pieces;

import java.awt.Graphics;
import java.awt.Image;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public abstract class Piece {
	
	public Player owner;
	public Square currentSquare;
	public int x,y;
	public boolean taken = false;
	
	public Piece(Player owner, Square currentSquare) {
		this.owner = owner;
		this.currentSquare = currentSquare;
		this.x = currentSquare.getX();
		this.y = currentSquare.getY();
		currentSquare.setPiece(this);
		owner.addPiece(this);
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Square getPos() {
		return currentSquare;
	}

	public void setPos(Square pos) {
		this.currentSquare = pos;
		this.x = pos.getX();
		this.y = pos.getY();
	}
	
	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
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
		if(!taken) {
			g.drawImage(this.getSprite(), x, y, 100, 100, null);
		}
	}

	public abstract void movePiece(Square newSquare);	
}