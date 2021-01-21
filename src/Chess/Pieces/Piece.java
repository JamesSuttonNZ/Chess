package Chess.Pieces;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import Chess.Board;
import Chess.Chess;
import Chess.Move;
import Chess.Player;
import Chess.Square;
import UI.ChessPanel;

public abstract class Piece {
	
	public Player owner;
	public Square currentSquare;
	public int x,y;
	public boolean taken = false;
	public Stack<Move> moves = new Stack<Move>();
	public ArrayList<Move> validMoves = new ArrayList<Move>();
	
	public Piece(Player owner, Square currentSquare) {
		this.owner = owner;
		this.currentSquare = currentSquare;
		setPos(currentSquare);
		owner.addPiece(this);
	}
	
	public void drawValidMoves(Boolean valid) {
		for(Move m : validMoves) {
			m.setValid(valid);
		}
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
		//move piece
		pos.setPiece(this);
		this.currentSquare = pos;
		
		//draw at correct position
		this.x = pos.getX();
		this.y = pos.getY();
	}

	public void movePiece(Square from, Square to) {
		//move piece
		from.setPiece(null);
		to.setPiece(this);
		this.currentSquare = to;
		
		//draw at correct position
		this.x = to.getX();
		this.y = to.getY();
	}
	
	public void resetPos() {
		this.x = currentSquare.getX();
		this.y = currentSquare.getY();
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

	public Square getCurrentSquare() {
		return currentSquare;
	}

	public void setCurrentSquare(Square currentSquare) {
		this.currentSquare = currentSquare;
	}

	public Stack<Move> getMoves() {
		return moves;
	}

	public void setMoves(Stack<Move> moves) {
		this.moves = moves;
	}

	public void drawPiece(Graphics g) {
		if(!taken) {
			g.drawImage(this.getSprite(), x, y, 100, 100, null);
		}
	}
	
	public ArrayList<Move> getValidMoves() {
		return validMoves;
	}

	public void setValidMoves(ArrayList<Move> validMoves) {
		this.validMoves = validMoves;
	}

	public abstract Image getSprite();
	
	public abstract String toString();

	public abstract boolean validMoves(Chess chess);	
}
