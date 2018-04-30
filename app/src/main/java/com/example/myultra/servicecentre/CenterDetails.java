package com.example.myultra.servicecentre;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


public class CenterDetails extends ActionBarActivity {


    TextView t2;
    Button b;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_details);
        t2=(TextView) findViewById(R.id.textView2);
        b=(Button)findViewById(R.id.button1);
        show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_center_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.item1)
        {
			/*Intent i = new Intent(Intent.ACTION_SEND);
			i.putExtra(Intent.EXTRA_TEXT,t2.getText().toString()+t1.getText().toString()+t3.getText().toString()+t4.getText().toString());
			i.setType("text/plain");
			startActivity(i);*/
        }
        if(item.getItemId()==R.id.item2)
        {
			/*Intent i = new Intent(Intent.ACTION_DIAL);
			i.setData(Uri.parse("tel:"+t3.getText().toString()));
			startActivity(i);*/
        }
        return super.onOptionsItemSelected(item);
    }

    public void show()
    {
        Intent i = getIntent();
        String name= i.getStringExtra("name");

        SQLiteDatabase sd = openOrCreateDatabase("p.db", SQLiteDatabase.OPEN_READONLY, null);
        Cursor c = sd.query("MyTable",null,null,null,null,null,null);
        if(c.moveToFirst())
        {
            while(!c.isAfterLast())
            {
                if(c.getString(c.getColumnIndex("servicecenter")).equalsIgnoreCase(name))
                {

                    t2.setText(c.getString(c.getColumnIndex("servicecenter")));
                    //t1.setText(c.getString(3));
                    //t3.setText(c.getString(4));
                    //t4.setText(c.getString(5));

                }
                c.moveToNext();
            }
        }

    }

    public void locate(View v)
    {

        String address=t2.getText().toString();
        address = address.replace(" ", "+");
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
        startActivity(i);
    }
}
