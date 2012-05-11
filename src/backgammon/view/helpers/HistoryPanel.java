package backgammon.view.helpers;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.WindowConstants;

import backgammon.model.player.Move;
import backgammon.view.BackgammonViewGUI;

@SuppressWarnings("serial")
public class HistoryPanel extends JFrame{

	private JList JList1;
	private JList JList2;
	
	private DefaultListModel m1 = new DefaultListModel();
	private DefaultListModel m2 = new DefaultListModel();
	
	private BackgammonViewGUI parent;
	private JButton load;
	
	public HistoryPanel(BackgammonViewGUI backgammonViewGUI)
	{
		this.parent = backgammonViewGUI;
		
		this.setTitle("History");
		this.setSize(300,600);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setResizable(false); 
		this.setLocationRelativeTo(null);
		this.setVisible(false); 

		this.JList1 = new JList(m1);
		this.JList1.setPreferredSize(new Dimension(140, 580));
		this.add(this.JList1,BorderLayout.WEST);

		this.JList2 = new JList(m2);
		this.JList2.setPreferredSize(new Dimension(140, 580));
		this.add(this.JList2, BorderLayout.EAST);
		
		this.load = new JButton("Lade Spielzug");
		this.load.setPreferredSize(new Dimension(125, 25));
		this.add(load, BorderLayout.SOUTH);
		
		
		
	}
	public void addListEntry(Move move)
	{
		if(move.getID() == 1)
		{
			this.m1.addElement("SFSSF");
		}
		else
		{
			this.m2.addElement("SFSSF");
		}
	}
}
