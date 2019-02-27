package com.edenranch.administrator.caringforyouandme.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.edenranch.administrator.caringforyouandme.R;
import com.edenranch.administrator.caringforyouandme.activity.DialogActivity;
import com.edenranch.administrator.caringforyouandme.database.domain.Alarm;
import com.edenranch.administrator.caringforyouandme.database.query.AlarmQuery;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmService extends Service {

    private String TAG = "AlarmService";

    private AlarmQuery alarmQuery;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        alarmQuery = new AlarmQuery(this);
        Log.i(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        alarmThread.start();
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        alarmThread.close();
    }

    /**
     * 알림 쓰레드
     */
    private AlarmThread alarmThread = new AlarmThread();

    @SuppressWarnings("Duplicates")
    class AlarmThread extends Thread{
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
    private void _action(String content){

        // 팝업 띄우기
        Intent intent = new Intent(getApplicationContext(), DialogActivity.class);
        intent.putExtra("content", content);
        startActivity(intent);

        // 알람 소리
        MediaPlayer mediaPlayer = MediaPlayer.create(AlarmService.this, R.raw.alert );
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
