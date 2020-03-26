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
	
	public Chess chess;
	public Square selectedSquare;
	public Piece selectedPiece;
	public ArrayList<Move> validMoves;
	public ChessOptions options;
	
	public ChessPanel(ChessOptions options) {
		this.options = options;
		setPreferredSize(new Dimension(800,800));
		this.chess = new Chess();
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
	
	public void newGame() {
		this.chess = new Chess();
		repaint();
	}
	
	public void logMoves() {
		chess.getBoard().logMoves(options);
	}

	
	private void drawLetters(Graphics g) {
		//draw letters
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		g.setColor(Color.WHITE);
		int x = 85;
		int y = 795;
		char c = 'a';
		for(int i = 0; i < 8; i++) {
			g.drawString(Character.toString(c), x, y);
			x += 100;
			c++;
		}
	}


	private void drawNumbers(Graphics g) {
		//draw numbers
		g.setColor(Color.WHITE);
		int x = 2;
		int y = 720;
		for(int i = 0; i < 8; i++) {
			g.drawString(Integer.toString(i+1), x, y);
			y -= 100;
		}
	}


	private void drawSquares(Graphics g) {
		chess.getBoard().drawBoard(g);
	}
	
	private void drawPieces(Graphics g) {
		chess.drawPieces(g);
	}

	public void undoMove() {
		chess.getBoard().undoMove(chess);
		logMoves();
		repaint();
	}

	public void redoMove() {
////		chess.getBoard().redoMove(chess, this);
//		logMoves();
//		repaint();
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
				
				//set valid moves and draw green circle at valid squares
				validMoves = chess.getBoard().getValidMoves(selectedSquare, selectedPiece);
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
						valid = true;
						m.executeMove(chess, this);
						logMoves();
						selectedPiece = null;
						chess.getBoard().getUndone().clear();
						break;
					}	
				}
				if(!valid) {
					selectedPiece.cancelMove();
				}
				selectedSquare.setPressed(false);
				
				for(Move m : validMoves) {
					m.setInvalid();
				}
				
				validMoves.clear();
				
				repaint();
			}
			else {
				selectedPiece.cancelMove();
				selectedPiece = null;
				selectedSquare.setPressed(false);
				selectedSquare = null;
				if(validMoves.size() > 0) {
					for(Move m : validMoves) {
						m.setInvalid();
					}
					validMoves.clear();
				}
				repaint();
			}
		}
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		//if piece selected, stick to cursor
		if(selectedPiece != null) {
			Point p = e.getPoint();
//			if(p.x >= 0 && p.x <= 800) {
				selectedPiece.setX(p.x-50);
//			}
//			if(p.y >= 0 && p.y <= 800) {
				selectedPiece.setY(p.y-50);
//			}
			repaint();
		}
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
