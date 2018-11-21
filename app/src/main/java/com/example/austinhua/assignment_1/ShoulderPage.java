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
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class ShoulderPage extends AppCompatActivity {

    Spinner shoulderNames;
    ArrayAdapter<CharSequence> adapter;

    private ExerciseDatabase myeManager;
    private boolean recInserted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoulder_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button button = (Button) findViewById(R.id.button);
        final NumberPicker np1;
        final NumberPicker np2;
        final NumberPicker np3;

        np1 = (NumberPicker) findViewById(R.id.numberPicker1);
        np2 = (NumberPicker) findViewById(R.id.numberPicker2);
        np3 = (NumberPicker) findViewById(R.id.numberPicker3);

        shoulderNames = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.shoulder_workouts,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shoulderNames.setAdapter(adapter);

        shoulderNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int index = shoulderNames.getSelectedItemPosition();
                if (index == 0){
                    button.setVisibility(View.INVISIBLE);
                    // make all values zero
                    np1.setMinValue(0);
                    np1.setMaxValue(0);
                    np2.setMinValue(0);
                    np2.setMaxValue(0);
                    np3.setMinValue(0);
                    np3.setMaxValue(0);
                }
                else {
                    button.setVisibility(View.VISIBLE);
                    // reset values when clicking on new item
                    np1.setValue(0);
                    np2.setValue(0);
                    np3.setValue(0);

                    // setting values when choosing
                    np1.setWrapSelectorWheel(false);
                    np1.setMinValue(0);
                    np1.setMaxValue(50);
                    np2.setWrapSelectorWheel(false);
                    np2.setMinValue(0);
                    np2.setMaxValue(50);
                    np3.setWrapSelectorWheel(false);
                    np3.setMinValue(0);
                    np3.setMaxValue(200);

                    final String exercise = shoulderNames.getItemAtPosition(i).toString();

                    // Once button is clicked, save information into exerciseDatabase
                    button.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {

                            int sets = np1.getValue();
                            int reps = np2.getValue();
                            int weight = np3.getValue();

                            myeManager = new ExerciseDatabase(ShoulderPage.this);
                            recInserted = myeManager.addRow(exercise, sets, reps, weight);
                            if (recInserted) {
                                Toast.makeText(ShoulderPage.this,
                                        "Successfully added ",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ShoulderPage.this,
                                        ExerciseScreen.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(ShoulderPage.this,
                                        "not Successfully added ",Toast.LENGTH_SHORT).show();
                            }

                            myeManager.close();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

    }

}
