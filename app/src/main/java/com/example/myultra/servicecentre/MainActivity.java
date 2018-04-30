package com.example.myultra.servicecentre;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class MainActivity extends TabActivity {
    TabHost th;
    TabHost.TabSpec t1,t2;
    DataHelper dbHeplper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("My_PREFS", Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        boolean check = sp.getBoolean("check",true);
        if(check)
        {
            SamsungDatabase s = new SamsungDatabase(this);
            edit.putBoolean("check", false);
            edit.commit();
        }

        th=(TabHost)findViewById(android.R.id.tabhost);
        th.setup();

        t1=th.newTabSpec("tab1");
        t2=th.newTabSpec("tab2");

        Intent i1 = new Intent(this,MobileActivity.class);
        Intent i2 = new Intent(this,LaptopActivity.class);

        t1.setContent(R.id.tab1);
        t1.setIndicator("Mobiles");
        t1.setContent(i1);

        t2.setContent(R.id.tab2);
        t2.setIndicator("Laptops");
        t2.setContent(i2);


        th.addTab(t1);
        th.addTab(t2);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
