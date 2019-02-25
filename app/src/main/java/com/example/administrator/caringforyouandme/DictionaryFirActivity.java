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
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain1Fragment0;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain1Fragment1;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain1Fragment2;
import com.example.administrator.caringforyouandme.DictionaryFragment.DictionaryMain1Fragment3;

public class DictionaryFirActivity extends AppCompatActivity {

	private PagerAdapter mPagerAdapter1;
	private ViewPager mViewPager1;

	private LinearLayout mbasicView;
	private Toolbar toolbar;
	Context context;

	TabHost tabHost;
	TextView textViewFirContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary_fir);

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

		TabHost.TabSpec tabDictionaryMain1 = tabHost.newTabSpec("0").setContent(R.id.tabHostFirContent).setIndicator("1.초기치매", null);
		// 상단 탭 추가
		tabHost.addTab(tabDictionaryMain1);

		textViewFirContent = tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);

		textViewFirContent.setTextSize(16);

		// 초기셋팅
		textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
		//tabHost.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorBackground_Tap, null));

		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
			int index = Integer.parseInt(tabId);
			switch (index) {
				case 0:
					textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));

					tabHost.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorBackground_Tap, null));

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
		getMenuInflater().inflate(R.menu.menu_dictionary, menu);
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
			case R.id.action_dictionary1 :
				AlertDialog.Builder builder1 = new AlertDialog.Builder(DictionaryFirActivity.this);
				builder1.setMessage("" +
					"이 글을 통해\n" +
					"치매 어르신도 아름다운 추억의 단면들을 지니고 있는 한 사람임을 잊지 않도록 합니다.\n" +
					"돌봄 지식의 필요성을 깨닫고, 돌보는 사람 또한 자신감을 갖고 돌봄을 시행하며  돌봄의 질 향상을 위해 게시합니다.\n")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
			case R.id.action_dictionary2:
				AlertDialog.Builder builder2 = new AlertDialog.Builder(DictionaryFirActivity.this);
				builder2.setMessage("치매환자가족들이 환자와 함께하는 일상생활의 어려움에 대해 가장 힘들어 합니다.\n" +
					"현재 돌봄 실천사항이 적절한지에 대한 평가를 원하며, 가장 어렵고 힘들며 도움이 필요한 교육으로 식사, 배설, 목욕이나 이닦기와 같은 개인위생, 운동 영역이었습니다.\n\n" +
					"특히 경도치매환자가족들의 돌봄교육 요구도가 높습니다.\n" +
					"글의 목적상 지문이 길어 읽기 부담스러울 수 있으나 앱 디자이너와 기획자와 함께 의논하여 삽화나 그림을 첨부하고, 음성지원을 통하여 노인들도 부담 없이 이용 가능하도록 제작되었습니다.\n" +
					"정신행동증상: 치매의 특징적인 증상이며, 치매환자가족의 환자 돌봄 실천사항 중 가장 힘들어하며 도움을 필요로 하는 부분입니다.\n.")
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
						DictionaryMain1Fragment0 tab1 = new DictionaryMain1Fragment0();
						return tab1;
					case 1:
						DictionaryMain1Fragment1 tab2 = new DictionaryMain1Fragment1();
						return tab2;
					case 2:
						DictionaryMain1Fragment2 tab3 = new DictionaryMain1Fragment2();
						return tab3;
					case 3:
						DictionaryMain1Fragment3 tab4 = new DictionaryMain1Fragment3();
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
