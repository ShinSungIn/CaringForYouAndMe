package com.edenranch.administrator.caringforyouandme.DictionaryFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.edenranch.administrator.caringforyouandme.R;
import com.edenranch.administrator.caringforyouandme.YoutubePlayerActivity;

import java.util.Locale;

/**
 * 치매환자돌봄방법-2
 */
public class DictionaryMain1Fragment2 extends Fragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;
	TextToSpeech tts;

	private OnFragmentInteractionListener mListener;

	public DictionaryMain1Fragment2() {
	}

	public static DictionaryMain1Fragment2 newInstance(String param1, String param2) {
		DictionaryMain1Fragment2 fragment = new DictionaryMain1Fragment2();
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
		View view = inflater.inflate(R.layout.fragment_dictionary_main1_sub2, container, false);

		// 식사 영상
		ImageButton imagePlayButton1 = (ImageButton) view.findViewById(R.id.dic_main1_sub2_playbutton1);
		imagePlayButton1.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), YoutubePlayerActivity.class);
				intent.putExtra("videoID","dXjENRItrCE");
				startActivity(intent);
			}
		});
		// 배설 영상
		ImageButton imagePlayButton2 = (ImageButton) view.findViewById(R.id.dic_main1_sub2_playbutton2);
		imagePlayButton2.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), YoutubePlayerActivity.class);
				intent.putExtra("videoID","Yqpolcvg0kA");
				startActivity(intent);
			}
		});

		tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if(status == TextToSpeech.SUCCESS) {
					int result = tts.setLanguage(Locale.KOREA);
					if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
						Toast.makeText(view.getContext(), "지원하지 않는 언어입니다.", Toast.LENGTH_SHORT).show();
					} else {

					}
				} else {
				}
			}
		});
		//음성출력 버튼
		Button speakButton = (Button) view.findViewById(R.id.dic_speak2);
		speakButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String totalSpeak = String.format(getResources().getString(R.string.text_dictionary_main1_sub2));

				tts.setPitch(1.0f); //1.5톤 올려서
				tts.setSpeechRate(1.0f); //1배속으로 읽기

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					tts.speak(totalSpeak, TextToSpeech.QUEUE_FLUSH,null,null);
				} else {
					tts.speak(totalSpeak, TextToSpeech.QUEUE_FLUSH, null);
				}
			}
		});

		Button speakStopButton = (Button) view.findViewById(R.id.dic_speakstop2);
		speakStopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tts.stop();
			}
		});

		return view;
	}

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
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction(Uri uri);
	}
}
