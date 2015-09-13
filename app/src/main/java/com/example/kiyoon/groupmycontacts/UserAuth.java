package com.example.kiyoon.groupmycontacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;
import java.util.jar.Attributes;

/**
 * Created by mzwee on 9/12/15.
 */
public class UserAuth extends AppCompatActivity{
    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_auth);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button login = (Button) findViewById(R.id.login);
        final TextView forgot_password = (TextView) findViewById(R.id.forgot_password);
        final TextView register = (TextView) findViewById(R.id.register);
        ref = new Firebase("https://groupmycontacts.firebaseio.com");

        //Login function
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.authWithPassword(email.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Log.e("User", "Login failed: " + firebaseError.getMessage());
                    }
                });
            }
        });

        //Register function
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.createUser(email.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        System.out.println("Successfully created user account with uid: " + stringObjectMap.get("uid"));
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Log.e("User", "Registration failed: " + firebaseError.getMessage());
                    }
                });
            }
        });

        //Forgot password function
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.resetPassword(email.getText().toString(), new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {

                    }
                });
            }
        });
    }
}
