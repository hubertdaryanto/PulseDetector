package com.yangpentingyakin.pulsedetector;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.bluetooth.BluetoothProfile.STATE_CONNECTED;
import static android.bluetooth.BluetoothProfile.STATE_CONNECTING;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    BluetoothAdapter myBluetoothAdapter;
    int REQUEST_ENABLE_BT=1;
    String address = null, name=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(myBluetoothAdapter==null)
        {
            //Device does not support bluetooth
        }
        else
        {
            if(!myBluetoothAdapter.isEnabled())
            {
               Intent enableBluetoothIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
               startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BT);
            }
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==REQUEST_ENABLE_BT)
        {
            if(resultCode==RESULT_OK)
            {
                //bluetooth is enabled
            }
            else if (resultCode==RESULT_CANCELED)
            {
                //Bluetooth enabling is cancelled
            }
        }
    }

    //serial input
    bluetoothIn = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == handlerState) {
                String readMessage = (String) msg.obj;
                recDataString.append(readMessage);
                int startofLineIndex = recDataString.indexOf("#");
                int endOfLineIndex = recDataString.indexOf("\n");

                if (endOfLineIndex > 0) {
                    String dataInPrint = recDataString.substring(0, endOfLineIndex);
                    txtInput.setText("Input: "+dataInPrint);
                    if (recDataString.charAt(0) == '#')
                    {
                        String[] separated =dataInPrint.split(":");
                        //inisial=separated[0];
                        String data1 = separated[1];
                        String data2 = separated[2];
                        String data3 = separated[3];
                        String data4 = separated[4];
                        String data5 = separated[5];

                        txtJarak1.setText("Data Ultrasonik1 :"+data4 );
                        txtJarak2.setText("Data Ultrasonik2 : "+data5 );
                        txtPh.setText("Data Ph : "+data1);
                        txtLoad.setText("Data Load Cell : "+data3);
                        txtTurbidity.setText("Data Turbidity : "+data2 );
                    }
                    recDataString.delete(0, recDataString.length());
                    dataInPrint = " ";
                }
            }
        }
    };
}

//bluetooth chat systems
//
//
//
//
//
//    //013
//    Handler handler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            switch (msg.what) {
//                case STATE_LISTENING:
//                    status.setText("Listening");
//                    break;
//                case STATE_CONNECTING:
//                    status.setText("Connecting");
//                    break;
//                case STATE_CONNECTED:
//                    status.setText("Connected");
//                    break;
//                case STATE_CONNECTION_FAILED:
//                    status.setText("Connection Failed");
//                    break;
//                case STATE_MESSAGE_RECEIVED:
//                    byte[] readBuff = (byte[]) msg.obj;
//                    String tempMsg = new String(readBuff, 0, msg.arg1);
//                    msg_box.setText(tempMsg);
//                    break;
//            }
//            return true;
//        }
//    });
//
//    private class SendReceive extends Thread {
//        private final BluetoothSocket bluetoothSocket;
//        private final InputStream inputStream;
//        private final OutputStream outputStream;
//
//        public SendReceive(BluetoothSocket socket) {
//            bluetoothSocket = socket;
//            InputStream tempIn = null;
//            OutputStream tempOut = null;
//
//            try {
//                tempIn = bluetoothSocket.getInputStream();
//                tempOut = bluetoothSocket.getOutputStream();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            inputStream = tempIn;
//            outputStream = tempOut;
//        }
//    }
//
//    public void run() {
//        byte[] buffer = new byte[1024];
//        int bytes;
//
//        while (true) {
//            try {
//                bytes = inputStream.read(buffer);
//                handler.obtainMessage(STATE_MESSAGE_RECEIVED, bytes, -1, buffer).sendToTarget();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void write(byte[] bytes) {
//        try {
//            outputStream.weite(bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}