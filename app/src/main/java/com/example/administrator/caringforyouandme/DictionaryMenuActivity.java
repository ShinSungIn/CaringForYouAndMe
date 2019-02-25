package com.example.administrator.caringforyouandme;

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

public class DictionaryMenuActivity extends AppCompatActivity {

	private Toolbar toolbar;
	Context context;

	TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary_menu);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		setToolbar();

		// 1.초기치매
		Button DicMenuButton1 = (Button) findViewById(R.id.DicMenuButton1);
		DicMenuButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(DictionaryMenuActivity.this, DictionaryFirActivity.class);
				DictionaryMenuActivity.this.startActivity(registerIntent);
			}
		});

		// 2.중고도치매
		Button DicMenuButton2 = (Button) findViewById(R.id.DicMenuButton2);
		DicMenuButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(DictionaryMenuActivity.this, DictionarySecActivity.class);
				DictionaryMenuActivity.this.startActivity(registerIntent);
			}
		});

		// 3.정신행동증상
		Button DicMenuButton3 = (Button) findViewById(R.id.DicMenuButton3);
		DicMenuButton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(DictionaryMenuActivity.this, DictionaryThiActivity.class);
				DictionaryMenuActivity.this.startActivity(registerIntent);
			}
		});

		// 4.안전관리
		Button DicMenuButton4 = (Button) findViewById(R.id.DicMenuButton4);
		DicMenuButton4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(DictionaryMenuActivity.this, DictionaryForActivity.class);
				DictionaryMenuActivity.this.startActivity(registerIntent);
			}
		});

		// 5.치매환자와의 일상생활 동영상
		Button DicMenuButton5 = (Button) findViewById(R.id.DicMenuButton5);
		DicMenuButton5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(DictionaryMenuActivity.this, DictionaryFivActivity.class);
				DictionaryMenuActivity.this.startActivity(registerIntent);
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
				AlertDialog.Builder builder1 = new AlertDialog.Builder(DictionaryMenuActivity.this);
				builder1.setMessage("" +
					"이 글을 통해\n" +
					"치매 어르신도 아름다운 추억의 단면들을 지니고 있는 한 사람임을 잊지 않도록 합니다.\n" +
					"돌봄 지식의 필요성을 깨닫고, 돌보는 사람 또한 자신감을 갖고 돌봄을 시행하며  돌봄의 질 향상을 위해 게시합니다.\n")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
			case R.id.action_dictionary2:
				AlertDialog.Builder builder2 = new AlertDialog.Builder(DictionaryMenuActivity.this);
				builder2.setMessage("치매환자가족들이 환자와 함께하는 일상생활의 어려움에 대해 가장 힘들어 합니다.\n" +
					"현재 돌봄 실천사항이 적절한지에 대한 평가를 원하며, 가장 어렵고 힘들며 도움이 필요한 교육으로 식사, 배설, 목욕이나 이닦기와 같은 개인위생, 운동 영역이었습니다.\n\n" +
					"특히 경도치매환자가족들의 돌봄교육 요구도가 높습니다.\n" +
					"글의 목적상 지문이 길어 읽기 부담스러울 수 있으나 앱 디자이너와 기획자와 함께 의논하여 삽화나 그림을 첨부하고, 음성지원을 통하여 노인들도 부담 없이 이용 가능하도록 제작되었습니다.\n" +
					"정신행동증상: 치매의 특징적인 증상이며, 치매환자가족의 환자 돌봄 실천사항 중 가장 힘들어하며 도움을 필요로 하는 부분입니다.\n.")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
