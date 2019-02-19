package com.example.administrator.caringforyouandme.listview.diary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.caringforyouandme.R;
import com.example.administrator.caringforyouandme.database.domain.Diary;
import com.google.common.collect.Lists;

import java.util.ArrayList;

public class DiaryListviewAdapter extends BaseAdapter {

    private Activity activity;

    ArrayList<DiaryListviewItem> arrayList = Lists.newArrayList();

    public DiaryListviewAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.listview_diary, parent, false);

        DiaryListviewItem DiaryListviewItem = arrayList.get(position);

        TextView regDate = convertView.findViewById(R.id.textview_regist_date);
        regDate.setText(DiaryListviewItem.getRegDate());

        TextView subject = convertView.findViewById(R.id.textview_diary_subject);
        subject.setText(DiaryListviewItem.getSubject());

        return convertView;
    }

    @SuppressWarnings("Duplicates")
    public void addItem(Diary diary) {
        DiaryListviewItem DiaryListviewItem = new DiaryListviewItem();
        DiaryListviewItem.setSeq(diary.getSeq());
        DiaryListviewItem.setRegDate(diary.getRegDt());
        DiaryListviewItem.setSubject(diary.getSubject());
        arrayList.add(DiaryListviewItem);
    }
}
