package com.edenranch.administrator.caringforyouandme.KnownFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import com.edenranch.administrator.caringforyouandme.R;
import com.edenranch.administrator.caringforyouandme.YoutubePlayerActivity;

/**
 * 1.치매알기-루이체치매
 */
public class KnownKindFragment4 extends Fragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public KnownKindFragment4() {
	}

	public static KnownKindFragment4 newInstance(String param1, String param2) {
		KnownKindFragment4 fragment = new KnownKindFragment4();
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
		View view = inflater.inflate(R.layout.fragment_known_kind4, container, false);

		ImageButton imagePlayButton = (ImageButton) view.findViewById(R.id.known_kind4_playbutton);
		imagePlayButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), YoutubePlayerActivity.class);
				intent.putExtra("videoID","ckidUsbw1rE");
				startActivity(intent);
			}
		});

		Button linkButton = (Button) view.findViewById(R.id.known_kind4_linkbutton);
		linkButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nid.or.kr/info/diction_list4.aspx?gubun=0403")));
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
