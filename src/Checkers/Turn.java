package Checkers;

import java.util.ArrayList;

import UI.CheckersPanel;

public class Turn {
	
	public ArrayList<Move> moves = new ArrayList<Move>();
	public Player p;
	public Board b;
	
	public Turn(Player p, Board b) {
		this.p = p;
		this.b = b;
		p.calculateMoves(b);
	}
	
	public void addMove(Move m) {
		moves.add(m);
	}

	public void undo(Checkers checkers) {
		for (int i = moves.size() - 1; i >= 0; i--) {
		    moves.get(i).undoMove(checkers);
		}
	}

	public void redo(Checkers checkers, CheckersPanel cp) {
		for(Move m : moves) {
			m.executeMove(checkers, cp);
		}
	}
	
	public String toString() {
		String s = moves.get(0).getFrom().toString();
		for (Move m : moves) {
			s += m.toString();
		}
		return s;
	}

}
