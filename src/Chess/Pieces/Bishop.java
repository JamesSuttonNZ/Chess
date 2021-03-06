package Chess.Pieces;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Chess;
import Chess.Move;
import Chess.Player;
import Chess.Square;
import UI.ChessPanel;

public class Bishop extends Piece {
	
	private BufferedImage sprite = null;

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
		return "B";
	}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public boolean validMoves(Chess chess) {
		validMoves.clear();
		
		Board board = chess.getBoard();
		//recurse northwest
		moveCheck(board, currentSquare, -1, -1);
		//recurse northeast
		moveCheck(board, currentSquare, 1, -1);
		//recurse southeast
		moveCheck(board, currentSquare, 1, 1);
		//recurse southwest
		moveCheck(board, currentSquare, -1, 1);
		return validMoves.size() > 0;
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
				Move move = new Move(this,p,this.getPos(),currentSquare);
				if(move.validMove(board)) {
					validMoves.add(move);
				}
				moveCheck(board,currentSquare,moveRow,moveCol);
			}
			else if(p != null && p.getOwner() != owner) {
				Move move = new Move(this,p,this.getPos(),currentSquare);
				if(move.validMove(board)) {
					validMoves.add(move);
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
