package com.example.myultra.servicecentre;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by My ultra on 7/30/2015.
 */
public class DataHelper {


    static  String DATABASE_NAME="p.db";
    static  int DATABASE_VERSION=1;

    Context context;
    static SQLiteDatabase sd;
    ContentValues cv = new ContentValues();


    DataHelper(Context con)
    {
        context=con;
        OpenHelper op = new OpenHelper(context);
        sd = op.getWritableDatabase();

    }


    public void onInsert(String b)
    {
        cv.put("brand",b);
        sd.insert("MyBrand",null, cv);

    }


    public static class OpenHelper extends SQLiteOpenHelper

    {

        Context c;


        public OpenHelper(Context context) {
            //super(context, name, factory, version);
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            c=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String str= "CREATE TABLE MyBrand(brand text)";
            db.execSQL(str);
            //Toast.makeText(c, "table created ",2000).show();





        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        }




    }
}
