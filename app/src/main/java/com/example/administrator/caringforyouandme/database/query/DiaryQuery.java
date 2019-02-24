package com.example.administrator.caringforyouandme.database.query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.example.administrator.caringforyouandme.database.Column;
import com.example.administrator.caringforyouandme.database.DatabaseHelper;
import com.example.administrator.caringforyouandme.database.Entity;
import com.example.administrator.caringforyouandme.database.domain.Diary;
import com.google.common.collect.Lists;

import java.util.List;

public class DiaryQuery {

    private String TAG = "DiaryQuery";

    private Context context;

    private DatabaseHelper databaseHelper;

    public DiaryQuery(Context context){
        this.context = context;
        _onCreate();
    }

    private void _onCreate() {
        databaseHelper = DatabaseHelper.getInstance(context, null, 1);
    }

    /**
     * 일기 등록
     */
    @SuppressWarnings("Duplicates")
    public long set(Diary diary) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column.DIARY_SUBJECT, diary.getSubject());
        contentValues.put(Column.DIARY_CONTENT, diary.getContent());
        contentValues.put(Column.DIARY_REGDT, diary.getRegDt());

        return databaseHelper.onInsert(Entity.diary, contentValues);
    }

    /**
     * 전체 조회
     */
    public List<Diary> gets() {
        Cursor cursor = databaseHelper.onSelect(Entity.diary);
        return _get(cursor);
    }

    /**
     * 조건 조회
     */
    @SuppressWarnings("Duplicates")
    public Diary get(int seq) {
        String whereClause = Column.DIARY_SEQ + " == ? ";
        String[] whereArgs = new String[]{Integer.toString(seq)};
        Cursor cursor = databaseHelper.onSelect(Entity.diary, null, whereClause, whereArgs, null , null, null, null);

        Diary diary = null;
        while (cursor.moveToNext()){
            diary = new Diary();
            diary.setSeq(cursor.getInt(cursor.getColumnIndex(Column.DIARY_SEQ)));
            diary.setSubject(cursor.getString(cursor.getColumnIndex(Column.DIARY_SUBJECT)));
            diary.setContent(cursor.getString(cursor.getColumnIndex(Column.DIARY_CONTENT)));
            diary.setRegDt(cursor.getString(cursor.getColumnIndex(Column.DIARY_REGDT)));
        }

        return diary;
    }

    /**
     * 조건 조회
     */
    public List<Diary> get(String regDt) {
        String whereClause = Column.DIARY_REGDT = " == ? ";
        String[] whereArgs = new String[]{regDt};
        Cursor cursor = databaseHelper.onSelect(Entity.diary, null, whereClause, whereArgs, null , null, null, "1");
        return _get(cursor);
    }

    /**
     * 조회 정보 추출
     */
    @SuppressWarnings("Duplicates")
    private List<Diary> _get(Cursor cursor){
        List<Diary> lists = Lists.newArrayList();
        while (cursor.moveToNext()) {
            Diary diary = new Diary();
            diary.setSeq(cursor.getInt(cursor.getColumnIndex(Column.DIARY_SEQ)));
            diary.setSubject(cursor.getString(cursor.getColumnIndex(Column.DIARY_SUBJECT)));
            diary.setContent(cursor.getString(cursor.getColumnIndex(Column.DIARY_CONTENT)));
            diary.setRegDt(cursor.getString(cursor.getColumnIndex(Column.DIARY_REGDT)));

            lists.add(diary);
        }
        return lists;
    }

    /**
     * 수정
     */
    @SuppressWarnings("Duplicates")
    public int modify(Diary diary) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column.DIARY_SUBJECT, diary.getSubject());
        contentValues.put(Column.DIARY_CONTENT, diary.getContent());
        contentValues.put(Column.DIARY_REGDT, diary.getRegDt());

        String whereClause = Column.DIARY_SEQ + " == ?";
        String[] whereArgs = new String[]{diary.getSeq().toString()};
        return databaseHelper.onUpdate(Entity.diary, contentValues, whereClause, whereArgs);
    }

    /**
     * 알람 삭제
     */
    public void remove(String tableName, String whereClause, String[] whereArgs) {
        databaseHelper.onDelete(tableName, whereClause, whereArgs);
    }
}
