package com.example.mylastnight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class sqldb1 extends SQLiteOpenHelper {
	
	public static final String LOGTAG = "Explore";
	
	public static final String DATABASE_NAME = "Drinks1.db";
	public static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_DRINK = "drinks";
	public static final String COLUMN_ID = "tourid";
	public static final String COLUMN_NAME = "drinkname";
	public static final String COLUMN_ALCVAL = "alcval";
	
	public static final String TABLE_CREATE = 
				"CREATE_TABLE " + TABLE_DRINK + " ( "+
				COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
				COLUMN_NAME + " TEXT, "+
				COLUMN_ALCVAL + "  NUMERIC "+
				" ) ";

	public sqldb1(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(TABLE_CREATE);
		Log.i(LOGTAG, "Database has been created");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINK);
		onCreate(db);
	}

}
