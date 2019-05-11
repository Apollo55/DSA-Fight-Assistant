package dsa.ini.tool.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import dsa.ini.tool.administration.FighteAdministration;
import dsa.ini.tool.beans.Charakter;

public class SplitPanel2 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8607020418519984481L;
	private final FighteAdministration FA;
	private final Gui GUI;

	private JFormattedTextField iniFeld;
	private JLabel lblIniziative;
	private JLabel lblName;
	private JButton btnDel;
	private JButton btnClone;

	/**
	 * Create the panel.
	 */
	public SplitPanel2(FighteAdministration fa, Gui gui) {
		FA = fa;
		GUI = gui;

		setIniFeld(new JFormattedTextField(NumberFormat.getInstance()));

		setLblName(new JLabel(""));
		getIniFeld().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == 10) {
					GUI.getList().getSelectedValue().setIni(Integer.parseInt(getIniFeld().getText()));
					GUI.refreschList();
				}
			}
		});
		getIniFeld().setColumns(10);

		lblIniziative = new JLabel("Ini-Wert:");
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.EAST, lblName, 0, SpringLayout.EAST, iniFeld);
		springLayout.putConstraint(SpringLayout.WEST, iniFeld, 16, SpringLayout.EAST, lblIniziative);
		springLayout.putConstraint(SpringLayout.EAST, iniFeld, -189, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblName, 33, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblIniziative, 101, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, iniFeld, -3, SpringLayout.NORTH, lblIniziative);
		setLayout(springLayout);
		add(lblName);

		btnDel = new JButton("L\u00F6schen");
		springLayout.putConstraint(SpringLayout.WEST, lblName, 0, SpringLayout.WEST, btnDel);
		springLayout.putConstraint(SpringLayout.WEST, lblIniziative, 0, SpringLayout.WEST, btnDel);
		springLayout.putConstraint(SpringLayout.WEST, btnDel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnDel, -10, SpringLayout.SOUTH, this);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				FA.getFighte().deleteChar(GUI.getList().getSelectedValue());
				GUI.getSplitPane().setRightComponent(new JPanel());
				GUI.refreschList();
			}
		});
		add(btnDel);
		add(iniFeld);
		add(lblIniziative);
		{
			btnClone = new JButton("Clone");
			btnClone.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					Collection<Charakter> chars = FA.getFighte().getALL();
					int i = 0;
					for (Charakter ch : chars) {
						if (ch.getName().contains(GUI.getList().getSelectedValue().getName()))
							i++;
					}

					FA.getFighte().addChar(new Charakter(GUI.getList().getSelectedValue().getName() + i,
							GUI.getList().getSelectedValue().getIniAsString()));
					GUI.refreschList();

				}
			});
			springLayout.putConstraint(SpringLayout.WEST, btnClone, 6, SpringLayout.EAST, btnDel);
			springLayout.putConstraint(SpringLayout.SOUTH, btnClone, 0, SpringLayout.SOUTH, btnDel);
			springLayout.putConstraint(SpringLayout.EAST, btnClone, 85, SpringLayout.EAST, btnDel);
			add(btnClone);
		}

	}

	public JFormattedTextField getIniFeld() {
		return iniFeld;
	}

	public void setIniFeld(JFormattedTextField iniFeld) {
		this.iniFeld = iniFeld;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}
}
