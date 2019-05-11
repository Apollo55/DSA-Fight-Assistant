package dsa.ini.tool.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dsa.ini.tool.administration.FighteAdministration;
import dsa.ini.tool.beans.Charakter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class addCharDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8495887757335942559L;

	private final JPanel contentPanel = new JPanel();

	private JTextField txtName;
	private JFormattedTextField txtIni;

	/**
	 * Create the dialog.
	 */
	public addCharDialog(FighteAdministration fa, Gui gui) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

		setBounds(100, 100, 258, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		txtIni = new JFormattedTextField(NumberFormat.getInstance());
		{
			JLabel lblName = new JLabel("Name");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 1;
			gbc_lblName.gridy = 2;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			JLabel lblIniwert = new JLabel("Ini-Wert");
			GridBagConstraints gbc_lblIniwert = new GridBagConstraints();
			gbc_lblIniwert.insets = new Insets(0, 0, 5, 5);
			gbc_lblIniwert.gridx = 5;
			gbc_lblIniwert.gridy = 2;
			contentPanel.add(lblIniwert, gbc_lblIniwert);
		}
		txtName = new JTextField();
		txtName.setToolTipText("Name");
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.gridwidth = 3;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 3;
		contentPanel.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		txtIni.setText("");
		txtIni.setToolTipText("Ini");
		GridBagConstraints gbc_txtIni = new GridBagConstraints();
		gbc_txtIni.insets = new Insets(0, 0, 5, 5);
		gbc_txtIni.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIni.gridx = 5;
		gbc_txtIni.gridy = 3;
		contentPanel.add(txtIni, gbc_txtIni);
		txtIni.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						okButton.disable();
						fa.getFighte().addChar(new Charakter(txtName.getText(), txtIni.getText()));
						gui.refreschList();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
