package dsa.ini.tool.beans;

public class Charakter implements java.lang.Comparable<Charakter> {
	private String name;
	private int ini;

	public Charakter(String name, String ini) {
		this.name = name;
		this.ini = Integer.parseInt(ini.replaceAll(" ", ""));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIni() {
		return ini;
	}

	public String getIniAsString() {
		return String.valueOf(ini);
	}

	public void setIni(int ini) {
		this.ini = ini;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Charakter arg0) {
		return arg0.getIni() - ini;
	}
}
