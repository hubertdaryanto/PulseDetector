package com.yangpentingyakin.pulsedetector;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Identity_pulse_data extends AppCompatActivity {

    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_pulse_data);
        db = new DatabaseHandler(this);
    }

    public void viewAll()
    {
        Cursor res = db.getAllData();
        if(res.getCount() == 0)
        {
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Time :"+ res.getString(1) + "\n");
            buffer.append("Pulse :"+ res.getString(2) + "\n");
        }
    }
}
