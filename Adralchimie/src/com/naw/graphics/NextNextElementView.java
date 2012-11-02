package com.naw.graphics;

import com.naw.engine.AdralchimieActivity;
import com.naw.engine.NextElement;
import com.naw.engine.NextNextElement;
import com.naw.tools.Paire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class NextNextElementView extends View {

	private int height = 50 ;
	private int width = 100;
	private Bitmap piece1_little;
	private Bitmap piece2_little;
	private Bitmap piece3_little;
	private Bitmap piece4_little;
	private Bitmap piece5_little;
	private Bitmap piece6_little;
	private Bitmap piece7_little;
	private Bitmap piece8_little;
	private Bitmap piece9_little;
	private Bitmap piece10_little;
	private Bitmap piece11_little;
	private Bitmap piece12_little;
	private NextNextElement nextNextElementController;
	private Paire element;

	public NextNextElementView(Context context, AttributeSet attrs) {
		super(context, attrs);
		NextNextElement.registerView(this);
		piece1_little = ((AdralchimieActivity)(context)).piece1_little;
		piece2_little = ((AdralchimieActivity)(context)).piece2_little;
		piece3_little = ((AdralchimieActivity)(context)).piece3_little;
		piece4_little = ((AdralchimieActivity)(context)).piece4_little;
		piece5_little = ((AdralchimieActivity)(context)).piece5_little;
		piece6_little = ((AdralchimieActivity)(context)).piece6_little;
		piece7_little = ((AdralchimieActivity)(context)).piece7_little;
		piece8_little = ((AdralchimieActivity)(context)).piece8_little;
		piece9_little = ((AdralchimieActivity)(context)).piece9_little;
		piece10_little = ((AdralchimieActivity)(context)).piece10_little;
		piece11_little = ((AdralchimieActivity)(context)).piece11_little;
		piece12_little = ((AdralchimieActivity)(context)).piece12_little;
		
		nextNextElementController = NextNextElement.getInstance();
		element = nextNextElementController.getElement();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint paint = new Paint();
		paint.setARGB(255, 255, 0, 0);
		
		element = nextNextElementController.getElement();
		RectF r1 = new RectF(0, 0, width/2, height);
		//System.out.println("Draw NEXTNEXT : "+element.toString());
		int elt = element.getB();
		switch (elt) {
		case 1:  canvas.drawBitmap(piece1_little, null, r1 , paint);			break;
		case 2:  canvas.drawBitmap(piece2_little, null, r1, paint); 	 		break;
		case 3:  canvas.drawBitmap(piece3_little, null, r1, paint); 	   		break;
		case 4:  canvas.drawBitmap(piece4_little, null, r1, paint); 			break;
		case 5:  canvas.drawBitmap(piece5_little, null, r1, paint); 			break;
		case 6:  canvas.drawBitmap(piece6_little, null, r1 , paint);			break;
		case 7:  canvas.drawBitmap(piece7_little, null, r1, paint); 			break;
		case 8:  canvas.drawBitmap(piece8_little, null, r1, paint); 			break;
		case 9:  canvas.drawBitmap(piece9_little, null, r1, paint); 	 	 	break;
		case 10: canvas.drawBitmap(piece10_little, null, r1, paint);			break;
		case 11: canvas.drawBitmap(piece11_little, null, r1 , paint);			break;
		case 12: canvas.drawBitmap(piece12_little, null, r1, paint);			break;
		default:
			break;
		}
		
		r1 = new RectF(width/2, 0, width, height);
		elt = element.getA();
		switch (elt) {
		case 1:  canvas.drawBitmap(piece1_little, null, r1 , paint);			break;
		case 2:  canvas.drawBitmap(piece2_little, null, r1, paint);			break;
		case 3:  canvas.drawBitmap(piece3_little, null, r1, paint);			break;
		case 4:  canvas.drawBitmap(piece4_little, null, r1, paint);			break;
		case 5:  canvas.drawBitmap(piece5_little, null, r1, paint);			break;
		case 6:  canvas.drawBitmap(piece6_little, null, r1 , paint);			break;
		case 7:  canvas.drawBitmap(piece7_little, null, r1, paint);			break;
		case 8:  canvas.drawBitmap(piece8_little, null, r1, paint);			break;
		case 9:  canvas.drawBitmap(piece9_little, null, r1, paint);			break;
		case 10: canvas.drawBitmap(piece10_little, null, r1, paint);			break;
		case 11: canvas.drawBitmap(piece11_little, null, r1 , paint);			break;
		case 12: canvas.drawBitmap(piece12_little, null, r1, paint);			break;
		default:
			break;
		}
		
//		canvas.drawARGB(255, 255, 125, 125);
//		canvas.drawText("NEXT-NEXT", 10, 10, paint);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		final int desiredHSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED);
		final int desiredWSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.UNSPECIFIED);
		setMeasuredDimension(desiredWSpec, desiredHSpec);
	}

}
