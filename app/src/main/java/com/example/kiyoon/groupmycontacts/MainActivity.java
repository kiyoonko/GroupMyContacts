package com.example.kiyoon.groupmycontacts;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> issues = new ArrayList<String>();


    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(R.layout.activity_main); //turns xml into actual displayed on screen.
        int category = 0; //this number will select one of our (currntly) three types.
        issues.add("Drunk");
        issues.add("Danger");
        issues.add("Lost");

        final Button btn_one;
        btn_one = (Button)findViewById(R.id.firstbutton);
        btn_one.setText(issues.get(category));



        btn_one.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, secondpage.class);
                startActivity(intent);
                Log.v("myapp", "drunk");

            }
        });

        final Button btn_two;

        category=1;

        btn_two = (Button)findViewById(R.id.secondbutton);
        btn_two.setText(issues.get(1));
        btn_two.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, secondpage1.class);
                startActivity(intent);
                Log.v("myapp", "danger");
            }
        });

        final Button btn_third;
        category=2;
        btn_third = (Button)findViewById(R.id.thirdbutton);
        btn_third.setText(issues.get(2));
        btn_third.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, secondpage2.class);
                startActivity(intent);
                Log.v("myapp", "lost");
            }
        });

        final Button btn_contacts;
        btn_contacts = (Button)findViewById(R.id.update);
        btn_contacts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // go to the other page
                Intent intent = new Intent(MainActivity.this, contactUpdate.class);
                startActivity(intent);
            }

        });




    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.categories_spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner.setAdapter(new SpinnerAdapter() {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
    }




}

