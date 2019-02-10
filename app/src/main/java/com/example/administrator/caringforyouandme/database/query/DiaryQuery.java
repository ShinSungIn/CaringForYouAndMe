package com.example.administrator.caringforyouandme.database.query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    }

    public void onCreate() {
        databaseHelper = DatabaseHelper.getInstance(context, null, 1);
    }

    /**
     * 일기 등록
     */
    public long set(Diary diary) {
        ContentValues contentValues = new ContentValues();
        return databaseHelper.onInsert(Entity.diary, contentValues);
    }

    /**
     * 일기 조회
     */
    public List<Diary> gets() {
        Cursor cursor = databaseHelper.onSelect(Entity.diary);

        return _get(cursor);
    }

    /**
     * 일기 조건 조회
     */
    public List<Diary> get(String regDt) {
        Cursor cursor = databaseHelper.onSelect(Entity.diary);
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

            lists.add(diary);
        }
        return lists;
    }

    /**
     * 수정
     */
    private int modify(Diary diary) {
        ContentValues contentValues = new ContentValues();
        return databaseHelper.onUpdate(Entity.diary, null, null, null);
    }
}
