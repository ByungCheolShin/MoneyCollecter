package com.example.cm.mc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MonthActivity extends AppCompatActivity {

    private Button monthButton;
    private GridAdapter gridAdapter1;
    private GridAdapter gridAdapter2;

    private ArrayList<String>[] dateList = new ArrayList[1];
    private ArrayList<String>[] dayList = new ArrayList[12];

    private GridView gridView1;
    private GridView gridView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_layout);

        monthButton = (Button)findViewById(R.id.MonthButton);
        gridView1 = (GridView)findViewById(R.id.datelist);
        gridView2 = (GridView)findViewById(R.id.gridview);
        Intent intent = getIntent();

        final int year = intent.getExtras().getInt("to_month_year");
        final int month = intent.getExtras().getInt("to_month_month");

        monthButton.setText(year + ". " + month);

        for(int i = 0; i<12; i++)
        {
            dayList[i] = new ArrayList<String>();
        }

        dateList[0] = new ArrayList<String>();
        dateList[0].add("일");
        dateList[0].add("월");
        dateList[0].add("화");
        dateList[0].add("수");
        dateList[0].add("목");
        dateList[0].add("금");
        dateList[0].add("토");

        int month_check = 0;
        int day_check = 0;

        if(month == 1) {
            month_check = 0;
            day_check = 31;
        }
        if(month == 2) {
            month_check = 3;
            day_check = 28;
        }
        if(month == 3){
            month_check = 3;
            day_check = 31;
        }
        if(month == 4) {
            month_check = 6;
            day_check = 30;
        }
        if(month == 5) {
            month_check = 1;
            day_check = 31;
        }
        if(month == 6){
            month_check = 4;
            day_check = 30;
        }
        if(month == 7) {
            month_check = 6;
            day_check = 31;
        }
        if(month == 8) {
            month_check = 2;
            day_check = 31;
        }
        if(month == 9) {
            month_check = 5;
            day_check = 30;
        }
        if(month == 10) {
            month_check = 0;
            day_check = 31;
        }
        if(month == 11) {
            month_check = 3;
            day_check = 30;
        }
        if(month == 12) {
            month_check = 5;
            day_check = 31;
        }


        for (int i = 0; i < month_check; i++)
        {
            dayList[month-1].add("");
        }

        for (int i = 0; i < day_check; i++)
        {
            dayList[month-1].add("" + (i + 1));
        }
        gridAdapter1 = new GridAdapter(getApplicationContext(),dateList[0]);
        gridView1.setAdapter(gridAdapter1);
        gridAdapter2 = new GridAdapter(getApplicationContext(), dayList[month-1]);
        gridView2.setAdapter(gridAdapter2);

        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String click_date = dayList[month-1].get(position);
                final int send_date = Integer.parseInt(click_date);
                final int check = 1;

                Intent year_view = new Intent(getApplicationContext(), MainActivity.class);
                year_view.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                year_view.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                year_view.putExtra("datedate",send_date);
                year_view.putExtra("check", check);
                startActivity(year_view);
                finish();
            }
        });
    }

    private class GridAdapter extends BaseAdapter {

        private final List<String> list;
        private final LayoutInflater inflater;

        public GridAdapter(Context context, List<String> list) {

            this.list = list;

            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override

        public int getCount() {

            return list.size();

        }
        @Override

        public String getItem(int position) {

            return list.get(position);

        }

        @Override

        public long getItemId(int position) {

            return position;

        }

        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {

                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);

                holder = new ViewHolder();
                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));
            holder.tvItemGridView.setTextColor(Color.BLACK);
            return convertView;
        }
    }
    private class ViewHolder {

        TextView tvItemGridView;
    }
}
