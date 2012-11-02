package com.naw.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class EndGameView extends View {

	private float paddLeft = 0;
	private float paddTop = 0;
	private float paddRight = 0;
	private float paddBottom = 0;

	public EndGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		
		Paint paint =new Paint();
		paint.setARGB(200, 50, 100, 150);
		RectF area = new RectF(paddLeft, paddTop, canvas.getWidth()-paddRight, canvas.getHeight()-paddBottom);
		canvas.drawRect(area, paint);
	}

}
