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

public class Knight extends Piece{
	
	public BufferedImage sprite = null;

	public Knight(Player owner, Square pos) {
		super(owner, pos);

		//get image
		if(owner.getName() == "White") {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/knightW.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/knightB.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return owner.getName()+" Knight";
	}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public Piece movePiece(Square newSquare) {
		return moveCheck(newSquare, owner.getName());
	}
	
	private Piece moveCheck(Square newSquare, String player) {
		//get piece at new square
		Piece newSquarePiece = newSquare.getPiece();
		
		//taking other players piece
		if(newSquarePiece != null && newSquarePiece.getOwner().getName() != player) {
			//take occupying piece
			newSquarePiece.setTaken(true);
			updatePosition(newSquare);
		}
		//take empty square
		else if(newSquarePiece == null) {
			updatePosition(newSquare);
		}
		
		return newSquarePiece;
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
		//recurse left
		recursiveCheck(board, selectedSquare, 2, 1, vm, 1);
		//recurse right
		recursiveCheck(board, selectedSquare, 2, -1, vm, 1);
		//recurse up
		recursiveCheck(board, selectedSquare, -2, 1, vm, 1);
		//recurse down
		recursiveCheck(board, selectedSquare, -2, -1, vm, 1);
		//
		recursiveCheck(board, selectedSquare, 1, 2, vm, 1);
		//recurse right
		recursiveCheck(board, selectedSquare, -1, 2, vm, 1);
		//recurse up
		recursiveCheck(board, selectedSquare, 1, -2, vm, 1);
		//recurse down
		recursiveCheck(board, selectedSquare, -1, -2, vm, 1);
		return vm;
	}
	
	public void recursiveCheck(Square[][] board, Square currentSquare, int moveRow, int moveCol, ArrayList<Square> vm, int moves) {
		if(moves == 0) {
			return;
		}
		
		int row = currentSquare.getRow();
		int col = currentSquare.getCol();
		
		Piece p = currentSquare.getPiece();
		
		if(p != null && p.getOwner().getName() != owner.getName()) {
			return;
		}
		
		if(row+moveRow >= 0 && row+moveRow < 8 && col+moveCol >= 0 && col+moveCol < 8) {
			currentSquare = board[row+moveRow][col+moveCol];
			//check diagonal
			if(valid(currentSquare)) {
				vm.add(currentSquare);
				recursiveCheck(board,currentSquare,moveRow,moveCol,vm,moves-1);
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
