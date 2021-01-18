package UI;

import java.util.Stack;

import javax.swing.table.AbstractTableModel;

import Chess.Move;

public class MoveLog extends AbstractTableModel {

	private String[] columnNames = {"Turn",
            "White",
            "Black"};
	
	private Stack<Move> whiteMoves = new Stack<Move>();
	private Stack<Move> blackMoves = new Stack<Move>();
	
	public void addMove(Move m, boolean white) {
		if(white) {
			whiteMoves.add(m);
		}else {
			blackMoves.add(m);
		}
		System.out.println(m.toString());
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return whiteMoves.size();
	}
	
	@Override
	public String getColumnName(int col) {
        return columnNames[col];
    }

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		if(col == 0) {
			return (row+1)+".";
		}else if(col == 1){
			if(row < whiteMoves.size()) {
				return whiteMoves.get(row).toString();
			}else {
				return null;
			}
			
		}else {
			if(row < blackMoves.size()) {
				return blackMoves.get(row).toString();
			}else {
				return null;
			}
		}
	}
	
//	@Override
//	public void setValueAt(Object m, int row, int col) {
//		if(col == 1) {
//			
//		}else if(col == 2) {
//			
//		}
//	}

	public boolean isCellEditable(int row, int col) {
		return false;
    }

}
