package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

import Chess.Chess;

public class ChessOptions extends JPanel {
	
	private Chess chess;
	private MainFrame mf;
	private JScrollPane scroll;
	private JTable table;
	private JButton undo, redo, newGame;
	
	public ChessOptions(Chess chess, MainFrame mf) {
		
		this.chess = chess;
		this.mf = mf;
		
		setPreferredSize(new Dimension(300,800));
		
		setupTable(chess);
		
		setupScrollPane();
		
		setupButtons(chess, mf);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 10;
		
		//scrollpane
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		add(scroll, c);
		
		//undo button
		c.weighty = 0.5;
		c.gridwidth = 1;
		c.gridy = 1;
		add(undo, c);
		
		//redo button
		c.gridx = 1;
		add(redo, c);
		
		//new game button
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		add(newGame,c);
	}

	private void setupButtons(Chess chess, MainFrame mf) {
		undo = new JButton("Undo Move");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chess.undoMove();
			}
		});
		
		redo = new JButton("Redo Move");
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chess.redoMove();
			}
		});
		
		newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mf.newChess();
			}
		});
	}

	private void setupScrollPane() {
		scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.getViewport().setBackground(Color.WHITE);
	}

	private void setupTable(Chess chess) {
		table = new JTable(new ChessTable(chess.getMoves()));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);
	}
	
	public void newGame() {
		mf.newChess();
	}
	
	public void updateTable() {
		((AbstractTableModel) table.getModel()).fireTableDataChanged();
	}

	public MainFrame getMf() {
		return mf;
	}

	public void setMf(MainFrame mf) {
		this.mf = mf;
	}
}
