package com.example.administrator.caringforyouandme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.example.administrator.caringforyouandme.activity.alarm.AlarmSetActivity;
import com.example.administrator.caringforyouandme.database.domain.Alarm;
import com.example.administrator.caringforyouandme.database.query.AlarmQuery;
import com.example.administrator.caringforyouandme.listview.alarm.AlarmListviewAdapter;

import java.util.List;

public class AlarmActivity extends AppCompatActivity {

	private Toolbar toolbar;

	private Context context;

	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
        context = this;

        button = findViewById(R.id.button_alarm_create);
        button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(context, AlarmSetActivity.class));
			}
		});

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
		_setListView();
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
                intent.putExtra("position", position + 1);
                startActivity(intent);
            }
        });

		AlarmQuery alarmQuery = new AlarmQuery(this);
		List<Alarm> alarmList = alarmQuery.gets();

		for (Alarm alarm : alarmList) {
			alarmListviewAdapter.addItem(alarm);
		}
    }

}
