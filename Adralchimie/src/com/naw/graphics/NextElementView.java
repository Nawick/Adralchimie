package com.naw.graphics;

import com.naw.engine.AdralchimieActivity;
import com.naw.engine.MainGrid;
import com.naw.engine.NextElement;
import com.naw.tools.Paire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class NextElementView extends View {

	private int nbCols = MainGrid.nbCols ;
	private int spaceBetween = 1;
	private int x = 1;
	private int y = 1;
	private int elt;
	private int width = 0;
	private int height = 80;
	private Bitmap piece1_normal;
	private Bitmap piece2_normal;
	private Bitmap piece3_normal;
	private Bitmap piece4_normal;
	private Bitmap piece5_normal;
	private Bitmap piece6_normal;
	private Bitmap piece7_normal;
	private Bitmap piece8_normal;
	private Bitmap piece9_normal;
	private Bitmap piece10_normal;
	private Bitmap piece11_normal;
	private Bitmap piece12_normal;
	private NextElement nextElementController;
	private Paire element;
	private Paire position;
	
	public NextElementView(Context context, AttributeSet attrs) {
		super(context, attrs);
		NextElement.registerView(this);
		nextElementController = NextElement.getInstance();
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
		element = nextElementController.getElement();
		position = nextElementController.getPosition();
	}

	public void setElement(Paire elt){
		element = elt;
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if(width == 0 ){
			width = canvas.getWidth();
		}
		
		
		Paint paint = new Paint();
		paint.setARGB(255, 0, 0, 0);
		float f = (float) (1.0/nbCols) ;
		
		RectF r1;
		RectF r2;
		int posX = position.getA();
		int posY = position.getB();
		
		if(posX == 0 || posX == 2){// Positions verticale
			r1 = new RectF(width*(f* posY), 0, width*(f*( posY+1)), (float) (height*0.5));
			r2 = new RectF(width*(f*posY), height/2, width*(f*(posY+1)), height);
		}else{ // Position horizontale
			r1 = new RectF(width*(f* posY), (float) (height*0.25), width*(f*( posY+1)), (float) (height*0.75));
			r2 = new RectF(width*(f* (posY+1)), (float) (height*0.25), width*(f*( posY+2)), (float) (height*0.75));
		}
		
		if(posX == 0 || posX == 3){
			elt = element.getA();
			switch (elt) {
			case 1:  canvas.drawBitmap(piece1_normal, null, r1, paint);			break;
			case 2:  canvas.drawBitmap(piece2_normal, null, r1, paint);			break;
			case 3:  canvas.drawBitmap(piece3_normal, null, r1, paint);			break;
			case 4:  canvas.drawBitmap(piece4_normal, null, r1, paint);			break;
			case 5:  canvas.drawBitmap(piece5_normal, null, r1, paint);			break;
			case 6:  canvas.drawBitmap(piece6_normal, null, r1, paint);			break;
			case 7:  canvas.drawBitmap(piece7_normal, null, r1, paint);			break;
			case 8:  canvas.drawBitmap(piece8_normal, null, r1, paint);			break;
			case 9:  canvas.drawBitmap(piece9_normal, null, r1, paint);			break;
			case 10: canvas.drawBitmap(piece10_normal, null, r1, paint);			break;
			case 11: canvas.drawBitmap(piece11_normal, null, r1, paint);			break;
			case 12: canvas.drawBitmap(piece12_normal, null, r1, paint);			break;
			default:
				break;
			}
			elt = element.getB();
			switch (elt) {
			case 1: canvas.drawBitmap(piece1_normal, null, r2, paint);			break;
			case 2: canvas.drawBitmap(piece2_normal, null, r2, paint);			break;
			case 3: canvas.drawBitmap(piece3_normal, null, r2, paint);			break;
			case 4: canvas.drawBitmap(piece4_normal, null, r2, paint);			break;
			case 5: canvas.drawBitmap(piece5_normal, null, r2, paint);			break;
			case 6: canvas.drawBitmap(piece6_normal, null, r2, paint);			break;
			case 7: canvas.drawBitmap(piece7_normal, null, r2, paint);			break;
			case 8: canvas.drawBitmap(piece8_normal, null, r2, paint);			break;
			case 9: canvas.drawBitmap(piece9_normal, null, r2, paint);			break;
			case 10: canvas.drawBitmap(piece10_normal, null, r2, paint);			break;
			case 11: canvas.drawBitmap(piece11_normal, null, r2, paint);			break;
			case 12: canvas.drawBitmap(piece12_normal, null, r2, paint);			break;
			default:
				break;
			}
		}else{
			elt = element.getB();
			switch (elt) {
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
			default:
				break;
			}
			elt = element.getA();
			switch (elt) {
			case 1: canvas.drawBitmap(piece1_normal, null, r2, paint);			break;
			case 2: canvas.drawBitmap(piece2_normal, null, r2, paint);			break;
			case 3: canvas.drawBitmap(piece3_normal, null, r2, paint);			break;
			case 4: canvas.drawBitmap(piece4_normal, null, r2, paint);			break;
			case 5: canvas.drawBitmap(piece5_normal, null, r2, paint);			break;
			case 6: canvas.drawBitmap(piece6_normal, null, r2, paint);			break;
			case 7: canvas.drawBitmap(piece7_normal, null, r2, paint);			break;
			case 8: canvas.drawBitmap(piece8_normal, null, r2, paint);			break;
			case 9: canvas.drawBitmap(piece9_normal, null, r2, paint);			break;
			case 10: canvas.drawBitmap(piece10_normal, null, r2, paint);			break;
			case 11: canvas.drawBitmap(piece11_normal, null, r2, paint);			break;
			case 12: canvas.drawBitmap(piece12_normal, null, r2, paint);			break;
			default:
				break;
			}
		}
		
		
		/*for (int i = 0; i < 2; i++) {
			float h = (float) (height*((0.5)*(i+1)));
			RectF r = new RectF(0, i*(height/2), width, h);
			paint.setARGB(255, 128, i*100, (i*10+1)*50);
			
			
			for (int j = 0; j < nbCols; j++) {
				
				RectF r1 = new RectF(width*(f*j), i*(height/2), width*(f*(j+1)), h);
				switch (elt) {
				case 1: canvas.drawBitmap(piece1_normal, null, r1, paint);			break;
				case 2: canvas.drawBitmap(piece2_normal, null, r1, paint);			break;
				case 3: canvas.drawBitmap(piece3_normal, null, r1, paint);			break;
				case 4: canvas.drawBitmap(piece4_normal, null, r1, paint);			break;
				default:
					break;
				}
			}
		}*/
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		height = MeasureSpec.getSize(heightMeasureSpec)/5 ;
		width =  MeasureSpec.getSize(widthMeasureSpec) ;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		final int desiredHSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED);
		//final int desiredWSpec = MeasureSpec.makeMeasureSpec(pixelWidth, MeasureSpec.MODE_CONSTANT);
		setMeasuredDimension(widthMeasureSpec, desiredHSpec);
	}
	

}
