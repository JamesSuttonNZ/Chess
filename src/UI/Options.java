package UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Options extends JPanel {
	
	public Options(MainFrame mf) {
		setPreferredSize(new Dimension(200,800));
		
		setBorder(BorderFactory.createTitledBorder("Options:"));
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mf.getChessPanel().newGame();
			}
		});
		JButton saveGame = new JButton("Save Game");
		JButton loadGame = new JButton("Load Game");
		
		add(newGame);
		add(saveGame);
		add(loadGame);
		
		
			
	}
	
	

}
