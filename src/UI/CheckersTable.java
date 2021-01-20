package UI;

import java.util.Stack;

import javax.swing.table.AbstractTableModel;

import Checkers.Turn;
import Chess.Move;

public class CheckersTable extends AbstractTableModel {

	private String[] columnNames = {"Turn",
            "White",
            "Black"};
	
	private Stack<Turn> turns = new Stack<Turn>();
	
	public CheckersTable(Stack<Turn> turns) {
		this.turns = turns;
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return (turns.size()+1)/2;
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
			
			return turns.get(row*2).toString();
			
		}else {
			if((row*2)+1 < turns.size()) {
				return turns.get((row*2)+1).toString();
			}
			else {
				return null;
			}
		}
	}

	public boolean isCellEditable(int row, int col) {
		return false;
    }

}
