package com.example.administrator.caringforyouandme.activity.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import com.example.administrator.caringforyouandme.R;
import com.example.administrator.caringforyouandme.database.domain.Alarm;
import com.example.administrator.caringforyouandme.database.query.AlarmQuery;

public class AlarmSetActivity extends AppCompatActivity implements TimePicker.OnTimeChangedListener{

    private Toolbar toolbar;
    private int position;

    private TimePicker timePicker;
    private TextView content;
    private String setTime = "00:00";

    AlarmQuery alarmQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);

        alarmQuery = new AlarmQuery(this);

        timePicker = findViewById(R.id.timepicker);
        timePicker.setOnTimeChangedListener(this);

        content = findViewById(R.id.textview_alarm_set_content);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        if ( position > 0) {
            Alarm alarm = alarmQuery.get(position);
        }

        setToolbar();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("알람설정");
        setSupportActionBar(toolbar);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        content.setText("time is  " + hourOfDay + ":" + minute );

        setTime = hourOfDay + ":" + minute;
    }

    public void onSave(View view) {

        Alarm alarm = new Alarm();
        alarm.setTime(setTime);
        alarm.setContent(content.getText().toString());
        alarm.setIsSun("true");
        alarm.setIsMon("true");
        alarm.setIsTue("true");
        alarm.setIsWed("true");
        alarm.setIsThu("true");
        alarm.setIsFri("true");
        alarm.setIsSat("true");

        long id = alarmQuery.set(alarm);
        Log.d("AlarmSetActivity"," set id value = " + id);

        finish();
    }

    public void onCancel(View view){
        finish();
    }
}
