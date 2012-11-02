package com.naw.engine;

import com.naw.tools.DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class HomeActivity extends Activity {
	private DBAdapter db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		CustomApplication cApp = (CustomApplication) getApplicationContext();
		db = cApp.getDBAdapter();
		if(db.selectScore() == 0){
			findViewById(R.id.button2).setVisibility(View.INVISIBLE);
		}else{
			findViewById(R.id.button2).setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	protected void onResume() {
		if(db.selectScore() == 0){
			findViewById(R.id.button2).setVisibility(View.INVISIBLE);
		}else{
			findViewById(R.id.button2).setVisibility(View.VISIBLE);
		}
		super.onResume();
	}


	public void launchNewGame(View v){
		
		db.deleteSavedGame();
		Intent myIntent = new Intent(HomeActivity.this, AdralchimieActivity.class);
		HomeActivity.this.startActivity(myIntent);
	}

	public void launchSavedGame(View v){
		Intent myIntent = new Intent(HomeActivity.this, AdralchimieActivity.class);
		HomeActivity.this.startActivity(myIntent);
	}

	public void launchHighscores(View v){
		Intent myIntent = new Intent(HomeActivity.this, HighscoreListActivity.class);
		HomeActivity.this.startActivity(myIntent);
	}
	
	public void launchAbout(View v){
		System.out.println("Pas implémenté");
	}
}
