package com.example.austinhua.assignment_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by AustinHua on 3/01/2018.
 */

public class ProgramDatabase {
    public static final String DB_NAME = "ProgramDatabase";
    public static final String DB_TABLE = "ProgramInfo";
    public static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE +
            " (Exercise TEXT, Day TEXT ) ";
    private ProgramDatabase.SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public ProgramDatabase(Context c){
        this.context = c;
        helper = new ProgramDatabase.SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    public ProgramDatabase openReadable(){
        helper = new ProgramDatabase.SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    // function to addRow, needs 2 inputs to store
    public boolean addRow (String exercise, String day ){
        ContentValues newProduct = new ContentValues();
        newProduct.put("Exercise", exercise);
        newProduct.put("Day", day);

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

    // retrieve rows in an arrayList
    public ArrayList<String> retrieveRows() {// return an ArrayList instead of String
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[]{"Exercise", "Day"};
        Cursor cursor = db.query(DB_TABLE, columns, null, null,
                null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            productRows.add(cursor.getString(0) + ", " + cursor.getString(1));
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
            Log.w("ProgramInfo", "Upgrading database i.e. dropping table and re-creating it");
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

}
