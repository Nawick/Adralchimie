package com.naw.engine;

import java.util.Random;

import com.naw.graphics.NextNextElementView;
import com.naw.tools.Paire;

public class NextNextElement {

	private static NextNextElement instance;
	private Paire element;
	final Random myRandom = new Random();
	private int a = 0;
	private int b = 0;
	private static NextNextElementView nextNextElementView;
	
	
	public NextNextElement() {
		instance = this;
		generateElement(3);
	}
	
	public void updateGraphics(){
		if(nextNextElementView != null){
			nextNextElementView.invalidate();
		}
	}
	
	public void generateElement(int rank){
		
		a = (((int)Math.ceil(Math.random()*100))%rank)+1;
		b = (((int)Math.ceil(Math.random()*100))%rank)+1;
		
		element = new Paire(a, b);
		updateGraphics();
	}
	
	public Paire getElement(){
		return this.element;
	}
	
	public void setElement(Paire elt){
		this.element =elt;
	}

	public static NextNextElement getInstance() {
		if(instance != null){
			return instance;
		}else{
			return new NextNextElement();
		}
	}

	public static void registerView(NextNextElementView n) {
		nextNextElementView = n;		
	}

	
	
}
