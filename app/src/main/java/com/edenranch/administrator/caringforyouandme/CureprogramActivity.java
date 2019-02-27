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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment1;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment10;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment11;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment12;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment13;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment14;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment2;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment3;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment4;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment5;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment6;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment7;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment8;
import com.edenranch.administrator.caringforyouandme.CureProgramFragment.CureMain1Fragment9;

public class CureprogramActivity extends AppCompatActivity {

	private PagerAdapter mPagerAdapter1;
	private ViewPager mViewPager1;

	private Toolbar toolbar;
	Context context;

	TabHost tabHost;
	TextView textViewFirContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cureprogram);

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

		TabHost.TabSpec tabCureMain1Sub1 = tabHost.newTabSpec("0").setContent(R.id.tabHostFirContent).setIndicator("인지향상프로그램", null);

		// 상단 탭 추가
		tabHost.addTab(tabCureMain1Sub1);

		textViewFirContent = tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);

		// 초기셋팅
		textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
		textViewFirContent.setTextSize(20);

		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				int index = Integer.parseInt(tabId);
				switch (index) {
					case 0:
						textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));

						break;
				}

			}
		});
	}

	private void setToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		//toolbar.setTitle("2.치매예방");
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
		getMenuInflater().inflate(R.menu.menu_curemenu, menu);
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
			case R.id.action_cure1 :
				AlertDialog.Builder builder1 = new AlertDialog.Builder(CureprogramActivity.this);
				builder1.setMessage("" +
					" 전문의 상담이나 교육이 세심하게 이루어지지 않는 부분입니다.\n\n" +
					"치매환자가족들은 다양한 각자의 상황에 맞는 프로그램을 선택하도록 합니다.\n\n" +
					"계획적으로 프로그램을 실행함으로써 집에서도 활용가능 하며, 자신감을 가지고 돌봄에 적극적으로 참여할 수 있도록 유도할 것입니다.")
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
						CureMain1Fragment1 tab1 = new CureMain1Fragment1();
						return tab1;
					case 1:
						CureMain1Fragment2 tab2 = new CureMain1Fragment2();
						return tab2;
					case 2:
						CureMain1Fragment3 tab3 = new CureMain1Fragment3();
						return tab3;
					case 3:
						CureMain1Fragment4 tab4 = new CureMain1Fragment4();
						return tab4;
					case 4:
						CureMain1Fragment5 tab5 = new CureMain1Fragment5();
						return tab5;
					case 5:
						CureMain1Fragment6 tab6 = new CureMain1Fragment6();
						return tab6;
					case 6:
						CureMain1Fragment7 tab7 = new CureMain1Fragment7();
						return tab7;
					case 7:
						CureMain1Fragment8 tab8 = new CureMain1Fragment8();
						return tab8;
					case 8:
						CureMain1Fragment9 tab9 = new CureMain1Fragment9();
						return tab9;
					case 9:
						CureMain1Fragment10 tab10 = new CureMain1Fragment10();
						return tab10;
					case 10:
						CureMain1Fragment11 tab11 = new CureMain1Fragment11();
						return tab11;
					case 11:
						CureMain1Fragment12 tab12 = new CureMain1Fragment12();
						return tab12;
					case 12:
						CureMain1Fragment13 tab13 = new CureMain1Fragment13();
						return tab13;
					case 13:
						CureMain1Fragment14 tab14 = new CureMain1Fragment14();
						return tab14;
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
