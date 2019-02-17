package com.example.administrator.caringforyouandme.database.query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.example.administrator.caringforyouandme.database.Column;
import com.example.administrator.caringforyouandme.database.DatabaseHelper;
import com.example.administrator.caringforyouandme.database.Entity;
import com.example.administrator.caringforyouandme.database.domain.Alarm;
import com.example.administrator.caringforyouandme.database.domain.Diary;
import com.google.common.collect.Lists;

import java.util.List;

public class AlarmQuery {

    private String TAG = "AlarmQuery";

    private Context context;

    private DatabaseHelper databaseHelper;

    public AlarmQuery(Context context) {
        this.context = context;
        _onCreate();
    }

    private void _onCreate() {
        databaseHelper = DatabaseHelper.getInstance(context, null, 1);
    }

    /**
     * 알람 등록
     */
    @SuppressWarnings("Duplicates")
    public long set(Alarm alarm) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column.ALARM_TIME, alarm.getTime());
        contentValues.put(Column.ALARM_CONTENT, alarm.getContent());
        contentValues.put(Column.ALARM_ISSUN, alarm.getIsSun());
        contentValues.put(Column.ALARM_ISMON, alarm.getIsMon());
        contentValues.put(Column.ALARM_ISTUE, alarm.getIsTue());
        contentValues.put(Column.ALARM_ISWED, alarm.getIsWed());
        contentValues.put(Column.ALARM_ISTHU, alarm.getIsThu());
        contentValues.put(Column.ALARM_ISFRI, alarm.getIsFri());
        contentValues.put(Column.ALARM_ISSAT, alarm.getIsSat());
        if(alarm == null) {
            return -1;
        }
        return databaseHelper.onInsert(Entity.alarm, contentValues);
    }

    /**
     * 조회
     */
    @SuppressWarnings("Duplicates")
    public Alarm get(int seq){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Column.ALARM_SEQ+ " = ? " );
        Cursor cursor = databaseHelper.onSelect(Entity.alarm, null, stringBuffer.toString(), new String[]{Integer.toString(seq)}, null, null, null, null );

        Alarm alarm = null;
        if ( cursor.moveToFirst() ){
            alarm = new Alarm();

            alarm.setSeq(cursor.getInt(cursor.getColumnIndex(Column.ALARM_SEQ)));
            alarm.setTime(cursor.getString(cursor.getColumnIndex(Column.ALARM_TIME)));
            alarm.setIsUse(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISUSE)));
            alarm.setContent(cursor.getString(cursor.getColumnIndex(Column.ALARM_CONTENT)));

            alarm.setIsSun(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISSUN)));
            alarm.setIsMon(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISMON)));
            alarm.setIsTue(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISTUE)));
            alarm.setIsWed(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISWED)));
            alarm.setIsThu(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISTHU)));
            alarm.setIsFri(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISFRI)));
            alarm.setIsSat(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISSAT)));
        }

        return alarm;
    }

    /**
     * 조회
     */
    public List<Alarm> gets() {
        Cursor cursor = databaseHelper.onSelect(Entity.alarm);
        return _get(cursor);
    }

    /**
     * 조건 조회
     */
    public List<Alarm> gets(int whatWeek, String time) {

        StringBuffer stringBuffer = new StringBuffer();
        switch (whatWeek){
            case 1:         // sun
                stringBuffer.append(Column.ALARM_ISSUN + " = ? and " );
                break;
            case 2:         // mon
                stringBuffer.append(Column.ALARM_ISMON + " = ? and " );
                break;
            case 3:         // tue
                stringBuffer.append(Column.ALARM_ISTUE + " = ? and " );
                break;
            case 4:         // wed
                stringBuffer.append(Column.ALARM_ISWED + " = ? and " );
                break;
            case 5:         // thu
                stringBuffer.append(Column.ALARM_ISTHU + " = ? and " );
                break;
            case 6:         // fri
                stringBuffer.append(Column.ALARM_ISFRI + " = ? and " );
                break;
            case 7:         // sat
                stringBuffer.append(Column.ALARM_ISSAT + " = ? and " );
                break;
        }

//        stringBuffer.append( Column.ALARM_ISUSE + " = ? and "  );
        stringBuffer.append( Column.ALARM_TIME + " = ? " );

//        Cursor cursor = databaseHelper.onSelect(Entity.alarm, null, stringBuffer.toString(), new String[]{"true", "true", time}, null, null, null, "1" );
        Cursor cursor = databaseHelper.onSelect(Entity.alarm, null, stringBuffer.toString(), new String[]{"true", time}, null, null, null, "1" );
        return _get(cursor);
    }

    /**
     * 조회 정보 추출
     */
    @SuppressWarnings("Duplicates")
    private List<Alarm> _get(Cursor cursor){
        List<Alarm> lists = Lists.newArrayList();

        while (cursor.moveToNext()) {
            Alarm alarm = new Alarm();
            alarm.setSeq(cursor.getInt(cursor.getColumnIndex(Column.ALARM_SEQ)));
            alarm.setTime(cursor.getString(cursor.getColumnIndex(Column.ALARM_TIME)));
            alarm.setIsUse(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISUSE)));
            alarm.setContent(cursor.getString(cursor.getColumnIndex(Column.ALARM_CONTENT)));

            alarm.setIsSun(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISSUN)));
            alarm.setIsMon(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISMON)));
            alarm.setIsTue(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISTUE)));
            alarm.setIsWed(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISWED)));
            alarm.setIsThu(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISTHU)));
            alarm.setIsFri(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISFRI)));
            alarm.setIsSat(cursor.getString(cursor.getColumnIndex(Column.ALARM_ISSAT)));
            lists.add(alarm);
        }
        return lists;
    }

    /**
     * 알람 수정
     */
    @SuppressWarnings("Duplicates")
    public int modify(Alarm alarm) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column.ALARM_TIME, alarm.getTime());
        contentValues.put(Column.ALARM_CONTENT, alarm.getContent());
        contentValues.put(Column.ALARM_ISSUN, alarm.getIsSun());
        contentValues.put(Column.ALARM_ISMON, alarm.getIsMon());
        contentValues.put(Column.ALARM_ISTUE, alarm.getIsTue());
        contentValues.put(Column.ALARM_ISWED, alarm.getIsWed());
        contentValues.put(Column.ALARM_ISTHU, alarm.getIsThu());
        contentValues.put(Column.ALARM_ISFRI, alarm.getIsFri());
        contentValues.put(Column.ALARM_ISSAT, alarm.getIsSat());

        String whereClause = Column.ALARM_SEQ + " == ? ";
        String[] whereArgs = new String[]{alarm.getSeq().toString()};
        return databaseHelper.onUpdate(Entity.alarm, contentValues, whereClause, whereArgs);
    }

    public void remove(String tableName, String whereClause, String[] whereArgs) {
        databaseHelper.onDelete(tableName, whereClause, whereArgs);
    }
}
