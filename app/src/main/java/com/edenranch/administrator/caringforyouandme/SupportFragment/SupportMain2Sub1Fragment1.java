package com.edenranch.administrator.caringforyouandme.SupportFragment;

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
import com.edenranch.administrator.caringforyouandme.BitmapHelper;
import com.edenranch.administrator.caringforyouandme.PhotoViewActivity;
import com.edenranch.administrator.caringforyouandme.R;

/**
 * 장기요양서비스 신청방법
 */
public class SupportMain2Sub1Fragment1 extends Fragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public SupportMain2Sub1Fragment1() {
	}

	public static SupportMain2Sub1Fragment1 newInstance(String param1, String param2) {
		SupportMain2Sub1Fragment1 fragment = new SupportMain2Sub1Fragment1();
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
		View view = inflater.inflate(R.layout.fragment_support_main2_sub1_1, container, false);
		/*
		ImageButton imageButton = (ImageButton) view.findViewById(R.id.support_main2_sub1_view1);
		imageButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 전달할 이미지 만들기
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.support_main2_sub1_1);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});
		*/
		// 링크 이미지로 수정
		ImageButton imageButton = (ImageButton) view.findViewById(R.id.support_main2_sub1_view1);
		imageButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nhis.or.kr/comWeb/wo/e/wcej00.html")));
			}
		});

		Button button = (Button) view.findViewById(R.id.support_main2_sub1_button);
		button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nhis.or.kr/comWeb/wo/e/wce02.html")));
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

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction(Uri uri);
	}
}
