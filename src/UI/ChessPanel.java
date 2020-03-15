package UI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import Chess.Chess;
import Chess.Square;
import Chess.Pieces.Piece;

public class ChessPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	public Chess chess;
	public Piece selectedPiece;
	
	public ChessPanel(Chess chess) {
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(800,800));
		this.chess = chess;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		
//		drawLetters(g);
		
//		drawNumbers(g);
		
		drawSquares(g);
		drawPieces(g);
		
	}

	
	private void drawLetters(Graphics g) {
		//draw letters
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		int x = 125;
		int y = 75;
		int y2 = 975;
		char c = 'A';
		for(int i = 0; i < 8; i++) {
			g.drawString(Character.toString(c), x, y);
			g.drawString(Character.toString(c), x, y2);
			x += 100;
			c++;
		}
	}


	private void drawNumbers(Graphics g) {
		//draw numbers
		int x = 25;
		int x2 = 925;
		int y = 175;
		for(int i = 0; i < 8; i++) {
			g.drawString(Integer.toString(i+1), x, y);
			g.drawString(Integer.toString(i+1), x2, y);
			y += 100;
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

	}


	@Override
	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		int row = p.y/100;
		int col = p.x/100;
		Square s = chess.getBoard().getSquare(row, col);
		selectedPiece = s.getPiece();
		repaint();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
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
