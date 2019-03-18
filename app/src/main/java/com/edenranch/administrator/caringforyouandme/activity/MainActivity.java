package com.edenranch.administrator.caringforyouandme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.edenranch.administrator.caringforyouandme.AlarmActivity;
import com.edenranch.administrator.caringforyouandme.CureprogramActivity;
import com.edenranch.administrator.caringforyouandme.DiaryMenuActivity;
import com.edenranch.administrator.caringforyouandme.DictionaryMenuActivity;
import com.edenranch.administrator.caringforyouandme.FeelingActivity;
import com.edenranch.administrator.caringforyouandme.KnownActivity;
import com.edenranch.administrator.caringforyouandme.LoginActivity;
import com.edenranch.administrator.caringforyouandme.PreventionActivity;
import com.edenranch.administrator.caringforyouandme.R;
import com.edenranch.administrator.caringforyouandme.RoadmapActivity;
import com.edenranch.administrator.caringforyouandme.SupportMenuActivity;
import com.edenranch.administrator.caringforyouandme.service.AlarmJobService;
import com.edenranch.administrator.caringforyouandme.service.AlarmService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//implements NavigationView.OnNavigationItemSelectedListener
public class MainActivity extends AppCompatActivity {

	private long backKeyPressedTime = 0;
	private Toast toast;
	private Activity activity;
	private Context context;
	private String loginID;	// 로그인 ID
	String target;
	PackageInfo packageInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		Intent intent = getIntent();
		loginID = intent.getExtras().getString("ID");
/*
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
			this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
*/
		//NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		//navigationView.setNavigationItemSelectedListener(this);
		context = this;

		// 버전 가져오기
		try {
			packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		// 알람 서비스 시작
		_startAlarmService();

		// 1.치매알기 클릭
		Button knownBuuton1 = (Button) findViewById(R.id.KnownButton1);
		knownBuuton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new BackgroundTask().execute(1);

				Intent registerIntent = new Intent(MainActivity.this, KnownActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 2.치매예방 클릭
		Button preventionButton2 = (Button) findViewById(R.id.PreventionButton2);
		preventionButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new BackgroundTask().execute(2);

				Intent registerIntent = new Intent(MainActivity.this, PreventionActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 3.치매서비스로드맵 클릭
		Button RoadmapButton3 = (Button) findViewById(R.id.RoadmapButton3);
		RoadmapButton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new BackgroundTask().execute(3);

				Intent registerIntent = new Intent(MainActivity.this, RoadmapActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 4.치매지원서비스 클릭
		Button SupportButton4 = (Button) findViewById(R.id.SupportButton4);
		SupportButton4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new BackgroundTask().execute(4);

				Intent registerIntent = new Intent(MainActivity.this, SupportMenuActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 5.인지향상프로그램
		Button CureProgramButton5 = (Button) findViewById(R.id.CureProgramButton5);
		CureProgramButton5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new BackgroundTask().execute(5);

				Intent registerIntent = new Intent(MainActivity.this, CureprogramActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 6.우리들의 돌봄일기
		Button DiaryButton6 = (Button) findViewById(R.id.DiaryButton6);
		DiaryButton6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new BackgroundTask().execute(6);

				Intent registerIntent = new Intent(MainActivity.this, DiaryMenuActivity.class);
				registerIntent.putExtra("ID", loginID);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 7.치매환자돌봄방법 클릭
		Button DictionaryButton7 = (Button) findViewById(R.id.DictionaryButton7);
		DictionaryButton7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new BackgroundTask().execute(7);

				Intent registerIntent = new Intent(MainActivity.this, DictionaryMenuActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 8.나의감정 살피기
		Button FeelingsButton8 = (Button) findViewById(R.id.FeelingsButton8);
		FeelingsButton8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new BackgroundTask().execute(8);

				Intent registerIntent = new Intent(MainActivity.this, FeelingActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		this.activity = this;
	}

	// 사용자 조회수 저장
	class BackgroundTask extends AsyncTask<Integer, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Integer... integers) {
			try {

				target = "http://sungin0605.cafe24.com/UseHistoryRegister.php?ID=" + loginID + "&MenuNum=" + integers[0];
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
				// 반환값 없음
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
			// 반환값 없음
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		//finish();
	}


	@Override
	public void onPause() {
		super.onPause();
		// Remove the activity when its off the screen
		//finish();
	}

	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
			backKeyPressedTime = System.currentTimeMillis();
			showGuide();
			return;
		}
		if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
			activity.finish();
			toast.cancel();
		}
	}

	private void showGuide() {
		toast = Toast.makeText(activity, "뒤로버튼을 한번 더 누르면 앱을 종료합니다.", Toast.LENGTH_LONG);
		toast.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_main2) {
			AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
			builder1.setMessage("" +
				"로그인 앱: 치매돌봄톡\n" +
				"로그인 아이디: " + loginID + "\n")
				.setNegativeButton("닫기", null)
				.create()
				.show();
			return true;
		} else if (id == R.id.action_main3) {
			// 앱정보
			AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
			builder2.setMessage("" +
				"앱 정보\n\n" +
				"치매돌봄톡 앱은 치매환자와 보호자를 위한 유용한 어플입니다.\n\n" +
				"앱 버전: " + packageInfo.versionCode + "\n" +
				"앱 버전명: " + packageInfo.versionName + "\n\n" +
				"메인화면 상단 메뉴에서 전국치매센터 알아보기 및 알람기능이 제공되고 있습니다.\n\n" +
				"안드로이드폰 기종이 너무 오래되었거나 또는 최신 안드로이드 폰에서는 사용시 제약사항이 발생 할 수 있습니다.")
				.setNegativeButton("닫기", null)
				.create()
				.show();
			return true;
		} else if (id == R.id.action_main4) {
			//앱 평가하기(일반)
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/forms/ZNWt0XMUlzAH3zIx2")));
			return true;
		} else if (id == R.id.action_main6) {
			//앱 평가하기(고급)
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/forms/TZNBTMygTh4ooI0Z2")));
			return true;
		} else if (id == R.id.action_main7) {
			//개인정보처리방침
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://sungin0605.cafe24.com/PrivacyPolicy.php")));
			return true;
		} else if (id == R.id.action_main5) {
			//로그아웃
			new AlertDialog.Builder(this)
				.setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?\n\n주의:\n로그인 아이디와 비밀번호를 기억하고 계신가요? 로그아웃하시면 다시 입력하셔야합니다.")
				.setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						SharedPreferences appData = getSharedPreferences("appData", MODE_PRIVATE);
						SharedPreferences.Editor editor = appData.edit();

						editor.clear();	// 로그인 정보 삭제
						editor.apply();

						Intent intent = new Intent(MainActivity.this, LoginActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
						startActivity(intent);
					}
				})
				.setNegativeButton("취소", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				})
				.show();

			return true;
		} else if (id == R.id.action_alarm) {
			// 알람설정 클릭
			Intent alarmIntent = new Intent(MainActivity.this, AlarmActivity.class);
			MainActivity.this.startActivity(alarmIntent);
			return true;
		} else if (id == R.id.action_centerlist) {
			// 전국치매센터 보기
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://sungin0605.cafe24.com/CenterList.php")));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
/*
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_camera) {
			// Handle the camera action
		} else if (id == R.id.nav_gallery) {

		} else if (id == R.id.nav_slideshow) {

		} else if (id == R.id.nav_manage) {

		} else if (id == R.id.nav_share) {

		} else if (id == R.id.nav_send) {

		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
*/
	/**
	 * Alarm 서비스 시작
	 */
	private void _startAlarmService(){
		if(!isServiceRunning(AlarmService.class)){
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				// 오류나서 주석처리함
				AlarmJobService.enqueueWork(context, new Intent(context, AlarmService.class));
			} else {
				startService(new Intent(this, AlarmService.class));
			}
		}
	}

	/**
	 * cls 클래스가 실행중인지 여부확인
	 */
	private boolean isServiceRunning(Class<?> cls) {
		if (cls == null) {
			return false;
		}
		android.app.ActivityManager activityManager = (android.app.ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
		for (android.app.ActivityManager.RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
			if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
				return true;
			}
		}
		return false;
	}
}
