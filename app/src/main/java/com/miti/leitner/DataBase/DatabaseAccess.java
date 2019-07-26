package com.miti.leitner.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.miti.leitner.Model.NewWord;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess
{
    private SqliteDataBase openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess Instance;
    private Cursor cursor = null;

    private DatabaseAccess(Context context)
    {
        this.openHelper = new SqliteDataBase(context);
    }

    public static DatabaseAccess getInstance(Context context) {

        if (Instance == null)
        {
            Instance = new DatabaseAccess(context);
        }
        return Instance;
    }

    public void open()
    {
        this.db = openHelper.getReadableDatabase();
    }

    public void close()
    {
        if (db != null)
            this.db.close();
    }

    public String getMeaning(String English_word)
    {
        cursor = db.rawQuery("Select Persian from leitner where English = '"+English_word+"'",new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext())
        {
            String persian = cursor.getString(0);
            buffer.append(""+persian);
        }
        return buffer.toString();
    }

    public List<String> getAll()
    {
        cursor = db.rawQuery("Select Persian from leitner",new String[]{});
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext())
        {
            String persian = cursor.getString(0);
            list.add(persian);
        }
        return list;
    }

    public List<String> getman(int grade , int nums)
    {
        cursor = db.rawQuery("Select Persian from leitner where Grade ='" +grade + "'",new String[]{});
        List<String> list = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext())
        {
            String persian = cursor.getString(0);
            list.add(persian);
            i++;
            if (i == nums)
            {
                cursor.moveToLast();
            }
        }
        return list;
    }

    public List<NewWord> getWords(int grade , int nums)
    {
        cursor = db.rawQuery("Select English,Persian from leitner where Grade ='" +grade + "'",new String[]{});
        List<NewWord> list = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext())
        {
            String English = cursor.getString(0);
            String Persian = cursor.getString(1);
            list.add(new NewWord(Persian,English,"Beginner"));
            i++;
            if (i == nums)
            {
                cursor.moveToLast();
            }
        }
        return list;
    }

}
