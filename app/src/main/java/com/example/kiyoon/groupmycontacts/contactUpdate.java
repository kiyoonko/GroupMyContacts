package com.example.kiyoon.groupmycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


import java.util.ArrayList;

/**
 * Created by badsha on 9/12/2015.
 */
public class contactUpdate extends AppCompatActivity {
    Firebase contactRef;
    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        final EditText Name = (EditText)findViewById(R.id.name);
        final EditText Number = (EditText)findViewById(R.id.phoneNum);
        final Spinner Cat = (Spinner)findViewById(R.id.categories_spinner);
        Button submit = (Button)findViewById(R.id.submit_area);
        Button clear = (Button)findViewById(R.id.clearlist);

        //Firebase set up
        Firebase.setAndroidContext(this);
        contactRef = new Firebase("https://groupmycontacts.firebaseio.com/").child("contacts");
        ref = new Firebase("https://groupmycontacts.firebaseio.com");
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthData authData = ref.getAuth();
                if (authData != null) {
                    Contact person = new Contact(Name.getText().toString(), Number.getText().toString(), Cat.getSelectedItemPosition());
                    //contactRef.push().setValue(person);
                    ref.child("users").child(authData.getUid()).child("contacts").push().setValue(person);
                    finish();
                } else {
                    Intent intent = new Intent(contactUpdate.this, UserAuth.class);
                    startActivity(intent);
                }

            }
        });

        /*clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                contactRef
            }
        }*/
    }




}
