package Chess.Pieces;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Move;
import Chess.Player;
import Chess.Square;
import UI.ChessPanel;

public class Queen extends Piece {
	
	public BufferedImage sprite = null;
	
	public Queen(Player owner, Square pos) {
		super(owner, pos);
		
		//get image
		if(owner.getName() == "White") {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/queenW.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/queenB.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return "Q";
	}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public void validMoves(Board board) {
		validMoves.clear();
		//recurse left
		moveCheck(board, currentSquare, 0, -1);
		//recurse right
		moveCheck(board, currentSquare, 0, 1);
		//recurse up
		moveCheck(board, currentSquare, -1, 0);
		//recurse down
		moveCheck(board, currentSquare, 1, 0);
		//recurse northwest
		moveCheck(board, currentSquare, -1, -1);
		//recurse northeast
		moveCheck(board, currentSquare, 1, -1);
		//recurse southeast
		moveCheck(board, currentSquare, 1, 1);
		//recurse southwest
		moveCheck(board, currentSquare, -1, 1);
	}
	
	public void moveCheck(Board board, Square currentSquare, int moveRow, int moveCol) {
		//row and col of current square
		int row = currentSquare.getRow();
		int col = currentSquare.getCol();
		
		//check if gone off board
		if(row+moveRow >= 0 && row+moveRow < 8 && col+moveCol >= 0 && col+moveCol < 8) {
			
			//move square
			currentSquare = board.getSquare(row+moveRow, col+moveCol);
			Piece p = currentSquare.getPiece();
			if(p == null) {
				validMoves.add(new Move(this,p,this.getPos(),currentSquare));
				moveCheck(board,currentSquare,moveRow,moveCol);
			}
			else if(p != null && p.getOwner() != owner) {
				validMoves.add(new Move(this,p,this.getPos(),currentSquare));
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
