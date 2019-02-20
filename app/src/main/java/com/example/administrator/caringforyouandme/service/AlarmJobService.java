package com.example.administrator.caringforyouandme.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import com.example.administrator.caringforyouandme.R;
import com.example.administrator.caringforyouandme.activity.DialogActivity;
import com.example.administrator.caringforyouandme.database.domain.Alarm;
import com.example.administrator.caringforyouandme.database.query.AlarmQuery;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmJobService extends JobIntentService {

    private String TAG = "AlarmJobService";

    private AlarmQuery alarmQuery;

    static final int JOB_ID = 1000;

    static public void enqueueWork(Context context, Intent intent) {
//        enqueueWork(context, ?.class, JOB_ID, intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        alarmQuery = new AlarmQuery(this);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        alarmJobThread.start();
    }

//    @Override
//    public boolean onStopCurrentWork() {
//        return false;
//    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        alarmJobThread.close();
    }


    /**
     * 알림 쓰레드
     */
    private AlarmJobThread alarmJobThread = new AlarmJobThread();

    @SuppressWarnings("Duplicates")
    class AlarmJobThread extends Thread{
        private boolean runnable = true;
        @Override
        public void run(){
            while (runnable){

                Date today = new Date();
                if( !_isZeroSecond(today)){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                List<Alarm> alarmList = alarmQuery.gets(_getWeek(today), _getFormat(today,"HH:mm"));

                for(Alarm alarm : alarmList) {
                    _action(alarm.getContent());
                    Log.i(TAG, " alarm time = " + alarm.getTime());
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        public void close(){
            runnable = false;
        }

    }

    /**
     * 알림 동작
     */
    @SuppressWarnings("Duplicates")
    private void _action(String content){

        // 팝업 띄우기
        Intent intent = new Intent(getApplicationContext(), DialogActivity.class);
        intent.putExtra("content", content);
        startActivity(intent);

        // 알람 소리
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alert );
        mediaPlayer.start();
    }

    /**
     * 문자열 변환
     */
    private String _getFormat(Date date, String pattern){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 요일 구하기
     */
    private int _getWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 정각 분 구하기
     */
    private boolean _isZeroSecond(Date date){
        String zeroSecond = _getFormat(date, "ss");
        if (zeroSecond.equals("00")) {
            return true;
        }
        return false;
    }


}
