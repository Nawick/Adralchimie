package com.naw.engine;

import com.naw.tools.DBAdapter;

import android.app.Application;

public class CustomApplication extends Application {

	private DBAdapter db = null;
	
	public DBAdapter getDBAdapter(){
		
		if(db == null){
			//System.out.println("Create singleton");
			db = new DBAdapter(getApplicationContext());
		}else{
			//System.out.println("Reuse singleton");
		}
		return db;
	}
}
