package com.example.myultra.servicecentre;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


public class MobileActivity  extends ListActivity implements AdapterView.OnItemClickListener,TextWatcher {


    //String[] name = {"Nokia","Samsung","Motorola","Lg","Micromax","Apple","Sony"};

    //ArrayAdapter<String> adapter;
    ListView l;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,name);
        l = (ListView)findViewById(android.R.id.list);
        //l.setAdapter(adapter);
        l.setOnItemClickListener(this);
        SQLiteDatabase sd=openOrCreateDatabase("p.db", SQLiteDatabase.OPEN_READONLY, null);

        Cursor c = sd.rawQuery("SELECT DISTINCT BRAND FROM MYBRAND ",null);
        String name[] = new String[c.getCount()];
        int i=0;
        if(c.moveToFirst())
        {
            while(!c.isAfterLast())
            {
                name[i++]=c.getString(0);



                c.moveToNext();
            }
        }

        l.setAdapter(new MobileArrayAdapter(this, name));
        e=(EditText)findViewById(R.id.editText1);
        //e.addTextChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mobile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



        Intent in = new Intent(this,Cities.class);
        in.putExtra("brand", parent.getItemAtPosition(position).toString());
        startActivity(in);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
