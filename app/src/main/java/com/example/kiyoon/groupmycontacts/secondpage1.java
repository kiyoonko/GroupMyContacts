package com.example.kiyoon.groupmycontacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by badsha on 9/12/2015.
 */
public class secondpage1 extends AppCompatActivity {
    Firebase ref;
    Firebase usrRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page1);

        //Set up Firebase
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://groupmycontacts.firebaseio.com");
        final List<String> contacts = new ArrayList<String>();

        AuthData authData = ref.getAuth();
        if (authData != null) {
            usrRef = new Firebase("https://groupmycontacts.firebaseio.com/users/" + authData.getUid() + "/contacts");
            usrRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Contact user = dataSnapshot.getValue(Contact.class);
                    if (user.getCategory() == 1) {
                        contacts.add(user.getName() + " " + user.getPhoneNum());
                    }
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

                }
            });

        }

        final ListView listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, contacts);
        listView.setAdapter(adapter);

        btnSendSMS = (Button) this.findViewById(R.id.btn_SendSMS);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("SMS", "Clicked the button!!");
                String numbers[] = {"+19195970133", "+12407784399", "+17345459884"
                };
                for (String number : numbers) {
                    com.example.kiyoon.groupmycontacts.Util.sendSMS(number, "Your friend needs your help. Can you come? Respond with Yes or No. Yes will also provide his/her location", secondpage.this);
                }
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
