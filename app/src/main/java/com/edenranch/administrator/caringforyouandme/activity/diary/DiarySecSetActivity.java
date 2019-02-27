package com.edenranch.administrator.caringforyouandme.activity.diary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import com.edenranch.administrator.caringforyouandme.R;
import com.edenranch.administrator.caringforyouandme.database.domain.Diary;
import com.edenranch.administrator.caringforyouandme.database.query.DiaryQuery;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * 희망게시판 작성하기
 * @author 신성인
 * @since 2019-02-26
 */
public class DiarySecSetActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener{

	private Toolbar toolbar;

	private EditText subject;
	private EditText regDt;
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
        new DatePickerDialog(DiarySecSetActivity.this, this, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),	myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 툴바 설정
     */
	private void _setToolbar() {
		toolbar = findViewById(R.id.toolbar);
		toolbar.setTitle("6.우리들의 돌봄일기");
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
			content.setText(diary.getContent());
		}
	}


}
