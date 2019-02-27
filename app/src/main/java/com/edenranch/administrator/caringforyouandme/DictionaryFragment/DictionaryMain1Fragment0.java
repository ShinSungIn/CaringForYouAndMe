package com.edenranch.administrator.caringforyouandme.DictionaryFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import com.edenranch.administrator.caringforyouandme.BitmapHelper;
import com.edenranch.administrator.caringforyouandme.PhotoViewActivity;
import com.edenranch.administrator.caringforyouandme.R;

import java.io.IOException;

/**
 * 7.돌봄사전 - 부탁말씀...
 */
public class DictionaryMain1Fragment0 extends Fragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;
	TextToSpeech tts;
	MediaPlayer mediaPlayer;

	private OnFragmentInteractionListener mListener;

	public DictionaryMain1Fragment0() {
	}

	public static DictionaryMain1Fragment0 newInstance(String param1, String param2) {
		DictionaryMain1Fragment0 fragment = new DictionaryMain1Fragment0();
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
		View view = inflater.inflate(R.layout.fragment_dictionary_main1_sub0, container, false);

		mediaPlayer = MediaPlayer.create(getContext(), R.raw.dictionary_music);

		// 재생
		Button button1 = (Button) view.findViewById(R.id.dic_mp_play);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mediaPlayer.start();
			}
		});
		// 일시정지
		Button button2 = (Button) view.findViewById(R.id.dic_mp_pause);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mediaPlayer.stop();
			}
		});
		// 정지
		Button button3 = (Button) view.findViewById(R.id.dic_mp_stop);
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mediaPlayer.pause();
			}
		});
		// 이미지 보기
		ImageButton imageButton = (ImageButton) view.findViewById(R.id.dictionary_main1_sub0_view);
		imageButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 전달할 이미지 만들기
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dictionary_main1_sub0);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		return view;
	}

	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
		mediaPlayer.stop();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}


	@Override
	public void onStop() {
		super.onStop();
		if (tts != null) {
			tts.stop();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if(tts != null) {
			tts.shutdown();
		}
		mediaPlayer.stop();
	}


	public interface OnFragmentInteractionListener {
		void onFragmentInteraction(Uri uri);
	}
}
