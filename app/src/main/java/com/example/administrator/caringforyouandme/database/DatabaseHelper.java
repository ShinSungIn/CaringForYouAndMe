package com.example.administrator.caringforyouandme.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 곽우영
 * @since 2019-02-09
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private String TAG = "DatabaseHelper";

	private static DatabaseHelper databaseHelper;

	private SQLiteDatabase sqLiteDatabase;

	private DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, databaseName, factory, version);
		_initWritableDatabase();
	}

	public static synchronized DatabaseHelper getInstance(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int version){
		if(databaseHelper == null) {
			databaseHelper = new DatabaseHelper(context, databaseName, factory, version);
		}
		return databaseHelper;
	}

	public static synchronized DatabaseHelper getInstance(Context context, SQLiteDatabase.CursorFactory factory, int version){
		if(databaseHelper == null) {
			String databaseName = "caring.db";
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

	private void _initWritableDatabase() {
		if (sqLiteDatabase == null) {
			sqLiteDatabase = getWritableDatabase();
		}
	}

	public SQLiteDatabase getSqLiteDatabase() {
		return this.sqLiteDatabase;
	}

	/**
	 * 데이터베이스 연결 종료 (닫기)
	 */
	public void close() {
		if (this.databaseHelper != null) {
			sqLiteDatabase.close();
			this.databaseHelper.close();
		}
	}

	/**
	 * Alarm 테이블
 	 */
	private String _onCreateAlarm(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("create table " + Entity.alarm);
		stringBuffer.append(" (");
		stringBuffer.append(Column.ALARM_SEQ + " " + Type.INTEGER +  " PRIMARY KEY AUTOINCREMENT,");
		stringBuffer.append(Column.ALARM_TIME + " " + Type.TEXT + ",");
		stringBuffer.append(Column.ALARM_ISUSE + " " + Type.TEXT + ",");
		stringBuffer.append(Column.ALARM_ISSUN + " " + Type.TEXT + ",");
		stringBuffer.append(Column.ALARM_ISMON + " " + Type.TEXT + ",");
		stringBuffer.append(Column.ALARM_ISTUE + " " + Type.TEXT + ",");
		stringBuffer.append(Column.ALARM_ISWED + " " + Type.TEXT + ",");
		stringBuffer.append(Column.ALARM_ISTHU + " " + Type.TEXT + ",");
		stringBuffer.append(Column.ALARM_ISFRI + " " + Type.TEXT + ",");
		stringBuffer.append(Column.ALARM_ISSAT + " " + Type.TEXT + ",");
		stringBuffer.append(Column.ALARM_CONTENT + " " + Type.TEXT);
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
		stringBuffer.append(Column.ALARM_SEQ + " " + Type.INTEGER +  " PRIMARY KEY AUTOINCREMENT,");
		stringBuffer.append(Column.DIARY_SUBJECT + " " + Type.TEXT +  ",");
		stringBuffer.append(Column.DIARY_CONTENT + " " + Type.TEXT + ",");
		stringBuffer.append(Column.DIARY_POSTDT + " " + Type.TEXT + ",");
		stringBuffer.append(Column.DIARY_REGDT + " " + Type.TEXT);
		stringBuffer.append(" )" );
		return stringBuffer.toString();
	}

	/**
	 * 조회
	 */
	public Cursor onSelect(String tableName) {
		return onSelect(tableName, null, null, null, null, null, null, null);
	}

	/**
	 * 조회
	 */
	public Cursor onSelect(String tableName, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
		return sqLiteDatabase.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
	}

	/**
	 * 등록
	 */
	public long onInsert(String tableName, ContentValues contentValues){
		sqLiteDatabase.beginTransaction();
		long id = sqLiteDatabase.insert(tableName, null, contentValues);
		sqLiteDatabase.setTransactionSuccessful();
		sqLiteDatabase.endTransaction();
		return id;
	}

	/**
	 * 수정
	 */
	public int onUpdate(String tableName, ContentValues contentValues, String whereClause, String[] whereArgs) {
		if (sqLiteDatabase != null) {
			return sqLiteDatabase.update(tableName, contentValues, whereClause, whereArgs);
		}
		return 0;
	}

}
