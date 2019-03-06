package com.edenranch.administrator.caringforyouandme.activity.diary;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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

		try {
			target = "http://sungin0605.cafe24.com/SetBoardContent.php?subject="
				+ URLEncoder.encode(subject.getText().toString(), "UTF-8")
				+ "&content=" + URLEncoder.encode(content.getText().toString(), "UTF-8");


		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		finish();
	}

	class BackgroundTask extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			target = "http://sungin0605.cafe24.com/GetBoardContent.php?seq=" + seq;
		}

		@Override
		protected String doInBackground(Void... voids) {
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
				return stringBuilder.toString().trim();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public void onProgressUpdate(Void... values) {
			super.onProgressUpdate();
		}

		@Override
		public void onPostExecute(String result) {
			try {
				JSONObject jsonObject = new JSONObject(result);
				JSONArray jsonArray = jsonObject.getJSONArray("response");
				int count = 0;
				String Subject, Content, ID, insertDT;

				if (jsonArray.length() > 0) {
					JSONObject object = jsonArray.getJSONObject(count);
					Subject = object.getString("Subject");
					Content = object.getString("Content");
					ID = object.getString("ID");
					insertDT = object.getString("insertDT").substring(0, 10);
					Item item = new Item(R.drawable.menu_01, Subject, Content, ID, insertDT);

					subject.setText(item.getSubject());
					content.setText(item.getContent());
				}


			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
