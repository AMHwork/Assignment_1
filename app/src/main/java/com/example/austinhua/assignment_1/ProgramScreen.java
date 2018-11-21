package com.example.austinhua.assignment_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProgramScreen extends AppCompatActivity {

    private ProgramDatabase mypManager;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // show records of Program
        showRec();

        Button button = (Button) findViewById(R.id.buttonAdd);

        // Button to create new session
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProgramScreen.this, AddDay.class);
                startActivity(intent);

            }
        });

        // once session is selected, save data for future storing
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProgramScreen.this, ExerciseList.class);
                String data = (String) list.getAdapter().getItem(i);
                intent.putExtra("session", data);
                startActivity(intent);
            }
        });

    }

    // Display data via opening ProgramDatabase
    public boolean showRec() {
        mypManager = new ProgramDatabase(this);
        mypManager.openReadable();
        ArrayList<String> tableContent = mypManager.retrieveRows();
        list = (ListView)findViewById(R.id.programList);
        ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tableContent);
        list.setAdapter(arrayAdpt);
        mypManager.close();
        return true;
    }



}
