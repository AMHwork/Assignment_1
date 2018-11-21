package com.example.austinhua.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    private ExerciseDatabase myeManager;
    private TextView productRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myeManager = new ExerciseDatabase(HomeScreen.this);

        ImageView exercise = (ImageView) findViewById(R.id.imageView1);
        ImageView program = (ImageView) findViewById(R.id.imageView2);
        ImageView logs = (ImageView) findViewById(R.id.imageView3);


        // pressing image will lead to exercise screen
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, ExerciseScreen.class);
                startActivity(intent);
            }
        });

        // pressing image will lead to program screen
        program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, ProgramScreen.class);
                startActivity(intent);
            }
        });

        // pressing image will lead to workout screen
        logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, WorkoutScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.list:
                showRows();
                break;
            case R.id.hide:
                hideRows();
                break;
        }
        return true;
    }

    // old function, use to show exercises in textView
    public boolean showRows() {
        myeManager = new ExerciseDatabase(this);
        myeManager.openReadable();
        //String tableContent = myeManager.retrieveRows();
        //productRec.setText(tableContent);
        myeManager.close();
        return true;
    }

    public boolean hideRows() {
        productRec.setText("");
        return true;
    }
}
