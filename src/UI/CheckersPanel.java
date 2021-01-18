package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Checkers.Checkers;

import Checkers.Move;
import Checkers.Square;
import Checkers.CheckerPiece;

public class CheckersPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	//chess game
	public Checkers checkers;
	//clicked on square
	public Square selectedSquare;
	//clicked on piece
	public CheckerPiece selectedPiece;
	//valid moves for clicked piece
	public ArrayList<Move> validMoves;
	//options side bar
	public ChessOptions options;
	
	public CheckersPanel(ChessOptions options) {
		
		//set size
		setPreferredSize(new Dimension(800,800));
		
		//initialise options bar
		this.options = options;
		this.checkers = new Checkers(this);
		
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
	
	public void newGame() {
		this.selectedPiece = null;
		this.checkers = new Checkers(this);
		repaint();
	}
	
	public void logMoves() {
		checkers.logMoves(options);
	}

	
//	private void drawLetters(Graphics g) {
//		//draw letters
//		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
//		g.setColor(Color.WHITE);
//		int x = 85;
//		int y = 795;
//		char c = 'a';
//		for(int i = 0; i < 8; i++) {
//			g.drawString(Character.toString(c), x, y);
//			x += 100;
//			c++;
//		}
//	}
//
//
//	private void drawNumbers(Graphics g) {
//		//draw numbers
//		g.setColor(Color.WHITE);
//		int x = 2;
//		int y = 720;
//		for(int i = 0; i < 8; i++) {
//			g.drawString(Integer.toString(i+1), x, y);
//			y -= 100;
//		}
//	}


	private void drawSquares(Graphics g) {
		checkers.getBoard().drawBoard(g);
	}
	
	private void drawPieces(Graphics g) {
		checkers.drawPieces(g);
	}

	public void undoMove() {
		checkers.undoTurn();
		logMoves();
		repaint();
	}

	public void redoMove() {
		checkers.redoTurn();
		logMoves();
		repaint();
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
		selectedSquare = checkers.getBoard().getSquare(row, col);
		//clicked piece
		selectedPiece = selectedSquare.getPiece();
		
		//highlight if moveable piece
		if(selectedPiece != null) {

			//white piece and whites turn or black piece and blacks turn
			if(selectedPiece.getOwner().isWhite() == checkers.isWhitesTurn()) {
				
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
				Square newSquare = checkers.getBoard().getSquare(row, col);
				
				//check move to new square is valid
				boolean valid = false;
				for(Move m : validMoves) {
					if(m.getTo() == newSquare) {
						m.executeMove(checkers, this);
						logMoves();
						checkers.getUndoneTurns().clear();
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


	public Checkers getCheckers() {
		return checkers;
	}


	public void setCheckers(Checkers checkers) {
		this.checkers = checkers;
	}

}
