package backgammon.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Fri Mar 16 12:25:03 CET 2012
 */

import backgammon.app.GameAgent;



/**
 * @author Nils Plaschke
 */
public class BackgammonViewSettings extends JFrame {
	
	
	private GameAgent controller;
	
	public BackgammonViewSettings(GameAgent controller) {
		
		this.controller = controller;
		
		initComponents();
	}

	private void GameStartActionPerformed(ActionEvent e) {
		// TODO add your code here
		//load Config and start game
	}

	private void GameEndActionPerformed(ActionEvent e) {
		// TODO add your code here
		//End Application
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Nils Plaschke
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		label1 = new JLabel();
		pl1_name = new JTextField();
		pl1_human = new JRadioButton();
		pl1_ai = new JRadioButton();
		pl1_color = new JComboBox();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		pl1_style = new JComboBox();
		panel2 = new JPanel();
		label5 = new JLabel();
		pl2_name = new JTextField();
		pl2_human = new JRadioButton();
		pl2_ai = new JRadioButton();
		pl2_color = new JComboBox();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();
		pl2_style = new JComboBox();
		panel3 = new JPanel();
		ai_aggressive = new JRadioButton();
		ai_passive = new JRadioButton();
		board_style = new JComboBox();
		label10 = new JLabel();
		label11 = new JLabel();
		label12 = new JLabel();
		game_modus = new JComboBox();
		label13 = new JLabel();
		game_modus2 = new JComboBox();
		button1 = new JButton();
		button2 = new JButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== tabbedPane1 ========
		{

			//======== panel1 ========
			{

				// JFormDesigner evaluation mark
				panel1.setBorder(new javax.swing.border.CompoundBorder(
					new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
						"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
						javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
						java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

				panel1.setLayout(null);

				//---- label1 ----
				label1.setText("Name");
				panel1.add(label1);
				label1.setBounds(20, 5, 45, 25);

				//---- pl1_name ----
				pl1_name.setText("Karl");
				panel1.add(pl1_name);
				pl1_name.setBounds(35, 25, 140, pl1_name.getPreferredSize().height);

				//---- pl1_human ----
				pl1_human.setText("Mensch");
				pl1_human.setSelected(true);
				panel1.add(pl1_human);
				pl1_human.setBounds(30, 70, 80, pl1_human.getPreferredSize().height);

				//---- pl1_ai ----
				pl1_ai.setText("KI");
				panel1.add(pl1_ai);
				pl1_ai.setBounds(30, 90, 80, 23);

				//---- pl1_color ----
				pl1_color.setModel(new DefaultComboBoxModel(new String[] {
					"Blau",
					"Rot",
					"Gr\u00fcn",
					"Gelb"
				}));
				panel1.add(pl1_color);
				pl1_color.setBounds(35, 140, 110, pl1_color.getPreferredSize().height);

				//---- label2 ----
				label2.setText("Farbe");
				panel1.add(label2);
				label2.setBounds(20, 120, 40, label2.getPreferredSize().height);

				//---- label3 ----
				label3.setText("Spieler");
				panel1.add(label3);
				label3.setBounds(15, 50, 60, 25);

				//---- label4 ----
				label4.setText("Style");
				panel1.add(label4);
				label4.setBounds(new Rectangle(new Point(20, 170), label4.getPreferredSize()));

				//---- pl1_style ----
				pl1_style.setModel(new DefaultComboBoxModel(new String[] {
					"Holz",
					"Stein"
				}));
				panel1.add(pl1_style);
				pl1_style.setBounds(35, 185, 110, 22);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel1.getComponentCount(); i++) {
						Rectangle bounds = panel1.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel1.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel1.setMinimumSize(preferredSize);
					panel1.setPreferredSize(preferredSize);
				}
			}
			tabbedPane1.addTab("Spieler 1", panel1);


			//======== panel2 ========
			{
				panel2.setLayout(null);

				//---- label5 ----
				label5.setText("Name");
				panel2.add(label5);
				label5.setBounds(20, 5, 45, 25);

				//---- pl2_name ----
				pl2_name.setText("Hans");
				panel2.add(pl2_name);
				pl2_name.setBounds(35, 25, 140, 20);

				//---- pl2_human ----
				pl2_human.setText("Mensch");
				pl2_human.setSelected(true);
				panel2.add(pl2_human);
				pl2_human.setBounds(30, 70, 80, 23);

				//---- pl2_ai ----
				pl2_ai.setText("KI");
				panel2.add(pl2_ai);
				pl2_ai.setBounds(30, 90, 80, 23);

				//---- pl2_color ----
				pl2_color.setModel(new DefaultComboBoxModel(new String[] {
					"Blau",
					"Rot",
					"Gr\u00fcn",
					"Gelb"
				}));
				panel2.add(pl2_color);
				pl2_color.setBounds(35, 140, 110, 22);

				//---- label6 ----
				label6.setText("Farbe");
				panel2.add(label6);
				label6.setBounds(20, 120, 40, 14);

				//---- label7 ----
				label7.setText("Spieler");
				panel2.add(label7);
				label7.setBounds(15, 50, 60, 25);

				//---- label8 ----
				label8.setText("Style");
				panel2.add(label8);
				label8.setBounds(20, 170, 24, 14);

				//---- pl2_style ----
				pl2_style.setModel(new DefaultComboBoxModel(new String[] {
					"Holz",
					"Stein"
				}));
				panel2.add(pl2_style);
				pl2_style.setBounds(35, 185, 110, 22);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel2.getComponentCount(); i++) {
						Rectangle bounds = panel2.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel2.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel2.setMinimumSize(preferredSize);
					panel2.setPreferredSize(preferredSize);
				}
			}
			tabbedPane1.addTab("Spieler 2", panel2);


			//======== panel3 ========
			{
				panel3.setLayout(null);

				//---- ai_aggressive ----
				ai_aggressive.setText("Aggressiv");
				ai_aggressive.setSelected(true);
				panel3.add(ai_aggressive);
				ai_aggressive.setBounds(25, 25, 80, 23);

				//---- ai_passive ----
				ai_passive.setText("Passiv");
				panel3.add(ai_passive);
				ai_passive.setBounds(25, 45, 80, 23);

				//---- board_style ----
				board_style.setModel(new DefaultComboBoxModel(new String[] {
					"Rot & Wei\u00df",
					"Gelb & Braun"
				}));
				panel3.add(board_style);
				board_style.setBounds(30, 95, 110, 22);

				//---- label10 ----
				label10.setText("Board Style");
				panel3.add(label10);
				label10.setBounds(25, 75, 95, 14);

				//---- label11 ----
				label11.setText("KI Modus");
				panel3.add(label11);
				label11.setBounds(20, 5, 60, 25);

				//---- label12 ----
				label12.setText("Spielmodus");
				panel3.add(label12);
				label12.setBounds(25, 125, 80, 14);

				//---- game_modus ----
				game_modus.setModel(new DefaultComboBoxModel(new String[] {
					"Normal",
					"Tric Trac",
					"Wurfzabel"
				}));
				panel3.add(game_modus);
				game_modus.setBounds(30, 140, 110, 22);

				//---- label13 ----
				label13.setText("Verdoppelw\u00fcrfel Style");
				panel3.add(label13);
				label13.setBounds(20, 170, 150, 14);

				//---- game_modus2 ----
				game_modus2.setModel(new DefaultComboBoxModel(new String[] {
					"Normal",
					"Tric Trac",
					"Wurfzabel"
				}));
				panel3.add(game_modus2);
				game_modus2.setBounds(30, 185, 110, 22);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel3.getComponentCount(); i++) {
						Rectangle bounds = panel3.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel3.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel3.setMinimumSize(preferredSize);
					panel3.setPreferredSize(preferredSize);
				}
			}
			tabbedPane1.addTab("Allgemeine Einstellungen", panel3);

		}
		contentPane.add(tabbedPane1);
		tabbedPane1.setBounds(10, 10, 370, 245);

		//---- button1 ----
		button1.setText("Spiel starten");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameStartActionPerformed(e);
			}
		});
		contentPane.add(button1);
		button1.setBounds(15, 260, 145, button1.getPreferredSize().height);

		//---- button2 ----
		button2.setText("Spiel Beenden");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEndActionPerformed(e);
			}
		});
		contentPane.add(button2);
		button2.setBounds(225, 260, 145, 23);

		contentPane.setPreferredSize(new Dimension(400, 330));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Nils Plaschke
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JLabel label1;
	private JTextField pl1_name;
	private JRadioButton pl1_human;
	private JRadioButton pl1_ai;
	private JComboBox pl1_color;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JComboBox pl1_style;
	private JPanel panel2;
	private JLabel label5;
	private JTextField pl2_name;
	private JRadioButton pl2_human;
	private JRadioButton pl2_ai;
	private JComboBox pl2_color;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JComboBox pl2_style;
	private JPanel panel3;
	private JRadioButton ai_aggressive;
	private JRadioButton ai_passive;
	private JComboBox board_style;
	private JLabel label10;
	private JLabel label11;
	private JLabel label12;
	private JComboBox game_modus;
	private JLabel label13;
	private JComboBox game_modus2;
	private JButton button1;
	private JButton button2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
