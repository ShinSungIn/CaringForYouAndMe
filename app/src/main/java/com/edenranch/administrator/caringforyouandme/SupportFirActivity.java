package com.edenranch.administrator.caringforyouandme;

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
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.edenranch.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment1;
import com.edenranch.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment2;
import com.edenranch.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment3;
import com.edenranch.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment4;
import com.edenranch.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment5;
import com.edenranch.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment6;
import com.edenranch.administrator.caringforyouandme.SupportFragment.SupportMain1Sub2Fragment1;
import com.edenranch.administrator.caringforyouandme.SupportFragment.SupportMain1Sub2Fragment2;
import com.edenranch.administrator.caringforyouandme.SupportFragment.SupportMain1Sub3Fragment1;

public class SupportFirActivity extends AppCompatActivity {

	private PagerAdapter mPagerAdapter1;
	private PagerAdapter mPagerAdapter2;
	private PagerAdapter mPagerAdapter3;
	private ViewPager mViewPager1;
	private ViewPager mViewPager2;
	private ViewPager mViewPager3;

	private LinearLayout mbasicView;
	private Toolbar toolbar;
	Context context;

	TabHost tabHost;
	TextView textViewFirContent;
	TextView textViewSecContent;
	TextView textViewThiContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_support_fir);

		setToolbar();
		setTab();

		TabLayout tabLayout1 = (TabLayout) findViewById(R.id.tabLayout1);
		TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabLayout2);
		TabLayout tabLayout3 = (TabLayout) findViewById(R.id.tabLayout3);
		tabLayout1.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout3.setTabGravity(TabLayout.GRAVITY_FILL);

		mPagerAdapter1 = new PagerAdapter(getSupportFragmentManager(), tabLayout1.getTabCount());
		mPagerAdapter2 = new PagerAdapter(getSupportFragmentManager(), tabLayout2.getTabCount());
		mPagerAdapter3 = new PagerAdapter(getSupportFragmentManager(), tabLayout3.getTabCount());

		mViewPager1 = (ViewPager) findViewById(R.id.Container1);
		mViewPager1.setAdapter(mPagerAdapter1);
		mViewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout1));

		mViewPager2 = (ViewPager) findViewById(R.id.Container2);
		mViewPager2.setAdapter(mPagerAdapter2);
		mViewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));

		mViewPager3 = (ViewPager) findViewById(R.id.Container3);
		mViewPager3.setAdapter(mPagerAdapter3);
		mViewPager3.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout3));

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

		tabLayout3.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				mViewPager3.setCurrentItem(tab.getPosition());
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

		TabHost.TabSpec tabSupportMain1Sub1 = tabHost.newTabSpec("0").setContent(R.id.tabHostFirContent).setIndicator("재가급여", null);
		TabHost.TabSpec tabSupportMain1Sub2 = tabHost.newTabSpec("1").setContent(R.id.tabHostSecContent).setIndicator("시설급여", null);
		TabHost.TabSpec tabSupportMain1Sub3 = tabHost.newTabSpec("2").setContent(R.id.tabHostThiContent).setIndicator("가족요양비", null);

		// 상단 탭 추가
		tabHost.addTab(tabSupportMain1Sub1);
		tabHost.addTab(tabSupportMain1Sub2);
		tabHost.addTab(tabSupportMain1Sub3);

		textViewFirContent = tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
		textViewSecContent = tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
		textViewThiContent = tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);

		textViewFirContent.setTextSize(16);
		textViewSecContent.setTextSize(16);
		textViewThiContent.setTextSize(16);

		// 초기셋팅
		textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
		textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
		textViewThiContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
		tabHost.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorBackground_Tap, null));

		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
			int index = Integer.parseInt(tabId);
			switch (index) {
				case 0:
					textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
					textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewThiContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));

					tabHost.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorBackground_Tap, null));
					tabHost.getTabWidget().getChildAt(1).setBackgroundColor(getResources().getColor(R.color.colorGray, null));
					tabHost.getTabWidget().getChildAt(2).setBackgroundColor(getResources().getColor(R.color.colorGray, null));
					
					break;
				case 1:
					textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
					textViewThiContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));

					tabHost.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorGray, null));
					tabHost.getTabWidget().getChildAt(1).setBackgroundColor(getResources().getColor(R.color.colorBackground_Tap, null));
					tabHost.getTabWidget().getChildAt(2).setBackgroundColor(getResources().getColor(R.color.colorGray, null));

					break;
				case 2:
					textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewThiContent.setTextColor(getResources().getColor(R.color.colorText_White, null));

					tabHost.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorGray, null));
					tabHost.getTabWidget().getChildAt(1).setBackgroundColor(getResources().getColor(R.color.colorGray, null));
					tabHost.getTabWidget().getChildAt(2).setBackgroundColor(getResources().getColor(R.color.colorBackground_Tap, null));

					break;
			}

			}
		});
	}

	private void setToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar 뒤로 버튼 활성화
		toolbar.setTitle("4-1.장기요양급여 지원서비스");
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
		getMenuInflater().inflate(R.menu.menu_supportfir, menu);
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
			case R.id.action_supportfir1 :
				AlertDialog.Builder builder1 = new AlertDialog.Builder(SupportFirActivity.this);
				builder1.setMessage("" +
					"고령이나 치매 등으로 6개월 이상 \n" +
					"다른 사람의 도움 없이는 일상생활이 어려운 어르신에게 신체활동 및 가사활동, 인지활동 지원 등의 서비스를 제공합니다.")
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
						SupportMain1Sub1Fragment1 tab1 = new SupportMain1Sub1Fragment1();
						return tab1;
					case 1:
						SupportMain1Sub1Fragment2 tab2 = new SupportMain1Sub1Fragment2();
						return tab2;
					case 2:
						SupportMain1Sub1Fragment3 tab3 = new SupportMain1Sub1Fragment3();
						return tab3;
					case 3:
						SupportMain1Sub1Fragment4 tab4 = new SupportMain1Sub1Fragment4();
						return tab4;
					case 4:
						SupportMain1Sub1Fragment5 tab5 = new SupportMain1Sub1Fragment5();
						return tab5;
					case 5:
						SupportMain1Sub1Fragment6 tab6 = new SupportMain1Sub1Fragment6();
						return tab6;
					default:
						return null;
				}
			} else if (currentTab == 1) {
				switch (position) {
					case 0:
						SupportMain1Sub2Fragment1 tab1 = new SupportMain1Sub2Fragment1();
						return tab1;
					case 1:
						SupportMain1Sub2Fragment2 tab2 = new SupportMain1Sub2Fragment2();
						return tab2;
					default:
						return null;
				}
			} else if (currentTab == 2) {
				switch (position) {
					case 0:
						SupportMain1Sub3Fragment1 tab1 = new SupportMain1Sub3Fragment1();
						return tab1;
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
