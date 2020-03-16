package Chess.Pieces;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public class Rook extends Piece{

	public BufferedImage sprite = null;
	
	public Rook(Player owner, Square pos) {
		super(owner, pos);
		
		//get image
		if(owner.getName() == "White") {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/rookW.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/rookB.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return owner.getName()+" Rook";
	}

	@Override
	public boolean move(char x, int y, Board board) {
		// TODO Auto-generated method stub
		return false;
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
			
			//allowed move check
			if((moveRow == 0 && moveCol != 0) || (moveRow != 0 && moveCol == 0) ) {
				if(owner.getName() == "White") {
					moveCheck(newSquare, newSquarePiece, "Black");
				}
				else {
					moveCheck(newSquare, newSquarePiece, "White");
				}
			}
			else {
				//reset position
				super.setPos(currentSquare);
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
	}
	
}
