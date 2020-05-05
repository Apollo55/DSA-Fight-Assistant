package dsa.ini.tool.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import dsa.ini.tool.gui.SplitPanel2;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Charakter implements java.lang.Comparable<Charakter> {
	@JsonProperty("name")
	private String name;
	@JsonProperty("ini")
	private int ini;
	@JsonProperty("hp")
	private int hp;
	@JsonProperty("mu")
	private int mu;
	@JsonProperty("kl")
	private int kl;
	@JsonProperty("in")
	private int in;
	@JsonProperty("ch")
	private int ch;
	@JsonProperty("ff")
	private int ff;
	@JsonProperty("ge")
	private int ge;
	@JsonProperty("ko")
	private int ko;
	@JsonProperty("kk")
	private int kk;
	@JsonProperty("at")
	private int at;
	@JsonProperty("pa")
	private int pa;
	
	@JsonIgnore
	private SplitPanel2 sp;

	public Charakter() {}
	
	public Charakter(String name, String ini, SplitPanel2 sp) {
		this.name = name;
		this.ini = Integer.parseInt(ini.replaceAll(" ", ""));
		this.setHp(0);
		this.setSp(sp);
		sp.setCh(this);
	}
	
	public Charakter(String name, int ini, int hp, int mu, int kl, int in, int ch, int ff, int ge, int ko, int kk, int at, int pa) {
		this.name = name;
		this.ini = ini;
		this.hp = hp;
		this.mu = mu;
		this.kl = kl;
		this.in = in;
		this.ch = ch;
		this.ff = ff;
		this.ge = ge;
		this.ko = ko;
		this.kk = kk;
		this.at = at;
		this.pa = pa;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("ini")
	public int getIni() {
		return ini;
	}
	
	@JsonIgnore
	public String getIniAsString() {
		return String.valueOf(ini);
	}

	@JsonProperty("ini")
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

	@JsonProperty("hp")
	public int getHp() {
		return hp;
	}

	@JsonProperty("hp")
	public void setHp(int hp) {
		this.hp = hp;
	}

	@JsonIgnore
	public String getHpAsString() {
		return String.valueOf(hp);
	}

	@JsonProperty("mu")
	public int getMu() {
		return mu;
	}

	@JsonProperty("mu")
	public void setMu(int mu) {
		this.mu = mu;
	}

	@JsonProperty("kl")
	public int getKl() {
		return kl;
	}

	@JsonProperty("kl")
	public void setKl(int kl) {
		this.kl = kl;
	}

	@JsonProperty("in")
	public int getIn() {
		return in;
	}

	@JsonProperty("in")
	public void setIn(int in) {
		this.in = in;
	}

	@JsonProperty("ch")
	public int getCh() {
		return ch;
	}

	@JsonProperty("ch")
	public void setCh(int ch) {
		this.ch = ch;
	}

	@JsonProperty("ff")
	public int getFf() {
		return ff;
	}

	@JsonProperty("ff")
	public void setFf(int ff) {
		this.ff = ff;
	}

	@JsonProperty("ge")
	public int getGe() {
		return ge;
	}

	@JsonProperty("ge")
	public void setGe(int ge) {
		this.ge = ge;
	}

	@JsonProperty("ko")
	public int getKo() {
		return ko;
	}

	@JsonProperty("ko")
	public void setKo(int ko) {
		this.ko = ko;
	}

	@JsonProperty("kk")
	public int getKk() {
		return kk;
	}

	@JsonProperty("kk")
	public void setKk(int kk) {
		this.kk = kk;
	}

	@JsonIgnore
	public SplitPanel2 getSp() {
		return sp;
	}

	@JsonIgnore
	public void setSp(SplitPanel2 sp) {
		this.sp = sp;
	}

	@JsonProperty("at")
	public int getAt() {
		return at;
	}

	@JsonProperty("at")
	public void setAt(int at) {
		this.at = at;
	}

	@JsonProperty("pa")
	public int getPa() {
		return pa;
	}

	@JsonProperty("pa")
	public void setPa(int pa) {
		this.pa = pa;
	}
}
