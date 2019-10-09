package dsa.ini.tool.beans;

import dsa.ini.tool.gui.SplitPanel2;

public class Charakter implements java.lang.Comparable<Charakter> {
	private String name;
	private int ini;
	private int hp;
	
	private int mu=0;
	private int kl=0;
	private int in=0;
	private int ch=0;
	private int ff=0;
	private int ge=0;
	private int ko=0;
	private int kk=0;
	
	private int at=0;
	private int pa=0;
	
	private SplitPanel2 sp;

	public Charakter(String name, String ini, SplitPanel2 sp) {
		this.name = name;
		this.ini = Integer.parseInt(ini.replaceAll(" ", ""));
		this.setHp(0);
		this.setSp(sp);
		sp.setCh(this);
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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getHpAsString() {
		return String.valueOf(hp);
	}

	public int getMu() {
		return mu;
	}

	public void setMu(int mu) {
		this.mu = mu;
	}

	public int getKl() {
		return kl;
	}

	public void setKl(int kl) {
		this.kl = kl;
	}

	public int getIn() {
		return in;
	}

	public void setIn(int in) {
		this.in = in;
	}

	public int getCh() {
		return ch;
	}

	public void setCh(int ch) {
		this.ch = ch;
	}

	public int getFf() {
		return ff;
	}

	public void setFf(int ff) {
		this.ff = ff;
	}

	public int getGe() {
		return ge;
	}

	public void setGe(int ge) {
		this.ge = ge;
	}

	public int getKo() {
		return ko;
	}

	public void setKo(int ko) {
		this.ko = ko;
	}

	public int getKk() {
		return kk;
	}

	public void setKk(int kk) {
		this.kk = kk;
	}

	public SplitPanel2 getSp() {
		return sp;
	}

	public void setSp(SplitPanel2 sp) {
		this.sp = sp;
	}

	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}

	public int getPa() {
		return pa;
	}

	public void setPa(int pa) {
		this.pa = pa;
	}
}
