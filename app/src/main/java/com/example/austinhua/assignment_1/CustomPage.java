package com.example.austinhua.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class CustomPage extends AppCompatActivity {

    private ExerciseDatabase myeManager;
    private boolean recInserted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button button = (Button) findViewById(R.id.button);
        final EditText custom = (EditText) findViewById(R.id.edit);
        final NumberPicker np1;
        final NumberPicker np2;
        final NumberPicker np3;

        np1 = (NumberPicker) findViewById(R.id.numberPicker1);
        np2 = (NumberPicker) findViewById(R.id.numberPicker2);
        np3 = (NumberPicker) findViewById(R.id.numberPicker3);

        button.setVisibility(View.INVISIBLE);

        custom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    button.setVisibility(View.INVISIBLE);
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

                    //set Values
                    np1.setWrapSelectorWheel(false);
                    np1.setMinValue(0);
                    np1.setMaxValue(50);
                    np2.setWrapSelectorWheel(false);
                    np2.setMinValue(0);
                    np2.setMaxValue(50);
                    np3.setWrapSelectorWheel(false);
                    np3.setMinValue(0);
                    np3.setMaxValue(200);

                    // Once button is clicked, save information into exerciseDatabase
                    button.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {

                            String exercise = custom.getText().toString();
                            int sets = np1.getValue();
                            int reps = np2.getValue();
                            int weight = np3.getValue();

                            myeManager = new ExerciseDatabase(CustomPage.this);
                            recInserted = myeManager.addRow(exercise, sets, reps, weight);
                            if (recInserted) {
                                Toast.makeText(CustomPage.this,
                                        "Successfully added ",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CustomPage.this,
                                        ExerciseScreen.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(CustomPage.this,
                                        "not Successfully added ",Toast.LENGTH_SHORT).show();
                            }

                            myeManager.close();
                        }
                    });
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

        });



    }

}
