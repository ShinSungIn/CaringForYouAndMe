package com.edenranch.administrator.caringforyouandme;

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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class DictionaryFivActivity extends AppCompatActivity {

	private Toolbar toolbar;
	Context context;

	TabHost tabHost;
	TextView textViewFirContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary_fiv);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		setToolbar();

		// 10.종합
		Button DicFivButton10 = (Button) findViewById(R.id.DicFivButton10);
		DicFivButton10.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/XRx5D4raMd4")));
			}
		});

		// 1
		Button DicFivButton1 = (Button) findViewById(R.id.DicFivButton1);
		DicFivButton1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/f5zPKOcd8sg")));
			}
		});

		// 2
		Button DicFivButton2 = (Button) findViewById(R.id.DicFivButton2);
		DicFivButton2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/g_mSrCTCnoQ")));
			}
		});

		// 3
		Button DicFivButton3 = (Button) findViewById(R.id.DicFivButton3);
		DicFivButton3.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/wWNBDnq0bBA")));
			}
		});

		// 4
		Button DicFivButton4 = (Button) findViewById(R.id.DicFivButton4);
		DicFivButton4.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/SRsP-EplEws")));
			}
		});

		// 5
		Button DicFivButton5 = (Button) findViewById(R.id.DicFivButton5);
		DicFivButton5.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/QN4m6Fj2D_Q")));
			}
		});

		// 6
		Button DicFivButton6 = (Button) findViewById(R.id.DicFivButton6);
		DicFivButton6.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/owhfZjFdMdA")));
			}
		});

		// 7
		Button DicFivButton7 = (Button) findViewById(R.id.DicFivButton7);
		DicFivButton7.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/1IDJhkpJMsw")));
			}
		});

		// 8
		Button DicFivButton8 = (Button) findViewById(R.id.DicFivButton8);
		DicFivButton8.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/Zn8s-9pLwbg")));
			}
		});

		// 9
		Button DicFivButton9 = (Button) findViewById(R.id.DicFivButton9);
		DicFivButton9.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/ZFX7Dh5riI8")));
			}
		});

		context = this;
	}

	private void setToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar 뒤로 버튼 활성화
		toolbar.setTitle("7-5.치매환자와의 일상생활");
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
		getMenuInflater().inflate(R.menu.menu_dictionary, menu);
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
			case R.id.action_dictionary1 :
				AlertDialog.Builder builder1 = new AlertDialog.Builder(DictionaryFivActivity.this);
				builder1.setMessage("" +
					"이 글을 통해\n" +
					"치매 어르신도 아름다운 추억의 단면들을 지니고 있는 한 사람임을 잊지 않도록 합니다.\n" +
					"돌봄 지식의 필요성을 깨닫고, 돌보는 사람 또한 자신감을 갖고 돌봄을 시행하며  돌봄의 질 향상을 위해 게시합니다.\n")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
			case R.id.action_dictionary2:
				AlertDialog.Builder builder2 = new AlertDialog.Builder(DictionaryFivActivity.this);
				builder2.setMessage("치매환자가족들이 환자와 함께하는 일상의 어려움에 대해 가장 힘들어 합니다.\n\n" +
					"동영상과 음성지원, 삽화와 그림을 통해 부담없이 이용 가능하도록 제작하였습니다.\n")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
