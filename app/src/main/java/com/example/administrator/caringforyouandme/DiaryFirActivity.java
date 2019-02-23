package com.example.administrator.caringforyouandme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import com.example.administrator.caringforyouandme.activity.diary.DiaryFirSetActivity;
import com.example.administrator.caringforyouandme.database.Column;
import com.example.administrator.caringforyouandme.database.Entity;
import com.example.administrator.caringforyouandme.database.domain.Diary;
import com.example.administrator.caringforyouandme.listview.diary.DiaryListviewAdapter;
import com.example.administrator.caringforyouandme.listview.diary.DiaryListviewItem;

public class DiaryFirActivity extends AppCompatActivity {
	private Toolbar toolbar;

	Context context;

	private FloatingActionButton button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_fir);

		button = findViewById(R.id.floatingactionbutton_diary_create);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(context, DiaryFirSetActivity.class));
			}
		});

		setToolbar();

		context = this;

	}

	private void setToolbar() {
		toolbar = findViewById(R.id.toolbar);
		// toolbar 뒤로 버튼 활성화
		//toolbar.setTitle("4-1.장기요양급여 지원서비스");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

	@SuppressWarnings("Duplicates")
	private void _setListView(){
		DiaryListviewAdapter diaryListviewAdapter = new DiaryListviewAdapter(this);
		ListView listView_diary = findViewById(R.id.listview_diary);
		listView_diary.setAdapter(diaryListviewAdapter);
		listView_diary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				DiaryListviewItem diaryListviewItem = (DiaryListviewItem) diaryListviewAdapter.getItem(position);

				Intent intent = new Intent(getApplicationContext(), DiaryFirSetActivity.class);
				intent.putExtra("seq", diaryListviewItem.getSeq());

				// TODO : 테스트용
				intent.putExtra("subject", diaryListviewItem.getSubject());
				intent.putExtra("regdt", diaryListviewItem.getRegDate());

				startActivity(intent);

			}
		});

		listView_diary.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				DiaryListviewItem diaryListviewItem = (DiaryListviewItem) diaryListviewAdapter.getItem(position);
				_itemDiaryDialog(diaryListviewItem.getSeq());
				return true;
			}
		});

		//TODO: sample
		Diary diary = new Diary();
		diary.setSeq(1);
		diary.setRegDt("2019.02.19");
		diary.setSubject("오늘 약을 먹는날이 었다...... 하지만 그러지 못했다.....");

		diaryListviewAdapter.addItem(diary);

		diary.setSeq(2);
		diaryListviewAdapter.addItem(diary);
	}

	/**
	 * 일기 항목 삭제 여부 알림 다이얼로그
	 */
	@SuppressWarnings("Duplicates")
	private void _itemDiaryDialog(int seq){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder
			.setMessage("삭제 하시겠습니까?")
			.setCancelable(false)
			.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// TODO : 다이어리 삭제 알리고리즘 삽입


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
