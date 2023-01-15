package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

public class AddDate extends AppCompatActivity {

    EditText dateNameEditText, dateDescriptionEditText;
    Button createDateButton;
    CheckBox isRelaxCheckBox, isExpensiveCheckBox, isOutsideCheckBox;
    DB_Management myDB;
    int initial = 0;
    //List<int[]> dateIDList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_a_date);

        dateNameEditText = findViewById(R.id.dateNameEditText);
        dateDescriptionEditText = findViewById(R.id.dateDescriptionEditText);
        createDateButton = findViewById(R.id.createDateButton);
        isRelaxCheckBox = findViewById(R.id.isRelaxCheckBox);
        isExpensiveCheckBox = findViewById(R.id.isExpensiveCheckBox);
        isOutsideCheckBox = findViewById(R.id.isOutsideCheckBox);

        myDB = new DB_Management(this);

        createDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dateNameEmpty() && !dateNameExists()) {

                    myDB.insertNewDate(getDateID(),
                            getDateName(),
                            getDateDescrption(),
                            isRelaxChecked(),
                            isExpensiveChecked(),
                            isOutsideChecked());

                    finish();
                    Toast.makeText(AddDate.this, "Date added.", Toast.LENGTH_SHORT).show();

                } else {
                    paintDateNameRed();
                }
            }
        });

    }

    public void paintDateNameRed() {
        dateNameEditText.setHintTextColor(Color.RED);
        dateNameEditText.setTextColor(Color.RED);
    }

    public boolean dateNameEmpty() {
        if (dateNameEditText.getText().toString().equals("")) {
            Toast.makeText(AddDate.this, "Please make sure to fill in the text boxes.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public boolean dateNameExists() {
        if (myDB.checkSameDateName(getDateName())) {
            Toast.makeText(AddDate.this, "Please make sure this date name is free.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    public int getDateID() {
        Random rand = new Random();
        return rand.nextInt(9999999);
    }

    public String getDateName() {
            return dateNameEditText.getText().toString();
    }

    public String getDateDescrption() {
        return dateDescriptionEditText.getText().toString();
    }

    public boolean isRelaxChecked() {
        return isRelaxCheckBox.isChecked();
    }

    public boolean isExpensiveChecked() {
        return isExpensiveCheckBox.isChecked();
    }

    public boolean isOutsideChecked() {
        return isOutsideCheckBox.isChecked();
    }
}