package com.edenranch.administrator.caringforyouandme.DictionaryFragment;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.edenranch.administrator.caringforyouandme.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the {@link DictionaryMain3Fragment9.OnFragmentInteractionListener} interface to handle interaction events. Use the {@link DictionaryMain3Fragment9#newInstance} factory method to create an instance of this fragment.
 */
public class DictionaryMain3Fragment9 extends Fragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;
	TextToSpeech tts;

	private OnFragmentInteractionListener mListener;

	public DictionaryMain3Fragment9() {
	}

	public static DictionaryMain3Fragment9 newInstance(String param1, String param2) {
		DictionaryMain3Fragment9 fragment = new DictionaryMain3Fragment9();
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
		View view = inflater.inflate(R.layout.fragment_dictionary_main3_sub9, container, false);

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
		Button speakButton = (Button) view.findViewById(R.id.dic_speak15);
		speakButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String totalSpeak = String.format(getResources().getString(R.string.text_dictionary_main3_sub9));

				tts.setPitch(1.0f); //1.5톤 올려서
				tts.setSpeechRate(1.0f); //1배속으로 읽기

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					tts.speak(totalSpeak, TextToSpeech.QUEUE_FLUSH,null,null);
				} else {
					tts.speak(totalSpeak, TextToSpeech.QUEUE_FLUSH, null);
				}
			}
		});

		Button speakStopButton = (Button) view.findViewById(R.id.dic_speakstop15);
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
