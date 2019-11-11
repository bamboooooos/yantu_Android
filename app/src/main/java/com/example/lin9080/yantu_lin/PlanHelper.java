package com.example.lin9080.yantu_lin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class PlanHelper extends SQLiteOpenHelper {
    public  static final String CREATE_BOOK = "create table Plans ("
            +"id integer primary key autoincrement, "
            +"userid text, "
            +"calendar text, "
            +"plans text)";

    private Context mContext;

    public PlanHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}