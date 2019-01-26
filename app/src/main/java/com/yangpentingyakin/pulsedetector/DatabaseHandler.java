package com.yangpentingyakin.pulsedetector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yangpentingyakin.pulsedetector.MainActivity.input;


public class DatabaseHandler extends SQLiteOpenHelper {

        // static variable
        private static final int DATABASE_VERSION = 1;

        // Database name
        private static final String DATABASE_NAME = "PulseDetector";

        // table name
        private static final String TABLE_NAME = "PulseHistory";
        Integer totalRecord;

        // column tables
        private static final String COL_1 = "ID";
        private static final String COL_2 = "TimeStamp";
        private static final String COL_3 = "Pulse";


    public DatabaseHandler(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            SQLiteDatabase db = this.getWritableDatabase();
        }

        //Create table
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_PULSE_TABLE = ("CREATE TABLE " + TABLE_NAME +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,TimeStamp TEXT, Pulse INTEGER)");
            db.execSQL(CREATE_PULSE_TABLE);
        }

        // on Upgrade database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }


    public void addRecord(String waktu, int denyut){
        SQLiteDatabase db  = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2,waktu);
        values.put(COL_3,denyut);

        db.insert(TABLE_NAME, null, values);
    }



    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Bpm getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new Integer {
                        KEY_BPM} "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Bpm contact = new Bpm(cursor.getString(0));
        // return contact
        return contact;
    }
    // get All Record
    public List<Bpm> getAllRecord() {
        List<Bpm> contactList = new ArrayList<Bpm>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Bpm bpm = new Bpm();
                bpm.setBpm(cursor.getString(0));
                contactList.add(bpm);
            } while (cursor.moveToNext());
        }

        return contactList;
    }
}