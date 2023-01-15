package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DateConstraints extends AppCompatActivity {

    CheckBox isRelaxConstraintCheckBox, isExpensiveConstraintCheckBox, isOutsideConstraintCheckBox;
    Button findDateButton, findRandomDateButton;
    DB_Management myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_constraints);

        isRelaxConstraintCheckBox = findViewById(R.id.isRelaxConstraintCheckBox);
        isExpensiveConstraintCheckBox = findViewById(R.id.isExpensiveConstraintCheckBox);
        isOutsideConstraintCheckBox = findViewById(R.id.isOutsideConstraintCheckBox);
        findDateButton = findViewById(R.id.findDateButton);
        findRandomDateButton = findViewById(R.id.randomizeDateButton);
        myDB = new DB_Management(this);

        findDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintDateFoundAlert(findDateFromConstraints());
            }
        });

        findRandomDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomDateFoundAlert(findRandomDate());
            }
        });
    }

    private void randomDateFoundAlert(String randomDateName) {

        if (randomDateName.equals("")) {
            Toast.makeText(DateConstraints.this, "No dates available.", Toast.LENGTH_SHORT).show();
            return;
        }

        String randomDateDescription = myDB.getDateDescFromDateName(randomDateName);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(randomDateDescription)
                .setTitle(randomDateName)
                .setPositiveButton("we're ready!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO add the date to the list of dates done
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("choose another one", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        randomDateFoundAlert(findRandomDate());
                    }
                });
        builder.create();
        builder.show();
    }

    private void constraintDateFoundAlert(String constraintDateName) {

        if (constraintDateName.equals("")) {
            Toast.makeText(DateConstraints.this, "No dates available.", Toast.LENGTH_SHORT).show();
            return;
        }

        String constraintDateDescription = myDB.getDateDescFromDateName(constraintDateName);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(constraintDateDescription)
                .setTitle(constraintDateName)
                .setPositiveButton("we're ready!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO add the date to the list of dates done
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("choose another one", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        constraintDateFoundAlert(findDateFromConstraints());
                    }
                });
        builder.create();
        builder.show();
    }

    public String findDateFromConstraints() {
        List<String> allDateIDs = myDB.getAllDateIDs();
        List<String> allDateNames = new ArrayList<>();
        List<String> allFoundDateNames = new ArrayList<>();

        for (String dateID : allDateIDs) {
            allDateNames.add(myDB.getDateNameFromDateID(dateID));
        }

        for (String dateName : allDateNames) {
            if (isRelaxConstraint(dateName) && isExpensiveConstraint(dateName) && isOutsideConstraint(dateName)) {
                allFoundDateNames.add(dateName);
            }
        }

        if (allFoundDateNames.size() == 0) {
            return "";
        }

        Random r = new Random();

        return allFoundDateNames.get(r.nextInt(allFoundDateNames.size()));
    }

    private boolean isRelaxConstraint(String dateName) {
        boolean isRelaxDateConstraint = myDB.getIsRelaxFromDateName(dateName);
        boolean isRelaxState = isRelaxConstraintCheckBox.isChecked();

        return ((!isRelaxState && !isRelaxDateConstraint) || (isRelaxState && isRelaxDateConstraint));
    }

    private boolean isExpensiveConstraint(String dateName) {
        boolean isExpensiveDateConstraint = myDB.getIsExpensiveFromDateName(dateName);
        boolean isExpensiveState = isExpensiveConstraintCheckBox.isChecked();

        return ((!isExpensiveState && !isExpensiveDateConstraint) || (isExpensiveState && isExpensiveDateConstraint));
    }

    private boolean isOutsideConstraint(String dateName) {
        boolean isOutsideDateConstraint = myDB.getIsOutsideFromDateName(dateName);
        boolean isOutsideState = isOutsideConstraintCheckBox.isChecked();

        return ((!isOutsideState && !isOutsideDateConstraint) || (isOutsideState && isOutsideDateConstraint));
    }

    public String findRandomDate() {
        List<String> allDateIDs = myDB.getAllDateIDs();
        List<String> allFoundDateNames = new ArrayList<>();

        for (String dateID : allDateIDs) {
            allFoundDateNames.add(myDB.getDateNameFromDateID(dateID));
        }

        if (allFoundDateNames.size() == 0) {
            return "";
        }

        Random r = new Random();

        return allFoundDateNames.get(r.nextInt(allFoundDateNames.size()));
    }
}