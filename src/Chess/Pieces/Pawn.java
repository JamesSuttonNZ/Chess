package Chess.Pieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public class Pawn extends Piece {
	
	public BufferedImage sprite = null;
	
	public Pawn(Player owner, Square pos) {
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
	
	public boolean move(char x, int y, Board board) {
		String val = "ABCDEFGH";
		Character.toUpperCase(x);
		int col = val.indexOf(x);
		int row = y-1;
		if(owner.getName() == "White") {
			if(col == pos.getCol() && (row-pos.getRow() == 1 || row-pos.getRow() == 2)) {
				board.getSquare(row, col).setPiece(this);
				pos.setPiece(null);
				return true;
			}
			else {
				return false;
			}
		}
		else{
			if(col == pos.getCol() && (row-pos.getRow() == -1 || row-pos.getRow() == -2)) {
				board.getSquare(row, col).setPiece(this);
				pos.setPiece(null);
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
}
