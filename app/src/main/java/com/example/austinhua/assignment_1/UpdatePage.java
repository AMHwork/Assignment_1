package com.example.austinhua.assignment_1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePage extends AppCompatActivity {

    private WorkoutDatabase mywManager;
    private EditText searchName;
    private EditText editSession;
    private EditText editWorkout;
    private Button update;
    private Button searchButton;
    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchName = findViewById(R.id.editTextSearch);
        searchButton = findViewById(R.id.buttonSearch);
        editWorkout = findViewById(R.id.editTextworkout);
        update = (Button) findViewById(R.id.buttonUpdate);


        // press search to search for workout type
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mywManager.searchRow(searchName.getText().toString());

            }
        });

        // press update button to update the information
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });


    }

    // function to updateWorkout
    public void UpdateData (){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkUpdate = mywManager.updateRow(editSession.getText().toString(),
                        editWorkout.getText().toString());
                if (checkUpdate == true){
                    Toast.makeText(UpdatePage.this,
                            "Session Updated ",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UpdatePage.this,
                            "Session Not Updated ",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



}
