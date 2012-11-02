package com.naw.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DBAdapter {
	private static final String DATABASE_NAME = "adralchimie.db";
	private static final int DATABASE_VERSION = 2;
	private static final String TABLE_ADRAL_NEXT_NEXT = "adral_next_next";
	private static final String TABLE_ADRAL_NEXT = "adral_next";
	private static final String TABLE_ADRAL_MAIN_GRID = "adral_main_grid";
	private static final String TABLE_ADRAL_SCORE = "adral_score";
	private static final String TABLE_ADRAL_LAST_PSEUDO = "adral_last_pseudo";
	private static final String TABLE_ADRAL_LOCAL_HIGHSCORE = "adral_local_highscore";
	private Context context;
	private SQLiteDatabase db;

	//private SQLiteStatement insertStmt;
	private SQLiteStatement countStmt;
	private ContentValues values;
	private static final String INSERT = "insert into " + TABLE_ADRAL_LAST_PSEUDO + " (pseudo) values (?)";
	private static final String COUNT = "select count(*) from " + TABLE_ADRAL_LAST_PSEUDO;

	public DBAdapter(Context context) {
		this.context = context;

		values = new ContentValues();

		OpenHelper openHelper = new OpenHelper(this.context);
		this.db = openHelper.getWritableDatabase();

		//this.insertStmt = this.db.compileStatement(INSERT);
		this.countStmt = this.db.compileStatement(COUNT);

	}

	public long insertTest() {
		/*int idGame = (int) insertGame("testGame", 1, 5, 2, 3, 2);
		int idP = 0;
		idP = (int) insertPlayer("Porky Pig");
		insertGamePlayer(idP, idGame);
		
		
		*/
		return 0;
	}
	
	
	private long insertNextNextelement(int eltA, int eltB){
		values.clear();
		values.put("elementA", eltA);
		values.put("elementB", eltB);
		
		return this.db.insert(TABLE_ADRAL_NEXT_NEXT, null, values);
	}
	
	private long insertNextelement(int eltA, int eltB){
		values.clear();
		values.put("elementA", eltA);
		values.put("elementB", eltB);
		
		return this.db.insert(TABLE_ADRAL_NEXT, null, values);
	}


	public long insertMainGrid(ArrayList<ArrayList<Integer>> tabGrid){
		values.clear();
		int s1 = tabGrid.size();
		int s2 = 0;
		long rVal = 0;
		ArrayList<Integer> tmp = null;
		for (int i = 0; i < s1; i++) {
			
			tmp = tabGrid.get(i);
			s2 = tmp.size();
			for (int j = 0; j < s2; j++) {
				
				if(tmp.get(j) != 0){
					//System.out.println(i+","+j+" : "+tmp.get(j));
					values.put("element", tmp.get(j));
					values.put("posX", i);
					values.put("posY", j);
					rVal = this.db.insert(TABLE_ADRAL_MAIN_GRID, null, values);
				}
			}
		}
		
		return rVal;
	}
	
	private long insertScore(int score){
		values.clear();
		values.put("score", score);
		
		return this.db.insert(TABLE_ADRAL_SCORE, null, values);
	}
	
	private long insertHighScore(String pseudo, int highScore){
		values.clear();
		String currentDateTimeString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//System.out.println("Date : "+currentDateTimeString);
		values.put("score", highScore);
		values.put("pseudo", pseudo);
		values.put("date", currentDateTimeString);
		
		return this.db.insert(TABLE_ADRAL_LOCAL_HIGHSCORE, null, values);
	}

	private long insertLastPseudo(String lastPseudo){
		values.clear();
		values.put("lastPseudo", lastPseudo);
		
		return this.db.insert(TABLE_ADRAL_LAST_PSEUDO, null, values);
	}

	public void deleteAll() {
		this.db.delete(TABLE_ADRAL_LAST_PSEUDO, null, null);
		this.db.delete(TABLE_ADRAL_LOCAL_HIGHSCORE, null, null);
		this.db.delete(TABLE_ADRAL_MAIN_GRID, null, null);
		this.db.delete(TABLE_ADRAL_NEXT, null, null);
		this.db.delete(TABLE_ADRAL_NEXT_NEXT, null, null);
		this.db.delete(TABLE_ADRAL_SCORE, null, null);
		
		//System.out.println("Database ERASED");
	}
	
	
	
	public Paire selectNextNextElement() {
		Paire elt = null ;
		Cursor cursor = db.rawQuery("SELECT elementA, elementB FROM "+TABLE_ADRAL_NEXT_NEXT, null);
		
		if (cursor.moveToFirst()) {
			do {
				elt = new Paire(cursor.getInt(0),cursor.getInt(1));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return elt;
	}
	
	public Paire selectNextElement() {
		Paire elt = null ;
		Cursor cursor = db.rawQuery("SELECT elementA, elementB FROM "+TABLE_ADRAL_NEXT, null);
		
		if (cursor.moveToFirst()) {
			do {
				elt = new Paire(cursor.getInt(0),cursor.getInt(1));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return elt;
	}
	
	public int selectScore() {
		int score = 0;
		Cursor cursor = db.rawQuery("SELECT score FROM "+TABLE_ADRAL_SCORE, null);
		
		if (cursor.moveToFirst()) {
			do {
				score = cursor.getInt(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return score;
	}
	
	public ArrayList<Triplet<Integer,Integer,Integer>> selectMainGrid(){
		ArrayList<Triplet<Integer,Integer,Integer>> tabGrid = new ArrayList<Triplet<Integer,Integer,Integer>>() ;
		
		Cursor cursor = db.rawQuery("SELECT element, posX, posY FROM "+TABLE_ADRAL_MAIN_GRID, null);
		
		if (cursor.moveToFirst()) {
			do {
				tabGrid.add(new Triplet<Integer,Integer,Integer>(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2))) ;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		return tabGrid;
	}
	
	public String selectLastPseudo() {
		String s = "";
		Cursor cursor = db.rawQuery("SELECT lastPseudo FROM "+TABLE_ADRAL_LAST_PSEUDO, null);
		
		if (cursor.moveToFirst()) {
			do {
				s = cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return s;
	}
	
	public ArrayList<Triplet<Integer,String,String>> selectHighscores() {
		ArrayList<Triplet<Integer,String,String>> listScores = new ArrayList<Triplet<Integer,String,String>>();
		Cursor cursor = db.rawQuery("SELECT score, pseudo, date FROM "+TABLE_ADRAL_LOCAL_HIGHSCORE+" ORDER BY score DESC", null);
		Triplet<Integer,String,String> tmp;
		if (cursor.moveToFirst()) {
			do {
				tmp = new Triplet<Integer,String,String>(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
				listScores.add(tmp);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return listScores;
	}
	
	public void addHighScore(String pseudo, int score){
		insertHighScore(pseudo, score);
	}
	

	public int getNbPlayers(){
		
		return (int) this.countStmt.simpleQueryForLong();
	}

	public void close(){

		//System.out.println("CLOSE");
		
		db.close();
	}

	public int updateGameSave(Paire nextNext, Paire next, ArrayList<ArrayList<Integer>> tabGrid, int score, String lastPseudo){
		                      
		/*updateNextNextElement(nextNext.getA(),nextNext.getB());
		updateNextNextElement(next.getA(),next.getB());		
		updateNextNextElement(nextNext.getA(),nextNext.getB());
		updateNextNextElement(nextNext.getA(),nextNext.getB());*/
		// TODO updater plutot que supprimer et recreer
		
		deleteSavedGame();
		
		insertNextNextelement(nextNext.getA(), nextNext.getB());
		insertNextelement(next.getA(), next.getB());
		insertMainGrid(tabGrid);
		insertScore(score);
		insertLastPseudo(lastPseudo);
		
		return 1;
	}
	
	public void deleteSavedGame() {
		db.execSQL("DELETE FROM "+TABLE_ADRAL_LAST_PSEUDO);
		db.execSQL("DELETE FROM "+TABLE_ADRAL_MAIN_GRID);
		db.execSQL("DELETE FROM "+TABLE_ADRAL_NEXT);
		db.execSQL("DELETE FROM "+TABLE_ADRAL_NEXT_NEXT);
		db.execSQL("DELETE FROM "+TABLE_ADRAL_SCORE);
	}
	
	
	private int updateNextNextElement(int a, int b) {
		values.clear();
		String[] whereArgs = {""+0};
		int nbRecordDone = db.update(TABLE_ADRAL_NEXT_NEXT, values, "id = ? ", whereArgs );
		
		return nbRecordDone;
	}



	private static class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			//System.out.println("Creation");
			
			db.execSQL("CREATE TABLE " + TABLE_ADRAL_NEXT_NEXT + 
					"(id INTEGER PRIMARY KEY, elementA INTEGER, elementB INTEGER )");
			db.execSQL("CREATE TABLE " + TABLE_ADRAL_NEXT + 
					"(id INTEGER PRIMARY KEY, elementA INTEGER, elementB INTEGER )");
			db.execSQL("CREATE TABLE " + TABLE_ADRAL_MAIN_GRID + 
					"(id INTEGER PRIMARY KEY, element INTEGER, posX INTEGER, posY INTEGER )");
			db.execSQL("CREATE TABLE " + TABLE_ADRAL_SCORE + 
					"(id INTEGER PRIMARY KEY, score INTEGER )");
			db.execSQL("CREATE TABLE " + TABLE_ADRAL_LAST_PSEUDO + 
					"(id INTEGER PRIMARY KEY, lastPseudo TEXT )");
			db.execSQL("CREATE TABLE " + TABLE_ADRAL_LOCAL_HIGHSCORE + 
					"(id INTEGER PRIMARY KEY, score INTEGER, pseudo TEXT, date DATE  )");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("Database", "Upgrading database, this will drop all tables and recreate.");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADRAL_LAST_PSEUDO);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADRAL_LOCAL_HIGHSCORE);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADRAL_MAIN_GRID);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADRAL_NEXT);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADRAL_NEXT_NEXT);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADRAL_SCORE);
			onCreate(db);
		}
	}



	
}
