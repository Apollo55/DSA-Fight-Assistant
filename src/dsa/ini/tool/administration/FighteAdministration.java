package dsa.ini.tool.administration;

import dsa.ini.tool.beans.Charakter;
import dsa.ini.tool.beans.Fighte;
import dsa.ini.tool.gui.Gui;
import dsa.ini.tool.gui.SplitPanel2;

public class FighteAdministration {

	private Gui gui;
	private Fighte fighte = new Fighte();

	public FighteAdministration() {
		setGui(new Gui(this));
	}

	public Fighte getFighte() {
		return fighte;
	}

	public void setFighte(Fighte fighte) {
		this.fighte = fighte;
		
		for(Charakter ch : fighte.getALL()) {
			ch.setSp(new SplitPanel2(this, gui));
			ch.getSp().setCh(ch);
		}
	}

	public Gui getGui() {
		return gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
	}
}
