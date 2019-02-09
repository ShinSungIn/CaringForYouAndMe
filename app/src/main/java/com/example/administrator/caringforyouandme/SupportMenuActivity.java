package com.example.administrator.caringforyouandme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

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
		setSupportActionBar(toolbar);
		// toolbar 뒤로 버튼 활성화
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//toolbar.setTitle("1.치매알기");
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
		getMenuInflater().inflate(R.menu.menu_known, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		TabLayout tabLayout1 = (TabLayout) findViewById(R.id.tabLayout1);
		TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabLayout2);

		System.out.println("onOptionsItemSelected getItemId()= " + item.getItemId());

		switch (item.getItemId()) {
			case android.R.id.home :
				//toolbar의 back키 눌렀을 때 동작
				finish();
				return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
