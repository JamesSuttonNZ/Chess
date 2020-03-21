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
		moveCheck(newSquare, owner.getName());
		moved = true;
	}
	
	private void moveCheck(Square newSquare, String player) {
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
		if(owner.getName() == "Black") {
			if(!moved) {
				recursiveCheck(board, selectedSquare, 1, 0, vm, 2);
			}
			else {
				recursiveCheck(board, selectedSquare, 1, 0, vm, 1);
			}
			recursiveCheck(board, selectedSquare, 1, 1, vm, 1);
			recursiveCheck(board, selectedSquare, 1, -1, vm, 1);
		}
		else {
			if(!moved) {
				recursiveCheck(board, selectedSquare, -1, 0, vm, 2);
			}
			else {
				recursiveCheck(board, selectedSquare, -1, 0, vm, 1);
			}
			recursiveCheck(board, selectedSquare, -1, 1, vm, 1);
			recursiveCheck(board, selectedSquare, -1, -1, vm, 1);
		}
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
			if(moveRow != 0 && moveCol != 0) {
				if(valid(currentSquare)) {
					vm.add(currentSquare);
					recursiveCheck(board,currentSquare,moveRow,moveCol,vm,moves-1);
				}
				else {
					return;
				}
			}
			else {
				if(validForward(currentSquare)) {
					vm.add(currentSquare);
					recursiveCheck(board,currentSquare,moveRow,moveCol,vm,moves-1);
				}
				else {
					return;
				}
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
		else {
			return false;
		}
	}
	
	public boolean validForward(Square s) {
		//get piece at new square
		Piece p = s.getPiece();
		
		if(p == null) {
			return true;
		}
		else {
			return false;
		}
	}
}
