package com.example.kiyoon.groupmycontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> issues = new ArrayList<String>();

    Firebase contactRef;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Firebase set up
        Firebase.setAndroidContext(this);
        contactRef = new Firebase("https://groupmycontacts.firebaseio.com/").child("contacts");

        //Update data from Firebase for favorite contacts
        contactRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Contact user = dataSnapshot.getValue(Contact.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("Contact", "The read failed: " + firebaseError.getMessage());
            }
        });



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
            }
        });

        final Button btn_third;
        category=2;
        btn_third = (Button)findViewById(R.id.thirdbutton);
        btn_third.setText(issues.get(2));
        btn_two.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, secondpage2.class);
                startActivity(intent);
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

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.categories_spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // Function for adding contact button
    public void onAddButtonClick() {
        Contact user = new Contact("name", "phoneNum", 1);
        contactRef.push().setValue(user);
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

