package com.example.cm.mc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sbc01 on 2017-03-15.
 */

//데이터베이스 핸들러 구현

public class MCDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "InputData.db";
    private static final String TABLE_PRODUCTS = "InputData";

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

    public void InsertFirst()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATE, "20160403");
        values.put(COLUMN_ID, 1);
        values.put(COLUMN_DATA, "asdf");
        values.put(COLUMN_DATE, "asdf");
        values.put(COLUMN_DATE, "50000");

        db.insert(TABLE_PRODUCTS, null, values);
    }

    public InputData findASDF()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
        InputData _data_ = new InputData();
        cursor.moveToFirst();
        _data_.setDate("asdf");
        return _data_;
    }
}
