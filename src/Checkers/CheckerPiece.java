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
	
	private Player owner;
	private Square currentSquare;
	//co-ords on graphics pane
	private int x,y;
	private boolean taken = false;
	private boolean king = false;
	private BufferedImage sprite = null;
	
	//moves since start of game
	private Stack<Move> moves = new Stack<Move>();
	//valid moves this turn
	private ArrayList<Move> validMoves = new ArrayList<Move>();

	public CheckerPiece(Player owner, Square pos) {
		this.owner = owner;
		this.currentSquare = pos;
		setPos(currentSquare);
		owner.addPiece(this);
		//get image
		if(owner.getName() == "White") {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/kingB.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/kingW.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return "Checker";
	}

	public boolean validMoves(Board board, boolean jumpAvailable) {
		validMoves.clear();
		if(owner.isWhite() || king) {
			//recurse northwest
			moveCheck(board, currentSquare, -1, -1, jumpAvailable);
			//recurse northeast
			moveCheck(board, currentSquare, -1, 1, jumpAvailable);
		} 
		if(!owner.isWhite() || king) {
			//recurse southeast
			moveCheck(board, currentSquare, 1, 1, jumpAvailable);
			//recurse southwest
			moveCheck(board, currentSquare, 1, -1, jumpAvailable);
		}
		return validMoves.size() > 0;
	}
	
	public void moveCheck(Board board, Square currentSquare, int moveRow, int moveCol, boolean jumpAvailable) {
		//row and col of current square
		int row = currentSquare.getRow();
		int col = currentSquare.getCol();
		
		//check if gone off board
		if(inBounds(row,col,moveRow,moveCol)) {
			
			//move square
			currentSquare = board.getSquare(row+moveRow, col+moveCol);
			
			CheckerPiece p = currentSquare.getPiece();
			
			if(p == null && !jumpAvailable) {
				Move move = new Move(this, this.getPos(), currentSquare);
				validMoves.add(move);

			}
			else if(p != null && p.getOwner() != owner) {
				
				//check hasnt gone off board
				if(inBounds(row,col,moveRow*2,moveCol*2)) {
					
					currentSquare = board.getSquare(row+(moveRow*2), col+(moveCol*2));
					
					if(currentSquare.isEmpty()) {
						Move move = new Move(this,p,this.getPos(),currentSquare);
						
						validMoves.add(move);
					}
				}		
			}
		}
	}
	
	public boolean checkForJumps(Board board) {
		
		int row = currentSquare.getRow();
		int col = currentSquare.getCol();
		
		if(owner.isWhite() || king) {
			if(checkJump(board,row,col,-1,1) || checkJump(board,row,col,-1,-1)) {
				return true;
			}
		}
		if(!owner.isWhite() || king) {
			if(checkJump(board,row,col,1,1) || checkJump(board,row,col,1,-1)) {
				return true;
			}
		}
		return false;
	}
	

	public boolean checkJump(Board board, int row, int col, int moveRow, int moveCol) {
		
		if(inBounds(row,col, moveRow*2, moveCol*2)) {
			Square s = board.getSquare(row+moveRow, col+moveCol);
			CheckerPiece p = s.getPiece();
			if(p != null && p.getOwner() != owner) {
				Square s2 = board.getSquare(row+(moveRow*2), col+(moveCol*2));
				if(s2.isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}
		
	public boolean inBounds(int row, int col, int moveRow, int moveCol) {
		if(row+moveRow >= 0 && row+moveRow < 8 && col+moveCol >= 0 && col+moveCol < 8) {
			return true;
		}
		else {
			return false;
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
		if(owner.isWhite()) {
			if(to.getRow() == 0) {
				king = true;
			}
		}
		else if(!owner.isWhite()) {
			if(to.getRow() == 7) {
				king = true;
			}
		}
		
		//draw at correct position
		this.x = to.getX();
		this.y = to.getY();
	}
	
	public void resetPos() {
		this.x = currentSquare.getX();
		this.y = currentSquare.getY();
	}
	
	public void taken(boolean taken) {
		if(taken) {
			this.taken = true;
			this.currentSquare.setPiece(null);
		}
		else if(!taken) {
			this.taken = false;
			this.currentSquare.setPiece(this);
		}
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
			if(king) {
				g.fillOval(x+20, y+20, 60, 60);
				g.drawImage(sprite, x+25, y+25, 50, 50, null);
			}
			
			
		}
	}
	
	public ArrayList<Move> getValidMoves() {
		return validMoves;
	}

	public void setValidMoves(ArrayList<Move> validMoves) {
		this.validMoves = validMoves;
	}

	public boolean isKing() {
		return king;
	}

	public void setKing(boolean king) {
		this.king = king;
	}
}
