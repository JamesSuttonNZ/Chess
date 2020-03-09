package UI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import Chess.Chess;

public class ChessPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	public Chess chess;
	
	public ChessPanel(Chess chess) {
		setPreferredSize(new Dimension(1000,1000));
		this.chess = chess;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawLetters(g);
		
		drawNumbers(g);
		
		drawChessboard(g);
		
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
		int x;
		int y;
		//draw numbers
		x = 25;
		int x2 = 925;
		y = 175;
		for(int i = 0; i < 8; i++) {
			g.drawString(Integer.toString(i+1), x, y);
			g.drawString(Integer.toString(i+1), x2, y);
			y += 100;
		}
	}


	private void drawChessboard(Graphics g) {
		chess.getBoard().drawBoard(g);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
