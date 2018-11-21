package com.example.austinhua.assignment_1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FinishPage extends AppCompatActivity {

    private WorkoutDatabase mywManager;
    private TextView workoutRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Display all saved Workouts via TextView
        workoutRec = (TextView) findViewById(R.id.resultText);
        showRec();

    }

    // Retrieve data by accessing workoutDatabase
    public boolean showRec() {
        mywManager = new WorkoutDatabase(this);
        mywManager.openReadable();
        String tableContent = mywManager.retrieveRows();
        workoutRec.setText(tableContent);
        mywManager.close();
        return true;
    }

}
