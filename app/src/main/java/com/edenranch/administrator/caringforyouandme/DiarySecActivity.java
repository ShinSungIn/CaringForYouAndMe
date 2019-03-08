package com.edenranch.administrator.caringforyouandme;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.edenranch.administrator.caringforyouandme.activity.diary.DiaryFirSetActivity;
import com.edenranch.administrator.caringforyouandme.activity.diary.DiarySecSetActivity;
import com.edenranch.administrator.caringforyouandme.activity.diary.Item;
import com.edenranch.administrator.caringforyouandme.activity.diary.RecyclerAdapter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 6. 우리들의 돌봄일기 - 희망게시판 리스트
 */
public class DiarySecActivity extends AppCompatActivity {

	private Toolbar toolbar;
	private String editID;
	Context context;
	private List<Item> items = new ArrayList<Item>();
	private RecyclerView recyclerView;
	private FloatingActionButton button;
	private EditText diarySearchStr;
	private RecyclerAdapter recyclerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_sec);

		Intent intent = getIntent();
		editID = intent.getExtras().getString("ID");

		setToolbar();

		recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(layoutManager);

		// 검색
		Button diarySearchButton = (Button) findViewById(R.id.diarySearchButton);
		diarySearchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new BackgroundTask().execute();
			}
		});
		// 글 작성하기
		button = (FloatingActionButton) findViewById(R.id.floatingactionbutton_diarysec_create);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(context, DiarySecSetActivity.class);
				intent1.putExtra("editID", editID);
				startActivity(intent1);
			}
		});

		diarySearchStr = (EditText) findViewById(R.id.diarySearchStr);

		context = this;

		// 리스트 가져오기
		new BackgroundTask().execute();
	}

	private void setToolbar() {
		toolbar = findViewById(R.id.toolbar);
		toolbar.setTitle("6-2.희망게시판");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {
			if (resultCode == DiarySecSetActivity.RESULT_OK) {
				new BackgroundTask().execute();
			}
			if (resultCode == DiarySecSetActivity.RESULT_CANCELED) {
				//만약 반환값이 없을 경우의 코드를 여기에 작성
			}
		}
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
				finish();
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	public class BackgroundTask extends AsyncTask<Void, Void, String> {

		String target;

		@Override
		protected void onPreExecute() {
			try {
				target = "http://sungin0605.cafe24.com/BoardList.php?str=" + URLEncoder.encode(diarySearchStr.getText().toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
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
				//Subject"=>$row[0], "Content"=>$row[1], "ID"=>$row[2], "insertDT"=>
				int Seq;
				String Subject, Content, ID, insertDT;
				items.clear();
				while(count < jsonArray.length()) {
					JSONObject object = jsonArray.getJSONObject(count);
					Seq = Integer.parseInt(object.getString("Seq"));
					Subject = object.getString("Subject");
					Content = object.getString("Content");
					ID = object.getString("ID");
					insertDT = object.getString("insertDT").substring(0, 10);
					Item item = new Item(R.drawable.menu_01, Seq, Subject, Content, ID, insertDT);

					items.add(item);
					count++;
				}

				recyclerAdapter = new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_diary_sec, editID);
				recyclerView.setAdapter(recyclerAdapter);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
