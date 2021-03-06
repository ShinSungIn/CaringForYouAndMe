package com.edenranch.administrator.caringforyouandme.KnownFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.edenranch.administrator.caringforyouandme.BitmapHelper;
import com.edenranch.administrator.caringforyouandme.PhotoViewActivity;
import com.edenranch.administrator.caringforyouandme.R;

/**
 * 치매알기 약물1
 */
public class KnownDrugFragment1 extends Fragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;
	TextToSpeech tts;

	private OnFragmentInteractionListener mListener;

	public KnownDrugFragment1() {
	}

	public static KnownDrugFragment1 newInstance(String param1, String param2) {
		KnownDrugFragment1 fragment = new KnownDrugFragment1();
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
		View view = inflater.inflate(R.layout.fragment_known_drug1, container, false);

		TextView known_drug1_info = (TextView) view.findViewById(R.id.known_drug1_info);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(1000);
		animation.setStartOffset(100);
		animation.setRepeatMode(Animation.REVERSE);
		animation.setRepeatCount(Animation.INFINITE);
		known_drug1_info.startAnimation(animation);

		ImageButton imageButton = (ImageButton) view.findViewById(R.id.known_drug1_view);
		imageButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 전달할 이미지 만들기
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.known_main2_sub1_drug);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		Button linkButton = (Button) view.findViewById(R.id.known_drug1_linkbutton);
		linkButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kimsonline.co.kr/drugcenter/search/totalSearch?Keyword=%EC%95%84%EB%A6%AC%EC%85%89%ED%8A%B8")));
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
