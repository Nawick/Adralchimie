package com.naw.engine;

import com.naw.graphics.NextElementView;
import com.naw.tools.Paire;


public class NextElement {
	//private int[] tabNextElementHaut;
	//private int[] tabNextElementBas;
	private Paire nextElement;
	private Paire position;
	private int nbCols = MainGrid.nbCols;
	private static NextElementView nextElementView;
	private static NextElement instance;
	
	public NextElement(Paire element) {
		//tabNextElementHaut = new int[MainGrid.nbCols];
		//tabNextElementBas = new int[MainGrid.nbCols];
		nextElement = element;
		position = new Paire(1, 2);
		instance = this;
	}
	
	public void updateGraphics(){
		if(nextElementView != null){
			nextElementView.invalidate();
		}
	}
	
	public void rotate(){
		if(position.getB()<nbCols-1){
			position.setA((position.getA()+1)%4);
		}else{
			position.setB(position.getB()-1);
			position.setA((position.getA()+1)%4);
		}
		updateGraphics();
	}
	
	public void toLeft(){
		if(position.getB()>0){
			position.setB(position.getB()-1);
		}
		updateGraphics();
	}
	
	public void toRight(){
		if((position.getA() == 1 || position.getA() == 3) && position.getB()<nbCols-2){
			position.setB(position.getB()+1);
		}else if((position.getA() == 0 || position.getA() == 2) && position.getB()<nbCols-1){
			position.setB(position.getB()+1);
		}
		updateGraphics();
	}
	
	public Paire getElement(){
		return nextElement;
	}
	
	public Paire getPosition(){
		return position;
	}
	
	public void setElement(Paire elt){
		nextElement = elt;
		position.setA(1);
		position.setB(2);
		if(nextElementView != null){
			nextElementView.setElement(nextElement);
		}
		
		updateGraphics();
	}
	
	public static void registerView(NextElementView v){
		nextElementView = v;
	}

	public static NextElement getInstance() {
		if(instance != null){
			return instance;
		}else{
			return new NextElement(new Paire(1,1));
		}
	}
	
	
	
}
