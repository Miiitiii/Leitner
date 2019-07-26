package com.miti.leitner.DataBase;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class SqliteDataBase extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "leitner.db";
    public static final int DATABASE_VERSION = 1;

    public SqliteDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
