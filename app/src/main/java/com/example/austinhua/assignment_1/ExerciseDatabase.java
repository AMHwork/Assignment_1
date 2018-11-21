package com.example.austinhua.assignment_1;

/**
 * Created by AustinHua on 28/12/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ExerciseDatabase {
    public static final String DB_NAME = "ExerciseDatabase";
    public static final String DB_TABLE = "ExerciseInfo";
    public static final int DB_VERSION = 2;
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE +
            " (Exercise TEXT, Sets NUMBER, Reps NUMBER, Weight NUMBER) ";
    private SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;


    public ExerciseDatabase(Context c){
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    public ExerciseDatabase openReadable(){
        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    // function to addRow, needs 4 inputs to store
    public boolean addRow (String exercise, Integer sets, Integer reps, Integer weight ){
        ContentValues newProduct = new ContentValues();
        newProduct.put("Exercise", exercise);
        newProduct.put("Sets", sets);
        newProduct.put("Reps", reps);
        newProduct.put("Weight", weight);

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

    /*

    public String retrieveRows() {
        String [] columns = new String[] {"Exercise", "Sets", "Reps", "Weight"};
        Cursor cursor = db.query(DB_TABLE, columns, null, null,
                null, null,null);
        String tablerows = "";
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            tablerows = tablerows + cursor.getString(0) + ", " + cursor.getInt(1) + ", " +
                    cursor.getInt(2) + ", " + cursor.getInt(3) + "\n";
            cursor.moveToNext();
        }

        if (cursor != null && !cursor.isClosed()){
            cursor.close();
        }

        return tablerows;
    }

    */

    // retrieve rows in an arrayList
    public ArrayList<String> retrieveRows() {// return an ArrayList instead of String
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[] {"Exercise", "Sets", "Reps", "Weight"};
        Cursor cursor = db.query(DB_TABLE, columns, null, null,
                null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            productRows.add(cursor.getString(0) + ", " + cursor.getInt(1) + "x" +
                    cursor.getInt(2) + "x" + cursor.getInt(3));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productRows;
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
            Log.w("ExerciseInfo", "Upgrading database i.e. dropping table and re-creating it");
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }
}
