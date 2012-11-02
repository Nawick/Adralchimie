package com.naw.engine;

import java.util.ArrayList;

import com.naw.graphics.MainGridView;
import com.naw.tools.Paire;

import android.view.View;
import android.widget.TextView;

public class MainGrid {
	
	// Parameters
	public static int nbCols = 6 ;
	public static int nbRows = 8 ;
	public static int maxRank = 12;
	public int currentRank = 3;
	public boolean gameOver = false;
	public AdralchimieActivity activity;
	
	
	
	// Objects / Lists
	private ArrayList<ArrayList<Integer>> tabGrid;
	private ArrayList<Bloc> tabBlocs; // Liste de composants formant un bloc 
	private int elementMaxHeight = 0;
	private static MainGrid instance;
	private static MainGridView gridView; // In order to update the view
	
	public MainGrid(AdralchimieActivity act) {
		activity = act;
		initValues();
	}

	public MainGrid(int nbCols,int nbRows) {
		MainGrid.nbCols = nbCols ;
		MainGrid.nbRows = nbRows ;
		initValues();
	}
	
	public static MainGrid getInstance(){
		if(instance != null){
			return instance;
		}else{
			return new MainGrid(null);
		}
		
	}
	
	public ArrayList<ArrayList<Integer>> getGrid(){
		return tabGrid;
	}
	
	private void initValues() {
		instance = this;
		
		tabGrid = new ArrayList<ArrayList<Integer>>(nbCols);
		for (int i = 0; i < nbCols; i++) {
			tabGrid.add(new ArrayList<Integer>(nbRows));
		}
		for (int i = 0; i < nbCols; i++) {
			for (int j = 0; j <nbRows+2; j++) {
				tabGrid.get(i).add(0);
			}
		}
		System.out.println("Creation grille");
		/* For tests purposes */
//		tabGrid.get(0).set(0,5);
//		tabGrid.get(1).set(0,5);
//		tabGrid.get(2).set(0,3);
//		tabGrid.get(2).set(1,2);
//		tabGrid.get(2).set(2,2);
//		tabGrid.get(2).set(3,1);
//		tabGrid.get(2).set(4,3);
//		tabGrid.get(3).set(0,1);
//		tabGrid.get(3).set(1,2);
		
		// tests unitaires :
//		Paire P1 = new Paire(1,2);
//		Paire P2 = new Paire(2,1);
//		Paire P3 = new Paire(1,2);
//		Paire P4 = new Paire(2,2);
//		//System.out.println("Pairing Comparator : "+P1.compareTo(P2)+"  |  "+P1.compareTo(P3)+"  |  "+P1.compareTo(P4));
//		Bloc b = new Bloc(1);
//		b.addCoords(P1.getA(), P1.getB());
//		b.addCoords(P2.getA(), P2.getB());
//		b.addCoords(P4.getA(), P4.getB());
		
		//System.out.println("Bloc comparator : "+b.paireIsIn(1, 2)+ "  |  "+b.paireIsIn(1, 3));
		
		
		
		//computeCycle();
		
		
		
		/**********************/
	}
	
	int updateScore(){
		int s1 = tabGrid.size();
		int s2 = 0;
		int score = 0;
		ArrayList<Integer> tmp;
		for (int i = 0; i < s1; i++) {
			tmp = tabGrid.get(i);
			s2 = tmp.size();
			for (int j = 0; j < s2; j++) {
				switch (tmp.get(j)) {
				case 0:									break;
				case 1:	 score = score + 1 ;				break;
				case 2:	 score = score + 3 ;				break;
				case 3:	 score = score + 9 ;				break;
				case 4:	 score = score + 30 ;				break;
				case 5:	 score = score + 90 ;				break;
				case 6:	 score = score + 300 ;				break;
				case 7:	 score = score + 900 ;				break;
				case 8:	 score = score + 3000 ;				break;
				case 9:  score = score + 9000 ;				break;
				case 10: score = score + 30000 ;				break;
				case 11: score = score + 90000 ;				break;
				case 12: score = score + 300000 ;				break;
				default:
					break;
				}
			}
		}
		
		if(activity != null){
			activity.updateScores(score);
			activity.updateDB();
		}
		
		
		
		return score;
	}
	
	public boolean addElement(Paire p, Paire position){
		int col1 = computePositionFirstElement(position);
		int col2 = computePositionSecondElement(position);
		//System.out.println("Position : "+position.toString());
		if(position.getA() == 1  || position.getA() == 0){
			int a = p.getA();
			int b = p.getB();
			p.setA(b);
			p.setB(a);
		}
		pile(col1,p.getA());
		pile(col2,p.getB());
		
		System.out.println(p.toString() +" "+ col1 +" "+col2+" "+position.toString());
		gridView.animateDown(p,col1,col2);
		
		computeCycle();
		
		
		if(gameIsOver()){
			activity.endGame();
		}
		
		updateGraphics();
		return true;
	}
	
	private boolean gameIsOver() {
		//System.out.println("ElementMaxHeight : "+elementMaxHeight);
		return elementMaxHeight > nbRows;
	}

	public void updateGraphics(){
		if (gridView != null) {
			gridView.invalidate();
		}
	}
	
	private boolean pile(int col, int b) {
		ArrayList<Integer> tabCol = tabGrid.get(col);
		for (int i = 0; i < tabCol.size(); i++) {
			if(tabCol.get(i) == 0){
				tabGrid.get(col).set(i, b); 
				return true;
			}
		}
		
		
		return false;
	}

	private int computePositionSecondElement(Paire pos) {
		if(pos.getA() == 0 || pos.getA() == 2){
			return pos.getB();
		}else{
			return pos.getB()+1;
		}
	}

	private int computePositionFirstElement(Paire pos) {
		return pos.getB();
	}

	protected int rankBlock = 0 ; // Needed to know if the block explode at n wave
	public void computeCycle() {
		//printGrid();
		boolean condition = true;
		rankBlock = 0 ;
		
		while (condition ) {
			//System.out.println("~~ New cycle ~~");
			
			analyzeGrid();
			
			//gridView.updateGrid(tabGrid);
			condition = tabBlocs.size() != 0 ;
			if (condition) {
				gridView.updateBlocks(tabBlocs, rankBlock);
			}
			
			computeGrid();

//			gridView.updateGrid(tabGrid);
//			printBlocks();
//			printGrid();
//			updateGraphics();
			rankBlock ++ ;
		}
		
		
		
	}
	
	private void computeGrid() {
		int elt = 0;
		int eltNext = 1;
		Paire resultant = new Paire(0, 0);
		Bloc bloc;
		if(tabBlocs.size() > 0){
			for (int i = 0; i < tabBlocs.size(); i++) {
				bloc = tabBlocs.get(i);
				elt = bloc.getElement();
				if(elt<maxRank){
					eltNext = elt +1 ;
					if(eltNext>currentRank){
						currentRank = eltNext;
					}
				}else{
					eltNext = elt ;
				}
				resultant = bloc.getFinalCoords();
				for (int j = 0; j < bloc.getCoords().size(); j++) {
					tabGrid.get(bloc.getCoords().get(j).getA()).set(bloc.getCoords().get(j).getB(), 0);
				}
				
				tabGrid.get(resultant.getA()).set(resultant.getB(), eltNext);
			}
		}
		tabBlocs.clear();
		cleanGrid();
	}

	private void cleanGrid() {
		ArrayList<Integer> col;
		elementMaxHeight = 0;
		for (int i = 0; i < tabGrid.size(); i++) {
			col = tabGrid.get(i);
			tabGrid.set(i, cleanColumn(col));
			
		}
		
	}

	private ArrayList<Integer> cleanColumn(ArrayList<Integer> col) {
		ArrayList<Integer> colTemp = new ArrayList<Integer>(col.size());
		int index = 0;
		for (int i = 0; i < col.size(); i++) {
			if(col.get(i)!=0){
				colTemp.add(index, col.get(i));
				index ++ ;
			}
		}
		if(index>elementMaxHeight ){
			elementMaxHeight = index;
		}
		for (int i = index; i < col.size(); i++) {
			colTemp.add(i, 0);
		}
		
		return colTemp;
		
	}

	private void analyzeGrid(){
		tabBlocs = new ArrayList<Bloc>(); // Int the blocks list
		int nbC = tabGrid.size() ;
		int nbR = 0 ;
		int elt = 0 ;
		Bloc block = new Bloc(0);
		
		for (int i = 0; i < nbC ; i++) {
			nbR = tabGrid.get(i).size();
			
			for (int j = 0; j < nbR; j++) {
				elt = tabGrid.get(i).get(j);
				// Test if this area has already been analyzed
				
				if( !testIfCaseComputed(i,j) && elt !=0 && elt !=maxRank ){
					//System.out.println(" <##> "+i+","+j+" -> "+elt);
					block = new Bloc(elt);
					block.addCoords(i, j);
					block.addRank(rankBlock);
					tabBlocs.add(block);
					
					explore(block);
				}
			}
			
			
		}	
		cleanTabBlocs();
		
	}
	
	private void cleanTabBlocs(){
		//printBlocks();
		for (int i = 0; i <  tabBlocs.size(); i++) {
			if (tabBlocs.get(i).getCoords().size()<3) {
				tabBlocs.remove(i);
				i--;
			}
		}
	}
	
	private void explore(Bloc block) {
		//System.out.println("explore");
		//printBlocks();
		// We are on this case :
		int a = block.getCoords().get(block.getCoords().size()-1).getA();
		int b = block.getCoords().get(block.getCoords().size()-1).getB();
		int elt = block.getElement();
		int aM1 = -1;
		int bM1 = -1;
		if(block.getCoords().size()>1){
			aM1 = block.getCoords().get(block.getCoords().size()-2).getA();
			bM1 = block.getCoords().get(block.getCoords().size()-2).getB();
		}
		
		// Maximum 4 cases to explore
		
		
		if(a == 0 && b == nbRows+1){ // Top Left Corner
			// a+1 , b
			exploreAssistant(block, elt, a+1, b, aM1, bM1);
			// a , b-1
			exploreAssistant(block, elt, a, b-1, aM1, bM1);
		}else if(a==0 && b == 0){ // Bottom left corner
			// a , b+1
			exploreAssistant(block, elt, a, b+1, aM1, bM1);
			// a+1 , b
			exploreAssistant(block, elt, a+1, b, aM1, bM1);
		}else if(a==nbCols-1 && b == 0){ // Bottom right corner
			// a , b+1
			exploreAssistant(block, elt, a, b+1, aM1, bM1);
			// a-1 , b
			exploreAssistant(block, elt, a-1, b, aM1, bM1);
		}else if(a==nbCols-1 && b == nbRows+1){ // Top right corner
			
			// a-1 , b
			exploreAssistant(block, elt, a-1, b, aM1, bM1);
			// a , b-1
			exploreAssistant(block, elt, a, b-1, aM1, bM1);
		}else if(a>0 && a<nbCols-1 && b == nbRows+1){ // Top center part
			// a+1 , b
			exploreAssistant(block, elt, a+1, b, aM1, bM1);
			// a-1 , b
			exploreAssistant(block, elt, a-1, b, aM1, bM1);
			// a , b-1
			exploreAssistant(block, elt, a, b-1, aM1, bM1);
		}else if(a>0 && a<nbCols-1 && b == 0){ // Bottom center part
			// a , b+1
			exploreAssistant(block, elt, a, b+1, aM1, bM1);
			// a+1 , b
			exploreAssistant(block, elt, a+1, b, aM1, bM1);
			// a-1 , b
			exploreAssistant(block, elt, a-1, b, aM1, bM1);
		}else if(a==0 && b > 0 && b<nbRows+1){ // Left center part
			// a , b+1
			exploreAssistant(block, elt, a, b+1, aM1, bM1);
			// a+1 , b
			exploreAssistant(block, elt, a+1, b, aM1, bM1);
			// a , b-1
			exploreAssistant(block, elt, a, b-1, aM1, bM1);
			
		}else if(a==nbCols-1 && b > 0 && b<nbRows+1){ // Right center part
			// a , b+1
			exploreAssistant(block, elt, a, b+1, aM1, bM1);
			// a-1 , b
			exploreAssistant(block, elt, a-1, b, aM1, bM1);
			// a , b-1
			exploreAssistant(block, elt, a, b-1, aM1, bM1);
			
		}else { // Absolute center part
			// a , b+1
			//System.out.println("a , b+1");
			exploreAssistant(block, elt, a, b+1, aM1, bM1);
			// a+1 , b
			//System.out.println("a+1 , b");
			exploreAssistant(block, elt, a+1, b, aM1, bM1);			
			// a-1 , b
			//System.out.println("a-1 , b");
			exploreAssistant(block, elt, a-1, b, aM1, bM1);
			// a , b-1
			//System.out.println("a , b-1");
			exploreAssistant(block, elt, a, b-1, aM1, bM1);
		}
		
	}
	
	private void exploreAssistant(Bloc block,int elt, int a,int b,int aM1, int bM1){
//		System.out.print("assistant : "+elt+"<>"+ " : "+a+","+b+" - "+aM1+","+bM1);
//		System.out.println((!(a == aM1 && b == bM1 )));
//		System.out.println((aM1==-1 && bM1 ==-1));
//		System.out.println((elt == tabGrid.get(a).get(b)));
		if(!testIfCaseComputed(a,b) &&  ((!(a == aM1 && b == bM1 )) || (aM1==-1 && bM1 ==-1)) && elt == tabGrid.get(a).get(b)){
			//System.out.println(" : Yeah");
			block.addCoords(a, b);
			explore(block);
		}
		//System.out.println("");
	}

	private boolean testIfCaseComputed(int a, int b) {
		int nbBlocs = tabBlocs.size();
		boolean output = false;
		boolean boo = false;
		for (int i = 0; i < nbBlocs; i++) {
			boo = tabBlocs.get(i).paireIsIn(a, b);			
			output  = output || boo;
		}
		return output;
		
	}
	
	public static void registerView(MainGridView v){
		gridView = v;
	}

	public void printGrid(){
		System.out.println("/******** GRID ********/");
		for (int i = 0; i < tabGrid.size(); i++) {
			System.out.print("# ");
			for (int j = 0; j < tabGrid.get(i).size(); j++) {
				System.out.print(tabGrid.get(i).get(j)+" | ");
			}
			System.out.println("# ");
		}
		System.out.println("/******** END GRID ********/");
	}
	
	public void printBlocks(){
		System.out.println("/******** BLOCKS ********/");
		for (int i = 0; i < tabBlocs.size(); i++) {
			System.out.print("# "+ tabBlocs.get(i).getElement()+" # ");
			for (int j = 0; j < tabBlocs.get(i).getCoords().size(); j++) {
				System.out.print(" (");
				System.out.print(tabBlocs.get(i).getCoords().get(j).getA());
				System.out.print(",");
				System.out.print(tabBlocs.get(i).getCoords().get(j).getB());
				System.out.print(")");
			}
			System.out.println("# ");
		}
		System.out.println("/******** END BLOCKS ********/");
	}

	public void updateElement(int elt, int posX, int posY) {
		tabGrid.get(posX).set(posY, elt);
	}

}
