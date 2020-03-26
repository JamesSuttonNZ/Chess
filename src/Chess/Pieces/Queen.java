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
	public void cancelMove() {
		super.setPos(currentSquare);
	}

	@Override
	public ArrayList<Move> validMoves(Board board, Square selectedSquare) {
		ArrayList<Move> vm = new ArrayList<Move>();
		//recurse left
		moveCheck(board, selectedSquare, 0, -1, vm);
		//recurse right
		moveCheck(board, selectedSquare, 0, 1, vm);
		//recurse up
		moveCheck(board, selectedSquare, -1, 0, vm);
		//recurse down
		moveCheck(board, selectedSquare, 1, 0, vm);
		//recurse northwest
		moveCheck(board, selectedSquare, -1, -1, vm);
		//recurse northeast
		moveCheck(board, selectedSquare, 1, -1, vm);
		//recurse southeast
		moveCheck(board, selectedSquare, 1, 1, vm);
		//recurse southwest
		moveCheck(board, selectedSquare, -1, 1, vm);
		return vm;
	}
	
	public void moveCheck(Board board, Square currentSquare, int moveRow, int moveCol, ArrayList<Move> vm) {
		//row and col of current square
		int row = currentSquare.getRow();
		int col = currentSquare.getCol();
		
		//check if gone off board
		if(row+moveRow >= 0 && row+moveRow < 8 && col+moveCol >= 0 && col+moveCol < 8) {
			
			//move square
			currentSquare = board.getSquare(row+moveRow, col+moveCol);
			Piece p = currentSquare.getPiece();
			if(p == null) {
				vm.add(new Move(this,p,this.getPos(),currentSquare));
				moveCheck(board,currentSquare,moveRow,moveCol,vm);
			}
			else if(p != null && p.getOwner().getName() != owner.getName()) {
				vm.add(new Move(this,p,this.getPos(),currentSquare));
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
