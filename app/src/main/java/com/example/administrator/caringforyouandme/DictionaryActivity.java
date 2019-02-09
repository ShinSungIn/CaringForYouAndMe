package com.example.administrator.caringforyouandme;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain1Fragment1;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain1Fragment2;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain1Fragment3;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain2Fragment1;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain2Fragment2;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain2Fragment3;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain3Fragment1;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain3Fragment2;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain3Fragment3;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain3Fragment4;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain3Fragment5;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain3Fragment6;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain3Fragment7;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain3Fragment8;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain3Fragment9;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain4Fragment1;

public class DictionaryActivity extends AppCompatActivity {

	private PagerAdapter mPagerAdapter1;
	private PagerAdapter mPagerAdapter2;
	private PagerAdapter mPagerAdapter3;
	private PagerAdapter mPagerAdapter4;
	private ViewPager mViewPager1;
	private ViewPager mViewPager2;
	private ViewPager mViewPager3;
	private ViewPager mViewPager4;

	private LinearLayout mbasicView;
	private Toolbar toolbar;
	Context context;

	TabHost tabHost;
	TextView textViewFirContent;
	TextView textViewSecContent;
	TextView textViewThiContent;
	TextView textViewForContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary);

		setToolbar();
		setTab();

		TabLayout tabLayout1 = (TabLayout) findViewById(R.id.tabLayout1);
		TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabLayout2);
		TabLayout tabLayout3 = (TabLayout) findViewById(R.id.tabLayout3);
		TabLayout tabLayout4 = (TabLayout) findViewById(R.id.tabLayout4);
		tabLayout1.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout3.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout4.setTabGravity(TabLayout.GRAVITY_FILL);

		mPagerAdapter1 = new PagerAdapter(getSupportFragmentManager(), tabLayout1.getTabCount());
		mPagerAdapter2 = new PagerAdapter(getSupportFragmentManager(), tabLayout2.getTabCount());
		mPagerAdapter3 = new PagerAdapter(getSupportFragmentManager(), tabLayout3.getTabCount());
		mPagerAdapter4 = new PagerAdapter(getSupportFragmentManager(), tabLayout4.getTabCount());

		mViewPager1 = (ViewPager) findViewById(R.id.Container1);
		mViewPager1.setAdapter(mPagerAdapter1);
		mViewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout1));

		mViewPager2 = (ViewPager) findViewById(R.id.Container2);
		mViewPager2.setAdapter(mPagerAdapter2);
		mViewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));

		mViewPager3 = (ViewPager) findViewById(R.id.Container3);
		mViewPager3.setAdapter(mPagerAdapter3);
		mViewPager3.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout3));

		mViewPager4 = (ViewPager) findViewById(R.id.Container4);
		mViewPager4.setAdapter(mPagerAdapter4);
		mViewPager4.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout4));

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

		tabLayout4.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				mViewPager4.setCurrentItem(tab.getPosition());
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

		TabHost.TabSpec tabDictionaryMain1 = tabHost.newTabSpec("0").setContent(R.id.tabHostFirContent).setIndicator("경도치매", null);
		TabHost.TabSpec tabDictionaryMain2 = tabHost.newTabSpec("1").setContent(R.id.tabHostSecContent).setIndicator("중고도치매", null);
		TabHost.TabSpec tabDictionaryMain3 = tabHost.newTabSpec("2").setContent(R.id.tabHostThiContent).setIndicator("정신행동\n증상", null);
		TabHost.TabSpec tabDictionaryMain4 = tabHost.newTabSpec("3").setContent(R.id.tabHostForContent).setIndicator("안전관리", null);
		// 상단 탭 추가
		tabHost.addTab(tabDictionaryMain1);
		tabHost.addTab(tabDictionaryMain2);
		tabHost.addTab(tabDictionaryMain3);
		tabHost.addTab(tabDictionaryMain4);

		textViewFirContent = tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
		textViewSecContent = tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
		textViewThiContent = tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
		textViewForContent = tabHost.getTabWidget().getChildAt(3).findViewById(android.R.id.title);

		textViewThiContent.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

		//textViewSecContent.setTextSize(20);

		// 초기셋팅
		textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
		textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
		textViewThiContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
		textViewForContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));

		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
			int index = Integer.parseInt(tabId);
			switch (index) {
				case 0:
					textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
					textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewThiContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewForContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));

					break;
				case 1:
					textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
					textViewThiContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewForContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));

					break;
				case 2:
					textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewThiContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
					textViewForContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));

					break;
				case 3:
					textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewSecContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewThiContent.setTextColor(getResources().getColor(R.color.colorText_Gray, null));
					textViewForContent.setTextColor(getResources().getColor(R.color.colorText_White, null));

					break;
			}

			}

		});
	}

	private void setToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar 뒤로 버튼 활성화
		toolbar.setTitle("7.돌봄사전");
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
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		TabLayout tabLayout1 = (TabLayout) findViewById(R.id.tabLayout1);
		TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabLayout2);

		System.out.println("onOptionsItemSelected getItemId()= " + item.getItemId());

		switch (item.getItemId()) {
			case android.R.id.home :
				//toolbar의 back키 눌렀을 때 동작
				finish();
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
						DictionaryMain1Fragment1 tab1 = new DictionaryMain1Fragment1();
						return tab1;
					case 1:
						DictionaryMain1Fragment2 tab2 = new DictionaryMain1Fragment2();
						return tab2;
					case 2:
						DictionaryMain1Fragment3 tab3 = new DictionaryMain1Fragment3();
						return tab3;
					default:
						return null;
				}
			} else if (currentTab == 1) {
				switch (position) {
					case 0:
						DictionaryMain2Fragment1 tab1 = new DictionaryMain2Fragment1();
						return tab1;
					case 1:
						DictionaryMain2Fragment2 tab2 = new DictionaryMain2Fragment2();
						return tab2;
					case 2:
						DictionaryMain2Fragment3 tab3 = new DictionaryMain2Fragment3();
						return tab3;
					default:
						return null;
				}
			} else if (currentTab == 2) {
				switch (position) {
					case 0:
						DictionaryMain3Fragment1 tab1 = new DictionaryMain3Fragment1();
						return tab1;
					case 1:
						DictionaryMain3Fragment2 tab2 = new DictionaryMain3Fragment2();
						return tab2;
					case 2:
						DictionaryMain3Fragment3 tab3 = new DictionaryMain3Fragment3();
						return tab3;
					case 3:
						DictionaryMain3Fragment4 tab4 = new DictionaryMain3Fragment4();
						return tab4;
					case 4:
						DictionaryMain3Fragment5 tab5 = new DictionaryMain3Fragment5();
						return tab5;
					case 5:
						DictionaryMain3Fragment6 tab6 = new DictionaryMain3Fragment6();
						return tab6;
					case 6:
						DictionaryMain3Fragment7 tab7 = new DictionaryMain3Fragment7();
						return tab7;
					case 7:
						DictionaryMain3Fragment8 tab8 = new DictionaryMain3Fragment8();
						return tab8;
					case 8:
						DictionaryMain3Fragment9 tab9 = new DictionaryMain3Fragment9();
						return tab9;
					default:
						return null;
				}
			} else if (currentTab == 3) {
				switch (position) {
					case 0:
						DictionaryMain4Fragment1 tab1 = new DictionaryMain4Fragment1();
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
