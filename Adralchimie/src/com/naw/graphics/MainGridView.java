package com.naw.graphics;

import java.util.ArrayList;

import com.naw.engine.AdralchimieActivity;
import com.naw.engine.Bloc;
import com.naw.engine.MainGrid;
import com.naw.engine.R;
import com.naw.tools.Paire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.MeasureSpec;

public class MainGridView extends View {

	private int nbCols = MainGrid.nbCols ;
	private int nbRows = MainGrid.nbRows ;
	private int height = 0;
	private int width = 0;
	private ArrayList<ArrayList<Integer>> tabGrid;
	private ArrayList<Bloc> tabBlocs; // Liste de composants formant un bloc 
	private MainGrid mainGridController;
	private Bitmap piece1_normal ;
	private Bitmap piece2_normal ;
	private Bitmap piece3_normal ;
	private Bitmap piece4_normal ;
	private Bitmap piece5_normal ;
	private Bitmap piece6_normal ;
	private Bitmap piece7_normal ;
	private Bitmap piece8_normal ;
	private Bitmap piece9_normal ;
	private Bitmap piece10_normal ;
	private Bitmap piece11_normal ;
	private Bitmap piece12_normal ;

	public MainGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		MainGrid.registerView(this);
		mainGridController = MainGrid.getInstance();
		piece1_normal = ((AdralchimieActivity)(context)).piece1_normal;
		piece2_normal = ((AdralchimieActivity)(context)).piece2_normal;
		piece3_normal = ((AdralchimieActivity)(context)).piece3_normal;
		piece4_normal = ((AdralchimieActivity)(context)).piece4_normal;
		piece5_normal = ((AdralchimieActivity)(context)).piece5_normal;
		piece6_normal = ((AdralchimieActivity)(context)).piece6_normal;
		piece7_normal = ((AdralchimieActivity)(context)).piece7_normal;
		piece8_normal = ((AdralchimieActivity)(context)).piece8_normal;
		piece9_normal = ((AdralchimieActivity)(context)).piece9_normal;
		piece10_normal = ((AdralchimieActivity)(context)).piece10_normal;
		piece11_normal = ((AdralchimieActivity)(context)).piece11_normal;
		piece12_normal = ((AdralchimieActivity)(context)).piece12_normal;
		//tabGrid = (ArrayList<ArrayList<Integer>>) mainGridController.getGrid().clone();
		tabGrid = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < mainGridController.getGrid().size(); i++) {
			tabGrid.add((ArrayList<Integer>)mainGridController.getGrid().get(i).clone());
		}
		this.tabBlocs = new ArrayList<Bloc>();
	}
	
	public void updateGrid(){
		tabGrid = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < mainGridController.getGrid().size(); i++) {
			tabGrid.add((ArrayList<Integer>)mainGridController.getGrid().get(i).clone());
		}
	}
	
	private boolean animate = false;
	private int animationInt = 0;
	public void animateDummy(){
		Thread t = new Thread(){
			

			@Override
			public void run() {
				animationInt = -100;
				int b = 400;
				animate = true;
				while (animationInt<b) {
					animationInt= animationInt + 4;
					
					postInvalidate();
					try {
						Thread.sleep(4);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				animate = false;
				
			}
		};
		t.start();
	}
	
	private RectF rectADown = null;
	private RectF rectBDown = null;
	private int eltADown = 0;
	private int eltBDown = 0;
	private int colDown = 0;
	private boolean vertical = false;
	
	long mAnimStartTime;

	Handler mHandler = new Handler();
	Runnable mTick = new Runnable() {
	    public void run() {
	        invalidate();
	        mHandler.postDelayed(this, 20); // 20ms == 60fps
	    }
	};
	void startAnimation() {
	    mAnimStartTime = SystemClock.uptimeMillis();
	    mHandler.removeCallbacks(mTick);
	    mHandler.post(mTick);
	}
	
	void stopAnimation() {
	    mHandler.removeCallbacks(mTick);
	}
	
	public void animateDown(Paire p, int col1, int col2){
		eltADown = p.getA();
		eltBDown = p.getB();
		colDown = col1;
		vertical = (col1 == col2);
		if (vertical) {
			eltADown = p.getB();
			eltBDown = p.getA();
		}
		
		Thread t = new Thread(){
			

			@Override
			public void run() {
				animationInt = -100;
				int b = 500;
				animate = true;
				while (animationInt<b) {
					animationInt= animationInt + 4;
					
					postInvalidate();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				animate = false;
				
				if (vertical) {
					pile(colDown,eltBDown);
					pile(colDown,eltADown);
				}else{
					pile(colDown,eltADown);
					pile(colDown+1,eltBDown);
				}
				// Doit on lancer l'animation des explosions
				//System.out.println("tabBlocsAnim?");
				//System.out.println(tabBlocs.toString());
				if (tabBlocs.size()>0) {
					animateExplostions();
				}
			}
		};
		t.start();
		
	}
	
	public void animateExplostions(){
		// Pour chaque bloc
		
			// Pour chacun des éléments constituant le bloc
		System.out.println("BOOM");
		animateVarExplode(25, 10, 10);
		
	}
	
	/**
	 * Facilitation of elements render
	 * 
	 * @param element Number of the element
	 * @return A bitmap representing the element
	 */
	private Bitmap getBitmapElement(int element){
		switch (element) {
			case 1: return piece1_normal ;			
			case 2: return piece2_normal ;			
			case 3: return piece3_normal ;			
			case 4: return piece4_normal ;			
			case 5: return piece5_normal ;			
			case 6: return piece6_normal ;			
			case 7: return piece7_normal ;			
			case 8: return piece8_normal ;			
			case 9: return piece9_normal ;			
			case 10: return piece10_normal ;			
			case 11: return piece11_normal ;			
			case 12: return piece12_normal ;			
			default: return piece1_normal ;			
		}
	}
	
	/**
	 * Create a Thread in wich the global variable varExplode will be incremented by "step", "time" times every "lapse" ms
	 * @param time
	 * @param step
	 * @param lapse
	 */
	private int varExplode = 0;
	private boolean animExplode = false;
	private void animateVarExplode(final int time,final int step,final int lapse){
		varExplode = 0;
		
		Thread t = new Thread(){
			@Override
			public void run() {
				int count = 0;
				animExplode = true;
				while (count<time) {
					varExplode = varExplode + step ;
					count++;
					postInvalidate();
					try {
						Thread.sleep(lapse);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				updateGrid();
				
				animExplode = false;
				tabBlocs = new ArrayList<Bloc>(); 
				
			}
		};
		t.start();
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		//canvas.drawARGB(255, 0, 255, 255);
		Paint paint = new Paint();
		paint.setARGB(255, 0, 0, 0);
		int elt = 0;
		//canvas.drawText("MainGridView", 25, 25, paint );
		//System.out.println("Taille Grille et premier elt : "+tabGrid.size()+" , "+tabGrid.get(0).get(0));
		float f = (float) (1.0/nbCols) ;
		for (int i = 0; i < nbRows; i++) {
			float h = (float) (height*(1.0/nbRows)*(i+1));
			RectF r = new RectF(0, i*(height/2), width, h);
			paint.setARGB(255, 255, 0, 0);
			
			
			//canvas.drawRect(r , paint);
			for (int j = 0; j < nbCols; j++) {
				elt = tabGrid.get(j).get(nbRows-1-i);
				
				if(elt != 0){
					//System.out.println("Element "+j+" , "+(nbRows-1-i));
					// For each blocks
					// TODO of the given rank
					boolean isGonnaXplode = false;
					if (animExplode) {
						for (int j2 = 0; j2 < tabBlocs.size(); j2++) {
							//System.out.println(j+","+(nbRows-1-i));
							if (tabBlocs.get(j2).paireIsIn(j, (nbRows-1-i))) {
								isGonnaXplode = true;
								
							}
						}
					}
					
					if (isGonnaXplode) {
						//System.out.println("La paire "+j+" "+(nbRows-1-i)+" est en train d'exploser");
						paint.setARGB(255-varExplode, 255, 0, 0);
					}else{
						paint.setARGB(255, 255, 0, 0);
					}
					RectF r1 = new RectF(width*(f*j), i*(height/nbRows), width*(f*(j+1)), h);
					//paint.setARGB(255, (i+1)*50, (i+1)*20, 15*((i+1)*2)*(j+3));
					//canvas.drawRect(r1 , paint);
					canvas.drawBitmap(getBitmapElement(elt), null, r1, paint);
				/*	switch (elt) {
					case 1: canvas.drawBitmap(piece1_normal, null, r1, paint);			break;
					case 2: canvas.drawBitmap(piece2_normal, null, r1, paint);			break;
					case 3: canvas.drawBitmap(piece3_normal, null, r1, paint);			break;
					case 4: canvas.drawBitmap(piece4_normal, null, r1, paint);			break;
					case 5: canvas.drawBitmap(piece5_normal, null, r1, paint);			break;
					case 6: canvas.drawBitmap(piece6_normal, null, r1, paint);			break;
					case 7: canvas.drawBitmap(piece7_normal, null, r1, paint);			break;
					case 8: canvas.drawBitmap(piece8_normal, null, r1, paint);			break;
					case 9: canvas.drawBitmap(piece9_normal, null, r1, paint);			break;
					case 10: canvas.drawBitmap(piece10_normal, null, r1, paint);			break;
					case 11: canvas.drawBitmap(piece11_normal, null, r1, paint);			break;
					case 12: canvas.drawBitmap(piece12_normal, null, r1, paint);			break;
					default:paint.setARGB(255, elt*50, (elt+1)*20, 60);
							canvas.drawRect(r1 , paint);
						break;
					}*/
				}
				
			}
			
			if(animate){
				float eltHeight = (float)(height*(1.0/nbRows)) ;
				int lastIndexColDown1 = getLastIndexForColumn(colDown);
				int lastIndexColDown2 = -1;
				if (vertical) {
					lastIndexColDown2 = lastIndexColDown1 - 1;
				}else{
					lastIndexColDown2 = getLastIndexForColumn(colDown +1 );
				}
				int coordsLast1 = (nbRows-(lastIndexColDown1+2))*(height/nbRows);
				int coordsLast2 = (nbRows-(lastIndexColDown2+2))*(height/nbRows);
				//System.out.println("CoordsLast : "+coordsLast);
				
				RectF r1;
				RectF r2;
				if (vertical) {
					if(animationInt < coordsLast1-2*eltHeight){
						r1 = new RectF(width*(f*colDown), animationInt, width*(f*(colDown+1)),  (animationInt+eltHeight));
						
					}else{
						r1 = new RectF(width*(f*colDown), coordsLast1-eltHeight, width*(f*(colDown+1)),  (coordsLast1));
					}
					
					if(animationInt < (coordsLast2-2*eltHeight)){
						r2 = new RectF(width*(f*colDown), animationInt + eltHeight, width*(f*(colDown+1)),  (animationInt+2*eltHeight));
					}else{
						r2 = new RectF(width*(f*colDown), coordsLast2-eltHeight, width*(f*(colDown+1)),  (coordsLast2));
					}
					
				}else{
					if(animationInt < coordsLast1){
						r1 = new RectF(width*(f*colDown), animationInt, width*(f*(colDown+1)),  (animationInt+eltHeight));
						
					}else{
						r1 = new RectF(width*(f*colDown), coordsLast1, width*(f*(colDown+1)),  (coordsLast1+eltHeight));
					}
					
					if(animationInt < coordsLast2){
						r2 = new RectF(width*(f*colDown)+ (width*f), animationInt, width*(f*(colDown+1))+ (width*f),  (animationInt+eltHeight));
					}else{
						r2 = new RectF(width*(f*colDown)+ (width*f), coordsLast2, width*(f*(colDown+1))+ (width*f),  (coordsLast2+eltHeight));
					}
					
				}
				
				
				canvas.drawBitmap(getBitmapElement(eltADown), null, r1, paint);
				canvas.drawBitmap(getBitmapElement(eltBDown), null, r2, paint);
			}
			
		}
	}
	
	/**
	 * 
	 * @param col Index of the column
	 * @return Index of the uppest element
	 */
	private int getLastIndexForColumn(int col){
		int r = 0;
		ArrayList<Integer> tab = tabGrid.get(col);
		int size = tab.size();
		for(int i=0;i<size;i++){
			//System.out.println(tab.get(i));
			if(tab.get(i) == 0){
				return i-1;
			}
		}
		
		return r;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		height = (MeasureSpec.getSize(heightMeasureSpec)*5)/5 ;
		width =  MeasureSpec.getSize(widthMeasureSpec) ;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		final int desiredHSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED);
		//final int desiredWSpec = MeasureSpec.makeMeasureSpec(pixelWidth, MeasureSpec.MODE_CONSTANT);
		setMeasuredDimension(widthMeasureSpec, desiredHSpec);
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

	public void updateBlocks(ArrayList<Bloc> tabBlock, int rankBlock) {
		//System.out.println("Le tableau de bloc a été mis à jour, des éléments vont exploser "+tabBlock.size());
		
		for (int i = 0; i < tabBlock.size(); i++) {
			this.tabBlocs.add(tabBlock.get(i));
		}
		
	}

}
