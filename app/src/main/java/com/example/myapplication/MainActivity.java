package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button giveMeADateIdeaButton, addADateButton, modifyADateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        giveMeADateIdeaButton = findViewById(R.id.giveMeADateIdeaButton);
        addADateButton = findViewById(R.id.addADateButton);
        modifyADateButton = findViewById(R.id.modifyADateButton);

        giveMeADateIdeaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DateConstraints.class);
                startActivity(intent);
            }
        });

        addADateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDate.class);
                startActivity(intent);
            }
        });

        modifyADateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ModifyDate.class);
                startActivity(intent);
            }
        });
    }



}