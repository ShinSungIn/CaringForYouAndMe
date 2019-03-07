package com.edenranch.administrator.caringforyouandme.activity.diary;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.edenranch.administrator.caringforyouandme.R;
import com.edenranch.administrator.caringforyouandme.database.query.DiaryQuery;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;


/**
 * 희망게시판 작성하기 뷰
 * @author 신성인
 * @since 2019-02-26
 */
public class DiarySecSetActivity extends AppCompatActivity {

	private Toolbar toolbar;

	private EditText subject;
	private EditText content;

	private DiaryQuery diaryQuery;

	private Item item;
	private int seq;
	String target;

	final Calendar myCalendar = Calendar.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_sec_set);

		diaryQuery = new DiaryQuery(this);

		subject = findViewById(R.id.subject_edit);
        content = findViewById(R.id.content_edit);

		Intent intent = getIntent();
		seq = intent.getIntExtra("seq", 0);

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

    /**
     * 툴바 설정
     */
	private void _setToolbar() {
		toolbar = findViewById(R.id.toolbar);
		toolbar.setTitle("6-2.희망게시판");
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
	public void onSaveBoard(View view){
		Toast.makeText(this, "작업중입니다.", Toast.LENGTH_SHORT).show();
		/*
		try {
			target = "http://sungin0605.cafe24.com/SetBoardContent.php?subject="
				+ URLEncoder.encode(subject.getText().toString(), "UTF-8")
				+ "&content=" + URLEncoder.encode(content.getText().toString(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		finish();
		*/
	}

}
