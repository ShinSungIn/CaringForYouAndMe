package com.example.administrator.caringforyouandme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.administrator.caringforyouandme.AlarmActivity;
import com.example.administrator.caringforyouandme.CureprogramActivity;
import com.example.administrator.caringforyouandme.DiaryMenuActivity;
import com.example.administrator.caringforyouandme.DictionaryActivity;
import com.example.administrator.caringforyouandme.DictionaryMenuActivity;
import com.example.administrator.caringforyouandme.FeelingActivity;
import com.example.administrator.caringforyouandme.KnownActivity;
import com.example.administrator.caringforyouandme.PreventionActivity;
import com.example.administrator.caringforyouandme.R;
import com.example.administrator.caringforyouandme.RoadmapActivity;
import com.example.administrator.caringforyouandme.SupportMenuActivity;
import com.example.administrator.caringforyouandme.service.AlarmJobService;
import com.example.administrator.caringforyouandme.service.AlarmService;
//implements NavigationView.OnNavigationItemSelectedListener
public class MainActivity extends AppCompatActivity {

	private long backKeyPressedTime = 0;
	private Toast toast;
	private Activity activity;
	private Context context;
	private String loginID;	// 로그인 ID

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
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

		// TODO : 임시 주석처리
		// 알람 서비스 시작
		_startAlarmService();

		// 1.치매알기 클릭
		Button knownBuuton1 = (Button) findViewById(R.id.KnownButton1);
		knownBuuton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(MainActivity.this, KnownActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 2.치매예방 클릭
		Button preventionButton2 = (Button) findViewById(R.id.PreventionButton2);
		preventionButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(MainActivity.this, PreventionActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 3.치매서비스로드맵 클릭
		Button RoadmapButton3 = (Button) findViewById(R.id.RoadmapButton3);
		RoadmapButton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(MainActivity.this, RoadmapActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 4.치매지원서비스 클릭
		Button SupportButton4 = (Button) findViewById(R.id.SupportButton4);
		SupportButton4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(MainActivity.this, SupportMenuActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 5.치료프로그램
		Button CureProgramButton5 = (Button) findViewById(R.id.CureProgramButton5);
		CureProgramButton5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(MainActivity.this, CureprogramActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 6.우리들의 돌봄일기
		Button DiaryButton6 = (Button) findViewById(R.id.DiaryButton6);
		DiaryButton6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(MainActivity.this, DiaryMenuActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 7.돌봄사전 클릭
		Button DictionaryButton7 = (Button) findViewById(R.id.DictionaryButton7);
		DictionaryButton7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(MainActivity.this, DictionaryMenuActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		// 8.나의감정 살피기
		Button FeelingsButton8 = (Button) findViewById(R.id.FeelingsButton8);
		FeelingsButton8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(MainActivity.this, FeelingActivity.class);
				MainActivity.this.startActivity(registerIntent);
			}
		});

		Intent intent = getIntent();
		loginID = intent.getExtras().getString("ID");

		this.activity = this;
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
		if (id == R.id.action_main1) {
			Toast.makeText(this, "도움말 기능입니다. 메뉴를 선택하세요.", Toast.LENGTH_SHORT).show();
			return true;
		} else if (id == R.id.action_main2) {
			AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
			builder1.setMessage("" +
				"로그인 앱: 치매돌봄\n" +
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
				"치매돌봄 앱은 치매환자와 보호자를 위한 유용한 어플입니다.\n\n" +
				"안드로이드폰 기종이 너무 오래되었거나 또는 최신 안드로이드 폰에서는 사용시 제약사항이 발생 할 수 있습니다.")
				.setNegativeButton("닫기", null)
				.create()
				.show();
			return true;
		} else if (id == R.id.action_main4) {
			//앱 평가하기
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/forms/ZNWt0XMUlzAH3zIx2")));
			return true;
		} else if (id == R.id.action_alarm) {
			// 알람설정 클릭
			Intent alarmIntent = new Intent(MainActivity.this, AlarmActivity.class);
			MainActivity.this.startActivity(alarmIntent);
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
