package com.example.myultra.servicecentre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by My ultra on 7/30/2015.
 */
public class SamsungDatabase {


    public SamsungDatabase(Context c) {



        try

        {
            InputStream is = c.getAssets().open("p.db");
            c.openOrCreateDatabase("p.db", SQLiteDatabase.OPEN_READONLY, null);
            OutputStream os = new FileOutputStream(c.getDatabasePath("p.db"));

            byte[] buffer = new byte[1024];
            while (is.read(buffer) > 0) {
                os.write(buffer);
            }

            os.flush();
            os.close();
            is.close();
        }

        catch (Exception e) {
            // TODO: handle exception
        }

        DataHelper d = new DataHelper(c);
        d.onInsert("Samsung");
        d.onInsert("Micromax");
        d.onInsert("Nokia");
        d.onInsert("Lg");
        d.onInsert("Acer");


    }



}
