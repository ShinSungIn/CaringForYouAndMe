package com.example.administrator.caringforyouandme;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.caringforyouandme.PreventionFragment.PreventionFragment1;
import com.example.administrator.caringforyouandme.PreventionFragment.PreventionFragment2;
import com.example.administrator.caringforyouandme.PreventionFragment.PreventionFragment3;

public class PreventionActivity extends AppCompatActivity {

	private PagerAdapter mPagerAdapter1;
	private ViewPager mViewPager1;

	private Toolbar toolbar;
	Context context;

	TabHost tabHost;
	TextView textViewHeartbeat;
	TextView textViewBreath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prevention);

		setToolbar();
		setTab();

		TabLayout tabLayout1 = (TabLayout) findViewById(R.id.tabLayout1);
		tabLayout1.setTabGravity(TabLayout.GRAVITY_FILL);

		mPagerAdapter1 = new PagerAdapter(getSupportFragmentManager(), tabLayout1.getTabCount());

		mViewPager1 = (ViewPager) findViewById(R.id.Container1);
		mViewPager1.setAdapter(mPagerAdapter1);
		mViewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout1));

		tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				mViewPager1.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			}
		});

		context = this;
	}

	private void setTab() {
		tabHost = findViewById(R.id.tabHost);
		tabHost.setup();

		TabHost.TabSpec tabHeartbeat = tabHost.newTabSpec("0").setContent(R.id.tabHostFirContent).setIndicator("치매예방", null);

		// 상단 탭 추가
		tabHost.addTab(tabHeartbeat);

		textViewHeartbeat = tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);

		// 초기셋팅
		textViewHeartbeat.setTextColor(getResources().getColor(R.color.colorText_White, null));
		textViewHeartbeat.setTextSize(20);

		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
			int index = Integer.parseInt(tabId);
			switch (index) {
				case 0:
					textViewHeartbeat.setTextColor(getResources().getColor(R.color.colorText_White, null));
					textViewBreath.setTextColor(getResources().getColor(R.color.colorText_Gray, null));

					//setPreventionView();
					break;
			}

			}
			/*
			private void setPreventionView() {
				System.out.println("setPreventionView in");
			}
			*/

		});
	}

	private void setToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("2.치매예방");
		// toolbar 뒤로 버튼 활성화
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		System.out.println("setToolbar in");
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
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_prevention, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case android.R.id.home :
				//toolbar의 back키 눌렀을 때 동작
				finish();
				return true;

			case R.id.action_mainAll :
				Toast.makeText(this, "도움말 기능입니다. 메뉴를 선택하세요.", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_prevention1 :
				AlertDialog.Builder builder1 = new AlertDialog.Builder(PreventionActivity.this);
				builder1.setMessage("" +
					" 치매환자가족들이 본인도 치매에 걸릴 수 있다는 두려움을 가지고 있으므로 실천사항을 제시 함으로써 예방 가능함을 강조\n")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
			case R.id.action_prevention2 :
				AlertDialog.Builder builder2 = new AlertDialog.Builder(PreventionActivity.this);
				builder2.setMessage("준비중입니다.")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
		}


		return super.onOptionsItemSelected(item);
	}

	// 메모리상 제거 되는 FragmentStatePagerAdapter 써야함, FragmentPagerAdapter 는 메모리 유지
	public class PagerAdapter extends FragmentStatePagerAdapter {
		int mNumOfTabs;

		public PagerAdapter (FragmentManager fm, int NumOfTabs) {
			super(fm);
			this.mNumOfTabs = NumOfTabs;
		}

		@Override
		public Fragment getItem(int position) {

			int currentTab = tabHost.getCurrentTab();

			if (currentTab == 0) {
				System.out.println("PagerAdapter Class=Tabhost:0 " + currentTab + " position :" + position);
				switch (position) {
					case 0:
						PreventionFragment1 tab1 = new PreventionFragment1();
						return tab1;
					case 1:
						PreventionFragment2 tab2 = new PreventionFragment2();
						return tab2;
					case 2:
						PreventionFragment3 tab3 = new PreventionFragment3();
						return tab3;
					default:
						return null;
				}
			} else {
				return null;
			}

		}

		@Override
		public int getCount() {
			return mNumOfTabs;
		}
	}
}
