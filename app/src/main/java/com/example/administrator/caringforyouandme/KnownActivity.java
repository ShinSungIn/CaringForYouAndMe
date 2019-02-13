package com.example.administrator.caringforyouandme;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.caringforyouandme.KnownFragment.KnownDrugFragment1;
import com.example.administrator.caringforyouandme.KnownFragment.KnownDrugFragment2;
import com.example.administrator.caringforyouandme.KnownFragment.KnownDrugFragment3;
import com.example.administrator.caringforyouandme.KnownFragment.KnownDrugFragment4;
import com.example.administrator.caringforyouandme.KnownFragment.KnownKindFragment1;
import com.example.administrator.caringforyouandme.KnownFragment.KnownKindFragment2;
import com.example.administrator.caringforyouandme.KnownFragment.KnownKindFragment3;
import com.example.administrator.caringforyouandme.KnownFragment.KnownKindFragment4;
import com.example.administrator.caringforyouandme.KnownFragment.KnownKindFragment5;
import com.example.administrator.caringforyouandme.KnownFragment.KnownKindFragment6;
import com.example.administrator.caringforyouandme.KnownFragment.KnownKindFragment7;

public class KnownActivity extends AppCompatActivity {

	private PagerAdapter mPagerAdapter1;
	private PagerAdapter mPagerAdapter2;
	private ViewPager mViewPager1;
	private ViewPager mViewPager2;

	private LinearLayout mbasicView;
	private Toolbar toolbar;
	Context context;

	TabHost tabHost;
	TextView textViewFirContent;
	TextView textViewSecContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_known);

		setToolbar();
		setTab();

		TabLayout tabLayout1 = (TabLayout) findViewById(R.id.tabLayout1);
		TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabLayout2);
		tabLayout1.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);

		mPagerAdapter1 = new PagerAdapter(getSupportFragmentManager(), tabLayout1.getTabCount());
		mPagerAdapter2 = new PagerAdapter(getSupportFragmentManager(), tabLayout2.getTabCount());

		mViewPager1 = (ViewPager) findViewById(R.id.Container1);
		mViewPager1.setAdapter(mPagerAdapter1);
		mViewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout1));

		mViewPager2 = (ViewPager) findViewById(R.id.Container2);
		mViewPager2.setAdapter(mPagerAdapter2);
		mViewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));

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

		tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				mViewPager2.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

		context = this;

		System.out.println("KnownActivity in");
	}

	private void setTab() {
		tabHost = findViewById(R.id.tabHost);
		tabHost.setup();

		TabHost.TabSpec tabHeartbeat = tabHost.newTabSpec("0").setContent(R.id.tabHostFirContent).setIndicator("치매종류 및 원인", null);
		TabHost.TabSpec tabBreath = tabHost.newTabSpec("1").setContent(R.id.tabHostSecContent).setIndicator("치매약물치료", null);
		// 상단 탭 추가
		tabHost.addTab(tabHeartbeat);
		tabHost.addTab(tabBreath);

		textViewFirContent = tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
		textViewSecContent = tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);

		textViewFirContent.setTextSize(16);
		textViewSecContent.setTextSize(16);

		// 초기셋팅
		textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
		textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
		tabHost.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorBackground_Tap, null));
		
		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				int index = Integer.parseInt(tabId);
				switch (index) {
				case 0:
					textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
					textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));

					tabHost.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorBackground_Tap, null));
					tabHost.getTabWidget().getChildAt(1).setBackgroundColor(getResources().getColor(R.color.colorGray, null));

					break;
				case 1:
					textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_White, null));

					tabHost.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorGray, null));
					tabHost.getTabWidget().getChildAt(1).setBackgroundColor(getResources().getColor(R.color.colorBackground_Tap, null));

					break;
				}

			}
		});
	}

	private void setToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar 뒤로 버튼 활성화
		toolbar.setTitle("1.치매알기");

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
		getMenuInflater().inflate(R.menu.menu_known, menu);
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
			case R.id.action_known1 :
				AlertDialog.Builder builder1 = new AlertDialog.Builder(KnownActivity.this);
				builder1.setMessage("" +
					" 치매환자가족들이 가족원에 대한 치매원인은 대략 알고 있으나 다른 치매 원인에 대해서도 관심이 있으며 알고 싶어 함\n" +
					" 링크 연결하여 원하는 치매종류와 약물을 바로 확인할 수 있도록 지원\n")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
			case R.id.action_known2 :
				AlertDialog.Builder builder2 = new AlertDialog.Builder(KnownActivity.this);
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
				switch (position) {
					case 0:
						KnownKindFragment1 tab1 = new KnownKindFragment1();
						return tab1;
					case 1:
						KnownKindFragment2 tab2 = new KnownKindFragment2();
						return tab2;
					case 2:
						KnownKindFragment3 tab3 = new KnownKindFragment3();
						return tab3;
					case 3:
						KnownKindFragment4 tab4 = new KnownKindFragment4();
						return tab4;
					case 4:
						KnownKindFragment5 tab5 = new KnownKindFragment5();
						return tab5;
					case 5:
						KnownKindFragment6 tab6 = new KnownKindFragment6();
						return tab6;
					case 6:
						KnownKindFragment7 tab7 = new KnownKindFragment7();
						return tab7;
					default:
						return null;
				}
			} else if (currentTab == 1) {
				switch (position) {
					case 0:
						KnownDrugFragment1 tab1 = new KnownDrugFragment1();
						return tab1;
					case 1:
						KnownDrugFragment2 tab2 = new KnownDrugFragment2();
						return tab2;
					case 2:
						KnownDrugFragment3 tab3 = new KnownDrugFragment3();
						return tab3;
					case 3:
						KnownDrugFragment4 tab4 = new KnownDrugFragment4();
						return tab4;
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
