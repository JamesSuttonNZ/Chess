package Chess.Pieces;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Move;
import Chess.Player;
import Chess.Square;

public class Pawn extends Piece {
	
	public BufferedImage sprite = null;
	
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
	
	public String toString() {
		return owner.getName()+" Pawn";
	}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public Piece movePiece(Board board, Square newSquare) {
		return moveCheck(board, newSquare, owner.getName());
		
	}
	
	private Piece moveCheck(Board board, Square newSquare, String player) {
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
			int colMove = newSquare.getCol() - currentSquare.getCol();
			if(colMove != 0) {
				Square adjSquare = board.getSquare(currentSquare.getRow(), currentSquare.getCol()+colMove);
				Piece p = adjSquare.getPiece();
				if(p != null) {
					p.setTaken(true);
					adjSquare.setPiece(null);
					updatePosition(newSquare);
					return p;
				}
			}
			else {
				updatePosition(newSquare);
			}
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
	public ArrayList<Square> validMoves(Board board, Square selectedSquare) {
		ArrayList<Square> vm = new ArrayList<Square>();
		if(owner.getName() == "Black") {
			if(moves.size() == 0) {
				recursiveMoveCheck(board, selectedSquare, 1, 0, vm, 2);
			}
			else {
				recursiveMoveCheck(board, selectedSquare, 1, 0, vm, 1);
			}
			recursiveMoveCheck(board, selectedSquare, 1, 1, vm, 1);
			recursiveMoveCheck(board, selectedSquare, 1, -1, vm, 1);
		}
		else {
			if(moves.size() == 0) {
				recursiveMoveCheck(board, selectedSquare, -1, 0, vm, 2);
			}
			else {
				recursiveMoveCheck(board, selectedSquare, -1, 0, vm, 1);
			}
			recursiveMoveCheck(board, selectedSquare, -1, 1, vm, 1);
			recursiveMoveCheck(board, selectedSquare, -1, -1, vm, 1);
		}
		return vm;
	}
	
	public void recursiveMoveCheck(Board board, Square currentSquare, int moveRow, int moveCol, ArrayList<Square> vm, int moves) {
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
			currentSquare = board.getSquare(row+moveRow, col+moveCol);
			//check diagonal
			if(moveRow != 0 && moveCol != 0) {
				Square adjacentSquare = board.getSquare(row, col+moveCol);
				if(valid(currentSquare,adjacentSquare,board)) {
					vm.add(currentSquare);
					recursiveMoveCheck(board,currentSquare,moveRow,moveCol,vm,moves-1);
				}
				else {
					return;
				}
			}
			//check forward
			else {
				if(validForward(currentSquare)) {
					vm.add(currentSquare);
					recursiveMoveCheck(board,currentSquare,moveRow,moveCol,vm,moves-1);
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
	
	public boolean valid(Square s, Square adjacentSquare, Board board) {
		//get piece at new square
		Piece p = s.getPiece();
		
		if(p != null && p.getOwner().getName() != owner.getName()) {
			return true;
		}
		//en passant
		else if(p == null){
			Piece adjP = adjacentSquare.getPiece();
			if(adjP != null) {
				if(adjP instanceof Pawn) {
					Stack<Move> boardMoves = board.getMoves();
					Stack<Move> adjMoves = adjP.getMoves();
					if(boardMoves.size() > 0 && adjMoves.size() > 0) {
						if(boardMoves.peek() == adjMoves.peek() && Math.abs(boardMoves.peek().getRowsMoved()) == 2) {
							return true;
						}
					}
				}
			}
			return false;
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
