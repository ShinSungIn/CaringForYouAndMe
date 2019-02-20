package com.example.administrator.caringforyouandme.activity.diary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.administrator.caringforyouandme.R;

/**
 * @author 곽우영
 * @since 2019-02-20
 */
public class DiaryFirSetActivity extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_fir_set);

	}

	@Override
	public void onDestroy(){
		super.onDestroy();

	}

	public void onCancel(View view){
		finish();
	}

	public void onSave(View view){

	}
}
