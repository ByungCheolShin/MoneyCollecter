package com.example.cm.mc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;

/**
 * Created by sbc01 on 2017-03-15.
 */

//데이터베이스 핸들러 구현

public class MCDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MCData.db";
    private static final String TABLE_PRODUCTS = "MCData";

    public static final String COLUMN_DATE = "_date";                                               //데이터베이스에 들어갈 데이터의 종류
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATA = "_data";
    public static final String COLUMN_DETAILDATA = "_detailData";
    public static final String COLUMN_CATEGORY = "_category";
    public static final String COLUMN_COST = "_cost";

    public MCDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int oldversion)
    {
        super(context, DATABASE_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_DATA_TABLE = "CREATE TABLE " + TABLE_PRODUCTS
                + "(" + COLUMN_DATE + " TEXT,"
                + COLUMN_ID + " INTEGER,"
                + COLUMN_DATA + " TEXT,"
                + COLUMN_DETAILDATA + " TEXT,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_COST + " INTEGER)";

        db.execSQL(CREATE_DATA_TABLE);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void InsertFirst(InputData _input, TextListAdapter adapter)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATE, _input.getDate());
        values.put(COLUMN_ID, _input.getID());
        values.put(COLUMN_DATA, _input.getData());
        values.put(COLUMN_DETAILDATA, _input.getDetailData());
        values.put(COLUMN_COST, _input.getCategory());
        values.put(COLUMN_DATE, _input.getCost());

        adapter.addItem(values.getAsString(COLUMN_DATE), "asdf", "asdf");

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public void FindData(int year, int month, int date, TextListAdapter adapter)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() == 0)
        {
            adapter.addItem("asdf", "asdf", "12");
        }
        else
        {
            cursor.moveToFirst();
            adapter.addItem(cursor.getString(0), Integer.toString(cursor.getColumnIndex(COLUMN_COST)), Integer.toString(cursor.getCount()));
        }
        cursor.close();
        db.close();
    }

    public void Drop()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_PRODUCTS);
        db.close();
    }
}
