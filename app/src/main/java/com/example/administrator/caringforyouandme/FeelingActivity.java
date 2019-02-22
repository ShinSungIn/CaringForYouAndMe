package com.example.administrator.caringforyouandme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class FeelingActivity extends AppCompatActivity {

	private Toolbar toolbar;
	Context context;

	TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feeling);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		setToolbar();

		// 1.우울증검사
		Button linkButton1 = (Button) findViewById(R.id.feeling_main1_linkbutton);
		linkButton1.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/forms/aLV3UnK3AXYAt9SM2")));
			}
		});

		// 2.자살생각 척도검사
		Button linkButton2 = (Button) findViewById(R.id.feeling_main2_linkbutton);
		linkButton2.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/forms/aLV3UnK3AXYAt9SM2")));
			}
		});

		// 3.부양스트레스검사
		Button linkButton3 = (Button) findViewById(R.id.feeling_main3_linkbutton);
		linkButton3.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/forms/wCB0UQqoRFp4oIYt1")));
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
		getMenuInflater().inflate(R.menu.menu_feelingmenu, menu);
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
			case R.id.action_feelingmenu1 :
				AlertDialog.Builder builder1 = new AlertDialog.Builder(FeelingActivity.this);
				builder1.setMessage("" +
					"치매환자가족이 환자 돌봄에 대한 정신적 스트레스가 높았으며  그로 인한 우울증을 호소합니다.\n" +
					"우울증 검사를 통하여 본인의 정신적 건강에 관심을 갖는 계기가 됩니다.\n" +
					"자신의 행동이나 생각 또는 느낌을 표현하여 자신의 상태를 확인합니다.\n" +
					"해당검사로 바로 연결되도록 제작합니다.")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
