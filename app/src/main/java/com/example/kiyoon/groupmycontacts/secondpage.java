package com.example.kiyoon.groupmycontacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by badsha on 9/12/2015.
 */
public class secondpage extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

        Button textButton = (Button) findViewById(R.id.textButtonID);
        textButton.setText("Text Contacts!");

        textButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // call the method to SMS user
                finish();
            }

        });
    }
}
