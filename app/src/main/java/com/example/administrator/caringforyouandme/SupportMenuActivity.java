package com.example.administrator.caringforyouandme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

public class SupportMenuActivity extends AppCompatActivity {

	private Toolbar toolbar;
	Context context;

	TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_support_menu);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		setToolbar();

		// 1.장기요양급여 지원 클릭
		Button SupportMenuButton1 = (Button) findViewById(R.id.SupportMenuButton1);
		SupportMenuButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(SupportMenuActivity.this, SupportFirActivity.class);
				SupportMenuActivity.this.startActivity(registerIntent);
			}
		});

		// 2.장기요양급여 지원 신청방법
		Button SupportMenuButton2 = (Button) findViewById(R.id.SupportMenuButton2);
		SupportMenuButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(SupportMenuActivity.this, SupportSecActivity.class);
				SupportMenuActivity.this.startActivity(registerIntent);
			}
		});

		// 3.치매안심센터
		Button SupportMenuButton3 = (Button) findViewById(R.id.SupportMenuButton3);
		SupportMenuButton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(SupportMenuActivity.this, SupportThiActivity.class);
				SupportMenuActivity.this.startActivity(registerIntent);
			}
		});

		// 4.기타 지원서비스
		Button SupportMenuButton4 = (Button) findViewById(R.id.SupportMenuButton4);
		SupportMenuButton4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(SupportMenuActivity.this, SupportForActivity.class);
				SupportMenuActivity.this.startActivity(registerIntent);
			}
		});

		context = this;
	}

	private void setToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar 뒤로 버튼 활성화
		//toolbar.setTitle("1.치매알기");
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
		getMenuInflater().inflate(R.menu.menu_supportmenu, menu);
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
			case R.id.action_supportmenu1 :
				AlertDialog.Builder builder1 = new AlertDialog.Builder(SupportMenuActivity.this);
				builder1.setMessage("" +
					"치매환자보호자에게 가장 필요한 정보이며 서비스 이용이 간단합니다.\n\n" +
					"최근 제도나 관련법규의 수정.보완으로 인해 장기요양서비스, 산정특례 적용 등 이용 가능한 서비스에 대해서 알 수 있습니다.\n\n" +
					"치매환자가족들이 시설과 병원에 차이점을 잘 알지 못하며 시설의 종류와 이용방법에 대해 알고 싶어합니다.")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
