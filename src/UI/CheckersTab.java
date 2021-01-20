package UI;

import javax.swing.JPanel;

import Checkers.Checkers;

public class CheckersTab extends JPanel {
	
	private MainFrame mf;
	private Checkers checkers;
	private CheckersPanel checkersPanel;
	private CheckersOptions checkersOptions;
	
	public CheckersTab(MainFrame mf) {
		
		this.mf = mf;
		this.checkers = new Checkers();
		this.checkersPanel = new CheckersPanel(checkers);
		this.checkersOptions = new CheckersOptions(checkers,mf);
		this.checkers.setCheckersPanel(checkersPanel);
		this.checkers.setCheckersOptions(checkersOptions);
		
		add(checkersPanel);
		add(checkersOptions);
		
	}

}
