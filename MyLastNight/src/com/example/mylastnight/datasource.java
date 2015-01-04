package com.example.mylastnight;

//Class handling the database. Creates new drink object, adds attributes and adds to database.

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class datasource {
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase data1;
	private static final String LOGTAG = "Explore";
	private static final String[] allcollumns = {
		sqldb1.COLUMN_ID,
		sqldb1.COLUMN_NAME,
		sqldb1.COLUMN_ALCVAL
	};
	
	public datasource(Context context){
		dbhelper = new sqldb1(context);
	}
	
	public void open() throws SQLException{
		Log.i(LOGTAG, "Database opened");
		data1 = dbhelper.getWritableDatabase();
	}
	
	public void close(){
		Log.i(LOGTAG, "Database closed");
		dbhelper.close();
	}
	
	public drinkval create(drinkval d){
		ContentValues vals = new ContentValues();
		vals.put(sqldb1.COLUMN_NAME, d.getname());
		vals.put(sqldb1.COLUMN_ALCVAL, d.getalval());
		
		long insertid = data1.insert(sqldb1.TABLE_DRINK, null, vals);
		d.setid(insertid);
		return d;
	}
	
	public List<drinkval> findall(){
		List<drinkval> drinks = new ArrayList<drinkval>();
		
		Cursor cursor = data1.query(sqldb1.TABLE_DRINK, allcollumns, null, null, null, null, null);
		Log.i(LOGTAG, "Number of rows returned: "+ cursor.getCount());
		
		if(cursor.getCount()>0){
			while(cursor.moveToNext()){
				drinkval drink = new drinkval();
				drink.setid(cursor.getLong(cursor.getColumnIndex(sqldb1.COLUMN_ID)));
				drink.setname(cursor.getString(cursor.getColumnIndex(sqldb1.COLUMN_NAME)));
				drink.setalval(cursor.getDouble(cursor.getColumnIndex(sqldb1.COLUMN_ALCVAL)));
				drinks.add(drink);
			}
		}
		cursor.close();
		return drinks;
	}
}
