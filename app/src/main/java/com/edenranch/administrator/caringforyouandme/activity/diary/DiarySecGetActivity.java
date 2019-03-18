package com.edenranch.administrator.caringforyouandme.activity.diary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.edenranch.administrator.caringforyouandme.DiarySecActivity;
import com.edenranch.administrator.caringforyouandme.R;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * 희망게시판 작성글 저장하기
 * @author 신성인
 * @since 2019-02-26
 */
public class DiarySecGetActivity extends AppCompatActivity {

	private Toolbar toolbar;

	private TextView seqtext;
	private EditText subject;
	private EditText content;

	private Item item;
	private int seq;
	private String editID;

	String target;
	private AlertDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_sec_get);

		subject = findViewById(R.id.subject_edit);
        content = findViewById(R.id.content_edit);
		seqtext = findViewById(R.id.Seq);
		Intent intent = getIntent();
		seq = intent.getIntExtra("Seq", 0);
		// 저장 권한 부여를 위한 아이디(로그인 아이디)
		editID = intent.getExtras().getString("editID");

		_setToolbar();

		new BackgroundTask().execute();
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
				int Seq;
				String Subject, Content, ID, insertDT;
				// 가져온 값이 있을 경우엔
				if (jsonArray.length() > 0) {

					JSONObject object = jsonArray.getJSONObject(0);
					// Seq visible 시켜주고 값 넣고
					Seq = Integer.parseInt(object.getString("Seq"));
					Subject = object.getString("Subject");
					Content = object.getString("Content");
					ID = object.getString("ID");
					//insertDT = object.getString("insertDT").substring(0, 10);

					// 제목, 내용 값 넣어주기
					seqtext.setText(String.valueOf(Seq));
					subject.setText(Subject);
					content.setText(Content);

					// 아이디값에 따른 저장버튼 숨기기
					Button savebutton = (Button) findViewById(R.id.button_diary_set_save);
					if (!editID.equals(ID)) {
						savebutton.setVisibility(View.INVISIBLE);
					} else {
						savebutton.setVisibility(View.VISIBLE);
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onDestroy(){
		super.onDestroy();

		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}

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

		new AlertDialog.Builder(this)
			.setTitle("저장").setMessage("저장 하시겠습니까?")
			.setPositiveButton("저장", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

					AsyncTask.execute(new Runnable() {
						@Override
						public void run() {
							try {
								target = "http://sungin0605.cafe24.com/SetBoardContent.php?Seq=" + seq
									+ "&subject=" + URLEncoder.encode(subject.getText().toString(), "UTF-8")
									+ "&content=" + URLEncoder.encode(content.getText().toString(), "UTF-8");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}

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

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

					// 리스트 새로고침을 위함
					Intent intent = new Intent(getApplicationContext(), DiarySecActivity.class);
					intent.putExtra("ID", editID);
					setResult(RESULT_OK, intent);
					startActivityForResult(intent, 1);

					finish();

				}
			})
			.setNegativeButton("취소", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

				}
			}).show();

	}

}
