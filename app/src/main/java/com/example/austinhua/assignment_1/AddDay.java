package com.example.austinhua.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddDay extends AppCompatActivity {

    ArrayAdapter<CharSequence> adapter;
    private ProgramDatabase mypManager;
    private boolean recInserted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // labels for the content screen
        final EditText workoutDayName = (EditText) findViewById(R.id.labelDay);
        final Spinner workoutDay = (Spinner) findViewById(R.id.daySpinner);
        Button button = (Button) findViewById(R.id.save);

        // using listview to display each session workout
        adapter = ArrayAdapter.createFromResource(this, R.array.addDays,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workoutDay.setAdapter(adapter);


        // once button is clicked, save the session name and session day into programDatabase
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selectedDay = workoutDay.getSelectedItem().toString();
                String selectedName = workoutDayName.getText().toString();

                mypManager = new ProgramDatabase(AddDay.this);
                recInserted = mypManager.addRow(selectedName, selectedDay);
                if (recInserted) {
                    Toast.makeText(AddDay.this,
                            "Successfully added ",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddDay.this,
                            ProgramScreen.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddDay.this,
                            "not Successfully added ",Toast.LENGTH_SHORT).show();
                }

                mypManager.close();

            }
        });

    }

}
