package Chess.Pieces;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Chess;
import Chess.EnPassant;
import Chess.Move;
import Chess.PawnPromotion;
import Chess.Player;
import Chess.Square;
import UI.ChessPanel;

public class Pawn extends Piece {
	
	private BufferedImage sprite = null;
	
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
		return "";
	}

	@Override
	public Image getSprite() {
		return sprite;
	}
	
	@Override
	public boolean validMoves(Chess chess) {
		validMoves.clear();
		
		Board board = chess.getBoard();
		if(owner.getName() == "Black") {
			if(moves.size() == 0) {
				moveCheck(chess, board, currentSquare, 1, 0, 2);
			}
			else {
				moveCheck(chess, board, currentSquare, 1, 0, 1);
			}
			moveCheck(chess, board, currentSquare, 1, 1, 1);
			moveCheck(chess, board, currentSquare, 1, -1, 1);
		}
		else {
			if(moves.size() == 0) {
				moveCheck(chess, board, currentSquare, -1, 0, 2);
			}
			else {
				moveCheck(chess, board, currentSquare, -1, 0, 1);
			}
			moveCheck(chess, board, currentSquare, -1, 1, 1);
			moveCheck(chess, board, currentSquare, -1, -1, 1);
		}
		return validMoves.size() > 0;
	}
	
	public void moveCheck(Chess chess, Board board, Square currentSquare, int moveRow, int moveCol, int moves) {
		
		//stop if moves used up
		if(moves == 0) {
			return;
		}
		
		//row and col of current square
		int row = currentSquare.getRow();
		int col = currentSquare.getCol();
		
		//check if gone off board
		if(row+moveRow >= 0 && row+moveRow < 8 && col+moveCol >= 0 && col+moveCol < 8) {
			
			//move square
			currentSquare = board.getSquare(row+moveRow, col+moveCol);
			Square adjacentSquare = board.getSquare(row, col+moveCol);
			Piece p = currentSquare.getPiece();
			Piece adjP = adjacentSquare.getPiece();
			
			//check diagonal
			if(moveRow != 0 && moveCol != 0) {
				//diag take
				if(p != null && p.getOwner() != owner) {
					if(owner.isWhite() && currentSquare.getRow() == 0 || !owner.isWhite() && currentSquare.getRow() == 7) {
						
						PawnPromotion move = new PawnPromotion(this, p, this.getPos(), currentSquare);
						if(move.validMove(board)) {
							validMoves.add(move);
						}
					}
					else {
						
						Move move = new Move(this, p, this.getPos(), currentSquare);
						if(move.validMove(board)) {
							validMoves.add(move);
						}
					}
				}
				//en passant
				else if(enPassant(p,adjP,chess,board)) {
					
					EnPassant move = new EnPassant(this, adjP, this.getPos(), currentSquare, adjacentSquare);
					if(move.validMove(board)) {
						validMoves.add(move);
					}
				}
			}
			//check forward
			else {
				//empty square
				if(p == null) {
					if(owner.isWhite() && currentSquare.getRow() == 0 || !owner.isWhite() && currentSquare.getRow() == 7) {
						
						PawnPromotion move = new PawnPromotion(this, p, this.getPos(), currentSquare);
						if(move.validMove(board)) {
							validMoves.add(move);
						}
					}
					else {
						
						Move move = new Move(this, p, this.getPos(), currentSquare);
						if(move.validMove(board)) {
							validMoves.add(move);
						}
						moveCheck(chess,board,currentSquare,moveRow,moveCol,moves-1);
					}
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
	
	public boolean enPassant(Piece p, Piece adjP, Chess chess, Board board) {
		if(p == null){
			if(adjP != null) {
				if(adjP instanceof Pawn) {
					Stack<Move> boardMoves = chess.getMoves();
					Stack<Move> adjMoves = adjP.getMoves();
					if(boardMoves.size() > 0 && adjMoves.size() > 0) {
						//was last move and moved 2 squares
						if(boardMoves.peek() == adjMoves.peek() && Math.abs(boardMoves.peek().getRowsMoved()) == 2) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
