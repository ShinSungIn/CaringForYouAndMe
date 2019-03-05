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
import android.widget.ImageButton;
import com.edenranch.administrator.caringforyouandme.BitmapHelper;
import com.edenranch.administrator.caringforyouandme.PhotoViewActivity;
import com.edenranch.administrator.caringforyouandme.R;

/**
 * 치매안심센터 - 가족지원
 */
public class SupportMain3Sub1Fragment3 extends Fragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public SupportMain3Sub1Fragment3() {
	}

	public static SupportMain3Sub1Fragment3 newInstance(String param1, String param2) {
		SupportMain3Sub1Fragment3 fragment = new SupportMain3Sub1Fragment3();
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
		View view = inflater.inflate(R.layout.fragment_support_main3_sub1_3, container, false);

		ImageButton imageButton1 = (ImageButton) view.findViewById(R.id.support_main3_sub3_view1);
		imageButton1.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.support_main3_sub3_1);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.support_main3_sub3_view2);
		imageButton2.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.support_main3_sub3_2);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		ImageButton imageButton3 = (ImageButton) view.findViewById(R.id.support_main3_sub3_view3);
		imageButton3.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.support_main3_sub3_3);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		ImageButton imageButton4 = (ImageButton) view.findViewById(R.id.support_main3_sub3_view4);
		imageButton4.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.support_main3_sub3_4);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		ImageButton imageButton5 = (ImageButton) view.findViewById(R.id.support_main3_sub3_view5);
		imageButton5.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.support_main3_sub3_5);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		ImageButton imageButton6 = (ImageButton) view.findViewById(R.id.support_main3_sub3_view6);
		imageButton6.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.support_main3_sub3_6);
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
