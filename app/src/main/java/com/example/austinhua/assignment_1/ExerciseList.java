package com.example.austinhua.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExerciseList extends AppCompatActivity {

    private ExerciseDatabase myeManager;
    private ListView list;
    private ArrayList<String> selectedExercise = new ArrayList();
    private String sessionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // open ExerciseDatabase and display data via listview
        myeManager = new ExerciseDatabase(this);
        myeManager.openReadable();
        final ArrayList<String> tableContent = myeManager.retrieveRows();
        list = (ListView)findViewById(R.id.addedlists);
        final ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tableContent);
        list.setAdapter(arrayAdpt);
        myeManager.close();

        // obtain the sessionType = (sessionday,sessionName)
        sessionName = getIntent().getExtras().getString("session");

        // Once pressed, save data via tableContent
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedExercise.add(tableContent.get(i));
                arrayAdpt.notifyDataSetChanged();
                Toast.makeText(ExerciseList.this,
                        "added to List ",Toast.LENGTH_SHORT).show();

            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);

        // Must choose at least 6 exercises before going back to training page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedExercise.size() < 6){
                    Toast.makeText(ExerciseList.this,
                            "Please Choose at Least 6 Exercises ",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(ExerciseList.this, TrainingPage.class);
                    intent.putExtra("addedList", selectedExercise);
                    intent.putExtra("session", sessionName);
                    startActivity(intent);
                }

            }
        });


    }

}
