package com.example.cm.mc;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by CM on 2017-02-10.
 */

public class IconTextView extends LinearLayout {

    private TextView mText01;
    private TextView mText02;
    private TextView mText03;

    public IconTextView(Context context, TextItem aItem)
    {
        super(context);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.io_list,this,true);


        mText01 = (TextView) findViewById(R.id.io_item1);
        mText01.setText(aItem.getData(0));
        mText02 = (TextView) findViewById(R.id.io_item2);
        mText02.setText(aItem.getData(1));
        mText03 = (TextView) findViewById(R.id.io_item3);
        mText03.setText(aItem.getData(2));

    }
    public void setText(int index, String data)
    {
        if(index == 0)
            mText01.setText(data);
        else if (index == 1)
            mText02.setText(data);
        else if (index == 2)
            mText03.setText(data);
        else
            throw new IllegalArgumentException();
    }
}
