package com.example.shield;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DATABASE<cursor> extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="BHU.db";
    public static final String TABLE ="attendance";
    public static final String ID ="id";
    public static final String col1 = "date";
    public static final String col2="sub1";
    public static final String col3="sub2";
    public static final String col4="sub3";

    public DATABASE(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE +"(id INTEGER PRIMARY KEY AUTOINCREMENT ,date TEXT,sub1 TEXT,sub2 TEXT, sub3 TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE);
        onCreate(db);

    }

    public boolean insertData (String date, String sub1 ,String sub2 , String sub3)
    {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(col1 , date);
        CV.put(col2, sub1);
        CV.put(col3, sub2);
        CV.put(col4, sub3);
      // long result= db.insert(TABLE,null,CV);
        try {
           result = db.insert(TABLE, null, CV);
                   if(result==-1) {
           return false;
       }
      else {
           return true;
       }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

return false;

    }

    public Cursor showall()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor fillac =  db.rawQuery("select * from "+TABLE ,null);
         return fillac;



    }






    public boolean updatejk(String id,  String sub1 , String sub2, String sub3)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(ID , id);
       // CV.put(col1 , date);
        CV.put(col2, sub1);
        CV.put(col3, sub2);
        CV.put(col4, sub3);
        db.update(TABLE, CV,"ID=?", new String[]{id});
        return true;
    }

    public boolean hatmc (String limd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE,"ID=?",new String[]{limd});
      return true;
    }

}
