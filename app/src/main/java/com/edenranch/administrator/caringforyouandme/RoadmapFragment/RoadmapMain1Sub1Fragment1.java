package com.edenranch.administrator.caringforyouandme.RoadmapFragment;

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
 * 3.치매서비스 로드맵 - 치매정보내비게이션
 */
public class RoadmapMain1Sub1Fragment1 extends Fragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public RoadmapMain1Sub1Fragment1() {
	}

	public static RoadmapMain1Sub1Fragment1 newInstance(String param1, String param2) {
		RoadmapMain1Sub1Fragment1 fragment = new RoadmapMain1Sub1Fragment1();
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
		View view = inflater.inflate(R.layout.fragment_roadmap_main1_sub1, container, false);

		ImageButton linkButton = (ImageButton) view.findViewById(R.id.roadmap_main1_sub1_linkbutton);
		linkButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nid.or.kr/support/c_service.aspx")));
			}
		});

		ImageButton imageButton1 = (ImageButton) view.findViewById(R.id.roadmap_main1_sub1_button1);
		imageButton1.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.roadmap_main1_sub1_2);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.roadmap_main1_sub1_button2);
		imageButton2.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.roadmap_main1_sub1_3);
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
