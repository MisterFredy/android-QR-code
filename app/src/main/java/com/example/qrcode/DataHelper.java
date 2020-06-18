package com.example.qrcode;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DB_name = "machine.db";
    private static final  int DB_version = 1;

    public DataHelper(Context context){
        super(context,DB_name,null,DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Machine(Id integer primary key autoincrement not null,Name text,Type text,QR_code_number integer,Last_maintenance_date date)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
