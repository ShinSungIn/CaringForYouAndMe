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
 * 장기요양서비스 - 재가급여 - 복지용구
 */
public class SupportMain1Sub1Fragment6 extends Fragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public SupportMain1Sub1Fragment6() {
	}

	public static SupportMain1Sub1Fragment6 newInstance(String param1, String param2) {
		SupportMain1Sub1Fragment6 fragment = new SupportMain1Sub1Fragment6();
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
		View view = inflater.inflate(R.layout.fragment_support_main1_sub1_6, container, false);

		ImageButton imageButton1 = (ImageButton) view.findViewById(R.id.support_main1_sub1_view6_1);
		imageButton1.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 전달할 이미지 만들기
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.support_main1_sub1_6_1);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.support_main1_sub1_view6_2);
		imageButton2.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 전달할 이미지 만들기
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.support_main1_sub1_6_2);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		ImageButton imageButton3 = (ImageButton) view.findViewById(R.id.support_main1_sub1_view6_3);
		imageButton3.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 전달할 이미지 만들기
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.support_main1_sub1_6_3);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		//support_main1_sub1_button1
		Button linkButton1 = (Button) view.findViewById(R.id.support_main1_sub1_button1);
		linkButton1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nhis.or.kr/comWeb/wo/e/wceo00.html")));
			}
		});

		Button linkButton2 = (Button) view.findViewById(R.id.support_main1_sub1_button2);
		linkButton2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nhis.or.kr/comWeb/wo/e/wcep00.html")));
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
