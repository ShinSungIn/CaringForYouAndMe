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
import com.example.administrator.caringforyouandme.RoadmapFragment.RoadmapMain1Sub1Fragment1;
import com.example.administrator.caringforyouandme.RoadmapFragment.RoadmapMain1Sub1Fragment2;
import com.example.administrator.caringforyouandme.RoadmapFragment.RoadmapMain1Sub1Fragment3;

public class RoadmapActivity extends AppCompatActivity {

	private PagerAdapter mPagerAdapter1;
	private ViewPager mViewPager1;
	private Toolbar toolbar;
	Context context;
	TabHost tabHost;
	TextView textViewFirContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_roadmap);

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

		TabHost.TabSpec tabRoadmapMain1Sub1 = tabHost.newTabSpec("0").setContent(R.id.tabHostFirContent).setIndicator("치매서비스 로드맵", null);

		// 상단 탭 추가
		tabHost.addTab(tabRoadmapMain1Sub1);

		textViewFirContent = tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);

		textViewFirContent.setTextSize(20);

		// 초기셋팅
		textViewFirContent.setTextColor(getResources().getColor(R.color.colorText_White, null));
		tabHost.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorBackground_Tap, null));

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
		toolbar.setTitle("4-3.치매서비스 로드맵 알아보기");
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
		getMenuInflater().inflate(R.menu.menu_roadmap, menu);
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
			case R.id.action_roadmap1 :
				AlertDialog.Builder builder1 = new AlertDialog.Builder(RoadmapActivity.this);
				builder1.setMessage("" +
					"치매진단 이후 환자와 보호자들은 혼란스럽고, 현재 상태나 질환의 경과에 대해 궁금하며, 실제로 치매 진단 후 도움이 되는 치매 지원 서비스에 대한 요구도가 높습니다.\n" +
					"\n" +
					"이 서비스는 환자의 나이와 거주형태, 소득수준, 치매 진단 여부 등을 입력하며, 이용 가능한 서비스 종류와 각각의 신청방법, 담당센터와 전화번호를 알려줍니다.\n" +
					"\n" +
					"이미 장기요양등급은 받은 환자들에게도 각 등급별 이용 가능한 서비스 안내 해주고, 아직 치매를 진단 받지 않는 환자들에게도 치매예방운동법 등을 안내합니다.\n" +
					"\n" +
					"환자 및 보호자들이 효율적으로 서비스 이용을 가능케 하고 실제 임상 진료에서 이와 같은 정보를 제공하는 데 많은 도움이 될 것입니다.\n")
					.setNegativeButton("닫기", null)
					.create()
					.show();
				return true;
			case R.id.action_roadmap2 :
				AlertDialog.Builder builder2 = new AlertDialog.Builder(RoadmapActivity.this);
				builder2.setMessage("초기 경도치매환자가족들은 치매 진단받는 것, 치매특별등급제도나 장기요양서비스, 치매지원센터 등 사회적 지원방법에 대해서 잘 알지 못하며 지인을 통해서 듣는 경우가 많고 정확하게 알고 싶어합니다.")
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
						RoadmapMain1Sub1Fragment1 tab1 = new RoadmapMain1Sub1Fragment1();
						return tab1;
					case 1:
						RoadmapMain1Sub1Fragment2 tab2 = new RoadmapMain1Sub1Fragment2();
						return tab2;
					case 2:
						RoadmapMain1Sub1Fragment3 tab3 = new RoadmapMain1Sub1Fragment3();
						return tab3;
					default:
						return null;
				}
			} else if (currentTab == 1) {
				return null;
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
