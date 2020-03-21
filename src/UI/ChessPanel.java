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
import Chess.Square;
import Chess.Pieces.Piece;

public class ChessPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	public Chess chess;
	public Square selectedSquare;
	public Piece selectedPiece;
	public ArrayList<Square> validMoves;
	
	public ChessPanel(Chess chess) {
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(800,800));
		this.chess = chess;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawSquares(g);
		
		drawLetters(g);
		
		drawNumbers(g);
		
		drawPieces(g);
		
		//draw selected piece on top
		if(selectedPiece != null) {
			selectedPiece.drawPiece(g);
		}
		
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
		int x = 0;
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
		// TODO Auto-generated method stub
		if(selectedPiece != null) {
			selectedPiece.cancelMove();
			selectedPiece = null;
			selectedSquare.setPressed(false);
			selectedSquare = null;
			for(Square s : validMoves) {
				s.setValid(false);
			}
			validMoves = null;
			repaint();
		}
		
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
			selectedSquare.setPressed(true);
			
			//draw green circle at square that can be moved to
			validMoves = chess.getBoard().getValidMoves(selectedSquare, selectedPiece);
			for(Square s : validMoves) {
				s.setValid(true);
			}
		}
		repaint();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if(selectedPiece != null) {
			
			//get clicked square
			Point p = e.getPoint();
			int row = p.y/100;
			int col = p.x/100;
			Square newSquare = chess.getBoard().getSquare(row, col);
			if(validMoves.contains(newSquare)) {
				selectedPiece.movePiece(newSquare);
			}else {
				selectedPiece.cancelMove();
			}
			selectedSquare.setPressed(false);
			for(Square s : validMoves) {
				s.setValid(false);
			}
			repaint();
		}
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		if(selectedPiece != null) {
			Point p = e.getPoint();
			selectedPiece.setX(p.x-50);
			selectedPiece.setY(p.y-50);
			repaint();
		}
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
