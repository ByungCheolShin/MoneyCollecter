package com.example.cm.mc;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CM on 2017-02-10.
 */

public class TextListAdapter extends BaseAdapter{
    private Context mContext;

    private List<TextItem> mItems = new ArrayList<TextItem>();

    public TextListAdapter(Context context)
    {
        mContext = context;
    }
    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }

    public int getCount()
    {
        return mItems.size();
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        IconTextView itemView;
        if(convertView == null)
        {
            itemView = new IconTextView(mContext, mItems.get(position));
        }
        else
        {
            itemView = (IconTextView) convertView;
        }

        itemView.setText(0, mItems.get(position).getData(0));
        itemView.setText(1, mItems.get(position).getData(1));
        itemView.setText(2, mItems.get(position).getData(2));

        return itemView;

    }

    public void addItem(String title, String event, String price) {

        TextItem item = new TextItem(title, event, price);

        item.setData1(title);
        item.setData2(event);
        item.setData3(price);

        mItems.add(item);
    }
}
