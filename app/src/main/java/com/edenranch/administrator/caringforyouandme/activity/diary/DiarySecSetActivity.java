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
		seq = intent.getIntExtra("Seq", 0);

		_setDiarySet(seq);

		_setToolbar();

	}

	/**
	 * 기본 값 설정
	 */
	private void _setDiarySet(int Seq){
		if(Seq > 0) {
			// Seq에 해당하는 게시판 글 가져오기
			target = "http://sungin0605.cafe24.com/GetBoardContent.php?seq=" + Seq;

			try {

				URL url = new URL(target);

				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				InputStream inputStream = httpURLConnection.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String temp;
				StringBuilder stringBuilder = new StringBuilder();

				while ((temp = bufferedReader.readLine()) != null) {
					stringBuilder.append(temp + "\n");
				}

				bufferedReader.close();
				inputStream.close();
				httpURLConnection.disconnect();
				//return stringBuilder.toString().trim();

				JSONObject jsonObject = new JSONObject(stringBuilder.toString().trim());
				JSONArray jsonArray = jsonObject.getJSONArray("response");
				String Subject, Content, ID, insertDT;
				// 가져온 값이 있을 경우엔
				if (jsonArray.length() > 0) {

					JSONObject object = jsonArray.getJSONObject(0);
					// Seq visible 시켜주고 값 넣고
					//Seq = Integer.parseInt(object.getString("Seq"));
					Subject = object.getString("Subject");
					Content = object.getString("Content");
					ID = object.getString("ID");
					insertDT = object.getString("insertDT").substring(0, 10);

					// 제목, 내용 값 넣어주기
					subject.setText(Subject);
					content.setText(Content);

					//Item item = new Item(R.drawable.menu_01, Seq, Subject, Content, ID, insertDT);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
