package com.example.kiyoon.groupmycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        contactRef = new Firebase("https://groupmycontacts.firebaseio.com/").child("users");
        ref = new Firebase("https://groupmycontacts.firebaseio.com");
        //Update data from Firebase for favorite contacts

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthData authData = ref.getAuth();
                if (authData != null) {
                    Contact person = new Contact(Name.getText().toString(), Number.getText().toString(), Cat.getSelectedItemPosition());
                    //contactRef.push().setValue(person);
                    ref.child("users").child(authData.getUid()).child("contacts").push().setValue(person);
                    Toast.makeText(getApplicationContext(), "Contact added!",
                            Toast.LENGTH_LONG).show();

                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Redirecting to Log in Page",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(contactUpdate.this, UserAuth.class);
                    startActivity(intent);
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthData authData = ref.getAuth();
                if (authData != null) {
                    //contactRef.push().setValue(person);
                    ref.child("users").child(authData.getUid()).child("contacts").removeValue();
                    Toast.makeText(getApplicationContext(), "List Deleted",
                            Toast.LENGTH_LONG).show();

                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Something's wrong... this is deleting. ",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(contactUpdate.this, UserAuth.class);
                    startActivity(intent);
                }
            }
        });
    }
}
