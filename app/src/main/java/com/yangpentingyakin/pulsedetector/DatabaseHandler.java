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
        private static final String TABLE_TALL = "PulseHistory";
        Integer totalRecord;

        // column tables
        private static final Integer KEY_BPM = 0;
        public DatabaseHandler(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //Create table
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TALL +
                    KEY_BPM;
            db.execSQL(CREATE_CONTACTS_TABLE);
        }

        // on Upgrade database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TALL);
            onCreate(db);
        }


    public void addRecord(Bpm bpm){
        SQLiteDatabase db  = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BPM, input);

        db.insert(TABLE_TALL, null, values);
        db.close();
    }

    public Bpm getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TALL, new Integer {
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
        String selectQuery = "SELECT  * FROM " + TABLE_TALL;

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