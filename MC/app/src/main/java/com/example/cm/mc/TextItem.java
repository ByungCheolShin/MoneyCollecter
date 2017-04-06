package com.example.cm.mc;

/**
 * Created by CM on 2017-02-10.
 */

public class TextItem {
    private  String[] mData;

    public TextItem(String[] obj){
        mData = obj;
    }

    public TextItem(String obj1, String obj2, String obj3)
    {
        mData = new String[3];
        mData[0] = obj1;
        mData[1] = obj2;
        mData[2] = obj3;
    }

    public String[] getmData()
    {
        return mData;
    }

    public String getData(int index)
    {
        if(mData==null || index>=mData.length)
        {
            return null;
        }

        return mData[index];
    }
    public void setData1(String obj)
    {
        mData[0] = obj;
    }
    public void setData2(String obj)
    {
        mData[1] = obj;
    }
    public void setData3(String obj) { mData[2] = obj; }
}


