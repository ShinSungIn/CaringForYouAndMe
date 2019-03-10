package com.edenranch.administrator.caringforyouandme.activity.diary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.edenranch.administrator.caringforyouandme.CureprogramActivity;
import com.edenranch.administrator.caringforyouandme.R;
import com.edenranch.administrator.caringforyouandme.database.domain.Diary;
import com.edenranch.administrator.caringforyouandme.database.query.DiaryQuery;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * 돌봄일기 작성하기
 * @author 곽우영
 * @since 2019-02-20
 */
public class DiaryFirSetActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener{

	private Toolbar toolbar;

	private EditText subject;
	private EditText regDt;
	private EditText condition;
	private EditText activity;
	private EditText feeling;
	private EditText content;

	private DiaryQuery diaryQuery;

	private Diary diary;

	final Calendar myCalendar = Calendar.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_fir_set);

		diaryQuery = new DiaryQuery(this);

		subject = findViewById(R.id.edittext_diary_set_subject);
		regDt = findViewById(R.id.edittext_diary_set_regdt);
        regDt.setOnClickListener(this);
		condition = findViewById(R.id.edittext_diary_set_condition);
		activity = findViewById(R.id.edittext_diary_set_activity);
		feeling = findViewById(R.id.edittext_diary_set_feeling);
        content = findViewById(R.id.edittext_diary_set_content);

		Intent intent = getIntent();
		int seq = intent.getIntExtra("seq", 0);

		_setDiarySet(seq);

		_setToolbar();

	}

	@Override
	public void onDestroy(){
		super.onDestroy();

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,  int dayOfMonth) {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREAN);
        regDt.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        new DatePickerDialog(DiaryFirSetActivity.this, this, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),	myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 툴바 설정
     */
	private void _setToolbar() {
		toolbar = findViewById(R.id.toolbar);
		toolbar.setTitle("6-1.돌봄일기");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * 취소
 	 */
	public void onCancel(View view){
		finish();
	}

	/**
	 * 저장
	 */
	public void onSave(View view){
		if (diary == null ) {
			diary = new Diary();
		}
		diary.setSubject(subject.getText().toString());
		diary.setRegDt(regDt.getText().toString());
		diary.setCondition(condition.getText().toString());
		diary.setActivity(activity.getText().toString());
		diary.setFeeling(feeling.getText().toString());
		diary.setContent(content.getText().toString());
		if (diary.getSeq() == null ) {
			diaryQuery.set(diary);
		} else {
			diaryQuery.modify(diary);
		}
		finish();
	}

	/**
	 * 기본 값 설정
	 */
	private void _setDiarySet(int seq){
		if(seq > 0) {
			diary = diaryQuery.get(seq);
			subject.setText(diary.getSubject());
			regDt.setText(diary.getRegDt());
			condition.setText(diary.getCondition());
			activity.setText(diary.getActivity());
			feeling.setText(diary.getFeeling());
			content.setText(diary.getContent());
		}
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
				AlertDialog.Builder builder1 = new AlertDialog.Builder(DiaryFirSetActivity.this);
				builder1.setMessage("" +
					"돌봄일기 작성하는 방법\n\n" +
					"제목: 일기 제목 입력\n(돌봄일기 리스트에 표시됩니다)\n\n" +
					"등록일: 일기에 해당하는 일자를 선택합니다\n\n" +
					"환자상태, 돌봄활동, 느낌, 기타 구분:\n" +
					"쉽게 기록할 수 있도록 항목을 구분해 놓은 것일 뿐 자유롭게 작성하세요^^")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
		}


		return super.onOptionsItemSelected(item);
	}
}
