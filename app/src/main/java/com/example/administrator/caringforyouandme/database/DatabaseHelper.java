package com.example.administrator.caringforyouandme.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 곽우영
 * @since 2019-02-09
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private String TAG = "DatabaseHelper";

	private static DatabaseHelper databaseHelper;

	private DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, databaseName, factory, version);
	}

	public static synchronized DatabaseHelper getInstance(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int version){
		if(databaseHelper == null) {
			databaseHelper = new DatabaseHelper(context, databaseName, factory, version);
		}
		return databaseHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(_onCreateAlarm());
		db.execSQL(_onCreateDiary());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * Alarm 테이블
 	 */
	private String _onCreateAlarm(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("create table " + Entity.alarm);
		stringBuffer.append(" (");
		stringBuffer.append(",");
		stringBuffer.append(",");
		stringBuffer.append(",");
		stringBuffer.append(",");
		stringBuffer.append(" )" );
		return stringBuffer.toString();
	}

	/**
	 * Caring Diary 테이블
	 */
	private String _onCreateDiary(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("create table " + Entity.diary);
		stringBuffer.append(" (");
		stringBuffer.append(",");
		stringBuffer.append(",");
		stringBuffer.append(",");
		stringBuffer.append(",");
		stringBuffer.append(" )" );
		return stringBuffer.toString();
	}
}
