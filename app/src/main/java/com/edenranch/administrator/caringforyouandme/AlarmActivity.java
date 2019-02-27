package com.edenranch.administrator.caringforyouandme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.edenranch.administrator.caringforyouandme.activity.alarm.AlarmSetActivity;
import com.edenranch.administrator.caringforyouandme.database.Column;
import com.edenranch.administrator.caringforyouandme.database.Entity;
import com.edenranch.administrator.caringforyouandme.database.domain.Alarm;
import com.edenranch.administrator.caringforyouandme.database.query.AlarmQuery;
import com.edenranch.administrator.caringforyouandme.listview.alarm.AlarmListviewAdapter;
import com.edenranch.administrator.caringforyouandme.listview.alarm.AlarmListviewItem;

import java.util.List;

public class AlarmActivity extends AppCompatActivity {

	private Toolbar toolbar;

	private Context context;

	private Button button;

	AlarmQuery alarmQuery;

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

		alarmQuery = new AlarmQuery(context);

		_setToolbar();

	}

	@Override
	public void onStart() {
		super.onStart();
		_setListView();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@SuppressWarnings("Duplicates")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				//toolbar의 back키 눌렀을 때 동작
				finish();
				return true;
		}

		return super.onOptionsItemSelected(item);
	}


	private void _setToolbar() {
		toolbar = findViewById(R.id.toolbar);
		toolbar.setTitle("알람설정");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void _setListView(){
        AlarmListviewAdapter alarmListviewAdapter = new AlarmListviewAdapter(this);
        ListView listView_Alarm = findViewById(R.id.listview_alarm);
        listView_Alarm.setAdapter(alarmListviewAdapter);
        listView_Alarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				AlarmListviewItem alarmListviewItem = (AlarmListviewItem) alarmListviewAdapter.getItem(position);
                Intent intent = new Intent(context, AlarmSetActivity.class);
                intent.putExtra("position", alarmListviewItem.getSeq());
                startActivity(intent);

            }
        });

        listView_Alarm.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				AlarmListviewItem alarmListviewItem = (AlarmListviewItem) alarmListviewAdapter.getItem(position);
				_itemAlertDialog(alarmListviewItem.getSeq());
				return true;
			}
		});

		AlarmQuery alarmQuery = new AlarmQuery(this);
		List<Alarm> alarmList = alarmQuery.gets();

		for (Alarm alarm : alarmList) {
			alarmListviewAdapter.addItem(alarm);
		}
    }

	/**
	 * 알람 항목 삭제 여부 알림 다이얼로그
	 */
	private void _itemAlertDialog(int seq){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder
				.setMessage("삭제 하시겠습니까?")
				.setCancelable(false)
				.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						String whereClause = Column.ALARM_SEQ + " = ? ";
						String[] whereArgs = new String[]{Integer.toString(seq)};
						alarmQuery.remove(Entity.alarm, whereClause, whereArgs);
						_setListView();

						dialog.cancel();
					}
				})
				.setNegativeButton("취소",	new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
