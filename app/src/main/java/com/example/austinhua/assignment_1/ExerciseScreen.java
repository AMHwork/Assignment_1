package com.example.austinhua.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ExerciseScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set Page as gridView and each icon leads to each workout type
        GridView gridView = (GridView) findViewById(R.id.exerciseType);
        gridView.setAdapter(new ExerciseImages(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                if (position == 0){
                    Intent intent = new Intent(ExerciseScreen.this, TricepsPage.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 1){
                    Intent intent = new Intent(ExerciseScreen.this, ChestPage.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 2){
                    Intent intent = new Intent(ExerciseScreen.this, ShoulderPage.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 3){
                    Intent intent = new Intent(ExerciseScreen.this, BicepsPage.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 4){
                    Intent intent = new Intent(ExerciseScreen.this, BackPage.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 5){
                    Intent intent = new Intent(ExerciseScreen.this, ThighPage.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 6){
                    Intent intent = new Intent(ExerciseScreen.this, CalfPage.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 7){
                    Intent intent = new Intent(ExerciseScreen.this, GlutesPage.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 8){
                    Intent intent = new Intent(ExerciseScreen.this, CustomPage.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 9){
                    Intent intent = new Intent(ExerciseScreen.this, HomeScreen.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 10){
                    Intent intent = new Intent(ExerciseScreen.this, ProgramScreen.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

                if (position == 11){
                    Intent intent = new Intent(ExerciseScreen.this, WorkoutScreen.class);
                    intent.putExtra("icons", position);
                    startActivity(intent);
                }

            }
        });

    }



}
