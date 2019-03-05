package com.edenranch.administrator.caringforyouandme;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiarySecActivity extends AppCompatActivity {

	private Toolbar toolbar;
	Context context;
	private List<Item> items = new ArrayList<Item>();
	private RecyclerView recyclerView;
	private FloatingActionButton button;
	private EditText diarySearchStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_sec);

		setToolbar();

		recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(layoutManager);

		Button diarySearchButton = (Button) findViewById(R.id.diarySearchButton);
		diarySearchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new BackgroundTask().execute();
			}
		});

		button = (FloatingActionButton) findViewById(R.id.floatingactionbutton_diarysec_create);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(context, DiarySecSetActivity.class));
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
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	class BackgroundTask extends AsyncTask<Void, Void, String> {

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
				String Subject, Content, ID, insertDT;
				while(count < jsonArray.length()) {
					JSONObject object = jsonArray.getJSONObject(count);
					Subject = object.getString("Subject");
					Content = object.getString("Content");
					ID = object.getString("ID");
					insertDT = object.getString("insertDT").substring(0, 10);
					Item item = new Item(R.drawable.menu_01, Subject, Content, ID, insertDT);

					items.add(item);
					count++;
				}

				recyclerView.removeAllViews();
				recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_diary_sec));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
