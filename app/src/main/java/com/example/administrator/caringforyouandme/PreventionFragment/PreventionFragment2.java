package com.example.administrator.caringforyouandme.PreventionFragment;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;
import com.example.administrator.caringforyouandme.BitmapHelper;
import com.example.administrator.caringforyouandme.PhotoViewActivity;
import com.example.administrator.caringforyouandme.R;
import com.example.administrator.caringforyouandme.androidyoutubeplayer.player.YouTubePlayer;
import com.example.administrator.caringforyouandme.androidyoutubeplayer.player.YouTubePlayerView;
import com.example.administrator.caringforyouandme.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.example.administrator.caringforyouandme.androidyoutubeplayer.player.playerUtils.FullScreenHelper;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the {@link PreventionFragment2.OnFragmentInteractionListener} interface to handle interaction events. Use the {@link PreventionFragment2#newInstance} factory method to create an instance of this fragment.
 */
public class PreventionFragment2 extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	VideoView videoView;
	private String[] videoIds = {"6JYIGclVQdw", "LvetJ9U_tVY"};
	private YouTubePlayerView youTubePlayerView;
	private FullScreenHelper fullScreenHelper;
	private Button playNextVideoButton;
	private PhotoViewAttacher photoViewAttacher;

	public PreventionFragment2() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment PreventionFragment1.
	 */
	// TODO: Rename and change types and number of parameters
	public static PreventionFragment2 newInstance(String param1, String param2) {
		PreventionFragment2 fragment = new PreventionFragment2();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_prevention2, container, false);

		ImageButton imageButton = (ImageButton) view.findViewById(R.id.prevention2_view);
		imageButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 전달할 이미지 만들기
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.prevention2_view);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		// 비디오뷰는 영상 파일이 있을때
		/*
		getActivity().getWindow().setFormat(PixelFormat.TRANSLUCENT);
		videoView = (VideoView) view.findViewById(R.id.videoView);
		videoView.setVideoURI(Uri.parse(Uri.encode("http://tv.kakao.com/v/395147538")));
		MediaController mediaController = new MediaController(getActivity());
		mediaController.setAnchorView(videoView);
		videoView.setMediaController(mediaController);
		//videoView.setVisibility(View.VISIBLE);

		videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			public void onPrepared(MediaPlayer mp) {
				videoView.start();
			}
		});
		*/

		// 화면안에 유뷰브 넣기(미완성이여서 안쓰기로 함)
		//youTubePlayerView = view.findViewById(R.id.youtube_player_view);
		//playNextVideoButton = view.findViewById(R.id.next_video_button);
		//initYouTubePlayerView();

		return view;
		//return inflater.inflate(R.layout.fragment_prevention2, container, false);
	}

	private void initYouTubePlayerView() {
		youTubePlayerView.getPlayerUIController().showFullscreenButton(false);

		// The player will automatically release itself when the fragment is destroyed.
		// The player will automatically pause when the fragment is stopped
		// If you don't add YouTubePlayerView as a lifecycle observer, you will have to release it manually.
		getLifecycle().addObserver(youTubePlayerView);

		youTubePlayerView.initialize(youTubePlayer -> {
			youTubePlayer.addListener(new AbstractYouTubePlayerListener() {
				@Override
				public void onReady() {
					//loadVideo(youTubePlayer, videoIds[0]);
				}
			});

			setPlayNextVideoButtonClickListener(youTubePlayer);

		}, true);
	}

	private void loadVideo(YouTubePlayer youTubePlayer, String videoId) {
		if(getLifecycle().getCurrentState() == Lifecycle.State.RESUMED)
			youTubePlayer.loadVideo(videoId, 0);
		else
			youTubePlayer.cueVideo(videoId, 0);
	}

	private void setPlayNextVideoButtonClickListener(final YouTubePlayer youTubePlayer) {
		playNextVideoButton.setOnClickListener(view -> {
			String videoId = videoIds[new Random().nextInt(videoIds.length)];
			loadVideo(youTubePlayer, videoId);
		});
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this fragment to allow an interaction in this fragment to be communicated to the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href= "http://developer.android.com/training/basics/fragments/communicating.html" >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
