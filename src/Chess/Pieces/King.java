package Chess.Pieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Castling;
import Chess.Chess;
import Chess.Move;
import Chess.Player;
import Chess.Square;
import UI.ChessPanel;

public class King extends Piece {

	private BufferedImage sprite = null;

	public King(Player owner, Square pos) {
		super(owner, pos);
		
		owner.setKing(this);
		
		//get image
		if(owner.getName() == "White") {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/kingW.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/kingB.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void drawPiece(Graphics g) {
		if(owner.isCheck()) {
			g.setColor(new Color(255,0,0));
			g.fillRect(currentSquare.getX(), currentSquare.getY(), 100, 100);
			g.setColor(currentSquare.getColor());
			g.fillRect(currentSquare.getX()+7, currentSquare.getY()+7, 86, 86);
		}
		
		if(!taken) {
			g.drawImage(this.getSprite(), x, y, 100, 100, null);
		}
	}
	
	public String toString() {
		return "K";
	}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public boolean validMoves(Chess chess) {
		validMoves.clear();
		
		Board board = chess.getBoard();
		//recurse left
		moveCheck(board, currentSquare, 0, -1, 1);
		//recurse right
		moveCheck(board, currentSquare, 0, 1, 1);
		//recurse up
		moveCheck(board, currentSquare, -1, 0, 1);
		//recurse down
		moveCheck(board, currentSquare, 1, 0, 1);
		//recurse northwest
		moveCheck(board, currentSquare, -1, -1, 1);
		//recurse northeast
		moveCheck(board, currentSquare, 1, -1, 1);
		//recurse southeast
		moveCheck(board, currentSquare, 1, 1, 1);
		//recurse southwest
		moveCheck(board, currentSquare, -1, 1, 1);
		return validMoves.size() > 0;
	}
	
	public void moveCheck(Board board, Square currentSquare, int moveRow, int moveCol, int moves) {
		
		//row and col of current square
		int row = currentSquare.getRow();
		int col = currentSquare.getCol();
		
		//check if gone off board
		if(row+moveRow >= 0 && row+moveRow < 8 && col+moveCol >= 0 && col+moveCol < 8) {
			
			//move square
			currentSquare = board.getSquare(row+moveRow, col+moveCol);
			Piece p = currentSquare.getPiece();
			
			if(p == null) {
				if(moves > 0) {
					Move move = new Move(this,p,this.getPos(),currentSquare);
					if(move.validMove(board)) {
						validMoves.add(move);
					}
				}
				if(moveRow == 0 && moveCol == 1 || moveRow == 0 && moveCol == -1) {
					moveCheck(board,currentSquare,moveRow,moveCol,moves-1);
				}
			}
			else if(p != null && p.getOwner() != owner && moves > 0) {
				Move move = new Move(this,p,this.getPos(),currentSquare);
				if(move.validMove(board)) {
					validMoves.add(move);
				}
			}
			else if(p != null && p.getOwner() == owner && p instanceof Rook) {
				if(this.moves.size() == 0 && p.getMoves().size() == 0) {
					Castling move = new Castling(this,p,this.getPos(), board.getSquare(this.currentSquare.getRow(), this.currentSquare.getCol()+(moveCol*2)), board.getSquare(this.currentSquare.getRow(), this.currentSquare.getCol()+moveCol), currentSquare);
					if(move.validMove(board)) {
						validMoves.add(move);
					}
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

	public boolean inCheck(Board board) {
		
		//recurse left
		if(checkSearch(board, currentSquare, 0, -1, 0)) return true;
		//recurse right
		if(checkSearch(board, currentSquare, 0, 1, 0)) return true;
		//recurse up
		if(checkSearch(board, currentSquare, -1, 0, 0)) return true;
		//recurse down
		if(checkSearch(board, currentSquare, 1, 0, 0)) return true;
		//recurse northwest
		if(checkSearch(board, currentSquare, -1, -1, 0)) return true;
		//recurse northeast
		if(checkSearch(board, currentSquare, 1, -1, 0)) return true;
		//recurse southeast
		if(checkSearch(board, currentSquare, 1, 1, 0)) return true;
		//recurse southwest
		if(checkSearch(board, currentSquare, -1, 1, 0)) return true;
		
		//recurse left
		if(checkSearch(board, currentSquare, 2, 1, 0)) return true;
		//recurse right
		if(checkSearch(board, currentSquare, 2, -1, 0)) return true;
		//recurse up
		if(checkSearch(board, currentSquare, -2, 1, 0)) return true;
		//recurse down
		if(checkSearch(board, currentSquare, -2, -1, 0)) return true;
		//
		if(checkSearch(board, currentSquare, 1, 2, 0)) return true;
		//recurse right
		if(checkSearch(board, currentSquare, -1, 2, 0)) return true;
		//recurse up
		if(checkSearch(board, currentSquare, 1, -2, 0)) return true;
		//recurse down
		if(checkSearch(board, currentSquare, -1, -2, 0)) return true;
		
		return false;
	}

	private boolean checkSearch(Board board, Square currentSquare, int moveRow, int moveCol, int moves) {
		
		//stop looking for knight after 1 move
		if(moves == 1 && (Math.abs(moveRow) == 2 || Math.abs(moveCol) == 2)) {
			return false;
		}

		//row and col of current square
		int row = currentSquare.getRow();
		int col = currentSquare.getCol();
		
		//check if gone off board
		if(row+moveRow >= 0 && row+moveRow < 8 && col+moveCol >= 0 && col+moveCol < 8) {
			
			//move square
			currentSquare = board.getSquare(row+moveRow, col+moveCol);
			Piece p = currentSquare.getPiece();
			
			//empty square
			if(p == null) {
				return checkSearch(board,currentSquare,moveRow,moveCol,moves+1);
			}
			else {
				//own piece
				if(p.getOwner() == owner) {
					return false;
				}
				//horizontal/vertical and enemy queen, rook or king
				else if((moveCol == 0 || moveRow == 0) && (p instanceof Queen || p instanceof Rook || p instanceof King)) {
					//king and 1 step
					if(p instanceof King && moves == 0) {
						return true;
					}
					//queen or rook
					else if(!(p instanceof King)) {
						return true;
					}
				}
				//diagonal and queen, bishop, king or pawn
				else if((Math.abs(moveCol) == 1 && Math.abs(moveRow) == 1) && (p instanceof Queen || p instanceof Bishop || p instanceof King || p instanceof Pawn)) {	
					//king or pawn and 1 step
					if((p instanceof King || p instanceof Pawn) && moves == 0) {
						if(p instanceof Pawn) {
							int rowDiff = p.getPos().getRow() - this.getPos().getRow();
							if(p.getOwner().isWhite() && rowDiff > 0) {
								return true;
							}
							else if(!p.getOwner().isWhite() && rowDiff < 0) {
								return true;
							}
						}
						else {
							return true;
						}
					}
					//queen or bishop
					else if(!(p instanceof King || p instanceof Pawn)) {
						return true;
					}
				}
				else if((Math.abs(moveRow) == 2 || Math.abs(moveCol) == 2) && p instanceof Knight) {
					return true;
				}
			}
		}
		return false;
	}
	
}
