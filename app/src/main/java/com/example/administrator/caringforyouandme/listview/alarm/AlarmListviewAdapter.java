package com.example.administrator.caringforyouandme.listview.alarm;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.caringforyouandme.R;
import com.example.administrator.caringforyouandme.database.domain.Alarm;
import com.example.administrator.caringforyouandme.database.query.AlarmQuery;
import com.google.common.collect.Lists;

import java.util.ArrayList;

public class AlarmListviewAdapter extends BaseAdapter {

    private Activity activity;

    ArrayList<AlarmListviewItem> arrayList = Lists.newArrayList();

    public AlarmListviewAdapter(Activity activity) {
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
        convertView = layoutInflater.inflate(R.layout.listview_alarm, parent, false);

        AlarmListviewItem alarmListviewItem = arrayList.get(position);

        TextView halfTime = convertView.findViewById(R.id.halfTime);
        halfTime.setText(alarmListviewItem.getHalfTime());

        TextView alarmTime = convertView.findViewById(R.id.alarmTime);
        alarmTime.setText(alarmListviewItem.getAlarmTime());

        TextView alarmWeek = convertView.findViewById(R.id.alarmWeek);
        alarmWeek.setText(alarmListviewItem.getAlarmWeek());

        return convertView;
    }


    @SuppressWarnings("Duplicates")
    public void addItem(Alarm alarm) {
        String timeArray[] = alarm.getTime().trim().split(":");
        int hour = Integer.parseInt(timeArray[0].trim());
        int minute = Integer.parseInt(timeArray[1].trim());

        String time;
        String zeroHour = "";
        String zeroMinute = "";

        String halfTime =  hour >= 12 ? "오후" : "오전";

        if (hour > 12 ) {
            hour = hour - 12;
        }

        if(hour < 10) {
            zeroHour = "0";
        }

        if(minute <10) {
            zeroMinute = "0";
        }

        time = zeroHour + hour + ":"+ zeroMinute + minute;

        StringBuffer stringBuffer  =  new StringBuffer();

        if ( alarm.getIsSun().equals("true")) {
            stringBuffer.append("일 ");
        }

        if ( alarm.getIsMon().equals("true")) {
            stringBuffer.append("월 ");
        }

        if ( alarm.getIsTue().equals("true")) {
            stringBuffer.append("화 ");
        }

        if ( alarm.getIsWed().equals("true")) {
            stringBuffer.append("수 ");
        }

        if ( alarm.getIsThu().equals("true")) {
            stringBuffer.append("목 ");
        }

        if ( alarm.getIsFri().equals("true")) {
            stringBuffer.append("금 ");
        }

        if ( alarm.getIsSat().equals("true")) {
            stringBuffer.append("토 ");
        }

        String alarmWeek = stringBuffer.toString();

        AlarmListviewItem alarmListviewItem = new AlarmListviewItem();
        alarmListviewItem.setSeq(alarm.getSeq());
        alarmListviewItem.setHalfTime(halfTime);
        alarmListviewItem.setAlarmTime(time);
        alarmListviewItem.setAlarmWeek(alarmWeek);

        arrayList.add(alarmListviewItem);
    }
}
