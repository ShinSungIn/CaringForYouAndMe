package com.example.administrator.caringforyouandme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.administrator.caringforyouandme.activity.alarm.AlarmSetActivity;
import com.example.administrator.caringforyouandme.listview.alarm.AlarmListviewAdapter;

public class AlarmActivity extends AppCompatActivity {

	private Toolbar toolbar;

	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
        context = this;
		setToolbar();
        _setListView();
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

    private void _setListView(){
        AlarmListviewAdapter alarmListviewAdapter = new AlarmListviewAdapter(this);
        ListView listView_Alarm = findViewById(R.id.listview_alarm);
        listView_Alarm.setAdapter(alarmListviewAdapter);
        listView_Alarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, AlarmSetActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

}
