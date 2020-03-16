package Chess.Pieces;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Chess.Board;
import Chess.Player;
import Chess.Square;

public class Rook extends Piece{

	public BufferedImage sprite = null;
	
	public Rook(Player owner, Square pos) {
		super(owner, pos);
		
		//get image
		if(owner.getName() == "White") {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/rookW.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sprite = ImageIO.read(new File("Resources/Chess Piece Images/rookB.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return owner.getName()+" Rook";
	}

	@Override
	public boolean move(char x, int y, Board board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Image getSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

	@Override
	public void movePiece(Square newSquare) {
		// TODO Auto-generated method stub
		
	}
	
}
