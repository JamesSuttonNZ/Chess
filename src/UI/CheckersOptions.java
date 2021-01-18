package UI;

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
import javax.swing.JTextArea;

public class CheckersOptions extends JPanel {
	
	public JTextArea moveLog;
	public JScrollPane scroll;
	
	public CheckersOptions(MainFrame mf) {
		setPreferredSize(new Dimension(250,800));
		
//		setBorder(BorderFactory.createTitledBorder("Move Log:"));
		
		moveLog = new JTextArea();
		moveLog.setFont(new Font("SansSerif", Font.PLAIN, 20));
		moveLog.setEditable(false);
		
		//move log
		scroll = new JScrollPane(moveLog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JButton undo = new JButton("Undo Move");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mf.getCheckersPanel().undoMove();
			}
		});
		
		JButton redo = new JButton("Redo Move");
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mf.getCheckersPanel().redoMove();
			}
		});
		
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mf.getCheckersPanel().newGame();
				getMoveLog().setText("");
			}
		});
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 10;
		
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		add(scroll, c);
		
		c.weighty = 0.5;
		c.gridwidth = 1;
		c.gridy = 1;
		add(undo, c);
		c.gridx = 1;
		add(redo, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		add(newGame,c);
	}

	public JTextArea getMoveLog() {
		return moveLog;
	}

	public void setMoveLog(JTextArea moveLog) {
		this.moveLog = moveLog;
	}

}
