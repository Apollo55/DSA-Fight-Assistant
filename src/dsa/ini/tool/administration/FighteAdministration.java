package dsa.ini.tool.administration;

import dsa.ini.tool.beans.Fighte;
import dsa.ini.tool.gui.Gui;

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
	}

	public Gui getGui() {
		return gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
	}
}
