package com.example.myultra.servicecentre;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class LaptopActivity extends Activity implements AdapterView.OnItemClickListener,TextWatcher {


    String[] name = {"Lenovo","Samsung","Dell","Sony","Acer","Asus","Compaq","Toshiba"};
    ArrayAdapter<String> adapter;
    ListView l;
    EditText e;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name);
        l = (ListView)findViewById(android.R.id.list);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);
        e=(EditText)findViewById(R.id.editText1);
        e.addTextChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_laptop, menu);
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
        this.adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
