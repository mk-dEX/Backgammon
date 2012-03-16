/*
 * Created by JFormDesigner on Fri Mar 16 15:41:56 CET 2012
 */

package backgammon.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import backgammon.app.GameAgent;
import backgammon.app.GameSettings;

import com.jgoodies.forms.factories.*;

/**
 * @author Nils Plaschke
 */
public class BackgammonViewSettings extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private GameAgent controller;
	
	public BackgammonViewSettings() {
		initComponents();
	}

	public BackgammonViewSettings(GameAgent controller) {
		
		this.controller = controller;
		
		initComponents();
		
		this.setVisible(true);
	}
	
	private void pl1_computerFocusGained(FocusEvent e) {
		//show ki mode
		layeredPane7.setVisible(true);
	}

	private void pl1_humanFocusGained(FocusEvent e) {
		layeredPane7.setVisible(false);
	}

	private void GameStart(ActionEvent e) {

		
		GameSettings config = this.handleInput();
		
		this.closeAll();
		
		this.controller.updateSettings(config);
	}

	private GameSettings handleInput() {

		GameSettings c = new GameSettings();
		
		//set names
		String pl1_name = this.pl1_name.getText().isEmpty() ? this.pl1_name.getText() : "Hans";
		String pl2_name = this.pl2_name.getText().isEmpty() ? this.pl2_name.getText() : "Wurst";

		c.setNamePlayer1(pl1_name);
		c.setNamePlayer2(pl2_name);
		
		//Game mode
		if(this.modus_normal.isSelected())
			c.setSelectedGameMode(GameSettings.gameMode.STANDARD);
		if(this.modus_trictrac.isSelected())
			c.setSelectedGameMode(GameSettings.gameMode.TRIC_TRAC);
		if(this.modus_wurfzabel.isSelected())
			c.setSelectedGameMode(GameSettings.gameMode.WURFZABEL);
		
		//set boardImage
		String boardImage = "";
		
		if(this.color_blackred.isSelected())
			boardImage = "BlackRed";
		if(this.color_bluewhite.isSelected())
			boardImage = "BlueWhite";
		if(this.color_redwhite.isSelected())
			boardImage = "RedWhite";
		
		if(this.style_standard.isSelected())
			boardImage += "Standard";
		if(this.style_holz.isSelected())
			boardImage += "Holz";
		if(this.style_stein.isSelected())
			boardImage += "Stein";
		
		boardImage += ".png";
		
		c.setPathBoard(boardImage);
		
		//ki and mode
		
		//pl1
		if(this.pl1_human.isSelected())
			c.setSelectedKIModePlayer1(GameSettings.KIMode.HUMAN);
		else
		{
			if(this.pl1_computer_agressive.isSelected())
				c.setSelectedKIModePlayer1(GameSettings.KIMode.AGGRESSIVE);
			else
				c.setSelectedKIModePlayer1(GameSettings.KIMode.PASSIVE);
		}
		
		//pl2
		if(this.pl2_human.isSelected())
			c.setSelectedKIModePlayer2(GameSettings.KIMode.HUMAN);
		else
		{
			if(this.pl2_computer_agressive.isSelected())
				c.setSelectedKIModePlayer2(GameSettings.KIMode.AGGRESSIVE);
			else
				c.setSelectedKIModePlayer2(GameSettings.KIMode.PASSIVE);
		}
		
		
		String checker1 = "";
		//Checker 
		switch (this.pl1_checker_color.getSelectedIndex()) 
		{
			case 0: checker1 = "weiss";break;
			case 1:	checker1 = "schwarz";break;
			case 2:	checker1 = "blau";break;
			case 3:	checker1 = "rot";break;
			case 4:	checker1 = "gruen";break;
			default: checker1 = "weiss";break;
		}
		
		c.setPathDicePlayer1(checker1 + "Dice.png");
		
		switch (this.pl1_checker_style.getSelectedIndex()) 
		{
			case 0: checker1 += "Holz";break;
			case 1:	checker1 += "Stein";break;
			default: checker1 += "Holz";break;
		}
		
		String checker2 = "";
		//Checker 
		switch (this.pl2_checker_color.getSelectedIndex()) 
		{
			case 0: checker2 = "weiss";break;
			case 1:	checker2 = "schwarz";break;
			case 2:	checker2 = "blau";break;
			case 3:	checker2 = "rot";break;
			case 4:	checker2 = "gruen";break;
			default: checker2 = "schwarz";break;
		}
		
		c.setPathDicePlayer2(checker2 + "Dice.png");
		
		switch (this.pl2_checker_style.getSelectedIndex()) 
		{
			case 0: checker2 += "Holz";break;
			case 1:	checker2 += "Stein";break;
			default: checker2 += "Stein";break;
		}
		
		c.setPathCheckerPlayer1(checker1 + "Checker.png");
		c.setPathCheckerPlayer2(checker2 + "Checker.png");
		
		String dd = "";
		
		if(this.dd_style_holz.isSelected())
			dd = "holz";
		else if(this.dd_style_standard.isSelected())
			dd = "standard";
		else
			dd = "stein";
		
		c.setPathDiceDouble(dd + "DDice.png");
		
		
		return c;
	}

	private void closeAll() {
		this.setVisible(false);
		this.dispose();
	}

	private void EndGame(ActionEvent e) {
		System.exit(0);
	}

	private void Help(ActionEvent e) {
		// TODO add your code here
	}

	private void pl2_computerFocusGained(FocusEvent e) {
		layeredPane13.setVisible(true);
	}
	private void pl2_humanFocusGained(FocusEvent e) {
		layeredPane13.setVisible(false);
	}

	private void pl1_computerFocusLost(FocusEvent e) {
		// TODO add your code here
	}

	private void pl2_computerFocusLost(FocusEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Nils Plaschke
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		layeredPane1 = new JLayeredPane();
		modus_normal = new JRadioButton();
		modus_trictrac = new JRadioButton();
		modus_wurfzabel = new JRadioButton();
		layeredPane2 = new JLayeredPane();
		style_standard = new JRadioButton();
		style_holz = new JRadioButton();
		style_stein = new JRadioButton();
		layeredPane3 = new JLayeredPane();
		color_blackred = new JRadioButton();
		color_bluewhite = new JRadioButton();
		color_redwhite = new JRadioButton();
		layeredPane9 = new JLayeredPane();
		dd_style_standard = new JRadioButton();
		dd_style_holz = new JRadioButton();
		dd_style_stein = new JRadioButton();
		panel3 = new JPanel();
		layeredPane4 = new JLayeredPane();
		pl1_name = new JTextField();
		layeredPane5 = new JLayeredPane();
		pl1_computer = new JRadioButton();
		pl1_human = new JRadioButton();
		layeredPane6 = new JLayeredPane();
		pl1_checker_color = new JComboBox();
		layeredPane7 = new JLayeredPane();
		pl1_computer_passive = new JRadioButton();
		pl1_computer_agressive = new JRadioButton();
		layeredPane8 = new JLayeredPane();
		pl1_checker_style = new JComboBox();
		panel4 = new JPanel();
		layeredPane10 = new JLayeredPane();
		pl2_name = new JTextField();
		layeredPane11 = new JLayeredPane();
		pl2_computer = new JRadioButton();
		pl2_human = new JRadioButton();
		layeredPane12 = new JLayeredPane();
		pl2_checker_color = new JComboBox();
		layeredPane13 = new JLayeredPane();
		pl2_computer_passive = new JRadioButton();
		pl2_computer_agressive = new JRadioButton();
		layeredPane14 = new JLayeredPane();
		pl2_checker_style = new JComboBox();
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();

		//======== this ========
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Spieleinstellungen");
		setVisible(true);
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

				//======== layeredPane1 ========
				{
					layeredPane1.setBorder(new CompoundBorder(
						new TitledBorder("Spielmodus"),
						Borders.DLU2_BORDER));
					layeredPane1.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- modus_normal ----
					modus_normal.setText("Normal");
					modus_normal.setSelected(true);
					layeredPane1.add(modus_normal, JLayeredPane.DEFAULT_LAYER);
					modus_normal.setBounds(10, 20, 70, modus_normal.getPreferredSize().height);

					//---- modus_trictrac ----
					modus_trictrac.setText("Tric Trac");
					layeredPane1.add(modus_trictrac, JLayeredPane.DEFAULT_LAYER);
					modus_trictrac.setBounds(10, 45, 85, 23);

					//---- modus_wurfzabel ----
					modus_wurfzabel.setText("Wurfzabel");
					layeredPane1.add(modus_wurfzabel, JLayeredPane.DEFAULT_LAYER);
					modus_wurfzabel.setBounds(10, 70, 110, 23);
				}
				panel1.add(layeredPane1);
				layeredPane1.setBounds(10, 5, 155, 100);

				//======== layeredPane2 ========
				{
					layeredPane2.setBorder(new CompoundBorder(
						new TitledBorder("Boardstyle"),
						Borders.DLU2_BORDER));
					layeredPane2.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- style_standard ----
					style_standard.setText("Standard");
					style_standard.setSelected(true);
					layeredPane2.add(style_standard, JLayeredPane.DEFAULT_LAYER);
					style_standard.setBounds(10, 20, 95, style_standard.getPreferredSize().height);

					//---- style_holz ----
					style_holz.setText("Holz");
					layeredPane2.add(style_holz, JLayeredPane.DEFAULT_LAYER);
					style_holz.setBounds(10, 45, 85, 23);

					//---- style_stein ----
					style_stein.setText("Stein");
					layeredPane2.add(style_stein, JLayeredPane.DEFAULT_LAYER);
					style_stein.setBounds(10, 70, 75, 23);
				}
				panel1.add(layeredPane2);
				layeredPane2.setBounds(180, 5, 165, 100);

				//======== layeredPane3 ========
				{
					layeredPane3.setBorder(new CompoundBorder(
						new TitledBorder("Boardfarbe"),
						Borders.DLU2_BORDER));
					layeredPane3.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- color_blackred ----
					color_blackred.setText("Schwarz + Rot");
					color_blackred.setSelected(true);
					layeredPane3.add(color_blackred, JLayeredPane.DEFAULT_LAYER);
					color_blackred.setBounds(10, 20, 130, color_blackred.getPreferredSize().height);

					//---- color_bluewhite ----
					color_bluewhite.setText("Blau + Wei\u00df");
					layeredPane3.add(color_bluewhite, JLayeredPane.DEFAULT_LAYER);
					color_bluewhite.setBounds(10, 45, 135, 23);

					//---- color_redwhite ----
					color_redwhite.setText("Rot + Wei\u00df");
					layeredPane3.add(color_redwhite, JLayeredPane.DEFAULT_LAYER);
					color_redwhite.setBounds(10, 70, 135, 23);
				}
				panel1.add(layeredPane3);
				layeredPane3.setBounds(10, 110, 155, 100);

				//======== layeredPane9 ========
				{
					layeredPane9.setBorder(new CompoundBorder(
						new TitledBorder("Doppelw\u00fcrfelstyle"),
						Borders.DLU2_BORDER));
					layeredPane9.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- dd_style_standard ----
					dd_style_standard.setText("Standard");
					dd_style_standard.setSelected(true);
					layeredPane9.add(dd_style_standard, JLayeredPane.DEFAULT_LAYER);
					dd_style_standard.setBounds(10, 20, 95, dd_style_standard.getPreferredSize().height);

					//---- dd_style_holz ----
					dd_style_holz.setText("Holz");
					layeredPane9.add(dd_style_holz, JLayeredPane.DEFAULT_LAYER);
					dd_style_holz.setBounds(10, 45, 85, 23);

					//---- dd_style_stein ----
					dd_style_stein.setText("Stein");
					layeredPane9.add(dd_style_stein, JLayeredPane.DEFAULT_LAYER);
					dd_style_stein.setBounds(10, 70, 75, 23);
				}
				panel1.add(layeredPane9);
				layeredPane9.setBounds(185, 110, 165, 100);
			}
			tabbedPane1.addTab("Allgemeine Einstellungen", panel1);


			//======== panel3 ========
			{
				panel3.setLayout(null);

				//======== layeredPane4 ========
				{
					layeredPane4.setBorder(new CompoundBorder(
						new TitledBorder("Spielername"),
						Borders.DLU2_BORDER));
					layeredPane4.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- pl1_name ----
					pl1_name.setText("Hans");
					layeredPane4.add(pl1_name, JLayeredPane.DEFAULT_LAYER);
					pl1_name.setBounds(15, 20, 125, pl1_name.getPreferredSize().height);
				}
				panel3.add(layeredPane4);
				layeredPane4.setBounds(10, 5, 155, 55);

				//======== layeredPane5 ========
				{
					layeredPane5.setBorder(new CompoundBorder(
						new TitledBorder("Wer spielt?"),
						Borders.DLU2_BORDER));
					layeredPane5.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- pl1_computer ----
					pl1_computer.setText("Computer");
					pl1_computer.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							pl1_computerFocusGained(e);
						}
						@Override
						public void focusLost(FocusEvent e) {
							pl1_computerFocusLost(e);
						}
					});
					layeredPane5.add(pl1_computer, JLayeredPane.DEFAULT_LAYER);
					pl1_computer.setBounds(15, 45, 95, pl1_computer.getPreferredSize().height);

					//---- pl1_human ----
					pl1_human.setText("Mensch");
					pl1_human.setSelected(true);
					pl1_human.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							pl1_humanFocusGained(e);
							pl1_humanFocusGained(e);
						}
					});
					layeredPane5.add(pl1_human, JLayeredPane.DEFAULT_LAYER);
					pl1_human.setBounds(15, 20, 90, pl1_human.getPreferredSize().height);
				}
				panel3.add(layeredPane5);
				layeredPane5.setBounds(10, 65, 155, 75);

				//======== layeredPane6 ========
				{
					layeredPane6.setBorder(new CompoundBorder(
						new TitledBorder("Checkerfarbe"),
						Borders.DLU2_BORDER));
					layeredPane6.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- pl1_checker_color ----
					pl1_checker_color.setModel(new DefaultComboBoxModel(new String[] {
						"Wei\u00df",
						"Schwarz",
						"Blau",
						"Rot",
						"Gr\u00fcn"
					}));
					layeredPane6.add(pl1_checker_color, JLayeredPane.DEFAULT_LAYER);
					pl1_checker_color.setBounds(15, 20, 130, 20);
				}
				panel3.add(layeredPane6);
				layeredPane6.setBounds(190, 5, 155, 50);

				//======== layeredPane7 ========
				{
					layeredPane7.setBorder(new CompoundBorder(
						new TitledBorder("Wie spielt der Computer?"),
						Borders.DLU2_BORDER));
					layeredPane7.setFont(new Font("Tahoma", Font.PLAIN, 10));
					layeredPane7.setVisible(false);

					//---- pl1_computer_passive ----
					pl1_computer_passive.setText("Passiv");
					layeredPane7.add(pl1_computer_passive, JLayeredPane.DEFAULT_LAYER);
					pl1_computer_passive.setBounds(15, 45, 95, pl1_computer_passive.getPreferredSize().height);

					//---- pl1_computer_agressive ----
					pl1_computer_agressive.setText("Agressiv");
					pl1_computer_agressive.setSelected(true);
					layeredPane7.add(pl1_computer_agressive, JLayeredPane.DEFAULT_LAYER);
					pl1_computer_agressive.setBounds(15, 20, 90, pl1_computer_agressive.getPreferredSize().height);
				}
				panel3.add(layeredPane7);
				layeredPane7.setBounds(10, 140, 155, 75);

				//======== layeredPane8 ========
				{
					layeredPane8.setBorder(new CompoundBorder(
						new TitledBorder("Checkerstyle"),
						Borders.DLU2_BORDER));
					layeredPane8.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- pl1_checker_style ----
					pl1_checker_style.setModel(new DefaultComboBoxModel(new String[] {
						"Stein",
						"Holz"
					}));
					layeredPane8.add(pl1_checker_style, JLayeredPane.DEFAULT_LAYER);
					pl1_checker_style.setBounds(15, 20, 130, 20);
				}
				panel3.add(layeredPane8);
				layeredPane8.setBounds(190, 60, 155, 50);
			}
			tabbedPane1.addTab("Spieler 1", panel3);


			//======== panel4 ========
			{
				panel4.setLayout(null);

				//======== layeredPane10 ========
				{
					layeredPane10.setBorder(new CompoundBorder(
						new TitledBorder("Spielername"),
						Borders.DLU2_BORDER));
					layeredPane10.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- pl2_name ----
					pl2_name.setText("Wurst");
					layeredPane10.add(pl2_name, JLayeredPane.DEFAULT_LAYER);
					pl2_name.setBounds(15, 20, 125, pl2_name.getPreferredSize().height);
				}
				panel4.add(layeredPane10);
				layeredPane10.setBounds(10, 5, 155, 55);

				//======== layeredPane11 ========
				{
					layeredPane11.setBorder(new CompoundBorder(
						new TitledBorder("Wer spielt?"),
						Borders.DLU2_BORDER));
					layeredPane11.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- pl2_computer ----
					pl2_computer.setText("Computer");
					pl2_computer.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							pl1_computerFocusGained(e);
							pl2_computerFocusGained(e);
						}
						@Override
						public void focusLost(FocusEvent e) {
							pl1_computerFocusLost(e);
							pl2_computerFocusLost(e);
						}
					});
					layeredPane11.add(pl2_computer, JLayeredPane.DEFAULT_LAYER);
					pl2_computer.setBounds(15, 45, 95, pl2_computer.getPreferredSize().height);

					//---- pl2_human ----
					pl2_human.setText("Mensch");
					pl2_human.setSelected(true);
					pl2_human.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							pl2_humanFocusGained(e);
						}
					});
					layeredPane11.add(pl2_human, JLayeredPane.DEFAULT_LAYER);
					pl2_human.setBounds(15, 20, 90, pl2_human.getPreferredSize().height);
				}
				panel4.add(layeredPane11);
				layeredPane11.setBounds(10, 65, 155, 75);

				//======== layeredPane12 ========
				{
					layeredPane12.setBorder(new CompoundBorder(
						new TitledBorder("Checkerfarbe"),
						Borders.DLU2_BORDER));
					layeredPane12.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- pl2_checker_color ----
					pl2_checker_color.setModel(new DefaultComboBoxModel(new String[] {
						"Schwarz",
						"Wei\u00df",
						"Blau",
						"Rot",
						"Gr\u00fcn"
					}));
					layeredPane12.add(pl2_checker_color, JLayeredPane.DEFAULT_LAYER);
					pl2_checker_color.setBounds(15, 20, 130, pl2_checker_color.getPreferredSize().height);
				}
				panel4.add(layeredPane12);
				layeredPane12.setBounds(190, 5, 155, 50);

				//======== layeredPane13 ========
				{
					layeredPane13.setBorder(new CompoundBorder(
						new TitledBorder("Wie spielt der Computer?"),
						Borders.DLU2_BORDER));
					layeredPane13.setFont(new Font("Tahoma", Font.PLAIN, 10));
					layeredPane13.setVisible(false);

					//---- pl2_computer_passive ----
					pl2_computer_passive.setText("Passiv");
					layeredPane13.add(pl2_computer_passive, JLayeredPane.DEFAULT_LAYER);
					pl2_computer_passive.setBounds(15, 45, 95, pl2_computer_passive.getPreferredSize().height);

					//---- pl2_computer_agressive ----
					pl2_computer_agressive.setText("Agressiv");
					pl2_computer_agressive.setSelected(true);
					layeredPane13.add(pl2_computer_agressive, JLayeredPane.DEFAULT_LAYER);
					pl2_computer_agressive.setBounds(15, 20, 90, pl2_computer_agressive.getPreferredSize().height);
				}
				panel4.add(layeredPane13);
				layeredPane13.setBounds(10, 140, 155, 75);

				//======== layeredPane14 ========
				{
					layeredPane14.setBorder(new CompoundBorder(
						new TitledBorder("Checkerstyle"),
						Borders.DLU2_BORDER));
					layeredPane14.setFont(new Font("Tahoma", Font.PLAIN, 10));

					//---- pl2_checker_style ----
					pl2_checker_style.setModel(new DefaultComboBoxModel(new String[] {
						"Holz",
						"Stein"
					}));
					layeredPane14.add(pl2_checker_style, JLayeredPane.DEFAULT_LAYER);
					pl2_checker_style.setBounds(15, 20, 130, 20);
				}
				panel4.add(layeredPane14);
				layeredPane14.setBounds(190, 60, 155, 50);
			}
			tabbedPane1.addTab("Spieler 2", panel4);

		}
		contentPane.add(tabbedPane1);
		tabbedPane1.setBounds(15, 15, 365, 245);

		//---- button1 ----
		button1.setText("Spiel Starten");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameStart(e);
			}
		});
		contentPane.add(button1);
		button1.setBounds(25, 270, 130, 30);

		//---- button2 ----
		button2.setText("Spiel Beenden");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EndGame(e);
			}
		});
		contentPane.add(button2);
		button2.setBounds(235, 270, 130, 30);

		//---- button3 ----
		button3.setText("?");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Help(e);
			}
		});
		contentPane.add(button3);
		button3.setBounds(165, 270, 60, 30);

		contentPane.setPreferredSize(new Dimension(400, 330));
		pack();
		setLocationRelativeTo(getOwner());

		//---- game_modus ----
		ButtonGroup game_modus = new ButtonGroup();
		game_modus.add(modus_normal);
		game_modus.add(modus_trictrac);
		game_modus.add(modus_wurfzabel);

		//---- board_style ----
		ButtonGroup board_style = new ButtonGroup();
		board_style.add(style_standard);
		board_style.add(style_holz);
		board_style.add(style_stein);

		//---- board_color ----
		ButtonGroup board_color = new ButtonGroup();
		board_color.add(color_blackred);
		board_color.add(color_bluewhite);
		board_color.add(color_redwhite);

		//---- doubleDice ----
		ButtonGroup doubleDice = new ButtonGroup();
		doubleDice.add(dd_style_standard);
		doubleDice.add(dd_style_holz);
		doubleDice.add(dd_style_stein);

		//---- pl1_player ----
		ButtonGroup pl1_player = new ButtonGroup();
		pl1_player.add(pl1_computer);
		pl1_player.add(pl1_human);

		//---- pl1_computer_mode ----
		ButtonGroup pl1_computer_mode = new ButtonGroup();
		pl1_computer_mode.add(pl1_computer_passive);
		pl1_computer_mode.add(pl1_computer_agressive);

		//---- pl2_player ----
		ButtonGroup pl2_player = new ButtonGroup();
		pl2_player.add(pl2_computer);
		pl2_player.add(pl2_human);

		//---- pl2_computer_mode ----
		ButtonGroup pl2_computer_mode = new ButtonGroup();
		pl2_computer_mode.add(pl2_computer_passive);
		pl2_computer_mode.add(pl2_computer_agressive);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Nils Plaschke
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JLayeredPane layeredPane1;
	private JRadioButton modus_normal;
	private JRadioButton modus_trictrac;
	private JRadioButton modus_wurfzabel;
	private JLayeredPane layeredPane2;
	private JRadioButton style_standard;
	private JRadioButton style_holz;
	private JRadioButton style_stein;
	private JLayeredPane layeredPane3;
	private JRadioButton color_blackred;
	private JRadioButton color_bluewhite;
	private JRadioButton color_redwhite;
	private JLayeredPane layeredPane9;
	private JRadioButton dd_style_standard;
	private JRadioButton dd_style_holz;
	private JRadioButton dd_style_stein;
	private JPanel panel3;
	private JLayeredPane layeredPane4;
	private JTextField pl1_name;
	private JLayeredPane layeredPane5;
	private JRadioButton pl1_computer;
	private JRadioButton pl1_human;
	private JLayeredPane layeredPane6;
	private JComboBox pl1_checker_color;
	private JLayeredPane layeredPane7;
	private JRadioButton pl1_computer_passive;
	private JRadioButton pl1_computer_agressive;
	private JLayeredPane layeredPane8;
	private JComboBox pl1_checker_style;
	private JPanel panel4;
	private JLayeredPane layeredPane10;
	private JTextField pl2_name;
	private JLayeredPane layeredPane11;
	private JRadioButton pl2_computer;
	private JRadioButton pl2_human;
	private JLayeredPane layeredPane12;
	private JComboBox pl2_checker_color;
	private JLayeredPane layeredPane13;
	private JRadioButton pl2_computer_passive;
	private JRadioButton pl2_computer_agressive;
	private JLayeredPane layeredPane14;
	private JComboBox pl2_checker_style;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
