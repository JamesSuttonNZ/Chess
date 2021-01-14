package Checkers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;

import Checkers.Board;
import Checkers.Move;
import Checkers.Player;
import Checkers.Square;

public class CheckerPiece {
	
	public Player owner;
	public Square currentSquare;
	public int x,y;
	public boolean taken = false;
	public Stack<Move> moves = new Stack<Move>();
	public ArrayList<Move> validMoves = new ArrayList<Move>();

	public CheckerPiece(Player owner, Square pos) {
		this.owner = owner;
		this.currentSquare = pos;
		setPos(currentSquare);
		owner.addPiece(this);
	}
	
	public String toString() {
		return "CheckerPiece";
	}

	public boolean validMoves(Board board) {
		validMoves.clear();
		//recurse northwest
		moveCheck(board, currentSquare, -1, -1);
		//recurse northeast
		moveCheck(board, currentSquare, 1, -1);
		//recurse southeast
		moveCheck(board, currentSquare, 1, 1);
		//recurse southwest
		moveCheck(board, currentSquare, -1, 1);
		return validMoves.size() > 0;
	}
	
	public void moveCheck(Board board, Square currentSquare, int moveRow, int moveCol) {
		//row and col of current square
		int row = currentSquare.getRow();
		int col = currentSquare.getCol();
		
		//check if gone off board
		if(row+moveRow >= 0 && row+moveRow < 8 && col+moveCol >= 0 && col+moveCol < 8) {
			
			//move square
			currentSquare = board.getSquare(row+moveRow, col+moveCol);
			CheckerPiece p = currentSquare.getPiece();
			if(p == null) {
				Move move = new Move(this,p,this.getPos(),currentSquare);
				if(move.validMove(board)) {
					validMoves.add(move);
				}
				//moveCheck(board,currentSquare,moveRow,moveCol);
			}
			else if(p != null && p.getOwner() != owner) {
				Move move = new Move(this,p,this.getPos(),currentSquare);
				if(move.validMove(board)) {
					validMoves.add(move);
				}
			}
			else {
				return;
			}
		}
		else {
			return;
		}
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
			if(owner.isWhite()) {
				g.setColor(Color.WHITE);
			}else {
				g.setColor(Color.BLACK);
			}
			g.fillOval(x+20, y+20, 60, 60);
		}
	}
	
	public ArrayList<Move> getValidMoves() {
		return validMoves;
	}

	public void setValidMoves(ArrayList<Move> validMoves) {
		this.validMoves = validMoves;
	}
}