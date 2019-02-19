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
import android.view.View;
import android.widget.*;
import com.example.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment1;
import com.example.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment2;
import com.example.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment3;
import com.example.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment4;
import com.example.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment5;
import com.example.administrator.caringforyouandme.SupportFragment.SupportMain1Sub1Fragment6;
import com.example.administrator.caringforyouandme.SupportFragment.SupportMain1Sub2Fragment1;
import com.example.administrator.caringforyouandme.SupportFragment.SupportMain1Sub2Fragment2;
import com.example.administrator.caringforyouandme.SupportFragment.SupportMain1Sub3Fragment1;
import com.example.administrator.caringforyouandme.database.domain.Diary;
import com.example.administrator.caringforyouandme.listview.diary.DiaryListviewAdapter;

public class DiaryFirActivity extends AppCompatActivity {
	private Toolbar toolbar;

	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_fir);

		setToolbar();

		context = this;

	}

	private void setToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar 뒤로 버튼 활성화
		//toolbar.setTitle("4-1.장기요양급여 지원서비스");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void onStart() {
		super.onStart();
		_setListView();
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	@SuppressWarnings("Duplicates")
	private void _setListView(){
		DiaryListviewAdapter diaryListviewAdapter = new DiaryListviewAdapter(this);
		ListView listView_diary = findViewById(R.id.listview_diary);
		listView_diary.setAdapter(diaryListviewAdapter);
		listView_diary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			}
		});

		listView_diary.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

				return true;
			}
		});

		//TODO: sample
		Diary diary = new Diary();
		diary.setSeq(1);
		diary.setRegDt("2019.02.19");
		diary.setSubject("오늘 약을 먹는날이 었다...... 하지만 그러지 못했다.....");

		diaryListviewAdapter.addItem(diary);
		diaryListviewAdapter.addItem(diary);
	}
}
