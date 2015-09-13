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
public class secondpage extends AppCompatActivity {
    Firebase ref;
    Firebase usrRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

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
                    if (user.getCategory() == 0) {
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

        Button textButton = (Button) findViewById(R.id.textButtonID);
        textButton.setText("Text Contacts!");

        final ListView listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, contacts);
        listView.setAdapter(adapter);

        textButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // call the method to SMS user
                finish();
            }

        });
    }
}
