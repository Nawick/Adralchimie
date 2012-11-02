package com.naw.engine;


import java.util.ArrayList;

import com.naw.tools.DBAdapter;
import com.naw.tools.Triplet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class AdralchimieActivity extends Activity implements OnTouchListener {
	private MainGrid mainGridController;
	private NextElement nextElementController;
	private NextNextElement nextNextElementController;
	public Bitmap piece1_normal ;
	public Bitmap piece2_normal ;
	public Bitmap piece3_normal ;
	public Bitmap piece4_normal ;
	public Bitmap piece5_normal ;
	public Bitmap piece6_normal ;
	public Bitmap piece7_normal ;
	public Bitmap piece8_normal ;
	public Bitmap piece9_normal ;
	public Bitmap piece10_normal ;
	public Bitmap piece11_normal ;
	public Bitmap piece12_normal ;
	public Bitmap piece1_little ;
	public Bitmap piece2_little ;
	public Bitmap piece3_little ;
	public Bitmap piece4_little ;
	public Bitmap piece5_little ;
	public Bitmap piece6_little ;
	public Bitmap piece7_little ;
	public Bitmap piece8_little ;
	public Bitmap piece9_little ;
	public Bitmap piece10_little ;
	public Bitmap piece11_little ;
	public Bitmap piece12_little ;
	private DBAdapter db;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		piece1_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece1_normal);
		piece2_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece2_normal);
		piece3_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece3_normal);
		piece4_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece4_normal);
		piece5_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece5_normal);
		piece6_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece6_normal);
		piece7_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece7_normal);
		piece8_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece8_normal);
		piece9_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece9_normal);
		piece10_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece10_normal);
		piece11_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece11_normal);
		piece12_normal = BitmapFactory.decodeResource(getResources(), R.drawable.piece12_normal);

		piece1_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece1_little);
		piece2_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece2_little);
		piece3_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece3_little);
		piece4_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece4_little);
		piece5_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece5_little);
		piece6_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece6_little);
		piece7_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece7_little);
		piece8_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece8_little);
		piece9_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece9_little);
		piece10_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece10_little);
		piece11_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece11_little);
		piece12_little = BitmapFactory.decodeResource(getResources(), R.drawable.piece12_little);


		mainGridController = new MainGrid(this);
		nextNextElementController = new NextNextElement();
		nextElementController = new NextElement(nextNextElementController.getElement());
		nextNextElementController.generateElement(mainGridController.currentRank);




		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		View v = findViewById(R.id.gameSection);
		v.setOnTouchListener(this);
		findViewById(R.id.endGameSection).setVisibility(View.GONE);


		CustomApplication cApp = (CustomApplication) getApplicationContext();
		db = cApp.getDBAdapter();
		if(db.selectScore() != 0){
			loadFromDB();
		}
		updateGraphics();
	}

	public void startGame(){

		mainGridController = new MainGrid(this);
		nextNextElementController = new NextNextElement();
		nextElementController = new NextElement(nextNextElementController.getElement());
		nextNextElementController.generateElement(mainGridController.currentRank);
		db.updateGameSave(nextNextElementController.getElement(), nextElementController.getElement(), mainGridController.getGrid(), 0, db.selectLastPseudo());
	}

	public void endGame(){
		mainGridController.gameOver = true;
		findViewById(R.id.endGameSection).setVisibility(View.VISIBLE);
		db.deleteSavedGame();
	}

	public void saveScoresWithPseudo(View v){
		
		TextView tv = (TextView) findViewById(R.id.scoreEndGameSection);
		int score = 0;
		if(tv != null){
			score = Integer.parseInt((String) tv.getText());
		}
		EditText et = (EditText) findViewById(R.id.editPseudo);
		String pseudo = "";
		if(et != null){
			
			pseudo = (String) et.getText().toString();
			if(et.getText().length() <= 0){
				pseudo = "Noobie001";
			}
		}
		endGame();
		db.addHighScore(pseudo, score);
		Intent myIntent = new Intent(AdralchimieActivity.this, HomeActivity.class);
		AdralchimieActivity.this.startActivity(myIntent);
	}

	public void updateGraphics() {
		mainGridController.updateGraphics();
		nextElementController.updateGraphics();
		nextNextElementController.updateGraphics();
	}

	public void loadFromDB(){
		//System.out.println("DBNextNext : "+db.selectNextNextElement().toString());
		nextElementController.setElement(db.selectNextElement());
		nextNextElementController.setElement(db.selectNextNextElement());
		updateScores(db.selectScore());
		ArrayList<Triplet<Integer,Integer,Integer>> dbGrid = db.selectMainGrid();
		int s = dbGrid.size();
		//System.out.println("Taille DBGrid : "+s);
		for (int i = 0; i < s; i++) {
			//System.out.println(" --> DBGRID : ");
			//System.out.println("Element "+dbGrid.get(i).getA() + " en ("+dbGrid.get(i).getB()+","+dbGrid.get(i).getC()+")");
			mainGridController.updateElement(dbGrid.get(i).getA(),dbGrid.get(i).getB(),dbGrid.get(i).getC());
		}
	}

	public void updateScores(int score){
		TextView tv = (TextView) findViewById(R.id.scoresSection);
		if(tv != null){
			tv.setText(String.valueOf(score));
		}
		TextView tv2 = (TextView) findViewById(R.id.scoreEndGameSection);
		if(tv2 != null){
			tv2.setText(String.valueOf(score));
		}
	}

	public void updateDB(){
		TextView tv = (TextView) findViewById(R.id.scoresSection);
		int score = 0;
		if(tv != null){
			score = Integer.parseInt((String) tv.getText());
		}

		db.updateGameSave(nextNextElementController.getElement(), nextElementController.getElement(), mainGridController.getGrid(), score, "Ad");
		//System.out.println("En DB : score="+db.selectScore());
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//System.out.println("Touch "+keyCode+" - "+event);
		if(!mainGridController.gameOver){

			switch (keyCode) {
			case 19: nextElementController.rotate();			
			break; // Up key
			case 20: mainGridController.addElement(nextElementController.getElement(), nextElementController.getPosition());
			nextElementController.setElement(nextNextElementController.getElement());
			nextNextElementController.generateElement(mainGridController.currentRank);
			mainGridController.updateScore();
			break; // Down key
			case 21: nextElementController.toLeft();			
			break; // Left key
			case 22: nextElementController.toRight();			
			break; // Right key

			default: mainGridController.computeCycle();			break;
			}
		}


		return super.onKeyDown(keyCode, event);
	}


	private float touchDownX = 0;
	private float touchDownY = 0;

	private float touchUpX = 0;
	private float touchUpY = 0;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//System.out.println("Action : "+event.getAction());
		if(!mainGridController.gameOver){
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: 
				touchDownX = event.getX();
				touchDownY = event.getY();
				break;
			case MotionEvent.ACTION_UP :
				touchUpX = event.getX();
				touchUpY = event.getY();
				//System.out.println("Down: ("+touchDownX+","+touchDownY+")  Up : ("+event.getX()+","+event.getY()+")");


				if( touchUpY < touchDownY-30 && (Math.abs(touchDownX-touchUpX)<30) ){ // Up gesture
					nextElementController.rotate();
				}else if( touchUpY > touchDownY+30 && (Math.abs(touchDownX-touchUpX)<30) ){ // Down gesture
					mainGridController.addElement(nextElementController.getElement(), nextElementController.getPosition());
					nextElementController.setElement(nextNextElementController.getElement());
					nextNextElementController.generateElement(mainGridController.currentRank);	
					mainGridController.updateScore();
				}else if( touchUpX < touchDownX-10 && (Math.abs(touchDownY-touchUpY)<100) ){ // Left Gesture
					nextElementController.toLeft();
				}else if( touchUpX > touchDownX+10 && (Math.abs(touchDownY-touchUpY)<100) ){ // Right Gesture
					nextElementController.toRight();
				}else{
					nextElementController.rotate();
				}
				break;
			default:
				break;
			}
		}
		return true;
	}
}