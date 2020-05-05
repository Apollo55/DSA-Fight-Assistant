package dsa.ini.tool.beans;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Fighte {
	private Collection<Charakter> charakters;

	public Fighte() {
		charakters = new ArrayList<>();
	}

	public Fighte(Collection<Charakter> list) {
		charakters = list;
	}

	public Collection<Charakter> getCharakters() {
		return charakters;
	}
	
	public Charakter getCharacter(String name) {
		for(Charakter chara : charakters) {
			if(chara.getName().equals(name)) return chara;
		}	
		return null;
	}

	public void setCharakters(Collection<Charakter> charakters) {
		this.charakters = charakters;
	}

	public void addChar(Charakter ch) {
		this.charakters.add(ch);
	}

	@JsonIgnore
	public Collection<Charakter> getALL() {
		return charakters;
	}

	public void deleteChar(Charakter ch) {
		charakters.remove(ch);
	}

}
