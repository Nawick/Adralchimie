package com.naw.engine;

import java.util.ArrayList;
import java.util.List;

import com.naw.tools.DBAdapter;
import com.naw.tools.Triplet;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

public class HighscoreListActivity extends ListActivity {
	private HighscoreListAdapter mAdapter;
	private ListView lv;
	private DBAdapter db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		
		Triplet<Integer,String,String> score = null;
		mAdapter = new HighscoreListAdapter(this);
		CustomApplication cApp = (CustomApplication) getApplicationContext();
		db = cApp.getDBAdapter();
		
		
        ArrayList<Triplet<Integer, String, String>> scores = this.db.selectHighscores();
        //db.close();
        
		for (int i = 0; i < scores.size(); i++) {
			
            mAdapter.addItem(scores.get(i));
        }
		
        setListAdapter(mAdapter);
        
        /*ListView v = getListView();
        v.setCacheColorHint(Color.TRANSPARENT);*/
	}
}
