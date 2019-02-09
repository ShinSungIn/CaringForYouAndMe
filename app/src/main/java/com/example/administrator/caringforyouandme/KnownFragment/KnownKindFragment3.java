package com.example.administrator.caringforyouandme.KnownFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.administrator.caringforyouandme.R;
import com.example.administrator.caringforyouandme.YoutubePlayerActivity;

/**
 * 1.치매알기-혈관성 치매
 */
public class KnownKindFragment3 extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public KnownKindFragment3() {
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
	public static KnownKindFragment3 newInstance(String param1, String param2) {
		KnownKindFragment3 fragment = new KnownKindFragment3();
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
		View view = inflater.inflate(R.layout.fragment_known_kind3, container, false);

		ImageButton imagePlayButton = (ImageButton) view.findViewById(R.id.known_kind3_playbutton);
		imagePlayButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), YoutubePlayerActivity.class);
				intent.putExtra("videoID","UAbHppwAtzI");
				startActivity(intent);
			}
		});

		Button linkButton = (Button) view.findViewById(R.id.known_kind3_linkbutton);
		linkButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nid.or.kr/info/diction_list4.aspx?gubun=0402")));
			}
		});

		return view;
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
