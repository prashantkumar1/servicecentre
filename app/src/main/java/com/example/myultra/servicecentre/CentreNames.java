package com.example.myultra.servicecentre;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by My ultra on 7/30/2015.
 */
public class CentreNames extends ListActivity implements AdapterView.OnItemClickListener {

    ListView l;
    String brand,city;
//SQLiteDatabase s;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevice_center);

        l = getListView();
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        setListAdapter(adapter);
        Intent i = getIntent();
        brand=i.getStringExtra("brand");
        city= i.getStringExtra("city");
        l.setOnItemClickListener(this);
        show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sevice_center, menu);
        return true;
    }
    public void show()
    {
        SQLiteDatabase sd = openOrCreateDatabase("p.db", SQLiteDatabase.OPEN_READONLY, null);
        Cursor c = sd.rawQuery("SELECT servicecenter FROM MYTABLE WHERE product like '%"+brand+"_mobile%' or" +
                " product like '%"+brand+"__' or " +
                "product like '%"+brand+"_smartphones%' or" +
                " product like '%"+brand+"_smart phone'"+
                "AND CITY= '"+city+"'",null);
        if(c.moveToFirst())
        {
            while(!c.isAfterLast())
            {
                list.add(c.getString(c.getColumnIndex("servicecenter")));
                adapter.notifyDataSetChanged();
                c.moveToNext();
            }
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        Intent i = new Intent(this,CenterDetails.class);
        i.putExtra("name", parent.getItemAtPosition(position).toString());
        startActivity(i);

    }
}
