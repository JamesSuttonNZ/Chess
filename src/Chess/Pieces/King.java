package Chess.Pieces;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Castling;
import Chess.Move;
import Chess.Player;
import Chess.Square;
import UI.ChessPanel;

public class King extends Piece {

	public BufferedImage sprite = null;

	public King(Player owner, Square pos) {
		super(owner, pos);
		
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
	
	public String toString() {
		return "K";
	}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public void validMoves(Board board) {
		validMoves.clear();
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
					validMoves.add(new Move(this,p,this.getPos(),currentSquare));
				}
				if(moveRow == 0 && moveCol == 1 || moveRow == 0 && moveCol == -1) {
					moveCheck(board,currentSquare,moveRow,moveCol,moves-1);
				}
			}
			else if(p != null && p.getOwner().getName() != owner.getName() && moves > 0) {
				validMoves.add(new Move(this,p,this.getPos(),currentSquare));
			}
			else if(p != null && p.getOwner().getName() == owner.getName() && p instanceof Rook) {
				if(this.moves.size() == 0 && p.getMoves().size() == 0) {
					validMoves.add(new Castling(this,p,this.getPos(), board.getSquare(this.currentSquare.getRow(), this.currentSquare.getCol()+(moveCol*2)), board.getSquare(this.currentSquare.getRow(), this.currentSquare.getCol()+moveCol), currentSquare));
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
	
}
