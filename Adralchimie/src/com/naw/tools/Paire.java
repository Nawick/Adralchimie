package com.naw.tools;

/**
 * To be a generic class in order to pair objects
 * 
 * @author Ad
 *
 */

public class Paire implements Comparable<Paire> {

	private int a;
	private int b;
	
	public Paire(int a, int b) {
		this.setA(a);
		this.setB(b);
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	@Override
	public int compareTo(Paire another) {
		int tA = this.a + this.b;
		int tB = another.a + another.b;
		if(this.a == another.a && this.b == another.b){
			return 0;
		}else if(this.a > another.a ){
			return 1;
		}else if(this.a < another.a && this.b > another.b){
			return 1;
		}else  {
			return -1;
		}
	}
	
	@Override
	public String toString() {
		//System.out.println("("+a+","+b+")");
		return "("+a+","+b+")";
	}
	
	
	
}
