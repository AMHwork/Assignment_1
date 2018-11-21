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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrainingPage extends AppCompatActivity {

    private WorkoutDatabase mywManager;
    private ProgramDatabase mypManager;
    private boolean recInserted;
    private ListView list;
    private ArrayList<String> exercise = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // storing array content using intent
        final ArrayList<String> exercises =
                (ArrayList<String>) getIntent().getSerializableExtra("addedList");


        // storing session name using intent
        final String session = getIntent().getExtras().getString("session");
        final String sessionName = convertStringFormat(session);

        // using an listview to show each session
        list = findViewById(R.id.addedExercises);
        final ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, exercises);
        list.setAdapter(arrayAdpt);

        // convert listView Data into String
        String[] exerciseArray = new String[exercises.size()];
        exerciseArray = exercises.toArray(exerciseArray);
        final String exerciseData = convertArrayToString(exerciseArray);

        Button save = (Button) findViewById(R.id.buttonE);
        Button add = (Button) findViewById(R.id.buttonextra);

        // once pressed , save data to workoutdatabase
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mywManager = new WorkoutDatabase(TrainingPage.this);
                recInserted = mywManager.addRow(sessionName, exerciseData);
                if (recInserted) {
                    Toast.makeText(TrainingPage.this,
                            "Successfully saved ",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TrainingPage.this, HomeScreen.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(TrainingPage.this,
                            "not Successfully saved ",Toast.LENGTH_SHORT).show();
                }

                mywManager.close();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainingPage.this, ExerciseList.class);
                startActivity(intent);
            }
        });


    }

    // function to separate the workouts with spaces
    public static String strSeparator = "\n";

    public static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0; i<array.length; i++){
            str = str+array[i];
            if (i<array.length-1){
                str = str+strSeparator;
            }
        }

        return str + "\n";
    }

    public static String convertStringFormat(String str){

        str=str+strSeparator;
        return str;
    }

}
