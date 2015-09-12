package com.example.kiyoon.groupmycontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> issues = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //turns xml into actual displayed on screen.
        issues.add("Drunk");
        issues.add("Danger");
        issues.add("Lost");
        final Button btn_one;
        btn_one = (Button)findViewById(R.id.firstbutton);
        btn_one.setText(issues.get(0));

        btn_one.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                //call the method that would send the sms.


            }
        });

        final Button btn_two;
        btn_two = (Button)findViewById(R.id.secondbutton);
        btn_two.setText(issues.get(1));
        btn_two.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //add the method that would send the sms

            }
        });

        final Button btn_third;
        btn_third = (Button)findViewById(R.id.thirdbutton);
        btn_third.setText(issues.get(2));
        btn_two.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // add the method that would send the sms.

            }
        });

        final Button btn_contacts;
        btn_contacts = (Button)findViewById(R.id.update);
        btn_two.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // go to the other page
                Intent intent = new Intent(MainActivity.this, contactUpdate.class);
                startActivity(intent);
            }
        });




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

