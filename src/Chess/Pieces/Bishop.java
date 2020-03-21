package Chess.Pieces;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public class Bishop extends Piece {
	
	public BufferedImage sprite = null;

	public Bishop(Player owner, Square pos) {
		super(owner, pos);
		
		//get image
		if(owner.getName() == "White") {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/bishopW.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/bishopB.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return owner.getName()+" Bishop";
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
			if(Math.abs(moveRow) == Math.abs(moveCol)) {
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

	@Override
	public void cancelMove() {
		super.setPos(currentSquare);
	}

	@Override
	public ArrayList<Square> validMoves(Square[][] board, Square selectedSquare) {
		ArrayList<Square> vm = new ArrayList<Square>();
		//recurse northwest
		recursiveCheck(board, selectedSquare, -1, -1, vm);
		//recurse northeast
		recursiveCheck(board, selectedSquare, 1, -1, vm);
		//recurse southeast
		recursiveCheck(board, selectedSquare, 1, 1, vm);
		//recurse southwest
		recursiveCheck(board, selectedSquare, -1, 1, vm);
		return vm;
	}
	
	public void recursiveCheck(Square[][] board, Square currentSquare, int moveRow, int moveCol, ArrayList<Square> vm) {
		int row = currentSquare.getRow();
		int col = currentSquare.getCol();
		
		Piece p = currentSquare.getPiece();
		
		if(p != null && p.getOwner().getName() != owner.getName()) {
			return;
		}
		
		if(row+moveRow >= 0 && row+moveRow < 8 && col+moveCol >= 0 && col+moveCol < 8) {
			currentSquare = board[row+moveRow][col+moveCol];
			if(valid(currentSquare)) {
				vm.add(currentSquare);
				recursiveCheck(board,currentSquare,moveRow,moveCol,vm);
			}
			else {
				return;
			}
		}
		else {
			return;
		}
	}
	
	public boolean valid(Square s) {
		//get piece at new square
		Piece p = s.getPiece();
		
		if(p != null && p.getOwner().getName() != owner.getName()) {
			return true;
		}
//		else if(p != null && p.getOwner().getName() == owner.getName()) {
//			return false;
//		}
		//take empty square
		else if(p == null) {
			return true;
		}
		else {
			return false;
		}
	}
}
