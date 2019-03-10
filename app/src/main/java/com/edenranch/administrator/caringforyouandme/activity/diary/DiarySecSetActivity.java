package com.edenranch.administrator.caringforyouandme.activity.diary;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.edenranch.administrator.caringforyouandme.DiarySecActivity;
import com.edenranch.administrator.caringforyouandme.LoginActivity;
import com.edenranch.administrator.caringforyouandme.R;
import com.edenranch.administrator.caringforyouandme.activity.MainActivity;
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
 * 희망게시판 작성글 저장하기
 * @author 신성인
 * @since 2019-02-26
 */
public class DiarySecSetActivity extends AppCompatActivity {

	private Toolbar toolbar;

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
		setContentView(R.layout.activity_diary_sec_set);

		subject = findViewById(R.id.subject_edit);
        content = findViewById(R.id.content_edit);

		Intent intent = getIntent();
		seq = intent.getIntExtra("Seq", 0);
		// 저장 권한 부여를 위한 아이디(로그인 아이디)
		editID = intent.getExtras().getString("editID");

		_setToolbar();

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
		toolbar.setTitle("6-2.희망게시판 작성하기");
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
									+ "&ID=" + URLEncoder.encode(editID, "UTF-8")
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

								// 리스트 새로고침을 위함
								Intent intent = new Intent(getApplicationContext(), DiarySecActivity.class);
								intent.putExtra("ID", editID);
								setResult(RESULT_OK, intent);
								startActivityForResult(intent, 1);

								finish();

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

				}
			})
			.setNegativeButton("취소", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

				}
			}).show();

	}

}
