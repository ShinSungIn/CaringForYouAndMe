package com.example.administrator.caringforyouandme.activity.alarm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.administrator.caringforyouandme.R;
import com.example.administrator.caringforyouandme.database.domain.Alarm;
import com.example.administrator.caringforyouandme.database.query.AlarmQuery;

public class AlarmSetActivity extends AppCompatActivity implements TimePicker.OnTimeChangedListener{

    private Toolbar toolbar;
    private int position;

    private TimePicker timePicker;
    private EditText content;
    private String setTime = "00:00";

    private TextView textViewSun;
    private TextView textViewMon;
    private TextView textViewTue;
    private TextView textViewWed;
    private TextView textViewThu;
    private TextView textViewFri;
    private TextView textViewSat;

    private String isSun = "false";
    private String isMon = "false";
    private String isTue = "false";
    private String isWed = "false";
    private String isThu = "false";
    private String isFri = "false";
    private String isSat = "false";


    private AlarmQuery alarmQuery;

    private Alarm alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);

        alarmQuery = new AlarmQuery(this);

        timePicker = findViewById(R.id.timepicker);
        timePicker.setOnTimeChangedListener(this);

        textViewSun = findViewById(R.id.textview_select_sun);
        textViewMon = findViewById(R.id.textview_select_mon);
        textViewTue = findViewById(R.id.textview_select_tue);
        textViewWed = findViewById(R.id.textview_select_wed);
        textViewThu = findViewById(R.id.textview_select_thu);
        textViewFri = findViewById(R.id.textview_select_fri);
        textViewSat = findViewById(R.id.textview_select_sat);

        content = findViewById(R.id.edittext_alarm_set_content);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        _setAlarmSet(position);

        _setToolbar();
    }

    private void _setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("알람설정");
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        String zeroHour = "";
        String zeroMinute = "";
        if(hourOfDay < 10 ) {
            zeroHour = "0";
        }
        if (minute < 10) {
            zeroMinute = "0";
        }

        setTime = zeroHour + hourOfDay + ":" + zeroMinute + minute;
    }

    public void onSave(View view) {

        if (isSun.equals("false") && isMon.equals("false")  && isTue.equals("false")  && isWed.equals("false")  && isThu.equals("false")  && isFri.equals("false")  && isSat.equals("false") ) {
            Toast.makeText(this, "반복 요일을 선택 하세요.", Toast.LENGTH_SHORT).show();
            return;
        } else if (content.getText().toString().trim().equals("")){
            Toast.makeText(this, "내용을 입력 하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if( alarm == null) {
            alarm = new Alarm();
        }

        alarm.setTime(setTime);
        alarm.setContent(content.getText().toString());
        alarm.setIsSun(isSun);
        alarm.setIsMon(isMon);
        alarm.setIsTue(isTue);
        alarm.setIsWed(isWed);
        alarm.setIsThu(isThu);
        alarm.setIsFri(isFri);
        alarm.setIsSat(isSat);

        if (alarm.getSeq() == null || alarm.getSeq() == 0 ) {
            alarmQuery.set(alarm);
        } else {
            alarmQuery.modify(alarm);
        }

        finish();
    }

    public void onCancel(View view){
        finish();
    }

    public void onSunday(View view) {
        if (isSun.equals("false")){
            isSun = "true";
        } else {
            isSun = "false";
        }
        _setTextview(textViewSun, isSun);
    }

    public void onMonday(View view) {
        if (isMon.equals("false")){
            isMon = "true";
        } else {
            isMon = "false";
        }
        _setTextview(textViewMon, isMon);
    }

    public void onTuesday(View view) {
        if (isTue.equals("false")){
            isTue = "true";
        } else {
            isTue = "false";
        }
        _setTextview(textViewTue, isTue);
    }

    public void onWednesday(View view) {
        if (isWed.equals("false")){
            isWed = "true";
        } else {
            isWed = "false";
        }
        _setTextview(textViewWed, isWed);
    }

    public void onThursday(View view) {
        if (isThu.equals("false")){
            isThu = "true";
        } else {
            isThu = "false";
        }
        _setTextview(textViewThu, isThu);
    }

    public void onFridy(View view) {
        if (isFri.equals("false")){
            isFri = "true";
        } else {
            isFri = "false";
        }
        _setTextview(textViewFri, isFri);
    }

    public void onSaturday(View view) {
        if (isSat.equals("false")){
            isSat = "true";
        } else {
            isSat = "false";
        }
        _setTextview(textViewSat, isSat);
    }

    private void _setAlarmSet(int position){
        if (position > 0) {
            alarm = alarmQuery.get(position);

            if(alarm == null) {
                Log.e("AlarmSetActivity", "null object reference (seq = " + position + ")");
                return;
            }

            String time[] = alarm.getTime().split(":");
            timePicker.setHour(Integer.parseInt(time[0]));
            timePicker.setMinute(Integer.parseInt(time[1]));
            content.setText(alarm.getContent());

            isSun = alarm.getIsSun();
            isMon = alarm.getIsMon();
            isTue = alarm.getIsTue();
            isWed = alarm.getIsWed();
            isThu = alarm.getIsThu();
            isFri = alarm.getIsFri();
            isSat = alarm.getIsSat();

            _setTextview(textViewSun,isSun);
            _setTextview(textViewMon,isMon);
            _setTextview(textViewTue,isTue);
            _setTextview(textViewWed,isWed);
            _setTextview(textViewThu,isThu);
            _setTextview(textViewFri,isFri);
            _setTextview(textViewSat,isSat);

        } else {
            timePicker.setHour(0);
            timePicker.setMinute(0);
            content.setText("");
        }
    }

    @SuppressLint("ResourceAsColor")
    private void _setTextview(TextView textView, String booleanValue){
        if(booleanValue.equals("true")) {
            textView.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
//            textView.setTypeface(null, Typeface.BOLD);
        } else {
            if(textView.getText().toString().trim().equals("일")) {
                textView.setTextColor(getResources().getColor(R.color.colorText_Red, null));
            } else  {
                textView.setTextColor(getResources().getColor(R.color.colorText_Black, null));
            }
//            textView.setTypeface(null, Typeface.NORMAL);
        }
    }

}
