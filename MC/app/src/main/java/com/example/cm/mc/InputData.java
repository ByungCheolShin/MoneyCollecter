package com.example.cm.mc;

/**
 * Created by sbc01 on 2017-03-15.
 */

public class InputData {

    private String _date;
    private int _id;
    private String _data;
    private String _detailData;
    private String _category;
    private int _cost;

    public InputData()
    {

    }

    public InputData(String date, int id,String data, String detailData, String category, int cost)
    {
        this._date = date;
        this._id = id;
        this._data = data;
        this._detailData = detailData;
        this._category = category;
        this._cost = cost;
    }

    public void setDate(String date)
    {
        this._date = date;
    }

    public String getDate()
    {
        return this._date;
    }

    public void setId(int id)
    {
        this._id = id;
    }

    public int getID()
    {
        return this._id;
    }

    public void setData(String data)
    {
        this._data = data;
    }

    public String getData()
    {
        return this._data;
    }

    public void setDetailData(String detailData)
    {
        this._detailData = detailData;
    }

    public String getDetailData()
    {
        return this._detailData;
    }

    public void setCategory(String category) { this._category = category; }

    public String getCategory() { return this._category; }

    public void setCost(int cost)
    {
        this._cost = cost;
    }

    public int getCost()
    {
        return this._cost;
    }
}
