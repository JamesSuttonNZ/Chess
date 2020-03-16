package Chess.Pieces;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public class Pawn extends Piece {
	
	public BufferedImage sprite = null;
	public boolean moved = false;
	
	public Pawn(Player owner, Square currentSquare) {
		super(owner, currentSquare);
		
		//get image
		if(owner.getName() == "White") {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/pawnW.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/pawnB.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean move(char x, int y, Board board) {
		String val = "ABCDEFGH";
		Character.toUpperCase(x);
		int col = val.indexOf(x);
		int row = y-1;
		if(owner.getName() == "White") {
			if(col == currentSquare.getCol() && (row-currentSquare.getRow() == 1 || row-currentSquare.getRow() == 2)) {
				board.getSquare(row, col).setPiece(this);
				currentSquare.setPiece(null);
				return true;
			}
			else {
				return false;
			}
		}
		else{
			if(col == currentSquare.getCol() && (row-currentSquare.getRow() == -1 || row-currentSquare.getRow() == -2)) {
				board.getSquare(row, col).setPiece(this);
				currentSquare.setPiece(null);
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

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public void movePiece(Square newSquare) {
		
		if(newSquare == currentSquare) {
			super.setPos(currentSquare);
		}
		else {
		
			//get move amount
			int moveRow = newSquare.getRow() - currentSquare.getRow();
			int moveCol = newSquare.getCol() - currentSquare.getCol();
			
			//get piece at new square
			Piece newSquarePiece = newSquare.getPiece();
			
			//white piece
			if(getOwner().getName() == "White") {
				//allowed move check
				if((moveRow == 1 || (moveRow == 2 && !moved)) && moveCol == 0) {
					moveCheck(newSquare, newSquarePiece, "Black");
				}
				else {
					//reset position
					super.setPos(currentSquare);
				}
			}
			//black piece
			else {
				//allowed move check
				if((moveRow == -1 || (moveRow == -2 && !moved)) && moveCol == 0) {
					moveCheck(newSquare, newSquarePiece, "White");
				}
				else {
					//reset position
					super.setPos(currentSquare);
				}
			}
		}
	}

	private void moveCheck(Square newSquare, Piece newSquarePiece, String player) {
		//taking other players piece
		if(newSquarePiece != null && newSquarePiece.getOwner().getName() == player) {
			//take occupying piece
			newSquarePiece.setTaken(true);
			updatePosition(newSquare);
		}
		//take empty square
		else if(newSquarePiece == null) {
			updatePosition(newSquare);
		}
		//reset position
		else {
			super.setPos(currentSquare);
		}
	}

	private void updatePosition(Square newSquare) {
		//remove piece from old square
		currentSquare.setPiece(null);
		//move piece
		newSquare.setPiece(this);
		super.setPos(newSquare);
		moved = true;
	}
}
