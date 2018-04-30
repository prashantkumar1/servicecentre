package com.example.myultra.servicecentre;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by My ultra on 7/30/2015.
 */
public class Cities extends ListActivity implements AdapterView.OnItemClickListener,TextWatcher {

    ListView l;
    String brand;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    //EditText e;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        l = getListView();
        Intent in = getIntent();
        brand = in.getStringExtra("brand");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        setListAdapter(adapter);
        show();
        l.setOnItemClickListener(this);
        //e=(EditText)findViewById(R.id.editText1);
        //e.addTextChangedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i = new Intent(this, CentreNames.class);
        i.putExtra("brand", brand);
        i.putExtra("city", parent.getItemAtPosition(position).toString());
        startActivity(i);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        this.adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    public void show()

    {
        SQLiteDatabase sd = openOrCreateDatabase("p.db", SQLiteDatabase.OPEN_READONLY, null);

        Cursor c = sd.rawQuery("SELECT DISTINCT CITY FROM MYTABLE " +
                "WHERE product like '%" + brand + "_mobile%' or" +
                " product like '%" + brand + "__' or " +
                "product like '%" + brand + "_smartphones%' or" +
                " product like '%" + brand + "_smart phone'", null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {

                list.add(c.getString(c.getColumnIndex("city")));
                adapter.notifyDataSetChanged();


                c.moveToNext();
            }


        }
    }
}
