package com.yangpentingyakin.pulsedetector;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import android.widget.Button;
import android.support.v4.content.ContextCompat;
import android.widget.EditText;
import android.util.Log;
import android.Manifest;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.widget.Toast;
import android.view.View;



public class SmsActivity extends AppCompatActivity {


    private static final int PERMISSION_REQUEST_CODE = 1;
    private Button sendSMS;

    public SmsActivity(MainActivity mainActivity) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final EditText phoneNumber = "081234567890" // diambil dari setting app;
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

        final EditText smsText = Nama + "Sedang dalam bahaya jantung di" + Lokasi + "Mohon segera ditindaklanjuti!. Terimakasih.";

        sendSMS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String sms = smsText.getText().toString();
                String phoneNum = phoneNumber.getText().toString();
                if(!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
                    if(checkPermission()) {

//Get the default SmsManager// 

                        SmsManager smsManager = SmsManager.getDefault();

//Send the SMS//

                        smsManager.sendTextMessage(phoneNum, null, sms, null, null);
                    }else {
                        Toast.makeText(SMSActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(SMSActivity.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(SMSActivity.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(SMSActivity.this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                    sendSMS.setEnabled(false);

                }
                break;
        }
    }
}