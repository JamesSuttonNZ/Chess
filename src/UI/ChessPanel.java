package UI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

import Chess.Chess;
import Chess.Move;
import Chess.Square;
import Chess.Pieces.Piece;

public class ChessPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	//chess game
	private Chess chess;
	//clicked on square
	private Square selectedSquare;
	//clicked on piece
	private Piece selectedPiece;
	//valid moves for clicked piece
	private ArrayList<Move> validMoves;
	
	public ChessPanel(Chess chess) {
		
		this.chess = chess;
		
		//set size
		setPreferredSize(new Dimension(800,800));
		
		//add mouse listeners
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawSquares(g);
		
		drawPieces(g);
		
		//draw selected piece on top
		if(selectedPiece != null) {
			selectedPiece.drawPiece(g);
		}
		
	}

	private void drawSquares(Graphics g) {
		chess.getBoard().drawBoard(g);
	}
	
	private void drawPieces(Graphics g) {
		ArrayList<Piece> pieces = chess.getPieces();
		for(Piece p : pieces) {
			p.drawPiece(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
		//get clicked point
		Point p = e.getPoint();
		int row = p.y/100;
		int col = p.x/100;
		//clicked square
		selectedSquare = chess.getBoard().getSquare(row, col);
		//clicked piece
		selectedPiece = selectedSquare.getPiece();
		
		//highlight if moveable piece
		if(selectedPiece != null) {

			//white piece and whites turn or black piece and blacks turn
			if(selectedPiece.getOwner().isWhite() == chess.isWhitesTurn()) {
				
				selectedSquare.setPressed(true);
				
				selectedPiece.drawValidMoves(true);
				
				//set valid moves and draw green circle at valid squares
				validMoves = selectedPiece.getValidMoves();
			}
			//wrong colour piece selected
			else {
				selectedPiece = null;
			}
		}
		repaint();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
		//if piece selected
		if(selectedPiece != null) {
			
			
			Point p = e.getPoint();
			
			//check on panel
			if(p.x >= 0 && p.x < 800 && p.y >=0 && p.y < 800) {
				int row = p.y/100;
				int col = p.x/100;
				
				//get dropped square
				Square newSquare = chess.getBoard().getSquare(row, col);
				
				//check move to new square is valid
				boolean valid = false;
				for(Move m : validMoves) {
					if(m.getTo() == newSquare) {
						m.executeMove(chess, this);
						chess.getUndone().clear();
						valid = true;
						break;
					}	
				}
				if(!valid) {
					selectedPiece.resetPos();
				}

			}
			//not on panel
			else {
				selectedPiece.resetPos();
			}
			
			selectedSquare.setPressed(false);
			if(selectedPiece != null) {
				selectedPiece.drawValidMoves(false);
			}
			repaint();
		}
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		//if piece selected, stick to cursor
		if(selectedPiece != null) {
			Point p = e.getPoint();
			
			//keep piece within panel
			if(p.x >= 0 && p.x <= 800) {
				selectedPiece.setX(p.x-50);
			}
			if(p.y >= 0 && p.y <= 800) {
				selectedPiece.setY(p.y-50);
			}
			repaint();
		}
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
