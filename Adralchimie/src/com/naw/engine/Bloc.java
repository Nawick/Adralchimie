package com.naw.engine;

import java.util.ArrayList;
import java.util.Collections;

import com.naw.tools.Paire;


/**
 * This class contains an element and a list of coords in the grid
 * 
 * @author Ad
 *
 */
public class Bloc {
	private int element = 0 ;
	private ArrayList<Paire> tabCoords;
	private int rankBlock;

	public Bloc(int element) {
		this.setElement(element) ;
		tabCoords = new ArrayList<Paire>();
	}
	
	public void addCoords(int a, int b) {
		tabCoords.add(new Paire(a,b));
	}

	public int getElement() {
		return element;
	}

	private void setElement(int element) {
		this.element = element;
	}
	
	public ArrayList<Paire> getCoords() {
		return tabCoords;
	}
	
	public Paire getFinalCoords() {
		Paire obj = Collections.min(tabCoords);
		return obj;
		
	}
	
	public boolean paireIsIn(int a, int b){
		Paire p = new Paire(a,b);
		Boolean output = false;
		Boolean boo = false;
		int o = 0;
		for (int i = 0; i  < tabCoords.size(); i++) {
			o = tabCoords.get(i).compareTo(p) ;
			boo = (o == 0 );
			output = output || boo;
		}
		return output;
	}
	
	@Override
	public String toString() {
		String s = "Rang "+rankBlock+" Elt "+element+" : ";
		for (int i = 0; i < tabCoords.size(); i++) {
			s = s + tabCoords.get(i).toString() + " ";
		}
		//return super.toString();
		return s;
	}

	public void addRank(int rankBlock) {
		this.rankBlock = rankBlock;
		
	}
	
	public int getRankBlock() {
		return rankBlock;
	}
	
}
