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

public class MoveLogPanel extends JPanel {
	
	public MoveLogPanel(MainFrame mf) {
		setPreferredSize(new Dimension(200,800));
		
		setBorder(BorderFactory.createTitledBorder("Move Log:"));
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
	}
	
	

}
