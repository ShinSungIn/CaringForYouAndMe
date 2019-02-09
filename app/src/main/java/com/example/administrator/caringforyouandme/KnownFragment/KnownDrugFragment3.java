package com.example.administrator.caringforyouandme.KnownFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.administrator.caringforyouandme.BitmapHelper;
import com.example.administrator.caringforyouandme.PhotoViewActivity;
import com.example.administrator.caringforyouandme.R;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the {@link KnownDrugFragment3.OnFragmentInteractionListener} interface to handle interaction events. Use the {@link KnownDrugFragment3#newInstance} factory method to create an instance of this fragment.
 */
public class KnownDrugFragment3 extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public KnownDrugFragment3() {
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
	public static KnownDrugFragment3 newInstance(String param1, String param2) {
		KnownDrugFragment3 fragment = new KnownDrugFragment3();
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
		View view = inflater.inflate(R.layout.fragment_known_drug3, container, false);

		TextView known_drug1_info = (TextView) view.findViewById(R.id.known_drug3_info);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(1000);
		animation.setStartOffset(100);
		animation.setRepeatMode(Animation.REVERSE);
		animation.setRepeatCount(Animation.INFINITE);
		known_drug1_info.startAnimation(animation);

		ImageButton imageButton = (ImageButton) view.findViewById(R.id.known_drug3_view);
		imageButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 전달할 이미지 만들기
				Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.known_main2_sub3_drug);
				BitmapHelper.getInstance().setBitmap(sendBitmap);

				Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
				startActivity(intent);
			}
		});

		Button linkButton = (Button) view.findViewById(R.id.known_drug3_linkbutton);
		linkButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kimsonline.co.kr/drugcenter/search/totalSearch?Keyword=%EB%A9%94%EB%A7%8C%ED%8B%B4")));
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
