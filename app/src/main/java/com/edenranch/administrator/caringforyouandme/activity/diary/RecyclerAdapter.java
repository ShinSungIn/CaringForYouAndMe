package com.edenranch.administrator.caringforyouandme.activity.diary;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.edenranch.administrator.caringforyouandme.LoginActivity;
import com.edenranch.administrator.caringforyouandme.R;
import com.edenranch.administrator.caringforyouandme.activity.MainActivity;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
	Context context;
	List<Item> items;
	int activity_diary_sec_item;
	String editID;

	public RecyclerAdapter(Context context, List<Item> items, int activity_diary_sec_item, String editID) {
		this.context = context;
		this.items = items;
		this.activity_diary_sec_item = activity_diary_sec_item;
		this.editID = editID;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_diary_sec_item, null);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final Item item = items.get(position);
		//Drawable drawable = ContextCompat.getDrawable(context, item.getImage());
		//holder.image.setBackground(drawable);
		holder.ID.setText(item.getID());
		holder.subject.setText(item.getSubject());
		holder.content.setText(item.getContent());
		holder.insertDT.setText(item.getinsertDT());
		holder.cardview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(context, String.valueOf(item.getSeq()), Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(context, DiarySecViewActivity.class);
				intent.putExtra("Seq", item.getSeq());
				intent.putExtra("editID", editID);
				context.startActivity(intent);

			}
		});
	}

	@Override
	public int getItemCount() {
		return this.items.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView ID;
		TextView subject;
		TextView content;
		TextView insertDT;
		CardView cardview;

		public ViewHolder(View itemView) {
			super(itemView);

			ID = (TextView) itemView.findViewById(R.id.ID);
			subject = (TextView) itemView.findViewById(R.id.Subject);
			content = (TextView) itemView.findViewById(R.id.Content);
			insertDT = (TextView) itemView.findViewById(R.id.insertDT);
			cardview = (CardView) itemView.findViewById(R.id.cardview);
		}
	}
}
