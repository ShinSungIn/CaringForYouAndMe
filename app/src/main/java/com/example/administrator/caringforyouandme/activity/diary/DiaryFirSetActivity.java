package com.example.administrator.caringforyouandme.activity.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import com.example.administrator.caringforyouandme.R;

/**
 * @author 곽우영
 * @since 2019-02-20
 */
public class DiaryFirSetActivity extends AppCompatActivity {

	private Toolbar toolbar;

	private EditText subject;
	private EditText regDt;
	private EditText content;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_fir_set);

		subject = findViewById(R.id.edittext_diary_set_subject);
		regDt = findViewById(R.id.edittext_diary_set_regdt);
		content = findViewById(R.id.edittext_diary_set_content);



		Intent intent = getIntent();

		int seq = intent.getIntExtra("seq", 0);

		// TODO : 테스트용
		String subjectStr =  intent.getStringExtra("subject");
		String regDtStr = intent.getStringExtra("regdt");
		subject.setText(subjectStr);
		regDt.setText(regDtStr);

		_setDiarySet(seq);

		_setToolbar();

	}

	@Override
	public void onDestroy(){
		super.onDestroy();

	}

	private void _setToolbar() {
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	public void onCancel(View view){
		finish();
	}

	public void onSave(View view){
	}

	private void _setDiarySet(int seq){

	}
}
