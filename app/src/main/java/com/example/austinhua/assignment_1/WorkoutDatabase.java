package com.example.austinhua.assignment_1;

/**
 * Created by AustinHua on 6/01/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class WorkoutDatabase {

    public static final String DB_NAME = "WorkoutDatabase";
    public static final String DB_TABLE = "WorkoutInfo";
    public static final int DB_VERSION = 2;
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE +
            " (Session TEXT, Workout TEXT) ";
    private WorkoutDatabase.SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public WorkoutDatabase(Context c){
        this.context = c;
        helper = new WorkoutDatabase.SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    public WorkoutDatabase openReadable(){
        helper = new WorkoutDatabase.SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    // function to addRow, needs 2 inputs to store
    public boolean addRow (String session, String workout ){
        ContentValues newProduct = new ContentValues();
        newProduct.put("Session", session);
        newProduct.put("Workout", workout);

        try {
            db.insertOrThrow(DB_TABLE, null, newProduct);
        }
        catch (Exception e) {
            Log.e("Error in insert rows ", e.toString());
            e.printStackTrace();
            return false;
        }

        db.close();
        return true;
    }

    // retrieve rows in an string, used to store as a TextView
    public String retrieveRows() {
        String [] columns = new String[] {"Session", "Workout"};
        Cursor cursor = db.query(DB_TABLE, columns, null, null,
                null, null,null);
        String tablerows = "";
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            tablerows = tablerows + cursor.getString(0) + cursor.getString(1) + "\n";
            cursor.moveToNext();
        }

        if (cursor != null && !cursor.isClosed()){
            cursor.close();
        }

        return tablerows;
    }

    // function to search information in database
    public String searchRow (String name){
        String [] columns = new String[] {"Session", "Workout"};
        Cursor cursor = db.query(DB_TABLE, columns, "Session", null,
                null, null,null);

        String tablerows = "";

        int index = cursor.getColumnIndex("Session");
        int index2 = cursor.getColumnIndex("Workout");
        String sessionType = cursor.getColumnName(index);
        String sessionExercises = cursor.getColumnName(index2);

        while(!cursor.isAfterLast()){
            tablerows = tablerows + sessionType + sessionExercises + "\n";
            cursor.moveToNext();
        }

        if (cursor != null && !cursor.isClosed()){
            cursor.close();
        }

        return tablerows;

    }

    // function to update Data once Saved
    public boolean updateRow(String session, String workout){

        ContentValues updateProduct = new ContentValues();
        updateProduct.put("Session", session);
        updateProduct.put("Workout", workout);
        db.update("WorkoutInfo", updateProduct,
                "Session = ?", new String[] {session});
        return true;

    }


    public class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper(Context c){
            super(c,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("WorkoutInfo", "Upgrading database i.e. dropping table and re-creating it");
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

}
