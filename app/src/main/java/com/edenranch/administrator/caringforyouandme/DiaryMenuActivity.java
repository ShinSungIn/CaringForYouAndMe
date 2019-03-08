package com.edenranch.administrator.caringforyouandme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

public class DiaryMenuActivity extends AppCompatActivity {

	private Toolbar toolbar;
	private String ID;
	Context context;

	TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_menu);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		Intent intent = getIntent();
		ID = intent.getExtras().getString("ID");

		setToolbar();

		// 1.돌봄일기(본인만 보이게)
		Button DiaryMenuButton1 = (Button) findViewById(R.id.DiaryMenuButton1);
		DiaryMenuButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(DiaryMenuActivity.this, DiaryFirActivity.class);
				DiaryMenuActivity.this.startActivity(intent);
			}
		});

		// 2.희망게시판(공유)
		Button DiaryMenuButton2 = (Button) findViewById(R.id.DiaryMenuButton2);
		DiaryMenuButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(DiaryMenuActivity.this, DiarySecActivity.class);
				intent.putExtra("ID", ID);
				DiaryMenuActivity.this.startActivity(intent);
			}
		});

		context = this;
	}

	private void setToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar 뒤로 버튼 활성화
		toolbar.setTitle("6.우리들의 돌봄일기");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_diarymenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case android.R.id.home :
				//toolbar의 back키 눌렀을 때 동작
				finish();
				return true;

			case R.id.action_mainAll :
				Toast.makeText(this, "도움말 기능입니다. 메뉴를 선택하세요.", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_diraymenu1 :
				AlertDialog.Builder builder1 = new AlertDialog.Builder(DiaryMenuActivity.this);
				builder1.setMessage("" +
					"주의:\n\n어플 삭제시 돌봄일기 내용도 삭제됩니다.")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
