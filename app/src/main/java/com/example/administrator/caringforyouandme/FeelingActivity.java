package com.example.administrator.caringforyouandme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class FeelingActivity extends YouTubeBaseActivity {

	YouTubePlayerView youtubeView;
	Button button;
	YouTubePlayer.OnInitializedListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feeling);

		Log.d("youtube Test","사용가능여부:"+ YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this)); //SUCCSESS

		button = (Button) findViewById(R.id.youtubeButton);
		youtubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);
		listener = new YouTubePlayer.OnInitializedListener() {

			@Override
			public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
				youTubePlayer.loadVideo("juQzAIrpejo");
			}

			@Override
			public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

			}
		};

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				youtubeView.initialize("AIzaSyBTox94o0xd0WLSYlxwY4OI2kYT4zZKQSk", listener);
			}
		});

		// 영상 클릭해서 재생시키기
		youtubeView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				youtubeView.initialize("AIzaSyBTox94o0xd0WLSYlxwY4OI2kYT4zZKQSk", listener);
			}
		});
	}
}
