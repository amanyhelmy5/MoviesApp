package com.example.mbmbmb.moviesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class favoirte extends SQLiteOpenHelper {
    SQLiteDatabase database;
    public  favoirte(Context context)
    {
        super(context, "favoirte", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table favorite(id int primary key,idfilm int ,title text ,poster text ,overview text ,relsesdata text ,rate real  ,backeground text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table favorite ");
        onCreate(sqLiteDatabase);

    }

    public  void insert (components c)
    {
        ContentValues cv =new ContentValues();
        cv.put("idfilm",c.getid());
        cv.put("title",c.getTitle());
        cv.put("poster",c.getPoster());
        cv.put("overview",c.getOverview());
        cv.put("relsesdata",c.getDate());
        cv.put("rate",c.getRating());
        cv.put("backeground",c.getBackground());
        database=getWritableDatabase();
        database.insert("favorite", null, cv);
        database.close();

    }
    public Cursor  retrive()
    {
        database=getReadableDatabase();
        Cursor fc=database.rawQuery("select *  from favorite",null);
        if(fc.getCount()!=0)
        {
            fc.moveToFirst();
        }
        else
        {
            fc=null;
        }
        database.close();
        return  fc;
    }
    public boolean check(String title)
    {
        database=getReadableDatabase();
        Cursor ck=database.rawQuery("select * from  favorite where title =?",new  String[]{title});
        if(ck.getCount()!=0)
        {
            database.close();
            return  true;
        }
        else
        {
            return false;
        }
    }
}
