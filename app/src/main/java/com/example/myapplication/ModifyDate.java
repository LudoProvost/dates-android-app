package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModifyDate extends AppCompatActivity {

    Spinner spinner;
    DB_Management myDB;
    EditText dateDescriptionEditText;
    Button modifyDateButton, deleteDateButton;
    CheckBox isRelaxCheckBox, isExpensiveCheckBox, isOutsideCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_a_date);

        dateDescriptionEditText = findViewById(R.id.dateDescriptionEditText2);
        isRelaxCheckBox = findViewById(R.id.isRelaxCheckBox2);
        isExpensiveCheckBox = findViewById(R.id.isExpensiveCheckBox2);
        modifyDateButton = findViewById(R.id.modifyDateButton);
        deleteDateButton = findViewById(R.id.deleteDateButton);
        isOutsideCheckBox = findViewById(R.id.isOutsideCheckBox2);
        spinner = findViewById(R.id.dateSpinner);
        myDB = new DB_Management(this);

        loadSpinnerData();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String selectedDateName = spinner.getSelectedItem().toString();

                isRelaxCheckBox.setChecked(myDB.getIsRelaxFromDateName(selectedDateName));
                isExpensiveCheckBox.setChecked(myDB.getIsExpensiveFromDateName(selectedDateName));
                isOutsideCheckBox.setChecked(myDB.getIsOutsideFromDateName(selectedDateName));

                if (spinner.getSelectedItem().toString().length() == 0) {
                    dateDescriptionEditText.setText("");
                    return;
                }

                dateDescriptionEditText.setText(myDB.getDateDescFromDateName(selectedDateName));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        modifyDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myDB.updateDate(spinner.getSelectedItem().toString(),
                        dateDescriptionEditText.getText().toString(),
                        (isRelaxCheckBox.isChecked() ? 1 : 0),
                        (isExpensiveCheckBox.isChecked() ? 1 : 0),
                        (isOutsideCheckBox.isChecked() ? 1 : 0))){
                    Toast.makeText(ModifyDate.this, "Date modified.", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        deleteDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areYouSureAlert();
            }
        });

    }

    /**
     * makes an alert pop on the screen to prompt the user
     */
    private void areYouSureAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?")
                .setTitle("You are about to delete this date idea.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myDB.deleteDate(spinner.getSelectedItem().toString());
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                });
        builder.create();
        builder.show();
    }

    /**
     * loads spinner data from the SQL database
     */
    private void loadSpinnerData() {
        List<String> allDateIDs = myDB.getAllDateIDs();
        List<String> dates = new ArrayList<>();
        int c = 1;

        dates.add(0,"");

        for (String dateID : allDateIDs) {
            dates.add(myDB.getDateNameFromDateID(dateID));
            c++;
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, dates);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
}