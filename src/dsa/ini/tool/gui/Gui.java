package dsa.ini.tool.gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dsa.ini.tool.administration.FighteAdministration;
import dsa.ini.tool.beans.Charakter;
import dsa.ini.tool.beans.Fighte;

public class Gui {

	private final FighteAdministration fA;
	private final Gui THIS = this;

	private JFrame frmDsaInisiativeKampf;
	private JSplitPane splitPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmAddCharacter;
	private JMenuItem mntmStartNewFighte;
	private JMenuItem mntmSaveFight;
	private JMenuItem mntmLoardFight;
	private JList<Charakter> list;
	private JScrollPane scrollPane;
	private SplitPanel2 panel;
	private JMenuItem mntmClose;

	/**
	 * Create the application.
	 */
	public Gui(FighteAdministration fa) {
		this.fA = fa;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDsaInisiativeKampf = new JFrame();
		frmDsaInisiativeKampf.setTitle("DSA initiative Kampf Tool");
		frmDsaInisiativeKampf.setBounds(100, 100, 707, 443);
		frmDsaInisiativeKampf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSplitPane(new JSplitPane());
		frmDsaInisiativeKampf.getContentPane().add(getSplitPane(), BorderLayout.CENTER);

		setList(new JList<Charakter>());
		getList().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getList().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int idx = getList().getSelectedIndex();
				if (idx != -1) {
					panel = getSelected().getSp();
					panel.getIniFeld().setText(getList().getSelectedValue().getIniAsString());
						panel.getHpBar().setText(getList().getSelectedValue().getHpAsString());
						panel.getLblName().setText(getList().getSelectedValue().getName());
						panel.getLblAt().setText("AT: ["+getSelected().getAt()+"]");
						panel.getLblPa().setText("PA: ["+getSelected().getPa()+"]");
						getSplitPane().setRightComponent(panel);
				}
			}
		});
		
		scrollPane = new JScrollPane(getList());
		getSplitPane().setLeftComponent(scrollPane);

		getSplitPane().setRightComponent(new JPanel());

		menuBar = new JMenuBar();
		frmDsaInisiativeKampf.setJMenuBar(menuBar);

		mnNewMenu = new JMenu("Fighte");
		menuBar.add(mnNewMenu);

		mntmAddCharacter = new JMenuItem("Add Character");
		mntmAddCharacter.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new addCharDialog(getfA(), THIS);
			}
		});

		mntmStartNewFighte = new JMenuItem("Start new Fighte");
		mntmStartNewFighte.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				getfA().setFighte(new Fighte());
				getfA().getGui().refreschList();
				getSplitPane().setRightComponent(new JPanel());
			}
		});
		mnNewMenu.add(mntmStartNewFighte);
		mnNewMenu.add(mntmAddCharacter);
		
		mntmLoardFight = new JMenuItem("Load Fight");
		mntmLoardFight.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ObjectMapper mapper = new ObjectMapper();

				String s = "{\"charakters\":[{\"name\":\"may\",\"ini\":1,\"hp\":0,\"mu\":0,\"kl\":0,\"in\":0,\"ch\":0,\"ff\":0,\"ge\":0,\"ko\":0,\"kk\":0,\"at\":0,\"pa\":0}]}";
				
				try {
					Fighte f = mapper.readValue(s, dsa.ini.tool.beans.Fighte.class);
					getfA().setFighte(f);
					
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				refreschList();
			}
		});
		mnNewMenu.add(mntmLoardFight);
		
		mntmSaveFight = new JMenuItem("Save Fight");
		mntmSaveFight.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//SaveFileWindow sfw = new SaveFileWindow(getfA().getFighte());
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(new JDialog());
				
				if (result == JFileChooser.APPROVE_OPTION) {
					ObjectMapper mapper = new ObjectMapper();
					
					try {
						mapper.writeValue(fileChooser.getSelectedFile(), getfA().getFighte());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		mnNewMenu.add(mntmSaveFight);
		
		mntmClose = new JMenuItem("Close");
		mntmClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Runtime.getRuntime().exit(0);
			}
		});
		mnNewMenu.add(mntmClose);
		
		frmDsaInisiativeKampf.setVisible(true);
	}

	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	public void refreschList() {
		Collection<Charakter> all = getfA().getFighte().getALL();
		Collections.sort((java.util.List<Charakter>) all);
		Charakter ch = getList().getSelectedValue();

		getList().setModel(new AbstractListModel() {
			Charakter[] values = all.toArray(new Charakter[all.size()]);

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

		getList().setSelectedValue(ch, true);
		//scrollPane.updateUI();
	}

	public JList<Charakter> getList() {
		return list;
	}

	public void setList(JList<Charakter> list) {
		this.list = list;
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}
	
	private Charakter getSelected() {
		return getList().getSelectedValue();
	}

	public FighteAdministration getfA() {
		return fA;
	}

}
