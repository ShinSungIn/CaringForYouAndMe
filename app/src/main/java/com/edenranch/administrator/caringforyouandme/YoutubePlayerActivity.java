package com.edenranch.administrator.caringforyouandme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

	private static final int RECOVERY_DIALOG_REQUEST = 1;
	private static final String YOUTUBE_KEY = "AIzaSyBTox94o0xd0WLSYlxwY4OI2kYT4zZKQSk";

	// YouTube player view
	private YouTubePlayerView youTubeView;
	String VIDEO_ID;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_youtube_player);
		/** Initializing YouTube player view **/

		Intent intent = getIntent();
		VIDEO_ID = intent.getStringExtra("videoID");
		youTubeView = findViewById(R.id.youtube_player_view);


		// Initializing video player with developer key
		youTubeView.initialize(YOUTUBE_KEY, this);
	}

	@Override
	public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
		if (errorReason.isUserRecoverableError()) {
			errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
		} else {
			String errorMessage = String.format("error_player");
			Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
		if (!wasRestored) {
			// loadVideo() will auto play video
			// Use cueVideo() method, if you don't want to play it automatically
			//String[]videocode = videourl.split("=");
			player.setPlayerStateChangeListener(playerStateChangeListener);
			player.setPlaybackEventListener(playbackEventListener);
			player.loadVideo(VIDEO_ID);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RECOVERY_DIALOG_REQUEST) {
			// Retry initialization if user performed a recovery action
			getYouTubePlayerProvider().initialize(YOUTUBE_KEY, this);
		}
	}

	private YouTubePlayer.Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.youtube_player_view);
	}

	private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {
		@Override
		public void onBuffering(boolean arg0) {
		}

		@Override
		public void onPaused() {
		}

		@Override
		public void onPlaying() {
		}

		@Override
		public void onSeekTo(int arg0) {
		}

		@Override
		public void onStopped() {
		}
	};
	private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {
		@Override
		public void onAdStarted() {
		}

		@Override
		public void onError(YouTubePlayer.ErrorReason arg0) {
		}

		@Override
		public void onLoaded(String arg0) {
		}

		@Override
		public void onLoading() {
		}

		@Override
		public void onVideoEnded() {
		}

		@Override
		public void onVideoStarted() {
		}
	};

}
