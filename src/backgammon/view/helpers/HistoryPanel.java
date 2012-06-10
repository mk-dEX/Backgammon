package backgammon.view.helpers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import backgammon.model.player.Move;
import backgammon.view.BackgammonViewGUI;

@SuppressWarnings("serial")
public class HistoryPanel extends JFrame{

	private JList JList1;
	private DefaultListModel m1 = new DefaultListModel();
	private JButton load;
	
	public HistoryPanel(BackgammonViewGUI backgammonViewGUI)
	{
		this.setTitle("History");
		this.setSize(300,600);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setResizable(false); 
		this.setLocationRelativeTo(null);
		this.setVisible(false); 

		this.JList1 = new JList(m1);
		this.JList1.setPreferredSize(new Dimension(260, 550));
		JScrollPane pane = new JScrollPane(this.JList1);
		
		this.load = new JButton("Lade Spielzug");
		this.load.setPreferredSize(new Dimension(125, 25));
		this.add(load,BorderLayout.SOUTH);
		this.add(pane, BorderLayout.CENTER);
		this.load.addActionListener(backgammonViewGUI);
			
	}

	public void addListEntry(Move move)
	{
		if(move == null)
			return;
		
		this.m1.addElement("["+move.getID()+"] "+move.getFromPoint()+","+move.getFromIndex()+" >>> "+move.getToPoint()+","+move.getToIndex());
	}
	public JList getList() {
		return this.JList1;
	}
	public DefaultListModel getListItems() {
		return this.m1;
	}
	public JButton getLoad()
	{
		return this.load;
	}
}
