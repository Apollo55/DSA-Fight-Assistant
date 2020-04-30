package dsa.ini.tool.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import dsa.ini.tool.administration.FighteAdministration;
import dsa.ini.tool.beans.Charakter;

import javax.swing.JCheckBox;
import java.text.Format;
import javax.swing.JTextPane;

public class SplitPanel2 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8607020418519984481L;
	private final FighteAdministration FA;
	private final Gui GUI;
	
	private Charakter ch = new Charakter("dummy", "1", this);

	private JFormattedTextField iniFeld;
	private JLabel lblIniziative;
	private JLabel lblName;
	private JButton btnDel;
	private JButton btnClone;
	private JTextPane txtpnNotes;
	private JFormattedTextField hpBar;
	private JCheckBox head1;
	private JCheckBox head2;
	private JCheckBox head3;
	private JCheckBox chest1;
	private JCheckBox chest2;
	private JCheckBox chest3;
	private JCheckBox rightArm1;
	private JCheckBox rightArm2;
	private JCheckBox rightArm3;
	private JCheckBox leftArm1;
	private JCheckBox leftArm2;
	private JCheckBox leftArm3;
	private JCheckBox rightLeg1;
	private JCheckBox rightLeg2;
	private JCheckBox rightLeg3;
	private JCheckBox leftLeg1;
	private JCheckBox leftLeg2;
	private JCheckBox leftLeg3;
	private JLabel lblAt;
	private JLabel lblPa;
	private JLabel lblMu;
	private JLabel lblKl;
	private JLabel lblIn;
	private JLabel lblCh;
	private JLabel lblFf;
	private JLabel lblKk;
	private JLabel lblGe;
	private JLabel lblKo;

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
					ch.setIni(Integer.parseInt(getIniFeld().getText()));
					GUI.refreschList();
				}
			}
		});
		getIniFeld().setColumns(10);
		
		lblIniziative = new JLabel("Ini-Wert:");
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, iniFeld, 16, SpringLayout.EAST, lblIniziative);
		springLayout.putConstraint(SpringLayout.WEST, lblName, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblName, -189, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblName, 33, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblIniziative, 101, SpringLayout.NORTH, this);
		setLayout(springLayout);
		add(lblName);

		btnDel = new JButton("L\u00F6schen");
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
				FA.getFighte().deleteChar(ch);
				GUI.getSplitPane().setRightComponent(new JPanel());
				GUI.refreschList();
				
				
			}
		});
		add(btnDel);
		add(iniFeld);
		add(lblIniziative);
		{
			btnClone = new JButton("Clone");
			springLayout.putConstraint(SpringLayout.EAST, iniFeld, 0, SpringLayout.EAST, btnClone);
			btnClone.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					Collection<Charakter> chars = FA.getFighte().getALL();
					int i = 0;
					String[] sAr = ch.getName().split(" ");
					for (Charakter chs : chars) {
						if (chs.getName().contains(sAr[0]))
							i++;
					}
					
					FA.getFighte().addChar(new Charakter(sAr[0] + " " +(i+1),
							ch.getIniAsString(), new SplitPanel2(fa, gui)));
					
					if(i==1)
						ch.setName(sAr[0] + " 1");
					
					GUI.refreschList();

				}
			});
			springLayout.putConstraint(SpringLayout.WEST, btnClone, 6, SpringLayout.EAST, btnDel);
			springLayout.putConstraint(SpringLayout.SOUTH, btnClone, 0, SpringLayout.SOUTH, btnDel);
			springLayout.putConstraint(SpringLayout.EAST, btnClone, 85, SpringLayout.EAST, btnDel);
			add(btnClone);
		}
		
		ItemListener headLisener = new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					ch.setMu(ch.getMu()-2);
					ch.setKl(ch.getKl()-2);
					ch.setIn(ch.getIn()-2);
					
					ch.setIni(ch.getIni()-2);
					
					ch.setIni(ch.getIni() - (int)(Math.random()*6+1) - (int)(Math.random()*6+1)); 		
				}
				
				else
				{
					ch.setMu(ch.getMu()+2);
					ch.setKl(ch.getKl()+2);
					ch.setIn(ch.getIn()+2);
					
				}
				
				GUI.refreschList();
			}
		};
	
		setHead1(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getHead1(), 59, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, getHead1(), -93, SpringLayout.EAST, this);
		getHead1().addItemListener(headLisener);
		add(getHead1());
		
		setHead2(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getHead2(), 0, SpringLayout.NORTH, getHead1());
		springLayout.putConstraint(SpringLayout.WEST, getHead2(), 8, SpringLayout.EAST, getHead1());
		getHead2().addItemListener(headLisener);
		add(getHead2());
		
		setHead3(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getHead3(), 0, SpringLayout.NORTH, getHead1());
		springLayout.putConstraint(SpringLayout.WEST, getHead3(), 6, SpringLayout.EAST, getHead2());
		getHead3().addItemListener(headLisener);
		add(getHead3());
		
		ItemListener chestListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					ch.setAt(ch.getAt()-1);
					ch.setPa(ch.getPa()-1);
					ch.setKo(ch.getKo()-1);
					ch.setKk(ch.getKk()-1);
				}
				
				else
				{
					ch.setAt(ch.getAt()+1);
					ch.setPa(ch.getPa()+1);
					ch.setKo(ch.getKo()+1);
					ch.setKk(ch.getKk()+1);
				}
				
				GUI.refreschList();
				
			}
		};
		
		setChest1(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getChest1(), 6, SpringLayout.SOUTH, getHead1());
		springLayout.putConstraint(SpringLayout.EAST, getChest1(), 0, SpringLayout.EAST, getHead1());
		getChest1().addItemListener(chestListener);
		add(getChest1());
		
		setChest2(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getChest2(), 6, SpringLayout.SOUTH, getHead2());
		springLayout.putConstraint(SpringLayout.WEST, getChest2(), 0, SpringLayout.WEST, getHead2());
		getChest2().addItemListener(chestListener);
		add(getChest2());
		
		setChest3(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getChest3(), 6, SpringLayout.SOUTH, getHead3());
		springLayout.putConstraint(SpringLayout.WEST, getChest3(), 0, SpringLayout.WEST, getHead3());
		getChest3().addItemListener(chestListener);
		add(getChest3());
		
		ItemListener ArmListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					ch.setAt(ch.getAt()-2);
					ch.setPa(ch.getPa()-2);
					ch.setKo(ch.getKo()-2);
					ch.setFf(ch.getFf()-2);
				}
				
				else
				{
					ch.setAt(ch.getAt()+2);
					ch.setPa(ch.getPa()+2);
					ch.setKo(ch.getKo()+2);
					ch.setFf(ch.getFf()+2);
				}
				
				GUI.refreschList();
			}
		};
		
		setRightArm1(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getRightArm1(), 6, SpringLayout.SOUTH, getChest1());
		springLayout.putConstraint(SpringLayout.EAST, getRightArm1(), 0, SpringLayout.EAST, getHead1());
		getRightArm1().addItemListener(ArmListener);
		add(getRightArm1());
		
		setRightArm2(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getRightArm2(), 6, SpringLayout.SOUTH, getChest2());
		springLayout.putConstraint(SpringLayout.WEST, getRightArm2(), 0, SpringLayout.WEST, getHead2());
		getRightArm2().addItemListener(ArmListener);
		add(getRightArm2());
		
		setRightArm3(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getRightArm3(), 6, SpringLayout.SOUTH, getChest3());
		springLayout.putConstraint(SpringLayout.EAST, getRightArm3(), 0, SpringLayout.EAST, getHead3());
		getRightArm3().addItemListener(ArmListener);
		add(getRightArm3());
		
		setLeftArm1(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getLeftArm1(), 6, SpringLayout.SOUTH, getRightArm1());
		springLayout.putConstraint(SpringLayout.EAST, getLeftArm1(), 0, SpringLayout.EAST, getHead1());
		getLeftArm1().addItemListener(ArmListener);
		add(getLeftArm1());
		
		setLeftArm2(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getLeftArm2(), 6, SpringLayout.SOUTH, getRightArm2());
		springLayout.putConstraint(SpringLayout.WEST, getLeftArm2(), 0, SpringLayout.WEST, getHead2());
		getLeftArm2().addItemListener(ArmListener);
		add(getLeftArm2());
		
		setLeftArm3(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getLeftArm3(), 6, SpringLayout.SOUTH, getRightArm3());
		springLayout.putConstraint(SpringLayout.EAST, getLeftArm3(), 0, SpringLayout.EAST, getHead3());
		getLeftArm3().addItemListener(ArmListener);
		add(getLeftArm3());
		
		ItemListener legListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					ch.setAt(ch.getAt()-1);
					ch.setPa(ch.getPa()-1);
					ch.setKo(ch.getKo()-1);
					ch.setKk(ch.getKk()-1);
					ch.setIni(ch.getIni()-1);
				}
				
				else
				{
					ch.setAt(ch.getAt()+1);
					ch.setPa(ch.getPa()+1);
					ch.setKo(ch.getKo()+1);
					ch.setKk(ch.getKk()+1);
					ch.setIni(ch.getIni()+1);
				}
				
				GUI.refreschList();
			}
		};
		
		setRightLeg1(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getRightLeg1(), 6, SpringLayout.SOUTH, getLeftArm1());
		springLayout.putConstraint(SpringLayout.EAST, getRightLeg1(), 0, SpringLayout.EAST, getHead1());
		getRightLeg1().addItemListener(legListener);
		add(getRightLeg1());
		
		setRightLeg2(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getRightLeg2(), 6, SpringLayout.SOUTH, getLeftArm2());
		springLayout.putConstraint(SpringLayout.WEST, getRightLeg2(), 0, SpringLayout.WEST, getHead2());
		getRightLeg2().addItemListener(legListener);
		add(getRightLeg2());
		
		setRightLeg3(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getRightLeg3(), 6, SpringLayout.SOUTH, getLeftArm2());
		springLayout.putConstraint(SpringLayout.EAST, getRightLeg3(), 0, SpringLayout.EAST, getHead3());
		getRightLeg3().addItemListener(legListener);
		add(getRightLeg3());
		
		setLeftLeg1(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getLeftLeg1(), 6, SpringLayout.SOUTH, getRightLeg1());
		springLayout.putConstraint(SpringLayout.EAST, getLeftLeg1(), 0, SpringLayout.EAST, getHead1());
		getLeftLeg1().addItemListener(legListener);
		add(getLeftLeg1());
		
		setLeftLeg2(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getLeftLeg2(), 6, SpringLayout.SOUTH, getRightLeg2());
		springLayout.putConstraint(SpringLayout.WEST, getLeftLeg2(), 0, SpringLayout.WEST, getHead2());
		getLeftLeg2().addItemListener(legListener);
		add(getLeftLeg2());
		
		setLeftLeg3(new JCheckBox(""));
		springLayout.putConstraint(SpringLayout.NORTH, getLeftLeg3(), 6, SpringLayout.SOUTH, getRightLeg3());
		springLayout.putConstraint(SpringLayout.WEST, getLeftLeg3(), 0, SpringLayout.WEST, getHead3());
		getLeftLeg3().addItemListener(legListener);
		add(getLeftLeg3());
		
		JLabel lblHead = new JLabel("Head");
		springLayout.putConstraint(SpringLayout.NORTH, lblHead, 0, SpringLayout.NORTH, getHead1());
		add(lblHead);
		
		JLabel lblChest = new JLabel("Chest");
		springLayout.putConstraint(SpringLayout.EAST, lblChest, -30, SpringLayout.WEST, getChest1());
		springLayout.putConstraint(SpringLayout.WEST, lblHead, 0, SpringLayout.WEST, lblChest);
		springLayout.putConstraint(SpringLayout.NORTH, lblChest, 0, SpringLayout.NORTH, getChest1());
		add(lblChest);
		
		JLabel lblRightarm = new JLabel("Right Arm");
		springLayout.putConstraint(SpringLayout.NORTH, lblRightarm, 0, SpringLayout.NORTH, getRightArm1());
		springLayout.putConstraint(SpringLayout.WEST, lblRightarm, 0, SpringLayout.WEST, lblHead);
		add(lblRightarm);
		
		JLabel lblLeftArm = new JLabel("Left Arm");
		springLayout.putConstraint(SpringLayout.NORTH, lblLeftArm, 0, SpringLayout.NORTH, getLeftArm1());
		springLayout.putConstraint(SpringLayout.WEST, lblLeftArm, 0, SpringLayout.WEST, lblHead);
		add(lblLeftArm);
		
		JLabel lblRightLeg = new JLabel("Right Leg");
		springLayout.putConstraint(SpringLayout.NORTH, lblRightLeg, 0, SpringLayout.NORTH, getRightLeg1());
		springLayout.putConstraint(SpringLayout.WEST, lblRightLeg, 0, SpringLayout.WEST, lblHead);
		add(lblRightLeg);
		
		JLabel lblLeftLeg = new JLabel("Left Leg");
		springLayout.putConstraint(SpringLayout.NORTH, lblLeftLeg, 0, SpringLayout.NORTH, getLeftLeg1());
		springLayout.putConstraint(SpringLayout.WEST, lblLeftLeg, 0, SpringLayout.WEST, lblHead);
		add(lblLeftLeg);
		
		JLabel lblWounds = new JLabel("Wounds");
		springLayout.putConstraint(SpringLayout.SOUTH, lblWounds, -19, SpringLayout.NORTH, getHead1());
		springLayout.putConstraint(SpringLayout.EAST, lblWounds, -59, SpringLayout.EAST, this);
		add(lblWounds);
		
		JLabel lblHpwert = new JLabel("Hp-Wert:");
		springLayout.putConstraint(SpringLayout.WEST, lblHpwert, 0, SpringLayout.WEST, lblName);
		springLayout.putConstraint(SpringLayout.SOUTH, lblHpwert, 0, SpringLayout.SOUTH, getRightLeg1());
		add(lblHpwert);
		
		setHpBar(new JFormattedTextField(NumberFormat.getInstance()));
		springLayout.putConstraint(SpringLayout.WEST, getHpBar(), 15, SpringLayout.EAST, lblHpwert);
		springLayout.putConstraint(SpringLayout.SOUTH, iniFeld, -50, SpringLayout.NORTH, getHpBar());
		springLayout.putConstraint(SpringLayout.SOUTH, getHpBar(), 0, SpringLayout.SOUTH, getRightLeg1());
		getHpBar().setColumns(10);
		getHpBar().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == 10) {
					ch.setIni(Integer.parseInt(getIniFeld().getText()));
					ch.setHp(Integer.parseInt(getHpBar().getText()));
					
					if(ch.getHp() < 0)
						ch.setName(ch.getName() + "(DEAD)");
					if(ch.getHp() >=0 && ch.getName().contains("(DEAD)"))
						ch.setName(ch.getName().substring(0, ch.getName().length()-6));
					
					GUI.refreschList();
				}
			}
		});
		add(getHpBar());					
		
		txtpnNotes = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, txtpnNotes, 19, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, txtpnNotes, -201, SpringLayout.WEST, lblHead);
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnNotes, 122, SpringLayout.SOUTH, getLeftLeg1());
		springLayout.putConstraint(SpringLayout.EAST, txtpnNotes, -6, SpringLayout.WEST, lblHead);
		add(txtpnNotes);
		
		JLabel lblNewLabel = new JLabel("Notes:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, head1);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, txtpnNotes);
		add(lblNewLabel);
		{
			setLblAt(new JLabel("AT: ["+ch.getAt()+"]"));
			springLayout.putConstraint(SpringLayout.NORTH, lblAt, 3, SpringLayout.NORTH, iniFeld);
			springLayout.putConstraint(SpringLayout.EAST, lblAt, -6, SpringLayout.WEST, txtpnNotes);
			add(lblAt);
		}
		{
			setLblPa(new JLabel("PA: ["+ch.getPa()+"]"));
			springLayout.putConstraint(SpringLayout.NORTH, getLblPa(), 0, SpringLayout.NORTH, rightLeg1);
			springLayout.putConstraint(SpringLayout.EAST, lblPa, -6, SpringLayout.WEST, txtpnNotes);
			add(getLblPa());
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

	public JFormattedTextField getHpBar() {
		return hpBar;
	}

	public void setHpBar(JFormattedTextField hpBar) {
		this.hpBar = hpBar;
	}
	
	public JCheckBox getHead1() {
		return head1;
	}

	public void setHead1(JCheckBox head1) {
		this.head1 = head1;
	}

	public JCheckBox getHead2() {
		return head2;
	}

	public void setHead2(JCheckBox head2) {
		this.head2 = head2;
	}

	public JCheckBox getHead3() {
		return head3;
	}

	public void setHead3(JCheckBox head3) {
		this.head3 = head3;
	}

	public JCheckBox getChest1() {
		return chest1;
	}

	public void setChest1(JCheckBox chest1) {
		this.chest1 = chest1;
	}

	public JCheckBox getChest2() {
		return chest2;
	}

	public void setChest2(JCheckBox chest2) {
		this.chest2 = chest2;
	}

	public JCheckBox getChest3() {
		return chest3;
	}

	public void setChest3(JCheckBox chest3) {
		this.chest3 = chest3;
	}

	public JCheckBox getRightArm1() {
		return rightArm1;
	}

	public void setRightArm1(JCheckBox rightArm1) {
		this.rightArm1 = rightArm1;
	}

	public JCheckBox getRightArm2() {
		return rightArm2;
	}

	public void setRightArm2(JCheckBox rightArm2) {
		this.rightArm2 = rightArm2;
	}

	public JCheckBox getRightArm3() {
		return rightArm3;
	}

	public void setRightArm3(JCheckBox rightArm3) {
		this.rightArm3 = rightArm3;
	}

	public JCheckBox getLeftArm1() {
		return leftArm1;
	}

	public void setLeftArm1(JCheckBox leftArm1) {
		this.leftArm1 = leftArm1;
	}

	public JCheckBox getLeftArm2() {
		return leftArm2;
	}

	public void setLeftArm2(JCheckBox leftArm2) {
		this.leftArm2 = leftArm2;
	}

	public JCheckBox getLeftArm3() {
		return leftArm3;
	}

	public void setLeftArm3(JCheckBox leftArm3) {
		this.leftArm3 = leftArm3;
	}

	public JCheckBox getRightLeg1() {
		return rightLeg1;
	}

	public void setRightLeg1(JCheckBox rightLeg1) {
		this.rightLeg1 = rightLeg1;
	}

	public JCheckBox getRightLeg2() {
		return rightLeg2;
	}

	public void setRightLeg2(JCheckBox rightLeg2) {
		this.rightLeg2 = rightLeg2;
	}

	public JCheckBox getRightLeg3() {
		return rightLeg3;
	}

	public void setRightLeg3(JCheckBox rightLeg3) {
		this.rightLeg3 = rightLeg3;
	}

	public JCheckBox getLeftLeg1() {
		return leftLeg1;
	}

	public void setLeftLeg1(JCheckBox leftLeg1) {
		this.leftLeg1 = leftLeg1;
	}

	public JCheckBox getLeftLeg2() {
		return leftLeg2;
	}

	public void setLeftLeg2(JCheckBox leftLeg2) {
		this.leftLeg2 = leftLeg2;
	}

	public JCheckBox getLeftLeg3() {
		return leftLeg3;
	}

	public void setLeftLeg3(JCheckBox leftLeg3) {
		this.leftLeg3 = leftLeg3;
	}

	private SplitPanel2 getThis() {
		return this;
	}

	public Charakter getCh() {
		return ch;
	}

	public void setCh(Charakter ch) {
		this.ch = ch;
	}

	public JLabel getLblAt() {
		return lblAt;
	}

	public void setLblAt(JLabel lblAt) {
		this.lblAt = lblAt;
	}

	public JLabel getLblPa() {
		return lblPa;
	}

	public void setLblPa(JLabel lblPa) {
		this.lblPa = lblPa;
	}
}
